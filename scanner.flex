import java_cup.runtime.Symbol;

%%

%class Scanner
%cup
%line
%column
%public

ID           = [a-yA-Z][a-zA-Z]*
NUM          = [0-9]+
WHITESPACE   = [ \t\r]+

STRING       = [".+"]

%%


"z"           { return new Symbol(sym.Z); }   
"="          { return new Symbol(sym.ASSIGN); }

"true"       { return new Symbol(sym.BOOLEAN, Boolean.parseBoolean(yytext())); }
"false"      { return new Symbol(sym.BOOLEAN, Boolean.parseBoolean(yytext())); }

{ID}         { return new Symbol(sym.IDENTIFIER); }
{NUM}        { 
    int aux = Integer.parseInt(yytext());
    return new Symbol(sym.NUMBER, aux); 
    }
{STRING}     { return new Symbol(sym.STRING); }

/* Newline is the BREAK terminal */
"\n"         { return new Symbol(sym.BREAK); }

/* Ignore whitespace */
{WHITESPACE} { System.err.println("Illegal character: <" + yytext() + ">"); }

/* Report other characters as errors */
.            { System.err.println("Illegal character: <" + yytext() + ">"); }
