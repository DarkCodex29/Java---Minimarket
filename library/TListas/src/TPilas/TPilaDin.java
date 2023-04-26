

package TPilas;

import TListas.*;

public class TPilaDin implements TPila{

        private TListaDin P;

	public TPilaDin()
	{
		P = new TListaDin();
	}

	public void Push(Object dato)
	{
		P.Adicionar(dato);
	}

	public Object	Top()
	{
		int pos = P.Cantidad()-1;
		return P.Obtener(pos);
	}

	public Object	Pop()
	{
		int pos = P.Cantidad()-1;
		Object aux = P.Obtener(pos);
		P.Eliminar(pos);
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
