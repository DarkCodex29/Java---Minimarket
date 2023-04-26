
package TPilas;


public interface TPila {

        void	Push(Object dato);
	Object	Pop();
	Object	Top();

	int	Cantidad();
	boolean Vacia();
	Object	Obtener(int pos);
//	void	Modificar(Object dato, int pos);

}
