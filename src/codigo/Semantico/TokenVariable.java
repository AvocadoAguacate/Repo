package codigo.Semantico;


/**
 *
 * @author Esteban Guzmán R
 */
public class TokenVariable {
    //Identificador en .cup
    private String id;
    //Tipo de retorno de la función
    private Tipos tipo;
    
    /**
     * Contructor de tokens de token variable
     * @param id identificador en .cup 
     * @param tipo tipo de la variable
     */
    public TokenVariable(String id,Tipos tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
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
        return "   {" + "id=" + id + ",\n tipo=" + tipo + "}\n";
    }
}
