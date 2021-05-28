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
    private ArrayList<TokenVariable> parametros;
    private ArrayList<TokenFuncion> funciones;
    private ArrayList<TokenArreglo> arreglos;
    private String funcionActual;
    private String bitacora;
    private int contErrores;
    
    public TablaSimbolos() {
        variables = new ArrayList<TokenVariable>();
        parametros = new ArrayList<TokenVariable>();
        funciones = new ArrayList<TokenFuncion>();
        funcionActual = "";
        bitacora = "";
        contErrores = 0;
        guardarBitacora();
    }
    
    /**
     * Agrega una funcion a la lista de funciones. No se permite funciones con
     * el mismo nombre y diferente cantidad de parametros.
     * @param id identificador en .cup
     * @param tipo tipo de retorno de la funcion
     */
    public void addFuncion(String id, Tipos tipo){
        if( getFuncion(id) == null) {
            funcionActual = id;
            TokenFuncion temp = new TokenFuncion(id,tipo);
            temp.addAllVariable(variables);
            temp.addAllParametro(parametros);
            temp.addArreglos(arreglos);
            funciones.add(temp);
            variables.clear();
            parametros.clear();
            arreglos.clear();
            String revisionVarPar = temp.revisarParametros();
            if(revisionVarPar.length() > 1){
                bitacora += revisionVarPar;
                contErrores += 1;
            }
        } else {
            bitacora += "La funcion ("+ id + ") ya existe, no es permitido dos "
                    + "funciones con el mismo nombre.\n";   
            contErrores += 1;
        }
        System.out.println(toString());
    }
    
    //limitar a arreglos con tamaño explicito
    public void addArreglo(String id, Tipos tipo, int tamaño){
        if( getArreglo(id) == null){
            TokenArreglo temp = new TokenArreglo(id,tipo,tamaño);
            arreglos.add(temp);
        } else {
            bitacora += "El arreglo ("+ id + ") ya existe, no es permitido dos "
                    + "arreglos con el mismo nombre.\n";  
        }
    }
    
    public TokenArreglo getArreglo(String id){
        for(int i = 0; i < arreglos.size(); i++){
            if(arreglos.get(i).getId().compareTo(id) == 0){
                return arreglos.get(i);
            }
        }
        return null;
    }
    
    //ocupo revisar desde funcion si el parametro no choca con 
    public void addParametro(String id, Tipos tipo){
        if (getParametro(id) == null) {
            TokenVariable temp = new TokenVariable(id, tipo);
            parametros.add(temp);
        } else {
            bitacora += "(" + id + ") no puede ser el id de dos parametros.\n";
            contErrores += 1;
        }
        System.out.println(toString());
    }
    
    /**
     * Agrega una variable a la lista de variables si están en main, sino lo 
     * agrega a las variables de la función actual
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     */
    public void addVariable(String id,Tipos tipo){
        if (getVariable(id) == null) {
            TokenVariable temp = new TokenVariable(id, tipo);
            variables.add(temp);
        } else {
            bitacora += "La variable (" + id + ") ya existe, no es permitido dos "
                    + "variables con el mismo nombre.\n";
            contErrores += 1;
        }
        System.out.println(toString());
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
    
    public TokenVariable getParametro(String id) {
        for(int i = 0; i < parametros.size(); i++){
            if(parametros.get(i).getId().compareTo(id) == 0){
                return parametros.get(i);
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
    public void verificarVariable(String id,Tipos tipo){
        if(funcionActual.compareTo("main") == 0){
            TokenVariable temp = getVariable(id);
            if(temp != null){
                if(temp.getTipo() == tipo){
                    guardarBitacora();
                } else {
                    bitacora += "La variable (" + id + ") no es tipo (" + tipo 
                            + ").\n";
                    contErrores += 1;
                    guardarBitacora();
                }
            } else {
                bitacora += "La variable ("+ id + ") no ha sido declarada.\n"; 
                contErrores += 1;
                guardarBitacora();
            }
        } else {
            verificarFuncionVariable(id,tipo);
        }
    }
    

     /**
     * Revisa si la variable fue declarada y el tipo de variable
     * @param id identificador de la variable
     * @param tipo tipo de la variable
     * @return true en caso de haber sido de declarada y del mismo tipo analisado
     */
    public void verificarFuncion(String id,Tipos tipo){
        TokenFuncion temp = getFuncion(id);
        if(temp != null){
            if(temp.getTipo() == tipo){
                guardarBitacora();
            } else {
                bitacora += "La funcion (" + id + ") no es tipo (" + tipo 
                        + ").\n";
                contErrores += 1;
                guardarBitacora();
            }
        } else {
            bitacora += "La funcion ("+ id + ") no ha sido declarada\n"; 
            contErrores += 1;
            guardarBitacora();
        }
    }
    
    /**
     * Sirve para dejar de verificar parametros, al indicar que la función es 
     * main el sistema debe verificar solo variables
     */
    public void setMain(){
        funcionActual = "main";
        System.out.println("\nHe cambiado a main\n");
    }
    
    public void verificarFuncionVariable(String id, Tipos tipo){
        TokenVariable temp = getFuncion(funcionActual).getVariable(id);
        if(temp != null){
            if(temp.getTipo() == tipo){
                guardarBitacora();
            } else {
                bitacora += "(" + id + ") no es de tipo (" + tipo + ").\n"; 
                contErrores += 1;
                guardarBitacora();
            }
        } else {
            bitacora += "(" + id + ") no ha sido declarado.\n";
            contErrores += 1;
            guardarBitacora();
        }
    }
    
    @Override
    public String toString() {
        return "\nTablaSimbolos{\n" + "variables=\n" + variables.toString() + 
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
