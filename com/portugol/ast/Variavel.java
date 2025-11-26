package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Variavel extends Expressao{
    public String id;

    public Variavel(String id) {
        this.id = id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }    
}
