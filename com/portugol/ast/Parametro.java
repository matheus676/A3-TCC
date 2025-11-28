package com.portugol.ast;

public class Parametro {
    public String nome;
    public Tipo tipo;

    public Parametro(Tipo tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }
}