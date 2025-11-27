package com.portugol;

import java.io.FileReader;
import java.util.List;

import com.portugol.parser.Scanner;
import com.portugol.parser.parser;
import com.portugol.ast.Node;
import com.portugol.visitor.TacGerador;
import com.portugol.visitor.SemanticVisitor;
import com.portugol.gerador.Gerador;
import com.portugol.gerador.Quadrupla;
import com.portugol.ast.FuncaoDeclaracao;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner lexer = new Scanner(new FileReader(args[0]));
        parser p = new parser(lexer);

        // 1. O Parser retorna a Árvore (AST)
        Node raiz = (Node) p.parse().value;

        SemanticVisitor semantico = new SemanticVisitor();
        raiz.accept(semantico);

        List<FuncaoDeclaracao> funcoes = (List<FuncaoDeclaracao>) p.parse().value;
        TacGerador gerador = new TacGerador();

        // Gera código para todas as funções
        for (FuncaoDeclaracao f : funcoes) {
            f.accept(gerador);
        }
    }
}