/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Charly Ponce
 * @edit Esteban Guzmán
 */

public class Principal {
    public static void main(String[] args) throws Exception {
        String rutaGeneral = "D:/AnalizadorSintactico/Analizador/";
        String ruta1 = rutaGeneral + "src/codigo/Lexer.flex";
        String ruta2 = rutaGeneral + "src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", rutaGeneral + "src/codigo/Sintax.cup"};
        generar(ruta1, ruta2, rutaS, rutaGeneral);
    }
    public static void generar(String ruta1, String ruta2, String[] rutaS, String rutaGeneral) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get(rutaGeneral + "src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(rutaGeneral + "sym.java"), 
                Paths.get(rutaGeneral + "src/codigo/sym.java")
        );
        Path rutaSin = Paths.get(rutaGeneral + "src/codigo/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(rutaGeneral + "Sintax.java"), 
                Paths.get(rutaGeneral + "src/codigo/Sintax.java")
        );
    }
}
