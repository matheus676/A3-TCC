@echo off
echo Cleaning up old generated files...

rem The 'del' command is used for Windows batch files.
rem We add '2>nul' to suppress errors if files don't exist.
del parser.java 2>nul
del Parser.java 2>nul
del parser.class 2>nul
del Parser.class 2>nul
del parser$CUP$parser$actions.class 2>nul
del Parser$CUP$Parser$actions.class 2>nul
del Scanner.java 2>nul
del Scanner.java~ 2>nul
del Scanner.class 2>nul
del sym.java 2>nul
del sym.class 2>nul
