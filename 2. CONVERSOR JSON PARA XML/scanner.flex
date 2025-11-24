import java_cup.runtime.Symbol;

%%

%class Scanner
%cup
%line
%column

%{
    // Método auxiliar para criar tokens com valor (ex: o texto da String)
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
    // Método para tokens simples (ex: '{')
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
%}

// Definições Regulares
Digito = [0-9]
Numero = {Digito}+ ("." {Digito}+)?
Espaco = [ \t\r\n\f]

// Strings em JSON são entre aspas duplas
StringLiteral = \"([^\\\"]|\\.)*\"

%%

<YYINITIAL> {
    // Palavras reservadas e Símbolos
    "{"       { return symbol(sym.LBRACE); }
    "}"       { return symbol(sym.RBRACE); }
    "["       { return symbol(sym.LBRACKET); }
    "]"       { return symbol(sym.RBRACKET); }
    ":"       { return symbol(sym.COLON); }
    ","       { return symbol(sym.COMMA); }
    "true"    { return symbol(sym.BOOLEAN, "true"); }
    "false"   { return symbol(sym.BOOLEAN, "false"); }
    "null"    { return symbol(sym.NULL, "null"); }

    // Valores dinâmicos
    {Numero}  { return symbol(sym.NUMBER, yytext()); }
    
    // Para Strings, removemos as aspas aqui para facilitar no XML
    {StringLiteral} { 
        String str = yytext(); 
        return symbol(sym.STRING, str.substring(1, str.length()-1)); 
    }

    {Espaco}  { /* Ignorar espaços em branco */ }
}

[^] { throw new Error("Caractere ilegal <"+yytext()+">"); }