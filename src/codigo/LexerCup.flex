package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yychar, yyline, yytext());}

/* Tipos de datos */
( byte | long  | double ) {return new Symbol(sym.T_dato, yychar, yyline, yytext());}

/* Tipo de dato Int */
( "int" ) {return new Symbol(sym.Int, yychar, yyline, yytext());}

/* Tipo de dato Float */
( "float" ) {return new Symbol(sym.Float, yychar, yyline, yytext());}

/* Tipo de dato Bool */
( "bool" ) {return new Symbol(sym.Bool, yychar, yyline, yytext());}

/* Tipo de dato Char */
( "char" ) {return new Symbol(sym.Char, yychar, yyline, yytext());}

/* Tipo de dato String */
( String ) {return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Palabra reservada Write */
( write ) {return new Symbol(sym.Write, yychar, yyline, yytext());}

/* Palabra reservada Read */
( read ) {return new Symbol(sym.Read, yychar, yyline, yytext());}

/* Palabra reservada Fun */
( fun ) {return new Symbol(sym.Fun, yychar, yyline, yytext());}

/* Palabra reservada If */
( if ) {return new Symbol(sym.If, yychar, yyline, yytext());}

/* Palabra reservada Elif */
( elif ) {return new Symbol(sym.Elif, yychar, yyline, yytext());}

/* Palabra reservada Else */
( else ) {return new Symbol(sym.Else, yychar, yyline, yytext());}

/* Palabra reservada Break */
( break ) {return new Symbol(sym.Break, yychar, yyline, yytext());}

/* Palabra reservada Return */
( return ) {return new Symbol(sym.Return, yychar, yyline, yytext());}

/* Palabra reservada Do */
( do ) {return new Symbol(sym.Do, yychar, yyline, yytext());}

/* Palabra reservada While */
( while ) {return new Symbol(sym.While, yychar, yyline, yytext());}

/* Palabra reservada For */
( for ) {return new Symbol(sym.For, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operador Suma */
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}

/* Operador Resta */
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}

/* Operador Multiplicacion */
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}

/* Operador Division */
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}

/* Operador Division */
( "^" ) {return new Symbol(sym.Potencia, yychar, yyline, yytext());}

/* Diferencia Operador */
( "!" ) {return new Symbol(sym.Op_diferencia, yychar, yyline, yytext());}

/* Pregunta Operador */
( "?" ) {return new Symbol(sym.Op_pregunta, yychar, yyline, yytext());}

/* Sino Operador */
( ":" ) {return new Symbol(sym.Op_Sino, yychar, yyline, yytext());}
/* Operadores logicos */
( "&&" | "||" ) {return new Symbol(sym.Op_logico, yychar, yyline, yytext());}

/*Operadores Relacionales */
( "==" | "!=" ) {return new Symbol(sym.Op_igualdad, yychar, yyline, yytext());}

/*Operadores Relacionales */
( ">" | "<" | ">=" | "<=" ) {return new Symbol(sym.Op_cantidad, yychar, yyline, yytext());}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" | "%=" | "=" ) {return new Symbol(sym.Op_atribucion, yychar, yyline, yytext());}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return new Symbol(sym.Op_incremento, yychar, yyline, yytext());}

/*Operadores Booleanos*/
( true | false ) {return new Symbol(sym.Op_booleano, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "main" ) {return new Symbol(sym.Main, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "null" ) {return new Symbol(sym.Nulo, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("-"{D}+)|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Flotante */
("-"{D}+ "." {D}+ )|{D}+ "." {D}+ {return new Symbol(sym.Flotante, yychar, yyline, yytext());}

/* Caracter */
"'"[a-zA-Z]"'" {return new Symbol(sym.Caracter, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
