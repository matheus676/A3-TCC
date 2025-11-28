package com.portugol;

import java.io.FileReader;
import java.io.PrintWriter; // Para escrever em arquivo
import java.util.List;

import com.portugol.parser.Scanner;
import com.portugol.parser.parser;
import com.portugol.ast.Node;
import com.portugol.visitor.TacGerador;
import com.portugol.visitor.SemanticVisitor;
import com.portugol.gerador.Gerador;
import com.portugol.gerador.Quadrupla;
import com.portugol.ast.FuncaoDeclaracao;
import com.portugol.ast.Programa;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java -jar portugol.jar <arquivo.txt>");
            return;
        }

        String arquivoEntrada = args[0];
        String arquivoSaida = arquivoEntrada.substring(0, arquivoEntrada.lastIndexOf('.')) + ".tac";

        try {
            Scanner lexer = new Scanner(new FileReader(arquivoEntrada));
            parser p = new parser(lexer);
            Programa raiz = (Programa) p.parse().value;

            SemanticVisitor semantico = new SemanticVisitor();
            raiz.accept(semantico);
            semantico.concluir(); 

            TacGerador gerador = new TacGerador();
            raiz.accept(gerador);

            try (PrintWriter writer = new PrintWriter(arquivoSaida)) {
                writer.println("CALL inicio, 0, t_main");
                writer.println("EXIT");
                
                for (Quadrupla q : Gerador.codigo) {
                    writer.println(q);
                }
                
                System.out.println("Compilacao com sucesso!");
                System.out.println("Codigo gerado em: " + arquivoSaida);
            }

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro na compilacao:");
            e.printStackTrace();
        }
    }
}