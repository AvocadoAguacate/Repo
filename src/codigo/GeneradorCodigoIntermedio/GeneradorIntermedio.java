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
        this.sourceCode = sourceCode.replaceAll("\t","").replace("\n", "");
        funciones = new ArrayList<Funcion>();
        sacarMain();
        if(sourceCode.contains("fun")){
            sacarFunciones();
        }
    }
    
    private void sacarMain(){
        String[] codigo = sourceCode.split("int main()");
        Funcion main = new Funcion(codigo[1],"main");
        funciones.add(main);
        sourceCode = codigo[0];
    }
    
    private void sacarFunciones(){
        String[] funciones = sourceCode.split("fun");
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
