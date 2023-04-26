/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_DetallesCompra;
import ListasAux.ListaEnlazada;
import java.io.Serializable;


/**
 *
 * @author Gianx
 */
public class DetalleCompra implements Serializable{
    private String codProducto;
    private String nombreProducto;
    private int cantidad ;
    private float precioU;
    private float totalCompra;
    private String codigoCompra;

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
      
     public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public float getPrecioU() {
        return precioU;
    }
    public void setPrecioU(float precioU) {
        this.precioU = precioU;
    }
    public float getTotalCompra() {
        return totalCompra;
    }
    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }
    public String getnombreProducto() {
        return nombreProducto;
    }
    public void setnombreProducto(String nombre) {
        this.nombreProducto = nombre;
    }
    
    public static ListaEnlazada consultarTodo() {
        ListaEnlazada datos = Lista_DetallesCompra.consultar();
        ListaEnlazada data = new ListaEnlazada();
        
        for (int i = 0; i < datos.tamaño(); i++) {
            DetalleCompra det = (DetalleCompra)datos.Buscar(i);
            
            if (det.getCodigoCompra()==null) {
                data.agregar(det);                
            }
        }        
        return data;                 
    }  
    
   public static ListaEnlazada cosultarDetalleCompra(String cod) {
        ListaEnlazada datos = Lista_DetallesCompra.consultar();
        ListaEnlazada data = new ListaEnlazada();
        
        System.out.println(""+cod);
        
        for (int i = 0; i < datos.tamaño(); i++) {
            DetalleCompra det = (DetalleCompra)datos.Buscar(i);
            System.out.println(""+det.getCodigoCompra());
            if (det.getCodigoCompra().equalsIgnoreCase(cod)) {
                data.agregar(det);                
            }
        }        
        return data;                 
    }
}
