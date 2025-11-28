package com.portugol.ast;

import com.portugol.visitor.Visitor;
import java.util.List;

public class Programa extends Node {
    public List<FuncaoDeclaracao> funcoes;

    public Programa(List<FuncaoDeclaracao> funcoes) {
        this.funcoes = funcoes;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}