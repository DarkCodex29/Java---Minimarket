/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.DetalleCompra;
import Capa_Logica.Producto;
import ListasAux.ListaEnlazada;
/**
 *
 * @author Gianx
 */
public class Lista_DetallesCompra {
    //   ENLAZA PORQUE SE VA A RECIBIR VARIOS PRODUCTOS
   //  Y SE VA A MANEJAR VARIOS DATOS.
    private static final String namelista = "Lista_Detalle_compra";
   public static final int NO_EXISTE = 1;
    public static final int CORRECTO = 2;
   
    private static ListaEnlazada lista =  new ListaEnlazada();
    
     public static void  obtainLista(){
      lista = ArchivoBaseDate.ObtenerLista(namelista);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(lista,namelista);
    }
    public static int adicionar(Object C){
      DetalleCompra objCompra  = (DetalleCompra) C;
        Producto objProducto = Lista_Productos.buscarProducto(objCompra.getCodProducto());
       if(objProducto == null)  return  NO_EXISTE;
       //int posProduct = Lista_Productos.IndexProduct(objProducto.getCodProducto());
       int cantida = objProducto.getCantidad()  +  objCompra.getCantidad();
        objProducto.setCantidad(cantida);
        Producto.modificarProducto(objProducto.getCodProducto(), objProducto);
       
     
    lista.agregar(C);
    saveLista();
    return CORRECTO;
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
    public static ListaEnlazada buscarDetalle(String codCompra) {
        ListaEnlazada ListaDetalle = new ListaEnlazada();
         for (int i = 0; i < lista.tamaño(); i++) {
            DetalleCompra obj = (DetalleCompra) lista.Buscar(i);
            if (obj.getCodigoCompra().equalsIgnoreCase(codCompra)) {
                ListaDetalle.agregar(obj);
            }
        }
        return ListaDetalle;
    } 
     
     
}
