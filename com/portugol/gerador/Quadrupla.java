package com.portugol.gerador;

public class Quadrupla {
    String op, arg1, arg2, res;

    public Quadrupla(String op, String arg1, String arg2, String res) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.res = res;
    }

    @Override
    public String toString() {
        switch (op) {
            case "+": case "-": case "*": case "/": case ">": case "<":
                return res + " = " + arg1 + " " + op + " " + arg2;
            case "=":
                return res + " = " + arg1;
            case "IF_FALSE":
                return "IF_FALSE " + arg1 + " GOTO " + res;
            case "GOTO":
                return "GOTO " + res;
            case "LABEL":
                return res + ":";
            default:
                return op + " " + arg1 + " " + arg2 + " " + res;
        }
    }
}