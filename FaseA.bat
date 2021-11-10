@Echo Off
clsdel %1.FT1 2>nul
java AnalizadorLexicografico%1
IF errorlevel 1 GOTO FALLO
echo Analizador Lexicografico Terminado
echo [
java SLR1 %1
IF errorlevel 1 GOTO FALLO
echo SLR1 Terminado
echo [
GOTO FIN 
:FALLO
echo[
ECHO errores en la compilacion!
ECHO[
:FIN
ECHO[
ECHO compilacion terminada
ECHO[
ECHO[
REM del %1.FT1 2>nul
:SALIR