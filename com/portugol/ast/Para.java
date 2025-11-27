package com.portugol.ast;

import com.portugol.visitor.Visitor;

public class Para extends Node {
    public Atribuicao inicializacao;
    public Expressao condicao;
    public Atribuicao incremento;
    public Node corpo;

    public Para(Atribuicao init, Expressao cond, Atribuicao inc, Node corpo) {
        this.inicializacao = init;
        this.condicao = cond;
        this.incremento = inc;
        this.corpo = corpo;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}