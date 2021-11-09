@Echo Off
CLS

del %1.FT2 2> nul
del %1.FT1 2> nul

java AnalizadorLexicografico.java %1

IF errorlevel 1 GOTO FALLLO

echo Analizador Lexicografico Terminado

java ParserSLR1Generador.java %1

IF errorlevel 1 GOTO FALLLO

echo Analisis sintactico SLR(1) Terminado
GOTO FIN

:FALLLO
ECHO ERRORES EN LLA COMPILACION!!!

:FIN
ECHO COMPILLACION TERMINADA

:SALLIR