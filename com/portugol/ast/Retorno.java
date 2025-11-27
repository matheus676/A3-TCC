package com.portugol.ast;
import com.portugol.visitor.Visitor;

public class Retorno extends Node {
    public Expressao expr;

    public Retorno(Expressao expr) {
        this.expr = expr;
    }

    @Override
    public void accept(Visitor v) { v.visit(this); }
}