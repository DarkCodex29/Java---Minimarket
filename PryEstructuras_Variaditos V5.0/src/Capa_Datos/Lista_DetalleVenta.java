/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.DetalleVenta;
import Capa_Logica.Producto;
import ListasAux.ListaEnlazada;

/**
 *
 * @author Gianx
 */
public class Lista_DetalleVenta {
 
     private static  ListaEnlazada objetos = new ListaEnlazada();
     public static final int PRODUCTO_NO_EXISTE = 1 ;
     public static final int PRODUCTO_STOCK_AGOTADO = 2 ;
     public static final int CORRECTO = 0 ;
     private static final String nameLista ="DetalleVenta";
     public static void  obtainLista(){
        objetos = ArchivoBaseDate.ObtenerLista(nameLista);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(objetos, nameLista);
    }
    public static int adicionar(Object objVenta) {
           //buscar repetidad
        /// { codigo_venta , codigo_producto } 
        DetalleVenta objDetalleVenta = (DetalleVenta) objVenta; 
        //codigo_producto
         Producto objProducto= Lista_Productos.buscarProducto(objDetalleVenta.getCodProducto());
        //exist producto 
        if(objProducto == null) return  PRODUCTO_NO_EXISTE;
        //cantidad excedida
        if(objProducto.getCantidad() == 0)return PRODUCTO_STOCK_AGOTADO;
        int posProduct =  Lista_Productos.IndexProduct(objProducto.getCodProducto());
        if(posProduct == -1)  return  PRODUCTO_NO_EXISTE;
        int pos =  existProduct(objDetalleVenta.getCodProducto() , objDetalleVenta.getCodVenta());
          //restar cantidad  
       
        boolean result = modificarProducto(objProducto, objDetalleVenta.getCantidad(), posProduct);
            if(!result)return PRODUCTO_STOCK_AGOTADO;
        if(pos == -1){ 
            objetos.agregar(objVenta);         
        }else{
          DetalleVenta auxDetalleVenta = (DetalleVenta) objetos.Buscar(pos);
          int cant = auxDetalleVenta.getCantidad();
          auxDetalleVenta.setCantidad(cant + objDetalleVenta.getCantidad());
            objetos.Modificar( auxDetalleVenta , pos);         
        }
         saveLista();
          return CORRECTO;
       
    }
     public static boolean modificarProducto(Producto  objProducto ,int  cantidad, int pos){
         // resta del detalle de venta    
     
         if((objProducto.getCantidad() - cantidad) <= -1 ){
              return false;
           }
         objProducto.setCantidad(objProducto.getCantidad() - cantidad);
         System.out.println("modifique el producto: " +  pos);
         Lista_Productos.consultar().Modificar(objProducto, pos);
         Lista_Productos.saveLista();
      return true;
     }
    public static ListaEnlazada obtener() {
        return objetos;
    }
    
    public static Object obtener(int pos) {
        return objetos.Buscar(pos);
    }
    

    public static void eliminar(int pos)
    {
        objetos.Eliminar(pos);
    }
   public static ListaEnlazada  buscarDetalleVenta(String codVenta){
     ListaEnlazada listaFiltrada  = new ListaEnlazada();
       for (int i = 0; i < objetos.tamaño(); i++) {
            DetalleVenta objDetalleVenta = (DetalleVenta) objetos.Buscar(i);
             if(objDetalleVenta.getCodVenta().equalsIgnoreCase(codVenta)){
                  //Producto obProducto = Lista_Productos.buscarProducto(objDetalleVenta.getCodProducto());
                  //listaFiltrada.agregar(obProducto);
               listaFiltrada.agregar(objDetalleVenta);
             } 
       }
     return listaFiltrada;
   }
      public static int existProduct(String idProduct, String codVenta){
       for (int i = 0; i < objetos.tamaño(); i++) {
            DetalleVenta objDetalleVenta = (DetalleVenta) objetos.Buscar(i);
             if(objDetalleVenta.getCodProducto().equalsIgnoreCase(idProduct) &&  objDetalleVenta.getCodVenta().equalsIgnoreCase(codVenta)){
                 return i;
             } 
       }
   
     return -1;
   }
}











