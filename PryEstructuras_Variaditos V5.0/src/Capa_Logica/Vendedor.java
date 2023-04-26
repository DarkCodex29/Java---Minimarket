/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_Vendedores;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gianx
 */
public class Vendedor implements Serializable{
            private String DNI;
            // de forma manual
            private String nombre;
            private String apellidos;
            private byte edad;
            private String sexo;
            private String celular;
            private String correo;
            private Date fnac;
            private String dep;
            private String prov;    
            private String dist;
            private String direccion;
            private int estado;
            private String codVenta;
            private float totalVenta;

    public String getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(String codVenta) {
        this.codVenta = codVenta;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

 
            
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public byte getEdad() {
        return edad;
    }
    public void setEdad(byte edad) {
        this.edad = edad;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Date getFnac() {
        return fnac;
    }
    public void setFnac(Date fnac) {
        this.fnac = fnac;
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
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public static ListaEnlazada consultarTodo() {
        return Lista_Vendedores.consultar();
    }
    
        public static ListaEnlazada BuscarDniVendedor(String dni){
        
        ListaEnlazada datos = consultarTodo();
        ListaEnlazada data = new ListaEnlazada();
        
        for (int i = 0; i < datos.tamaño(); i++) {
            Vendedor cdb = (Vendedor)datos.Buscar(i);
            if (cdb.getDNI().equalsIgnoreCase(dni)) {
                data.agregar(cdb);
            }
        }
        return data;
    }
    
    
    
         //Dado la vigencia y el sexo, mostrar el/los vendedores con la mayor edad
    public static ListaEnlazada mayorEdad(int estado, String sexo) {

        ListaEnlazada lista = consultarTodo();
        ListaEnlazada ListaFiltrada = new ListaEnlazada();
        int mayor = 0;

        for (int i = 0; i < lista.tamaño(); i++) {
            Vendedor objVendedor = (Vendedor) lista.Buscar(i);
            if (objVendedor.getEstado()==1 && objVendedor.getSexo().equalsIgnoreCase(sexo)) {
                if (objVendedor.getEdad() > mayor) {
                    mayor = objVendedor.getEdad();
                }
            }
        }

        for (int i = 0; i < lista.tamaño(); i++) {
            Vendedor objVendedor = (Vendedor) lista.Buscar(i);
            if (objVendedor.getEstado()==1 && objVendedor.getSexo().equalsIgnoreCase(sexo)) {
                if (objVendedor.getEdad() == mayor) {
                    ListaFiltrada.agregar(objVendedor);
                }
            }
        }

        return ListaFiltrada;

    }
}
