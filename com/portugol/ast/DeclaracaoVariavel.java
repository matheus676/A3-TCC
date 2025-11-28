package com.portugol.ast;

import com.portugol.ast.Expressao;
import com.portugol.ast.Tipo;
import com.portugol.visitor.Visitor;

public class DeclaracaoVariavel extends Node {
    public Tipo tipoDeclarado;
    public String id;
    public Expressao expressao;

    public DeclaracaoVariavel(Tipo tipo, String id, Expressao expressao) {
        this.tipoDeclarado = tipo;
        this.id = id;
        this.expressao = expressao;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
