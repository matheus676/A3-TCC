package com.portugol.ast;
import com.portugol.visitor.Visitor;
import java.util.List;

public class FuncaoChamada extends Expressao {
    public String nome;
    public List<Expressao> argumentos;

    public FuncaoChamada(String nome, List<Expressao> args) {
        this.nome = nome;
        this.argumentos = args;
    }

    @Override
    public void accept(Visitor v) { v.visit(this); }
}