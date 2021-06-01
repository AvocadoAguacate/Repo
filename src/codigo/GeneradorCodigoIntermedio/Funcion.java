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
        //hay que ver como partir esto
    }
    
    public Funcion(String funcionSinPartir){
        //hay que ver como partir y sacar nombre y parametros
    }
}
