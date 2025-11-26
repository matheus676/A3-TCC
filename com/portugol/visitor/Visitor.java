package com.portugol.visitor;
import com.portugol.ast.*;

public interface Visitor {
    void visit(Soma n);
    void visit(Maior n);
    void visit(CondicaoSe n);
    void visit(Atribuicao n);
    void visit(Numero n);
    void visit(Variavel n);
    void visit(Bloco n);
}