

package TArboles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TABB {

    private class CmpNum implements TComparar
	{
		public int Compara(Object a, Object b)
		{
			Integer e1 = (Integer)a;
			Integer e2 = (Integer)b;
			if ( e1.intValue() == e2.intValue() ) return 0;
			else
				if ( e1.intValue() < e2.intValue() )
					return -1;
				else
					return 1;
		}

	}


	public void Imprimir( TArbolBB A )  throws IOException
	{
		if ( A.Vacio() )
			System.out.print(" . ");
		else
		{
			System.out.print(((Integer)A.ObtenerDato()).toString());
			Imprimir( (TArbolBB)A.Izq() );
			Imprimir( (TArbolBB)A.Der() );
		}
	}

	public TABB() throws IOException
	{
		String o;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int opc;
		Integer valor;

		TArbolBB A;

		A = new TArbolBB( new CmpNum() );

		do {
			System.out.println("1.-Adicionar");
			System.out.println("2.-Eliminar");
			System.out.println("3.-Imprimir");
			System.out.println("0.-Terminar");
			System.out.print("\n   Ingrese la opcion: ");
			o = br.readLine();
			opc = Integer.valueOf(o).intValue();
			switch ( opc )
			{
			  case 1:
				System.out.print("Valor: ");
				o = br.readLine();
				valor = Integer.valueOf(o);
				A.Adicionar(valor);
				break;
			  case 2:
				System.out.print("Valor: ");
				o = br.readLine();
				valor = Integer.valueOf(o);
				A.Eliminar(valor);
				break;
			  case 3:
				System.out.println("");
				Imprimir(A);
				System.out.println("");
				break;
			}
		}
		while ( opc != 0 );
	}


}
