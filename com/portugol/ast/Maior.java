package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Maior extends Expressao {
    public Expressao esq, dir;

    public Maior(Expressao esq, Expressao dir) {
        this.esq = esq;
        this.dir = dir;
    }

    @Override
    public void accept(Visitor v) { v.visit(this); }
}