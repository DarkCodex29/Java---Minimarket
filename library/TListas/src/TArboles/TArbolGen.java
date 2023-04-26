//Implementación de un Arbol General

package TArboles;

import TListas.*;


public class TArbolGen implements TArbol{

        private class TNodoArbol {
		Object info;
		TLista hijos;
	}

	private TNodoArbol raiz;

	public TArbolGen()
	{
		CrearVacio();
	}

	public void	CrearHoja()
	{
		raiz = new TNodoArbol();
		raiz.info = null;
		raiz.hijos = new TListaEn();
	}

	public void CrearVacio()
	{
		raiz = null;
	}

	public boolean	Vacio()
	{
		return raiz == null;
	}

        public boolean Hoja()
        {
            return !Vacio() && CantidadHijos() == 0;
        }

	public Object  ObtenerDato()
	{
		return raiz.info;
	}

	public void	ModificarDato( Object dato )
	{
		raiz.info = dato;
	}

        public void ModificarRaiz( TArbol A )
        {
            raiz = ((TArbolGen)A).raiz;
        }

	public void	AdicionarHijo( TArbol hijo )
	{
		raiz.hijos.Adicionar(hijo);
	}

	public void	EliminarHijo( int pos )
	{
		raiz.hijos.Eliminar(pos);
	}

	public int	    CantidadHijos()
	{
		return raiz.hijos.Cantidad();
	}

	public TArbol 	ObtenerHijo( int pos )
	{
		return (TArbol)raiz.hijos.Obtener(pos);
	}

        public void	ModificarHijo( TArbol hijo, int pos )
                {
                        raiz.hijos.Modificar(hijo,pos);
	}

        public int  Cantidad()
        {
            if ( Vacio() )
                return 0;
            if ( Hoja() )
                return 1;
            int S, i;
            S = 1;//contar la raiz
            for ( i = 0; i < CantidadHijos(); i++ ) {
                TArbol hijo;
                hijo = ObtenerHijo(i);
                S = S + hijo.Cantidad();
            }
            return S;
        }

        public int  Altura()
        {
            if ( Vacio() )
                return 0;
            if ( Hoja() )
                return 0;
            int m, a, i;
            m = 0;
            for ( i = 0; i < CantidadHijos(); i++ ) {
                TArbol hijo;
                hijo = ObtenerHijo(i);
                a = hijo.Altura();
                if ( a > m )
                    m = a;
            }
            return 1+m;
        }

        public TArbol  Buscar(Object dato)
        {
            if ( Vacio() )
                return null;
            if ( Hoja() )
                if ( dato.equals( ObtenerDato() ))
                    return this;
                else
                    return null;

            if ( dato.equals( ObtenerDato() ))
               return this;

            int i;
            for ( i = 0; i < CantidadHijos(); i++ ) {
                TArbol hijo;
                hijo = ObtenerHijo(i);
                TArbol Res = hijo.Buscar(dato);
                if ( Res != null )
                    return Res;
            }
            return null;
        }

        public void Adicionar(Object dato) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
