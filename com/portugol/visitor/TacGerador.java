package com.portugol.visitor;

import com.portugol.ast.*;
import com.portugol.gerador.Gerador;

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
    public void visit(Soma n) {
        n.esq.accept(this);
        n.dir.accept(this);
        String temp = Gerador.novaTemp();
        Gerador.add("+", n.esq.tempResult, n.dir.tempResult, temp);
        n.tempResult = temp;
    }

    @Override
    public void visit(Maior n) {
        n.esq.accept(this);
        n.dir.accept(this);
        String temp = Gerador.novaTemp();
        Gerador.add(">", n.esq.tempResult, n.dir.tempResult, temp);
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
}