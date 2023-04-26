

package ListasAux;
/**
 *
 * @author Gianx
 */

public interface TCola {
        void	Insertar(Object dato);
	Object	Eliminar();
	Object	Primero();
	int	Cantidad();
	Object	Obtener(int pos);
}
