package codigo.Semantico;

import java.util.ArrayList;


/**
 *
 * @author Esteban Guzmán R
 */
public class TokenArreglo {
    //Identificador en .cup
    private String id;
    //Tipo de retorno de la función
    private Tipos tipo;
    
    private int tamaño;
    
//    private ArrayList<String> variables;
    
    /**
     * Contructor de tokens de token arreglo
     * @param id identificador en .cup 
     * @param tipo tipo de la variable
     */
    
    public TokenArreglo(String id,Tipos tipo, int tamaño) {
        this.id = id;
        this.tipo = tipo;
        this.tamaño = tamaño;
//        for(int i = 0; i < tamaño; i++){
//            variables.add("");
//        }
    }

    public int getTamaño() {
        return tamaño;
    }
    
    
//    public void addVariable(String var, int index){
//        if(variables.size()< tamaño){
//            if(index>=0){
//                variables.set(index, var);
//            } else {
//                variables.set(getLastIndex(),var);
//            }
//        }
//        
//    }
//    
//    public int getLastIndex(){
//        int sinResultado = -1;
//        for(int i = 0;i < tamaño;i++){
//            if(variables.get(i).compareTo("") == 0){
//                return i;
//            }
//        }
//        return sinResultado;
//    }
    
    /**
     * Retorna el identificador de la variable
     * @return identificador de la variable
     */
    public String getId(){
        return id;
    }
    
    /**
     * Retorna el tipo de la función para validar que se pueda usar la función 
     * en la expresion requerida
     * @return tipo de la función
     */
    public Tipos getTipo(){
        return tipo;
    }

    @Override
    public String toString() {
        return "TokenArreglo{" + "id=" + id + ", tipo=" + tipo + ", tama\u00f1o=" + tamaño + '}';
    }
    
}
