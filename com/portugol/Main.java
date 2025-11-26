package com.portugol;

import java.io.FileReader;
import com.portugol.parser.Scanner;
import com.portugol.parser.parser;
import com.portugol.ast.Node;
import com.portugol.visitor.TacGerador;
import com.portugol.gerador.Gerador;
import com.portugol.gerador.Quadrupla;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner lexer = new Scanner(new FileReader(args[0]));
        parser p = new parser(lexer);
        
        // 1. O Parser retorna a Árvore (AST)
        Node raiz = (Node) p.parse().value;
        
        // 2. O Visitor percorre a árvore gerando código
        TacGerador gerador = new TacGerador();
        raiz.accept(gerador);
        
        // 3. Imprime o resultado final
        System.out.println("--- CÓDIGO INTERMEDIÁRIO ---");
        for (Quadrupla q : Gerador.codigo) {
            System.out.println(q);
        }
    }
}