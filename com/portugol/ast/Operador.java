package com.portugol.ast;

public enum Operador {
    SOMA("+"), 
    SUB("-"), 
    MULT("*"), 
    DIV("/"), 
    MAIOR(">"),
    MENOR("<");

    public final String simbolo;
    
    Operador(String simbolo) {
        this.simbolo = simbolo;
    }
}