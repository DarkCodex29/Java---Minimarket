

package TArboles;


public class TArbolBB extends TArbolBin{

        private TComparar cmpFun;
	
	public TArbolBB( TComparar cmp )
	{
		super();
		cmpFun = cmp;
	}
	
	public void CrearHoja()
	{
	   super.CrearHoja();
	   ModificarIzq( new TArbolBB(cmpFun) );
	   ModificarDer( new TArbolBB(cmpFun) );
	}
	
	public TComparar ObtenerComparar()
	{
		return cmpFun;
	}

	public void ModificarComparar( TComparar cmp )
	{
		cmpFun = cmp;
	}
	
	public void Adicionar( Object dato )
	{
		if ( Vacio() )
		{
			CrearHoja();
			ModificarDato(dato);
		}
		else
		{
			int r = cmpFun.Compara( ObtenerDato(), dato );
			if ( r == 0 ) // ya esta
				return;
			else
			if ( r < 0 ) // por la derecha
				((TArbolBB)Der()).Adicionar(dato);
			else // por la izq
				((TArbolBB)Izq()).Adicionar(dato);
		}
	}
	public TArbolBin Buscar( Object dato )
	{
		if ( Vacio() )
			return null;
		else
		{
			int r = cmpFun.Compara( ObtenerDato(), dato );
			if ( r == 0 ) // ya esta
				return this; 
			else
			if ( r < 0 ) // por la derecha
				return Der().Buscar(dato);
			else // por la izq
				return Izq().Buscar(dato);
		}
	}
	
	public void Eliminar( Object dato )
	{
		if ( Vacio() )
			return;
		else
		{
			int r = cmpFun.Compara( ObtenerDato(), dato );
			if ( r < 0 ) // por la derecha
				((TArbolBB)Der()).Eliminar(dato);
			else
			if ( r > 0 ) // por la izq
				((TArbolBB)Izq()).Eliminar(dato);
			else
			{
			 if ( Izq().Vacio() && Der().Vacio() ) // Hoja()? caso 1
			 	CrearVacio();
			 else
				if ( Izq().Vacio() ) // caso 2
					ModificarRaiz( Der() );
				else
					if ( Der().Vacio() ) // caso 3
						ModificarRaiz( Izq() );
					else // caso 4
					{
						TArbolBB aux;
						aux = (TArbolBB)Der();
						while ( ! aux.Izq().Vacio() )
							aux = (TArbolBB)aux.Izq();
						ModificarDato( aux.ObtenerDato() );
						aux.Eliminar( aux.ObtenerDato() );
					}
			}
		}
	}


}
