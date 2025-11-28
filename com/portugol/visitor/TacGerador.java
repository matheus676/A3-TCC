package com.portugol.visitor;

import com.portugol.ast.*;
import com.portugol.gerador.Gerador;
import com.portugol.visitor.Visitor;

public class TacGerador implements Visitor {

    @Override
    public void visit(Bloco n) {
        // Visita cada comando da lista em ordem
        for (Node cmd : n.comandos) {
            cmd.accept(this);
        }
    }

    @Override
    public void visit(Atribuicao n) {
        // 1. Calcula o valor da expressão
        n.valor.accept(this);
        // 2. Gera: id = tempResult
        Gerador.add("=", n.valor.tempResult, n.id);
    }

    @Override
    public void visit(ExpressaoBinaria n) {
        n.esq.accept(this);
        n.dir.accept(this);
        
        String temp = Gerador.novaTemp();
        
        Gerador.add(n.op.simbolo, n.esq.tempResult, n.dir.tempResult, temp);
        
        n.tempResult = temp;
    }

    @Override
    public void visit(Numero n) {
        // Numero não gera código, o "resultado" é o próprio valor literal
        n.tempResult = String.valueOf(n.valor);
    }

    @Override
    public void visit(Variavel n) {
        n.tempResult = n.id;
    }

    @Override
    public void visit(CondicaoSe n) {
        String lElse = Gerador.novoLabel();
        String lFim = Gerador.novoLabel();

        n.condicao.accept(this);
        Gerador.add("IF_FALSE", n.condicao.tempResult, lElse);

        n.blocoEntao.accept(this);
        Gerador.add("GOTO", lFim);

        Gerador.add("LABEL", lElse);
        if (n.blocoSenao != null) {
            n.blocoSenao.accept(this);
        }
        Gerador.add("LABEL", lFim);
    }

    @Override
    public void visit(Enquanto n) {
        String lInicio = Gerador.novoLabel();
        String lFim = Gerador.novoLabel();

        // 1. Marca o início do loop (para podermos voltar aqui)
        Gerador.add("LABEL", lInicio);

        // 2. Calcula a condição
        n.condicao.accept(this);

        // 3. Se condição FALSA, pula para o fim (sai do loop)
        Gerador.add("IF_FALSE", n.condicao.tempResult, lFim);

        // 4. Executa o corpo do loop
        n.corpo.accept(this);

        // 5. Ao terminar o corpo, volta incondicionalmente para o início (loop)
        Gerador.add("GOTO", lInicio);

        // 6. Marca o fim (destino do IF_FALSE)
        Gerador.add("LABEL", lFim);
    }

    @Override
    public void visit(Para n) {
        String lInicio = Gerador.novoLabel();
        String lFim = Gerador.novoLabel();

        // 1. Executa a inicialização (ex: i = 0) antes de tudo
        n.inicializacao.accept(this);

        // 2. Marca o início do loop
        Gerador.add("LABEL", lInicio);

        // 3. Testa a condição
        n.condicao.accept(this);
        Gerador.add("IF_FALSE", n.condicao.tempResult, lFim);

        // 4. Executa o corpo
        n.corpo.accept(this);

        // 5. Executa o incremento (ex: i = i + 1)
        n.incremento.accept(this);

        // 6. Volta para testar a condição de novo
        Gerador.add("GOTO", lInicio);

        // 7. Saída do loop
        Gerador.add("LABEL", lFim);
    }

    @Override
    public void visit(Programa n) {
        // Itera sobre todas as funções gerando código
        for (FuncaoDeclaracao f : n.funcoes) {
            f.accept(this);
        }
    }

    @Override
    public void visit(FuncaoDeclaracao n) {
        // Gera o Label da função
        Gerador.add("LABEL", n.nome);
        
        for (String param : n.parametros) {
            Gerador.add("POP", param, null, null);
        }
        
        n.corpo.accept(this);
        
        // Retorno padrão vazio caso o usuário esqueça
        Gerador.add("RETURN", "0", null, null);
    }

    @Override
    public void visit(Retorno n) {
        n.expr.accept(this);
        Gerador.add("RETURN", n.expr.tempResult, null, null);
    }

    @Override
    public void visit(FuncaoChamada n) {
        // 1. Calcula e empilha cada argumento
        for (Expressao arg : n.argumentos) {
            arg.accept(this);
            // Instrução PARAM indica que é um argumento para a próxima chamada
            Gerador.add("PARAM", arg.tempResult, null, null);
        }

        // 2. Faz a chamada
        String temp = Gerador.novaTemp();
        String qtdArgs = String.valueOf(n.argumentos.size());
        
        // Instrução CALL: nome_funcao, qtd_args, variavel_destino
        Gerador.add("CALL", n.nome, qtdArgs, temp);
        
        n.tempResult = temp;
    }
}