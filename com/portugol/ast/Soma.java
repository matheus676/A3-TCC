package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Soma extends Expressao {
    public Expressao esq, dir;

    public Soma(Expressao esq, Expressao dir) {
        this.esq = esq;
        this.dir = dir;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}