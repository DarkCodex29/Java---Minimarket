
package TListas;

import TArboles.TArbol;

public class TListaEn implements TLista {

	private TNodo cab;
        private int cantidad;
	
	public TListaEn()
	{
		cab=null;
                cantidad = 0;
	}
	
	public int Cantidad()
	{
        	return (cantidad);
	}
	
	public void Adicionar(Object dato)
	{
		TNodo nuevo, aux;

		nuevo = new TNodo();
		nuevo.dato = dato;
		nuevo.siguiente = null;

		if ( getCab() != null ) {
			aux = getCab();
			while( aux.siguiente != null )
                            aux = aux.siguiente;
			aux.siguiente = nuevo;
		}
		else
			cab = nuevo;
                cantidad++;
	}
	
	public void Insertar(Object dato, int pos)
	{
		TNodo nuevo, aux;
		nuevo = new TNodo();
		nuevo.dato = dato;
		if( pos <= 0 ) {
			nuevo.siguiente = getCab();
			cab = nuevo;
                        cantidad++;}
		else {
			aux = getCab();
			for( int i=0; i < pos-1 && aux != null; i++)
				aux=aux.siguiente;
			if ( aux != null ) {
				nuevo.siguiente = aux.siguiente;
				aux.siguiente = nuevo;
                                cantidad++;
			}
			else
				;//delete nuevo;		}
                    }
        }
	
	public void Eliminar(int pos)
	{
		TNodo elim;
		TNodo aux;
		if( getCab() != null ) {
			if ( pos <= 0 ) {
				elim = getCab();
				cab = getCab().siguiente;
                                cantidad--;
				//delete elim;
			}
			else {
				aux = getCab();
				for(int i=0; i < pos-1 && aux != null; i++)
					aux = aux.siguiente;
				if ( aux != null && aux.siguiente != null ) {
					elim = aux.siguiente;
					aux.siguiente = elim.siguiente;
                                        cantidad--;
					//delete elim;
                                }
                            }
		}
        }

	public boolean Vacia()
	{
		return getCab() == null;
	}
	
	public Object Obtener(int pos)
	{
		TNodo aux;
		int i;

		aux = getCab();
		for( i=0; i < pos && aux != null; i++ )
			aux = aux.siguiente;

		if ( aux != null )
			return aux.dato;
		else
			return null;
	}
	
	
	public void Modificar(Object dato, int pos)
	{
		TNodo aux;
		int i;

		aux = cab;
		for( i=0; i < pos && aux != null; i++ )
			aux = aux.siguiente;

		if ( aux != null )
			aux.dato = dato;
	}

	public int Buscar(Object dato)
	{
		TNodo aux;
		int encontrado = -1;

		aux = cab;
		for( int i=0; aux != null && encontrado == -1 ; i++ )
                {
			if ( aux.dato.equals( dato )  )
				encontrado = i;
			else
				aux = aux.siguiente;
                }
     	return encontrado;
            
	}

   
        private TNodo getCab() {
            return cab;
        }

//	public int Buscar(Object dato, TComparar cmp)
//	{
//		TNodoSEP aux;
//		int encontrado = -1;
//
//		aux = L;
//		for( int i=0; aux != null && encontrado == -1 ; i++ )
//      	{
//			if ( cmp.Compara(aux.elem, dato ) == 0  )
//				encontrado = i;
//			else
//				aux = aux.prox;
//      	}
//     	return encontrado;
//	}

        public void Intercambiar(int p1, int p2)
        {

        }     
}
