package com.portugol.visitor;

import com.portugol.ast.*;
import java.util.HashSet;
import java.util.Set;
import com.portugol.visitor.Visitor;

public class SemanticVisitor implements Visitor {
    private Set<String> tabelaSimbolos = new HashSet<>();
    private boolean encontrouInicio;

    @Override
    public void visit(Bloco n) {
        for (Node cmd : n.comandos) {
            cmd.accept(this);
        }
    }

    @Override
    public void visit(Atribuicao n) {
        n.valor.accept(this);
        tabelaSimbolos.add(n.id);
    }

    @Override
    public void visit(Variavel n) {
        if (!tabelaSimbolos.contains(n.id)) {
            throw new RuntimeException("Erro Semântico: Variável '" + n.id + "' não foi declarada!");
        }
    }

    @Override
    public void visit(ExpressaoBinaria n) {
        n.esq.accept(this);
        n.dir.accept(this);
    }

    @Override
    public void visit(Numero n) {
        /* Nada a fazer */ }

    @Override
    public void visit(CondicaoSe n) {
        n.condicao.accept(this);
        n.blocoEntao.accept(this);
        if (n.blocoSenao != null)
            n.blocoSenao.accept(this);
    }

    @Override
    public void visit(Enquanto n) {
        n.condicao.accept(this);
        n.corpo.accept(this);
    }

    @Override
    public void visit(Para n) {
        n.inicializacao.accept(this);
        n.condicao.accept(this);
        n.incremento.accept(this);
        n.corpo.accept(this);
    }

    @Override
    public void visit(FuncaoChamada n) {
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

        // 2. Lógica de Escopo (que você já fez)
        tabelaSimbolos.clear(); 
        for (String param : n.parametros) {
            tabelaSimbolos.add(param);
        }
        n.corpo.accept(this);
    }

    @Override
    public void visit(Retorno n) {
    }

    public void concluir() {
        if (!encontrouInicio) {
            throw new RuntimeException("Erro Semântico: O programa não possui uma função 'inicio()' declarada.");
        }
    }

}