package com.portugol.ast;
import com.portugol.visitor.Visitor;
import java.util.List;

public class FuncaoDeclaracao extends Node {
    public String nome;
    public List<String> parametros;
    public Bloco corpo;

    public FuncaoDeclaracao(String nome, List<String> params, Bloco corpo) {
        this.nome = nome;
        this.parametros = params;
        this.corpo = corpo;
    }

    @Override
    public void accept(Visitor v) { v.visit(this); }
}