package ListasAux;

/**
 *
 * @author Gianx
 */
public class Nodo {
    public Object dato;
    public Nodo referencia;

    public Nodo(Object dato) {
        this.dato = dato;
    }

    public Nodo getReferencia() {
        return referencia;
    }

    public void setReferencia(Nodo referencia) {
        this.referencia = referencia;
    }

    public Object getDato() {
        return dato;
    }
    
    
    
    
}
