import java_cup.runtime.Symbol;

%%

%class Scanner
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

// Definições
Digito = [0-9]
Inteiro = {Digito}+
Espaco = [ \t\r\n]

%%

<YYINITIAL> {
    // Operadores Aritméticos
    "+"       { return symbol(sym.PLUS); }
    "-"       { return symbol(sym.MINUS); }
    "*"       { return symbol(sym.MULT); }
    "/"       { return symbol(sym.DIV); }
    "^"       { return symbol(sym.POWER); }
    "("       { return symbol(sym.LPAREN); }
    ")"       { return symbol(sym.RPAREN); }
    ";"       { return symbol(sym.SEMI); } // Para terminar a expressão

    // Dados
    {Inteiro} { return symbol(sym.NUMBER, yytext()); }
    
    {Espaco}  { /* Ignora */ }
}

[^] { throw new Error("Caractere inválido: " + yytext()); }