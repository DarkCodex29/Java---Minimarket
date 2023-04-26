

package TGrafos;

import TListas.TLista;
import TListas.TListaDin;
import TListas.TListaEn;



public class TGrafoLA implements TGrafo
{

       
        public class TVertice {
		public Object info;
		public TLista ady;

		public TVertice()
		{
			info = null;
			ady = new TListaEn();
		}

                public String toString()
                {
                    return info.toString();
                }
	}

	public class TArco {
		public Object info;
		public int v;

		public TArco(int vlleg)
		{
			info = null;
			v = vlleg;
		}
                public String toString()
                {
                    return info.toString();
                }
	}

	private TLista vertices;

	public TGrafoLA()
	{
		vertices = new TListaDin();
	}

	public int AdicionarVertice(Object info)
	{
		TVertice vert = new TVertice();
		vert.info = info;
		vertices.Adicionar(vert);
		return vertices.Cantidad()-1;
	}

	public int CantidadVertices()
	{
		return vertices.Cantidad();
	}

	public Object ObtenerVertice(int v)
	{
		if ( v < 0 || v >= vertices.Cantidad() )
			return null;
		return ((TVertice)vertices.Obtener(v)).info;
	}

	public void ModificarVertice(int v, Object info)
	{
		if ( v < 0 || v >= vertices.Cantidad() )
			return;
		((TVertice)vertices.Obtener(v)).info = info;
	}

	public int AdicionarArco(int vsal, int vlleg, Object info)
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() ||
			 vlleg < 0 || vlleg >= vertices.Cantidad() )
			return -1;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		TArco arco;
		arco = new TArco(vlleg);
		arco.info=info;
		vert.ady.Adicionar(arco);
		return vert.ady.Cantidad()-1;
	}

	public int CantidadArcos(int v)
	{
		if ( v < 0 || v >= vertices.Cantidad() )
			return 0;
		return ((TVertice)vertices.Obtener(v)).ady.Cantidad();
	}

	public Object ObtenerArco(int vsal, int apos )
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return null;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		if ( apos < 0 || apos >= vert.ady.Cantidad() )
			return null;

		TArco arco;
		arco = (TArco)vert.ady.Obtener(apos);
		return arco.info;
	}

	public void EliminarArco(int vsal, int apos )
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		if ( apos < 0 || apos >= vert.ady.Cantidad() )
			return;
                vert.ady.Eliminar(apos);
	}

	public void ModificarArco(int vsal, int apos, Object info )
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		if ( apos < 0 || apos >= vert.ady.Cantidad() )
			return;

		TArco arco;
		arco = (TArco)vert.ady.Obtener(apos);
		arco.info = info;
	}

	public int ObtenerVerticeLlegada(int vsal, int apos)
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return -1;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		if ( apos < 0 || apos >= vert.ady.Cantidad() )
			return -1;

		TArco arco;
		arco = (TArco)vert.ady.Obtener(apos);
		return arco.v;
	}

	public void ModificarVerticeLlegada(int vsal, int apos, int vlleg)
	{
		if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return;
		TVertice vert;
		vert = (TVertice)vertices.Obtener(vsal);

		if ( apos < 0 || apos >= vert.ady.Cantidad() )
			return;

		TArco arco;
		arco = (TArco)vert.ady.Obtener(apos);
                arco.v = vlleg;
	}

        public TLista ObtenerVertices()
        {
            return vertices;
        }

        public TLista ObtenerArcos(int vsal)
        {
            if ( vsal < 0 || vsal >= vertices.Cantidad() )
		   return null;
            TVertice vert;
            vert = (TVertice)vertices.Obtener(vsal);
            return vert.ady;
        }

}
