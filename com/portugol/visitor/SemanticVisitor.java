package com.portugol.visitor;

import com.portugol.ast.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.portugol.visitor.Visitor;

public class SemanticVisitor implements Visitor {
    private Map<String, Tipo> tabelaSimbolos = new HashMap<>();
    private boolean encontrouInicio;
    private Map<String, Tipo> tabelaVariaveis = new HashMap<>();
    private Map<String, Tipo> tabelaFuncoes = new HashMap<>();

    @Override
    public void visit(Bloco n) {
        for (Node cmd : n.comandos) {
            cmd.accept(this);
        }
    }

    @Override
    public void visit(Atribuicao n) {
        if (!tabelaSimbolos.containsKey(n.id)) {
            throw new RuntimeException("Erro Semântico: Variável '" + n.id + "' não foi declarada!");
        }
        n.valor.accept(this);

        Tipo tipoVariavel = tabelaSimbolos.get(n.id);

        if (tipoVariavel != n.valor.tipo) {
            throw new RuntimeException("Erro de Tipagem: Variável '" + n.id + "' é " + tipoVariavel + 
                                     " mas recebeu " + n.valor.tipo);
        }
    }

    @Override
    public void visit(Variavel n) {
        if (!tabelaSimbolos.containsKey(n.id)) {
            throw new RuntimeException("Erro Semântico: Variável '" + n.id + "' não foi declarada!");
        }
        n.tipo = tabelaSimbolos.get(n.id);
        n.tempResult = n.id;
    }

    @Override
    public void visit(ExpressaoBinaria n) {
        n.esq.accept(this);
        n.dir.accept(this);
        
        if (n.esq.tipo != Tipo.INTEIRO || n.dir.tipo != Tipo.INTEIRO) {
             throw new RuntimeException("Erro de Tipagem: Operação " + n.op + 
                                      " espera inteiros, mas recebeu " + n.esq.tipo + " e " + n.dir.tipo);
        }
        
        switch (n.op) {
            case SOMA:
            case SUB:
            case MULT:
            case DIV:
                n.tipo = Tipo.INTEIRO;
                break;
            case MAIOR:
            case MENOR:
                n.tipo = Tipo.BOOLEANO;
                break;
            default:
                n.tipo = Tipo.ERRO;
        }
    }

    @Override
    public void visit(Numero n) {
        n.tipo = Tipo.INTEIRO;
    }

    @Override
    public void visit(CondicaoSe n) {
        n.condicao.accept(this);
        if (n.condicao.tipo != Tipo.BOOLEANO) {
            throw new RuntimeException("Erro de Tipagem: Condição do SE deve ser BOOLEANO.");
        }
        n.blocoEntao.accept(this);
        if (n.blocoSenao != null)
            n.blocoSenao.accept(this);
    }

    @Override
    public void visit(Enquanto n) {
        n.condicao.accept(this);
        if (n.condicao.tipo != Tipo.BOOLEANO) {
            throw new RuntimeException("Erro de Tipagem: Condição do ENQUANTO deve ser BOOLEANO.");
        }
        n.corpo.accept(this);
    }

    @Override
    public void visit(Para n) {
        n.inicializacao.accept(this);
        n.condicao.accept(this);
        if (n.condicao.tipo != Tipo.BOOLEANO) {
            throw new RuntimeException("Erro de Tipagem: Condição do PARA deve ser BOOLEANO.");
        }
        n.incremento.accept(this);
        n.corpo.accept(this);
    }

    @Override
    public void visit(Programa n) {
        // 1. Registra funções
        for (FuncaoDeclaracao f : n.funcoes) {
            if (tabelaFuncoes.containsKey(f.nome)) {
                 throw new RuntimeException("Erro Semântico: Função '" + f.nome + "' já declarada.");
            }
            tabelaFuncoes.put(f.nome, f.tipoRetorno);
        }

        // 2. Visita corpos
        for (FuncaoDeclaracao f : n.funcoes) {
            f.accept(this);
        }
    }

    @Override
    public void visit(FuncaoChamada n) {
        if (!tabelaFuncoes.containsKey(n.nome)) {
            throw new RuntimeException("Erro Semântico: Função '" + n.nome + "' não declarada.");
        }
        n.tipo = tabelaFuncoes.get(n.nome);
        
        for(Expressao arg : n.argumentos){
            arg.accept(this);
        }
    }

    @Override
    public void visit(FuncaoDeclaracao n) {
        // 1. Verificação da Função Principal
        if (n.nome.equals("inicio")) {
            encontrouInicio = true;
            
            // Regra Extra: 'inicio' não pode ter parâmetros
            if (!n.parametros.isEmpty()) {
                throw new RuntimeException("Erro Semântico: A função 'inicio' não deve receber parâmetros.");
            }
        }

        // 2. Lógica de Escopo
        tabelaSimbolos.clear(); 
        for (Parametro param : n.parametros) {
            tabelaSimbolos.put(param.nome, param.tipo);
        }
        n.corpo.accept(this);
    }

    @Override
    public void visit(Retorno n) {
        n.expr.accept(this);
    }

    public void concluir() {
        if (!encontrouInicio) {
            throw new RuntimeException("Erro Semântico: O programa não possui uma função 'inicio()' declarada.");
        }
    }

    @Override
    public void visit(DeclaracaoVariavel n) {
        // 1. Verifica se já existe
        if (tabelaSimbolos.containsKey(n.id)) {
            throw new RuntimeException("Erro Semântico: Variável '" + n.id + "' já foi declarada!");
        }

        // 2. Calcula o tipo da expressão inicial (ex: 10)
        n.expressao.accept(this);

        // 3. Verifica se os tipos batem
        if (n.tipoDeclarado != n.expressao.tipo) {
             throw new RuntimeException("Erro de Tipagem: Tentando atribuir " + n.expressao.tipo + 
                                      " na variável '" + n.id + "' que é " + n.tipoDeclarado);
        }

        // 4. Registra na tabela
        tabelaSimbolos.put(n.id, n.tipoDeclarado);
    }
    @Override
    public void visit(Tipo n) {
    }

    @Override
    public void visit(Expressao n) {
    }

    @Override
    public void visit(Parametro n) {
    }
}