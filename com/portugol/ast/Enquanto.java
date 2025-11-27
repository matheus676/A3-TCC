package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Enquanto extends Node {
    public Expressao condicao;
    public Node corpo;

    public Enquanto(Expressao condicao, Node corpo) {
        this.condicao = condicao;
        this.corpo = corpo;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}