/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_Compras;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gianx
 */
public class Compra implements Serializable {
 private String codCompra;
 private Date fecha;
 private String rucProv;
 private float totalCompra;

    public float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }
 
 

    public String getRucProv() {
        return rucProv;
    }

    public void setRucProv(String rucProv) {
        this.rucProv = rucProv;
    }


    public String getCodCompra() {
        return codCompra;
    }
    public void setCodCompra(String codCompra) {
        this.codCompra = codCompra;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public static ListaEnlazada consultarTodo() {
        return Lista_Compras.consultar();
    }
    
    
    public static String generarCod(){
       String ma = "COM - ";
        ma = NumeroAleatorios(4, ma);
        boolean  retu = VerificarCodCompra(ma);
        //verificar serie = false (hay uno igual)
        while (!retu) {
            ma = NumeroAleatorios(4, ma);
            retu =  VerificarCodCompra(ma);
        }
        return ma;    
    }
    public static boolean VerificarCodCompra(String ma) {
        for (int i = 0; i < Lista_Compras.consultar().tamaño(); i++) {
            Compra objCompra = (Compra) Lista_Compras.consultar().Buscar(i);
            if (objCompra.getCodCompra().equalsIgnoreCase(ma)) {
                return false;
            }
        }
        return true;
    }
    private static String NumeroAleatorios(int ref, String ma) {
        for (int i = 0; i < ref; i++) {
            int ramdon = (int) (Math.random() * 9 + 1);
            ma = ma + ramdon;
        }
        return ma;
    }
    
    
    // BUSCAR 
    
    public static Object Buscar(String CodP) throws Exception{
     Producto prod = new Producto();
     ListaEnlazada lista=Producto.productoactivos();
     
        for (int i = 0; i < lista.tamaño(); i++) {
            Producto obj = (Producto)lista.Buscar(i);
            if (obj.getCodProducto().equalsIgnoreCase(CodP)) {
                prod = obj;
            }
        }
        return prod;
    }
    
    //Generer correlativo para la compra
     public static String generarCorrelativo() {
        String letra = "C001-00000";
        String num = "" + (Lista_Compras.Cantcompras()+ 1);

        String codiC = letra + num;
        return codiC;
    }
    
    
}
