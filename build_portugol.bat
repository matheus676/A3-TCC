@echo off
echo [INFO] Iniciando compilacao do Portugol...

REM Configura paths
set JFLEX_JAR=jflex-1.9.1\lib\jflex-full-1.9.1.jar
set CUP_JAR=java_cup\java-cup-11b.jar
set CUP_RUNTIME=java_cup\java-cup-11b-runtime.jar

REM Limpeza
echo [INFO] Limpando arquivos antigos...
del com\portugol\parser\Scanner.java 2>nul
del com\portugol\parser\parser.java 2>nul
del com\portugol\parser\sym.java 2>nul
del com\portugol\*.class 2>nul
del com\portugol\ast\*.class 2>nul
del com\portugol\parser\*.class 2>nul
del com\portugol\gerador\*.class 2>nul
del com\portugol\visitor\*.class 2>nul

REM 1. JFlex
echo [INFO] 1. Executando JFlex...
java -jar %JFLEX_JAR% -d com\portugol\parser com\portugol\parser\scanner.flex
if %errorlevel% neq 0 (
    echo [ERRO] Falha no JFlex
    pause
    exit /b 1
)

REM 2. CUP
echo.
echo [INFO] 2. Executando CUP...
java -jar %CUP_JAR% -destdir com\portugol\parser -parser parser -symbols sym com\portugol\parser\parser.cup
if %errorlevel% neq 0 (
    echo [ERRO] Falha no CUP
    pause
    exit /b 1
)

REM 3. Javac
echo.
echo [INFO] 3. Compilando Java...
javac -cp "%CUP_RUNTIME%;." com\portugol\Main.java com\portugol\ast\*.java com\portugol\parser\*.java com\portugol\gerador\*.java com\portugol\visitor\*.java
if %errorlevel% neq 0 (
    echo [ERRO] Falha no Java Compiler
    pause
    exit /b 1
)

echo.
echo [SUCESSO] Compilacao finalizada!
pause
