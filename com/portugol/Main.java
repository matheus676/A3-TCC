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

        // 1. O Parser retorna o nó Raiz (Programa)
        // Note o cast para Node ou Programa
        Node raiz = (Node) p.parse().value;

        // 2. Semântica
        SemanticVisitor semantico = new SemanticVisitor();
        raiz.accept(semantico);
        semantico.concluir(); // Verifica se tem 'inicio'

        // 3. Geração
        TacGerador gerador = new TacGerador();
        raiz.accept(gerador);

        // 4. Impressão (Bootstrap)
        System.out.println("CALL inicio, 0, t_main");
        System.out.println("EXIT");
        
        for (Quadrupla q : Gerador.codigo) {
            System.out.println(q);
        }
    }
}