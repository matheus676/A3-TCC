import java_cup.runtime.Symbol;

%%

%class Scanner
%cup
%line
%column
%public

IDENTIFIER = \[[a-zA-Z0-9_]+\]
WHITESPACE = [\t\r\n\s]
VAR = [a-zA-Z]+
STRING = \"([^\"\\]|\\.)*\"
INTEGER = [0-9]+
BOOLEAN = true|false
%%

{WHITESPACE} {/* IGNORA */}
{IDENTIFIER} {return new Symbol(sym.IDENTIFIER, yytext().substring(1, yytext().length()-1));} 
{STRING} {return new Symbol(sym.STRING, yytext().substring(1, yytext().length()-1));}
{INTEGER} {return new Symbol(sym.INTEGER, Integer.parseInt(yytext()));}
{BOOLEAN} {return new Symbol(sym.BOOLEAN, Boolean.parseBoolean(yytext()));}
{VAR} {return new Symbol(sym.VAR, yytext());}
"=" {return new Symbol(sym.ASSIGN);}
. {return new Symbol(sym.EOF);}

