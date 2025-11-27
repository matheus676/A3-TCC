package com.portugol.visitor;
import com.portugol.ast.*;

public interface Visitor {
    void visit(ExpressaoBinaria n);
    void visit(CondicaoSe n);
    void visit(Atribuicao n);
    void visit(Numero n);
    void visit(Variavel n);
    void visit(Bloco n);
    void visit(Enquanto n);
    void visit(Para n);
    void visit(FuncaoDeclaracao n);
    void visit(Retorno n);
    void visit(FuncaoChamada n);
}