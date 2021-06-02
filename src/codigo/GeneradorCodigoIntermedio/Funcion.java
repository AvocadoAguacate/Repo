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
public class Funcion {
    private ArrayList<Sentencia> sentencias;
    private String nombre;
    

    public Funcion(ArrayList<Sentencia> sentencias, String nombre) {
        this.sentencias = sentencias;
        this.nombre = nombre;
    }
    
    public Funcion(String sentenciasApartir, String nombre){
        this.nombre = nombre;
        System.out.println("main");
        System.out.println(sentenciasApartir);
        
        //hay que ver como partir esto
    }
    
    public Funcion(String funcionSinPartir){
        System.out.println("funcion");
        System.out.println(funcionSinPartir);
        //hay que ver como partir y sacar nombre y parametros
    }

    @Override
    public String toString() {
        return "Funcion{\n" + "sentencias=\n" + sentencias + "\n, nombre=\n" + nombre + "}\n";
    }
    
    private ArrayList<Sentencia> partirFor(String aPartir){
        return null;
    }
    
    private ArrayList<Sentencia> partir(String aPartir){
        ArrayList<Sentencia> resultado = new ArrayList<Sentencia>();
        while(aPartir.length() > 0) {
            String prefijo = aPartir.substring(0, 5); //if 2 else 4 elif 4 for 3
            int contLlave = 0;
            int index = 0;
            if(prefijo.contains("for")){
                do{
                    if(aPartir.charAt(index) == '{'){
                        contLlave += 1;
                    } 
                    if (aPartir.charAt(index) == '}'){
                        contLlave -= 1;
                    }
                    index += 1;
                } while (contLlave > 0);

                int indexPrimerLlave = aPartir.indexOf("{");
                int indexPrimerParentesis = aPartir.indexOf("(");
                int indexSegundoParentesis = aPartir.indexOf(")");
                String parametros = aPartir.substring(indexPrimerParentesis+1,indexSegundoParentesis);
                String nuevoApartir = aPartir.substring(index+1);
                ArrayList<Sentencia> sentencias = partir(nuevoApartir);
                resultado.add(new Sentencia(sentencias,Tipo.For,parametros));

                aPartir = aPartir.substring(index + 1);
                
            } else if (prefijo.contains("if")){
                do{
                    
                } while (contLlave > 0);
            } else if(prefijo.contains("elif")){
                
            } else if(prefijo.contains("else")){ 
                
            } else if (prefijo.contains("int") || prefijo.contains("float") ||
                    prefijo.contains("char") || prefijo.contains("String") ||
                    prefijo.contains("bool")){


            } else { //asignaciones

            }
        }
        return resultado;
    }
    
}
