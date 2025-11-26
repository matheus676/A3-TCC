package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Numero extends Expressao{

    public int valor;

    public Numero(String valor) {
        this.valor = Integer.parseInt(valor);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

}
