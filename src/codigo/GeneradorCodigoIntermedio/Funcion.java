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
    private ArrayList<String> parametros;
    
   
    public Funcion(String sentenciasApartir, String nombre){
        this.nombre = nombre;
        String sentencias = sentenciasApartir.replaceAll("[\n]", "");
        this.sentencias = partir(sentencias);
    }
    
    public Funcion(String funcionSinPartir){
        String[] codigo = funcionSinPartir.split("[){]",2);
        this.parametros = partirParametros(codigo[0]);//hay que sacarle la primera (
        String sentencias = codigo[1];
        sentencias = sentencias.substring(sentencias.indexOf("{")+1);
        sentencias = sentencias.replaceAll("[\n]", "");
        this.sentencias = partir(sentencias);
    }
    
    private ArrayList<String> partirParametros(String source){
        int indexPrimerParentesis = source.indexOf("(");
        String parametros = source.substring(indexPrimerParentesis+1);
        String[] paramArray = parametros.split(" ");
        this.nombre = paramArray[0];
        ArrayList<String> resultado = new ArrayList<String>();
        for(int i = 1; i < paramArray.length;i++){
            if(!paramArray[i].contains("int") && !paramArray[i].contains("char")
                    && !paramArray[i].contains("float") && !paramArray[i].contains("String")
                    &&!paramArray[i].contains("bool")){
                resultado.add(paramArray[i]);
            }
        }
        return resultado;
    }
    
    private ArrayList<Sentencia> partir(String aPartir){
        aPartir = aPartir.substring(0, aPartir.lastIndexOf("}"));
        ArrayList<Sentencia> resultado = new ArrayList<Sentencia>();
        while(aPartir.contains("}") || aPartir.contains(";")) {
        System.out.println("Partir:");
        System.out.println(aPartir);
            String prefijo = aPartir.substring(0, 7); //if 2 else 4 elif 4 for 3 String 6
            int contLlave = 0;
            int index = 0;
            if(prefijo.contains("for") || prefijo.contains("if")){
                int indexPrimerParentesis = aPartir.indexOf("(");
                int indexSegundoParentesis = aPartir.indexOf(")");
                String parametros = aPartir.substring(indexPrimerParentesis+1,indexSegundoParentesis);
                do{
                    if(aPartir.charAt(index) == '{'){
                        contLlave += 1;
                    } 
                    if (aPartir.charAt(index) == '}'){
                        contLlave -= 1;
                    }
                    System.out.println("C:"+aPartir.charAt(index)+" I:"+index);
                    index += 1;
                } while (contLlave > 0);
                aPartir = aPartir.substring(indexSegundoParentesis+1,index+1);
                int indexPrimerLlave = aPartir.indexOf("{");
                String nuevoApartir = aPartir.substring(indexPrimerLlave+1);
                ArrayList<Sentencia> sentencias = new ArrayList<Sentencia>();
                sentencias = otroPartir(nuevoApartir);
                Tipo tipo;
                if(prefijo.contains("for")){
                    tipo = Tipo.For;
                } else if (prefijo.contains("if") && !prefijo.contains("elif")){
                    tipo = Tipo.If;
                } else{ 
                    tipo = Tipo.Elif;
                }
                resultado.add(new Sentencia(sentencias,tipo,parametros));
                aPartir = aPartir.substring(index + 1);
            } else if (prefijo.contains("else")){
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
                String nuevoApartir = aPartir.substring(indexPrimerLlave+1,index);
                ArrayList<Sentencia> sentencias;
                sentencias = partir(nuevoApartir);
                resultado.add(new Sentencia(sentencias,Tipo.Else,""));
                aPartir = aPartir.substring(index + 1);
                
            } else if (prefijo.contains("int") || prefijo.contains("float") ||
                    prefijo.contains("char") || prefijo.contains("String") ||
                    prefijo.contains("bool")){
                int indexPuntoComa = aPartir.indexOf(";");
                String sentencia = aPartir.substring(0,indexPuntoComa);
                resultado.add(new Sentencia(Tipo.Declaracion,sentencia));
                aPartir = aPartir.substring(indexPuntoComa+1);
            } else { //asignaciones returns
                int indexPuntoComa = aPartir.indexOf(";");
                String sentencia = aPartir.substring(0,indexPuntoComa);
                resultado.add(new Sentencia(Tipo.Asignacion,sentencia));
                aPartir = aPartir.substring(indexPuntoComa+1);
            }
        }
        return resultado;
    }
    
    private ArrayList<Sentencia> otroPartir(String partir){
        System.out.println("otroPartir:");
        System.out.println(partir);
        return partir(partir);
    }

    @Override
    public String toString() {
        return "\n\nFuncion{" + "\nsentencias=" + sentencias + ", \nnombre=" + nombre + ", \nparametros=\n" + parametros + '}';
    }
    
}
