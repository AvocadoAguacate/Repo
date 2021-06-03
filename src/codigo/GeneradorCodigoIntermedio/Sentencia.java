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
public class Sentencia {
    private ArrayList<Sentencia> sentencias;
    private Tipo tipo;
    private String sentencia;
    ArrayList<String> pila;
    
    
    public Sentencia(Tipo tipo,String sentencia) {
        this.tipo = tipo;
        this.sentencia = sentencia;
        this.pila = new ArrayList<String>();
    }

    public Sentencia(ArrayList<Sentencia> sentencias, Tipo tipo, String sentencia) {
        sentencias = new ArrayList<Sentencia>();
        this.sentencias = sentencias;
        this.tipo = tipo;
        this.sentencia = sentencia;
        this.pila = new ArrayList<String>();
    }

    @Override
    public String toString() {
        if(sentencias == null){
            return "\n{" + "tipo=" + tipo + "\n, sentencia=\n" + sentencia + '}';
        } else {
            return "\n{" + "sentencias=" + sentencias + "\n, tipo=" + tipo + "\n, parametros=\n" + sentencia + '}';
        }
    }
    
    public String generarCodigo3(){
        String resultado = "";
        String[] source = sentencia.split("=",2);
        String identificador = source[0].replaceAll(" ", "").replaceAll("\n","").replaceAll("\t", "");
        System.out.println("id:"+identificador);
        String[] ops = source[1].split("[+] | [-]");
 
        return resultado;
    }
    
    
    
}
