package ini.parser;

import java_cup.runtime.Symbol;

%%

%class Lexer
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

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Keywords */
BOOLEAN = true|false

/* Numbers */
NUMBER = [0-9]+

/* Strings */
STRING = \"[^\"]*\"

/* Identifiers */
ID = [a-zA-Z_][a-zA-Z0-9_]*

%%

<YYINITIAL> {
  "["            { return symbol(sym.LBRACK); }
  "]"            { return symbol(sym.RBRACK); }
  "="            { return symbol(sym.EQUALS); }
  {BOOLEAN}      { return symbol(sym.BOOLEAN, yytext()); }
  {NUMBER}       { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
  {STRING}       { return symbol(sym.STRING, yytext().substring(1, yytext().length() - 1)); }
  {ID}           { return symbol(sym.ID, yytext()); }
  {WhiteSpace}   { /* ignore */ }
}

[^] { System.err.println("Illegal character: " + yytext()); }

