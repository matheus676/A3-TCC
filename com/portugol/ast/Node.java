package com.portugol.ast;

import com.portugol.visitor.Visitor;

public abstract class Node {
    // O método mágico que aceita o visitante
    public abstract void accept(Visitor v);
}
