

package Capa_Datos;

import Capa_Logica.Producto;
import ListasAux.ListaEnlazada;

/**
 *
 * @author Gianx
 */

public class Lista_Productos {

    private static final String namelista = "Lista_Productos";
    private static ListaEnlazada lista =  new ListaEnlazada();
    
    public static void  obtainLista(){
      lista = ArchivoBaseDate.ObtenerLista(namelista);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(lista,namelista);
    }  
    public static void adicionar(Object C){
    lista.agregar(C);
    saveLista();
    }
    
    public static ListaEnlazada consultar(){
    return lista;
    }
    
    public static Object obtener(int pos){
    return lista.Buscar(pos);
    }
    
    public static void modificar(Object dato, int pos){
        lista.Modificar(dato, pos);
    saveLista();
    }
    public static int buscarCodCliente (String id){
        for(int i=0; i<lista.tamaño();i++){
            Producto objCliente = (Producto)lista.Buscar(i);
            if(objCliente.getCodProducto().equalsIgnoreCase(id))
                     return i;
        }
        return -1;
    }
    
    
    public static Producto buscarProducto (String id){
        for(int i=0; i<lista.tamaño();i++){
            Producto  objProducto = (Producto)lista.Buscar(i);
            if(objProducto.getCodProducto().equalsIgnoreCase(id))
                     return objProducto;
        }
        return null;
    }
       public static int  IndexProduct(String id){
        
           for(int i=0; i<lista.tamaño();i++){
            Producto  objProducto = (Producto)lista.Buscar(i);
               System.out.println("===========" + objProducto.getCodProducto());
            if(objProducto.getCodProducto().equalsIgnoreCase(id)){
                      System.out.println("================================");
                  System.out.println("" + i);
                return i;
            }
         
        }
        
        return -1;
    }
       
       
     public static ListaEnlazada buscarProductoCoincidencia(String coincidencia){
       ListaEnlazada lista = new ListaEnlazada();
      for (int i = 0; i < Lista_Productos.consultar().tamaño(); i++) {
           Producto obProducto =  (Producto) Lista_Productos.consultar().Buscar(i);
           String nombre =  obProducto.getNombre();
           System.out.println("============coincidencia");
               System.out.println( coincidencia);
           if( nombre.indexOf(coincidencia) >= 0 ){
            System.out.println("============");
               System.out.println( nombre);
               lista.agregar(obProducto);
           }
      }
       
      return lista;
  }

}
