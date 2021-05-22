package codigo.Semantico;

import java.util.ArrayList;

/**
 *
 * @author Esteban Guzmán R
 */
public class TablaSimbolos {
    private ArrayList<TokenVariable> variables;
    private ArrayList<TokenFuncion> funciones;
    private String funcionActual;
    private String bitacora;
    
    public TablaSimbolos() {
        variables = new ArrayList<TokenVariable>();
        funciones = new ArrayList<TokenFuncion>();
        funcionActual = "";
        bitacora = "";
    }
    
    /**
     * Agrega una funcion a la lista de funciones
     * @param id identificador en .cup
     * @param tipo tipo de retorno de la funcion
     */
    public void addFuncion(String id, String tipo){
        funcionActual = id;
        TokenFuncion temp = new TokenFuncion(id,Tipos.valueOf(tipo));
        funciones.add(temp);
    }
    
    /**
     * Agrega un parametro a la funcion
     * @param id identificador de la funcion
     * @param tipo tipo de retorno de la funcion
     */
    public void addFuncionVariable(String id,String tipo){
        TokenVariable temp = new TokenVariable(id,Tipos.valueOf(tipo));
        getFuncion(funcionActual).addVariable(temp);
        
    }
    
    /**
     * Obtiene el token de una funcion
     * @param id identificador de la funcion
     * @return token de la funcion
     */
    public TokenFuncion getFuncion(String id){
        for(int i = 0; i < funciones.size(); i++){
            if(funciones.get(i).getId().compareTo(id) == 0){
                return funciones.get(i);
            }
        }
        return null;
    }
    
    /**
     * Obtiene el token de la variable
     * @param id identificador de la variable
     * @return token de la variable
     */
    public TokenVariable getVariable(String id){
        for(int i = 0; i < variables.size(); i++){
            if(variables.get(i).getId().compareTo(id) == 0){
                return variables.get(i);
            }
        }
        return null;
    }
    
    /**
     * Revisa si la variable fue declarada y el tipo de variable
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     * @return true en caso de haber sido de declarada y del mismo tipo analisado
     */
    public boolean verificarVariable(String id,String tipo){
        TokenVariable temp = getVariable(id);
        if(temp != null){
            if(temp.getTipo() == Tipos.valueOf(tipo)){
                return true;
            } else {
                bitacora += "La variable "+ id +"no es tipo "+ tipo;
                return false;
            }
        } else {
            bitacora += "La variable "+ id + "no ha sido declarada\n"; 
            return false;
        }
    }

    @Override
    public String toString() {
        return "TablaSimbolos{" + "variables=[" + variables.toString() + 
                "],\n funciones=[" + funciones.toString() + "],\n"
                + " bitacora=" + bitacora + '}';
    }
    
    
}
