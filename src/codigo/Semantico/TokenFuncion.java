package codigo.Semantico;

import java.util.ArrayList;

/**
 *
 * @author Esteban Guzmán R
 */
public class TokenFuncion {
    //Identificador en .cup
    private String id;
    //Tipo de retorno de la función
    private Tipos tipo;
    // Lista de variables de la función
    private ArrayList<TokenVariable> variables;
    
    /**
     * Contructor de tokens de token funcion
     * @param id identificador en .cup 
     * @param tipo tipo de returno en la función
     */
    public TokenFuncion(String id,Tipos tipo) {
        this.id = id;
        this.tipo = tipo;
        variables = new ArrayList<TokenVariable>();
    }
    
    /**
     * Agrega una variable a los parametros de la función
     * @param token variable 
     */
    public void addVariable(TokenVariable token) {
        variables.add(token);
    }
    
    /**
     * Retorna el identificador de la funcion
     * @return identificador de la funcion
     */
    public String getId(){
        return id;
    }
    
    /**
     * Retorna la cantidad de variables para calcular que se llame a la función
     * con la cantidad necesaria de variables
     * @return cantidad de parametros necesarios para llamar la función
     */
    public int cantidadVariables(){
        return variables.size();
    }
    
    /**
     * Retorna el tipo de la función para validar que se pueda usar la función 
     * en la expresion requerida
     * @return tipo de la función
     */
    public Tipos getTipo(){
        return tipo;
    }
    
    public TokenVariable getVariable (String id) {
        for(int i = 0; i < variables.size(); i++){
            if(variables.get(i).getId().compareTo(id) == 0){
                return variables.get(i);
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "   {" + "id=" + id + ",\n    tipo=" + tipo + ",\n    variables=\n" 
                + variables.toString() + "}\n";
    }
}
