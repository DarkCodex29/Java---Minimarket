

package TGrafos;

import TListas.TLista;


public interface TGrafo {

	public int AdicionarVertice(Object info);
	public int CantidadVertices();

	public Object ObtenerVertice(int v);
	public void ModificarVertice(int v, Object info);

	public int AdicionarArco(int vsal, int vlleg, Object info);
	public int CantidadArcos(int v);

        public Object ObtenerArco(int vsal, int apos );
	public void ModificarArco(int vsal, int apos, Object info );
	public void EliminarArco(int vsal, int apos );

	public int ObtenerVerticeLlegada(int vsal, int apos);
	public void ModificarVerticeLlegada(int vsal, int apos, int vlleg);

        public TLista ObtenerVertices();
        public TLista ObtenerArcos(int vsal);
}
