# Ideias de Exercícios para Gramáticas (JFlex/CUP)

Aqui estão algumas sugestões de "mini-linguagens" para praticar a criação de gramáticas, com complexidade crescente.

### 1. Analisador de Arquivo de Configuração Simples (`.ini`)

**Conceito:** Ler um arquivo de configuração com seções e pares de chave-valor.

**Sintaxe de Exemplo:**
```ini
[database]
host = localhost
port = 5432
user = "admin"

[server]
enable_https = true
```

**O que praticar:**
*   **Terminais:** `ID`, `EQUALS`, `STRING`, `NUMBER`, `BOOLEAN`, `LBRACK` (`[`), `RBRACK` (`]`).
*   **Gramática:** Definir regras para `secao`, `lista_de_propriedades`, e `propriedade`.
*   **Desafio:** Estruturar a gramática para que as propriedades pertençam à seção correta.

---

### 2. Uma Linguagem de Comandos para um Robô (ou Tartaruga)

**Conceito:** Criar comandos para controlar um robô em um grid 2D.

**Sintaxe de Exemplo:**
```
FORWARD 10;
TURN LEFT;
REPEAT 4 [
  FORWARD 20;
  TURN RIGHT;
];
PEN DOWN;
```

**O que praticar:**
*   **Terminais:** `FORWARD`, `TURN`, `LEFT`, `RIGHT`, `REPEAT`, `PEN`, `UP`, `DOWN`, `NUMBER`, `LBRACK`, `RBRACK`, `PTVIRG`.
*   **Gramática:** A parte mais interessante é a regra do `REPEAT`, que contém uma lista de outros comandos dentro dela.
*   **Desafio:** Aninhamento de comandos e introdução à necessidade de criar uma **Árvore Sintática Abstrata (AST)**.

---

### 3. Validador de JSON Simplificado

**Conceito:** Analisar uma estrutura de dados parecida com JSON, com objetos, arrays, strings e números.

**Sintaxe de Exemplo:**
```json
{
  "name": "JFlex/CUP",
  "features": [ "parsing", "lexing" ],
  "is_awesome": true,
  "version": 1.0
}
```

**O que praticar:**
*   **Terminais:** `LBRACE` (`{`), `RBRACE` (`}`), `LBRACK` (`[`), `RBRACK` (`]`), `COLON` (`:`), `COMMA` (`,`), `STRING_LITERAL`, `NUMBER`, `TRUE`, `FALSE`.
*   **Gramática:** Regras para `objeto`, `array`, `par_chave_valor` e `valor`.
*   **Desafio:** Recursão mútua (um objeto contém valores que podem ser outros objetos ou arrays).

---

### 4. Calculadora com Variáveis e Funções

**Conceito:** Evoluir a calculadora para suportar atribuição a variáveis e funções matemáticas.

**Sintaxe de Exemplo:**
```
let PI = 3.14159;
let r = 10;
let area = PI * (r^2);
print area;
print sin(PI / 2);
```

**O que praticar:**
*   **Terminais:** `LET`, `PRINT`, `ID` (para variáveis/funções), `EQUALS`, `POW` (`^`), `SIN`, `COS`, etc.
*   **Gramática:** Novas regras para `atribuicao`, `chamada_de_funcao`, `declaracao`.
*   **Desafio:** Gerenciamento de estado com uma "Tabela de Símbolos" (pode ser uma `HashMap`) para armazenar o valor das variáveis.
