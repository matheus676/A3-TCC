package com.portugol.parser;
import java_cup.runtime.Symbol;

%%

%class Scanner
%cup
%line
%column
%public

Digito = [0-9]+
Id = [a-zA-Z]+
Espaco = [ \t\r\n]+

%%

"se"        { return new Symbol(sym.SE); }
"entao"     { return new Symbol(sym.ENTAO); }
"senao"     { return new Symbol(sym.SENAO); }
/*"enquanto"  { return new Symbol(sym.ENQUANTO); }
"faca"      { return new Symbol(sym.FACA); }
"inicio"    { return new Symbol(sym.INICIO); }
"fim"       { return new Symbol(sym.FIM); }*/
"+"         { return new Symbol(sym.MAIS); }
"-"       { return new Symbol(sym.MENOS); }
"*"       { return new Symbol(sym.MULT); } 
"/"       { return new Symbol(sym.DIV); }
">"         { return new Symbol(sym.MAIOR); }
"<"         { return new Symbol(sym.MENOR); }
"="         { return new Symbol(sym.IGUAL); }
"("         { return new Symbol(sym.AP); }
")"         { return new Symbol(sym.FP); }
";"         { return new Symbol(sym.PV); }

{Digito}    { return new Symbol(sym.NUM, yytext()); }
{Id}        { return new Symbol(sym.ID, yytext()); }
{Espaco}    { /* Ignora */ }
.           { throw new Error("Caractere inválido: " + yytext()); }