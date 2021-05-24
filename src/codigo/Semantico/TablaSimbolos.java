package codigo.Semantico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Esteban Guzmán R
 */
public class TablaSimbolos {
    private ArrayList<TokenVariable> variables;
    private ArrayList<TokenFuncion> funciones;
    private String funcionActual;
    private String bitacora;
    private int contErrores;
    
    public TablaSimbolos() {
        variables = new ArrayList<TokenVariable>();
        funciones = new ArrayList<TokenFuncion>();
        funcionActual = "";
        bitacora = "";
        contErrores = 0;
    }
    
    /**
     * Agrega una funcion a la lista de funciones. No se permite funciones con
     * el mismo nombre y diferente cantidad de parametros.
     * @param id identificador en .cup
     * @param tipo tipo de retorno de la funcion
     */
    public void addFuncion(String id, String tipo){
        if( getFuncion(id) == null) {
            funcionActual = id;
            TokenFuncion temp = new TokenFuncion(id,Tipos.valueOf(tipo));
            funciones.add(temp);            
        } else {
            bitacora += "La funcion ("+ id + ") ya existe, no es permitido dos "
                    + "funciones con el mismo nombre.\n";   
            contErrores += 1;
        }

    }
    
    /**
     * Agrega un parametro a la funcion.
     * @param id identificador de la funcion
     * @param tipo tipo de retorno de la funcion
     */
    public void addFuncionVariable(String id,String tipo){
        if(getFuncion(funcionActual).getVariable(id) == null){
            TokenVariable temp = new TokenVariable(id,Tipos.valueOf(tipo));
            getFuncion(funcionActual).addVariable(temp);           
        } else {
            bitacora += "No es permitido que la función (" + funcionActual + ") "
                    + "tenga dos parametros/variables con el mismo nombre ("
                    + id +").\n";
            contErrores += 1;
        }

        
    }
    
    /**
     * Agrega una variable a la lista de variables si están en main, sino lo 
     * agrega a las variables de la función actual
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     */
    public void addVariable(String id,String tipo){
        if(funcionActual.compareTo("main") == 0){
            if(getVariable(id) == null) {
                TokenVariable temp = new TokenVariable(id,Tipos.valueOf(tipo));
                variables.add(temp);
            } else {
                bitacora += "La variable ("+ id + ") ya existe, no es permitido dos "
                        + "variables con el mismo nombre.\n";
                contErrores += 1;
            }
        } else {
            addFuncionVariable(id,tipo);
        }
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
     * Revisa si la variable fue declarada y el tipo de variable si están en 
     * main, sino revisa en las variables de la funcion actual
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     * @return true en caso de haber sido de declarada y del mismo tipo analisado
     */
    public boolean verificarVariable(String id,String tipo){
        if(funcionActual.compareTo("main") == 0){
            TokenVariable temp = getVariable(id);
            if(temp != null){
                if(temp.getTipo() == Tipos.valueOf(tipo)){
                    return true;
                } else {
                    bitacora += "La variable (" + id + ") no es tipo (" + tipo 
                            + ").\n";
                    contErrores += 1;
                    return false;
                }
            } else {
                bitacora += "La variable ("+ id + ") no ha sido declarada.\n"; 
                contErrores += 1;
                return false;
            }
        } else {
            return verificarFuncionVariable(id,tipo);
        }
    }
    

     /**
     * Revisa si la variable fue declarada y el tipo de variable
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     * @return true en caso de haber sido de declarada y del mismo tipo analisado
     */
    public boolean verificarFuncion(String id,String tipo){
        TokenFuncion temp = getFuncion(id);
        if(temp != null){
            if(temp.getTipo() == Tipos.valueOf(tipo)){
                return true;
            } else {
                bitacora += "La funcion (" + id + ") no es tipo (" + tipo 
                        + ").\n";
                contErrores += 1;
                return false;
            }
        } else {
            bitacora += "La funcion ("+ id + ") no ha sido declarada\n"; 
            contErrores += 1;
            return false;
        }
    }
    
    /**
     * Sirve para dejar de verificar parametros, al indicar que la función es 
     * main el sistema debe verificar solo variables
     */
    public void setMain(){
        funcionActual = "main";
    }
    
    public boolean verificarFuncionVariable(String id, String tipo){
        TokenVariable temp = getFuncion(funcionActual).getVariable(id);
        if(temp != null){
            if(temp.getTipo() == Tipos.valueOf(tipo)){
                return true;
            } else {
                bitacora += "(" + id + ") no es de tipo (" + tipo + ").\n"; 
                contErrores += 1;
                return false;
            }
        } else {
            bitacora += "(" + id + ") no ha sido declarado.\n";
            contErrores += 1;
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "TablaSimbolos{" + "variables=\n" + variables.toString() + 
                ",\n funciones=\n" + funciones.toString() + ",\n"
                + "bitacora=\n" + bitacora + '}';
    }
    
    public String getBitacora() {
        return bitacora;
    }
    
    public void guardarBitacora(){
        JSONObject myJson = new JSONObject();
        myJson.put("Cantidad", contErrores);
        myJson.put("Bitacora", bitacora);
        try {
            FileWriter file = new FileWriter("D:/AnalizadorSintactico/Analizador/bitacora.json");
            file.write(myJson.toJSONString());
            file.flush();
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(TablaSimbolos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
