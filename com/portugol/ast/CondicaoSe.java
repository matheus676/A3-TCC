package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class CondicaoSe extends Node {
    public Expressao condicao;
    public Node blocoEntao;
    public Node blocoSenao; // Pode ser null

    public CondicaoSe(Expressao c, Node entao, Node senao) {
        this.condicao = c;
        this.blocoEntao = entao;
        this.blocoSenao = senao;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}