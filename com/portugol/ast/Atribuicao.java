package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Atribuicao extends Node {

    public String id;
    public Expressao valor;

    public Atribuicao(String id, Expressao valor) {
        this.id = id;
        this.valor = valor;
    }

    @Override
    public void accept(Visitor v) {
         v.visit(this);
    }

}
