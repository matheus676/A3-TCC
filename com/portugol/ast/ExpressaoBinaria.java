package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class ExpressaoBinaria extends Expressao {
    public Expressao esq, dir;
    public Operador op;

    public ExpressaoBinaria(Expressao esq, Expressao dir, Operador op) {
        this.esq = esq;
        this.dir = dir;
        this.op = op;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}