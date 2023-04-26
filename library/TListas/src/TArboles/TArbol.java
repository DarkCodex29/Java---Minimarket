

package TArboles;


public interface TArbol {

        void	CrearVacio();
	void	CrearHoja();
	boolean	Vacio();
        boolean Hoja();
	Object  ObtenerDato();
        void    ModificarRaiz( TArbol A );
	void	ModificarDato( Object dato );
	void	AdicionarHijo( TArbol hijo );
        void    Adicionar(Object dato);
	void	EliminarHijo( int pos );
	int	CantidadHijos();
	TArbol 	ObtenerHijo( int pos );
	void	ModificarHijo( TArbol hijo, int pos );
        int     Cantidad();
        int     Altura();
        TArbol  Buscar(Object dato);

}
