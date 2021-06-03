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
        this.sourceCode = sourceCode.replaceAll("\t","").replaceAll("\n", "");
        String[] codigo = sourceCode.split("int main()");
        funciones = new ArrayList<Funcion>();
        sacarMain(codigo[1]);
        if(sourceCode.contains("fun")){
            sacarFunciones(codigo[0]);
        }
    }
    
    private void sacarMain(String codigo){
        int indexPrimerLlave = codigo.indexOf("{");
        String sentencias = codigo.substring(indexPrimerLlave+2);
        Funcion main = new Funcion(sentencias,"main");
        funciones.add(main);
    }
    
    private void sacarFunciones(String codigo){
        String[] funciones = codigo.split("fun");
        for(int i = 0; i < funciones.length; i++){
            if(funciones[i].length() > 0){
                Funcion temp = new Funcion(funciones[i]);
                this.funciones.add(temp);
            }
            
        }
    }

    @Override
    public String toString() {
        return "GeneradorIntermedio{\n" + "funciones=\n" + funciones + '}';
    }
}
