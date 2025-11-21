echo.
echo Running Java CUP to generate Parser.java and sym.java...
java -jar java_cup/java-cup-11b.jar -parser Parser -symbols sym parser.cup
if %errorlevel% neq 0 (
    echo Java CUP failed. Aborting build.
    goto:eof
)


echo.
echo Running JFlex to generate Scanner.java...
java -jar jflex-1.9.1/lib/jflex-full-1.9.1.jar scanner.flex
if %errorlevel% neq 0 (
    echo JFlex failed. Aborting build.
    goto:eof
)


echo.
echo Compiling all Java source files...
rem The classpath (-cp) is essential for the CUP runtime library.
javac -cp "java_cup/java-cup-11b-runtime.jar;." Main.java Parser.java Scanner.java sym.java
if %errorlevel% neq 0 (
    echo Java compilation failed. Aborting build.
    goto:eof
)

echo.
echo Build successful!
echo.
echo To run your parser, use a command like this:
echo java -cp "java_cup/java-cup-11b-runtime.jar;." Main your_test_file.txt
java Main.java tst.txt