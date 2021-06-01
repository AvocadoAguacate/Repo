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
    
    public Sentencia(Tipo tipo,String sentencia) {
        this.tipo = tipo;
        this.sentencia = sentencia;
    }

    public Sentencia(ArrayList<Sentencia> sentencias, Tipo tipo, String sentencia) {
        sentencias = new ArrayList<Sentencia>();
        this.sentencias = sentencias;
        this.tipo = tipo;
        this.sentencia = sentencia;
    }
    
    
}
