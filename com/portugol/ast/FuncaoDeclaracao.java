package com.portugol.ast;

import com.portugol.visitor.Visitor;
import java.util.List;

public class FuncaoDeclaracao extends Node {
    public Tipo tipoRetorno;
    public String nome;
    public List<Parametro> parametros;
    public Bloco corpo;

    public FuncaoDeclaracao(Tipo tipoRetorno, String nome, List<Parametro> params, Bloco corpo) {
        this.tipoRetorno = tipoRetorno;
        this.nome = nome;
        this.parametros = params;
        this.corpo = corpo;
    }

    @Override
    public void accept(Visitor v) { 
        v.visit(this); 
    }
}