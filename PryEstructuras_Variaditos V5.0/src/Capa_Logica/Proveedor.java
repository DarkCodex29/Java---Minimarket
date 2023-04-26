/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_Compras;
import Capa_Datos.Lista_Proveedores;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gianx
 */
public class Proveedor implements Serializable{
    private String RUC;
    private String nombre;
    private String telefono;
    private String direccion;
    private int vigencia;
    private Date FInscripcion;
    private String dep;
    private String prov;    
    private String dist;
            
    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }
 
    public static ListaEnlazada consultarTodos(){
        return Lista_Proveedores.consultar();
    }

    public Date getFInscripcion() {
        return FInscripcion;
    }

    public void setFInscripcion(Date FInscripcion) {
        this.FInscripcion = FInscripcion;
    }
    
     public static ListaEnlazada BuscarRucProveedor(String ruc){
        
        ListaEnlazada datos = consultarTodos();
        ListaEnlazada data = new ListaEnlazada();
        for (int i = 0; i < datos.tamaño(); i++) {
            Proveedor cdb = (Proveedor)datos.Buscar(i);
            if (cdb.getRUC().equalsIgnoreCase(ruc)) {
                data.agregar(cdb);
            }
        }
        return data;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }
    
    public static ListaEnlazada buscarProveedoresPorMesyAnio(int anio,int mes){
        ListaEnlazada LP=Proveedor.consultarTodos();
        ListaEnlazada LE= new ListaEnlazada();
        for(int i=0;i<LP.tamaño();i++){
            Proveedor objP= (Proveedor) LP.Buscar(i);
            Date fechaR=objP.getFInscripcion();
            Calendar calendar1= Calendar.getInstance();
            calendar1.setTime(fechaR);
            if(calendar1.get(Calendar.YEAR)==anio && calendar1.get(Calendar.MONTH)==mes){
                LE.agregar(objP);
                
            }
        }
        return LE;
    }
    
     public static int[] estadoPro(){
        ListaEnlazada datos = Lista_Proveedores.consultar();
        int cont[] = new int [2];
        for (int i = 0; i < datos.tamaño(); i++) {
            Proveedor cdb = (Proveedor)datos.Buscar(i);
            if (cdb.getVigencia()==1) {
                cont[0]++;
            }else{
                cont[1]++;
            }
        }
        return cont;
    }
      
      public static ListaEnlazada CompraProv(){
        ListaEnlazada datos = Lista_Proveedores.consultar();
        ListaEnlazada provcom = Lista_Compras.consultar();
        float cont[] = new float [2];
        float suma=0;
        ListaEnlazada nuevo = new ListaEnlazada();
        for (int i = 0; i < datos.tamaño(); i++) {
            Proveedor cdb = (Proveedor)datos.Buscar(i);
            
            for (int j = 0; j < provcom.tamaño(); j++) {
                Compra c = (Compra)provcom.Buscar(j);
                
                if (c.getRucProv().equalsIgnoreCase(cdb.getRUC())) {
                    suma+=c.getTotalCompra();
                    cont[0] = suma;
                    cont[1] = Float.parseFloat(cdb.getRUC());
                    nuevo.agregar(c);
                }
                        
            }
        }
        return nuevo;
      }
}
