

package TPilas;

import TListas.*;


public class TPilaEn implements TPila{

        private TListaEn P;

	public TPilaEn()
	{
		P = new TListaEn();
	}

	public void	Push(Object dato)
	{
		P.Insertar(dato,0);
	}

	public Object	Top()
	{
		return P.Obtener(0);
	}

	public Object	Pop()
	{
		Object aux = Top();
		P.Eliminar(0);
		return aux;
	}

	public int Cantidad()
	{
		return P.Cantidad();
	}

	public boolean Vacia()
	{
		return P.Vacia();
	}

	public Object Obtener(int pos)
	{
		return P.Obtener(pos);
	}

	

}
