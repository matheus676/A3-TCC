@echo off
REM Script de compilacao para o projeto Portugol
REM Deve ser executado de dentro da pasta com\portugol ou ajustado o caminho relativo

echo [INFO] Navegando para a raiz do projeto...
pushd ..\..

echo [INFO] Limpando arquivos gerados antigos...
del com\portugol\parser\Scanner.java 2>nul
del com\portugol\parser\parser.java 2>nul
del com\portugol\parser\sym.java 2>nul
del com\portugol\*.class 2>nul
del com\portugol\ast\*.class 2>nul
del com\portugol\parser\*.class 2>nul
del com\portugol\gerador\*.class 2>nul
del com\portugol\visitor\*.class 2>nul

echo.
echo [INFO] 1. Gerando Lexer (JFlex)...
jflex -d com\portugol\parser com\portugol\parser\scanner.flex
if %errorlevel% neq 0 goto error

echo.
echo [INFO] 2. Gerando Parser (CUP)...
java -jar java_cup\java-cup-11b.jar -destdir com\portugol\parser -parser parser -symbols sym com\portugol\parser\parser.cup
if %errorlevel% neq 0 goto error

echo.
echo [INFO] 3. Compilando Java (javac)...
javac -cp java_cup\java-cup-11b-runtime.jar;. com\portugol\Main.java com\portugol\ast\*.java com\portugol\parser\*.java com\portugol\gerador\*.java com\portugol\visitor\*.java
if %errorlevel% neq 0 goto error

echo.
echo [SUCCESS] Compilacao concluida com sucesso!
popd
pause
exit /b 0

:error
echo.
echo [ERROR] Falha na compilacao.
popd
pause
exit /b 1
