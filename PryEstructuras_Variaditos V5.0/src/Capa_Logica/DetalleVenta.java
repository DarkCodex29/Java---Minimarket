/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_DetalleVenta;
import Capa_Datos.Lista_Productos;
import Capa_Datos.Lista_Ventas;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.Alternos;

/**
 *
 * @author Gianx
 */
public class DetalleVenta implements Serializable{
    private int cantidad;
    private Date fecha;
    private float total;
    private float igv;
    private String codVenta;
    private String codProducto;
    
    public DetalleVenta(int cantidad, Date fecha,float total,float igv, String codVenta, String codProducto) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
        this.igv = igv;
        this.codVenta = codVenta;
        this.codProducto = codProducto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
        public Date getFecha() {
        return fecha;
    }
    public float getTotal() {
        return total;
    }

    public void setTotal(float precio) {
        this.total = cantidad * precio;
    }

    public float getIgv() {
        return igv;
    }

    public String getCodVenta() {
        return codVenta;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   
    public static int retornarMes(Date fecha ,int com){
         
        Calendar time = Calendar.getInstance();
        time.setTime(fecha);
        return time.get(com);
    }
    
 
 //dado un intervalo de fechas retornar los productos
   public static ListaEnlazada buscarVentar(Date comienzo , Date fin ){
        
       List<Date> fechas = Alternos.getIntervaloFechas(comienzo, fin, Calendar.MONTH);
       if(fechas.size() >  10) //fuera de rango
       {
           
       }
       ListaEnlazada filtro =  new ListaEnlazada();
       for (int i = 0; i <fechas.size(); i++) {
           Date fecha = fechas.get(i);
           int mesComp = retornarMes(fecha, Calendar.MONTH);
           ///recorro detalles de venta
           ListaEnlazada listaVenta = Lista_Ventas.obtener();
           if(listaVenta.tamaño() > 0){
               System.out.println("taamaño" + listaVenta.tamaño());
               System.out.println("tamaño " + fechas.size());
           }
           for (int j = 0; j < listaVenta.tamaño(); j++) {
               Venta venta = (Venta) listaVenta.Buscar(j);
          
              if(venta.getFecha() != null){      
              int mes = retornarMes(venta.getFecha() , Calendar.MONTH);
            
              if(mesComp == mes){
                  System.out.println("hizo match");
                   Coincidencia objCoincidencia = new Coincidencia();
                   objCoincidencia.mes = mes + 1;
                   objCoincidencia.codVenta = venta.getCodVenta();
                   objCoincidencia.fecha = venta.getFecha();
                   objCoincidencia.monto =  retornarMont(venta.getCodVenta());
                   filtro = insertarCoincidenica(filtro, objCoincidencia);
              }
              }
               
           }
           
           
       }
       
       //
       return filtro;
  
      
   }
   private static float retornarMont(String cod){
       float retorno = 0 ;   
       for (int i = 0; i < Lista_DetalleVenta.obtener().tamaño(); i++) {
            DetalleVenta objDetalleVenta = (DetalleVenta) Lista_DetalleVenta.obtener().Buscar(i);
             if(objDetalleVenta.getCodVenta().equalsIgnoreCase(cod)){
              Producto objProducto =  Lista_Productos.buscarProducto(objDetalleVenta.getCodProducto());
               retorno += (objDetalleVenta.getCantidad() * objProducto.getPrecio());
             } 
            
       }
        return retorno;
   }
   private static  ListaEnlazada  insertarCoincidenica(ListaEnlazada lista , Coincidencia obe){
      boolean res =  false;
       for (int i = 0; i < lista.tamaño(); i++) {
           Coincidencia obj = (Coincidencia) lista.Buscar(i);
           int anio = retornarMes( obj.fecha, Calendar.YEAR);
           int anioComp  = retornarMes( obe.fecha, Calendar.YEAR);
           if( obj.mes == obe.mes && anio == anioComp){
               obj.monto = obj.monto +  obe.monto;
               lista.Modificar(obj, i);
               res = true;
               break;
           }else{
               System.out.println("no hizo match" +  anio + " " + anioComp);
           }
           
       }
         if(!res){
             lista.agregar(obe);
         }
       
         return lista;
   }

}
