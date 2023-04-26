

package TColas;

import TListas.*;


public class TColaEn implements TCola{

        private TListaEn C;

	public TColaEn()
	{
	  C = new TListaEn();
	}

	public void Insertar(Object dato)
	{
		C.Adicionar(dato); //al final
	}

	public Object Eliminar()
	{
		Object aux = Primero();
		C.Eliminar(0);
		return aux;
	}

	public Object Primero()
	{
		return C.Obtener(0);
	}

	public int Cantidad()
	{
		return C.Cantidad();
	}

	public boolean Vacia()
	{
		return C.Vacia();
	}

	public Object Obtener(int pos)
	{
		return C.Obtener(pos);
	}

}
