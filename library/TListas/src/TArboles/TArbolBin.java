//Implementacion de un arbol Binario

package TArboles;

import javax.swing.JOptionPane;

public class TArbolBin{

    
            private class TNodoABin {
		Object info;
		TArbolBin izq, der;
	}

	private TNodoABin raiz;

    /**
         * @return the raiz
         */
        public TNodoABin getRaiz() {
            return raiz;
        }
        

	public TArbolBin()
	{
		CrearVacio();
	}

	public void CrearVacio()
	{
		raiz = null;
	}

	public void CrearHoja()
	{
		raiz = new TNodoABin();
		raiz.info = null;
		raiz.izq = new TArbolBin();
		raiz.der = new TArbolBin();
	}

	public boolean	Vacio()
	{
		return getRaiz() == null;
	}

        public boolean Hoja()
        {
            return !Vacio() && Izq().Vacio() && Der().Vacio();
        }

	public void ModificarRaiz( TArbolBin A )
	{
		raiz = A.getRaiz();
	}

	public Object  ObtenerDato()
	{
		return getRaiz().info;
	}

	public void	ModificarDato( Object dato )
	{
		raiz.info = dato;
	}

	public TArbolBin Izq()
	{
		return getRaiz().izq;
	}

	public TArbolBin Der()
	{
		return getRaiz().der;
	}

	public void ModificarIzq( TArbolBin a )
	{
		raiz.izq = a;
	}

	public void ModificarDer( TArbolBin a )
	{
		raiz.der = a;
	}

	public int Cantidad()
	{
		if ( Vacio() )
			return 0;
		else
		{
			int ci, cd;
			ci = getRaiz().izq.Cantidad();
			cd = getRaiz().der.Cantidad();
			return 1 + ci + cd;
		}
	}

	public int Altura()
	{
		if ( Vacio() )
			return 0;
		else
		{
			int ai, ad;
			// calcula la altura por la izquierda, 0 si no hay izq
			if ( getRaiz().izq.Vacio() )
				ai = 0;
			else
				ai = 1 + getRaiz().izq.Altura();

			// calcula la altura por la derecha, 0 si no hay der
			if ( getRaiz().der.Vacio() )
				ad = 0;
			else
				ad = 1 + getRaiz().der.Altura();
			// retorna la mayor altura
			return ai > ad ? ai : ad;
		}
	}

	public TArbolBin Buscar( Object dato )
	{
		if ( Vacio() )
			return null;
		else
			if ( getRaiz().info.equals( dato ) )  // encontrado
				return this;
			else
			{
				TArbolBin r;
				// buscar por la izquierda
				r = getRaiz().izq.Buscar(dato);
				if ( r == null ) // no encontrado ?
	 			    // buscar por la derecha
					r = getRaiz().der.Buscar(dato);
				return r;
			}

	}

//        public void    InOrden(TArbolBin A)
//        {
//            if(A.getRaiz()==null)
//                return;
//            else
//            {
//                JOptionPane.showMessageDialog(null, A.ObtenerDato(), "Mensaje", JOptionPane.OK_OPTION);
//                InOrden(A.Izq());
//                InOrden(A.Der());
//            }
//        }

//        public void    PreOrden(TArbolBin A)
//        {
//          if(A.getRaiz()==null)
//                return;
//            else
//            {
//                JOptionPane.showMessageDialog(null, A.ObtenerDato(), "Mensaje", JOptionPane.OK_OPTION);
//                PreOrden(A.Izq());
//                PreOrden(A.Der());
//            }
//        }
//
//        public void    PostOrden(TArbolBin A)
//        {
//            if(A.getRaiz()==null)
//                return;
//            else
//            {
//                JOptionPane.showMessageDialog(null, A.ObtenerDato(), "Mensaje", JOptionPane.OK_OPTION);
//                PostOrden(A.Izq());
//                PostOrden(A.Der());
//            }
//        }

//	public TArbolBin Buscar( Object dato, TComparar cmp )
//	{
//		if ( Vacio() )
//			return null;
//		else
//			if ( cmp.Compara( raiz.info, dato ) == 0 )  // encontrado
//				return this;
//			else
//			{
//				TArbolBin r;
//				// buscar por la izquierda
//				r = raiz.izq.Buscar(dato,cmp);
//				if ( r == null ) // no encontrado ?
//	 			    // buscar por la derecha
//					r = raiz.der.Buscar(dato,cmp);
//				return r;
//			}
//
//	}

        

}
