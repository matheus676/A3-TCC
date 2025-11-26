package com.portugol.ast;

import com.portugol.visitor.Visitor;
import java.util.ArrayList;
import java.util.List;

public class Bloco extends Node {
    public List<Node> comandos = new ArrayList<>();

    public void adicionar(Node c) {
        comandos.add(c);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
