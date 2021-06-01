/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.GeneradorCodigoIntermedio;

import java.util.ArrayList;

/**
 *
 * @author Esteban Guzmán R
 */
public class GeneradorIntermedio {
    private String sourceCode;
    private ArrayList<Funcion> funciones;
    
    public GeneradorIntermedio(String sourceCode){
        this.sourceCode = sourceCode;
        funciones = new ArrayList<Funcion>();
        sacarMain();
        sacarFunciones();
    }
    
    private void sacarMain(){
        String[] codigo = sourceCode.split("int main()");
        Funcion main = new Funcion(codigo[1],"main");
        funciones.add(main);
        sourceCode = codigo[0];
    }
    
    private void sacarFunciones(){
        String[] funciones = sourceCode.split("fun");
        
    }
}
