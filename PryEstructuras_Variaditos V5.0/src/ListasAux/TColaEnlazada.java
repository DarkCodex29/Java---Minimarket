

package ListasAux;
/**
 *
 * @author Gianx
 */

public class TColaEnlazada implements TCola{

        private ListaEnlazada C;

	public TColaEnlazada()
	{
	  C = new ListaEnlazada();
	}

	public void Insertar(Object dato)
	{
		C.agregar(dato); //al final
	}

	public Object Eliminar()
	{
		Object aux = Primero();
		C.Eliminar(0);
		return aux;
	}

	public Object Primero()
	{
		return C.Buscar(0);
	}

	public int Cantidad()
	{
		return C.tamaño();
	}

	public boolean Vacia()
	{
		return C.tamaño()>0;
	}

	public Object Obtener(int pos)
	{
		return C.Buscar(pos);
	}
        
}
