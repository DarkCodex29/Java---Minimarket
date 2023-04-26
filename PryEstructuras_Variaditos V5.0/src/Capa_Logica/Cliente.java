/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_Clientes;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gianx
 */
public class Cliente implements Serializable{
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
        return Lista_Clientes.consultar();
    }
    
    public static ListaEnlazada BuscarDniCliente(String dni){
        
        ListaEnlazada datos = consultarTodo();
        ListaEnlazada data = new ListaEnlazada();
        
        for (int i = 0; i < datos.tamaño(); i++) {
            Cliente cdb = (Cliente)datos.Buscar(i);
            if (cdb.getDNI().equalsIgnoreCase(dni)) {
                data.agregar(cdb);
            }
        }
        return data;
    }
           
    
    
    public static ListaEnlazada listarActivos(){
    ListaEnlazada datos = consultarTodo();
    ListaEnlazada data = new ListaEnlazada();
        
        for (int i = 0; i < datos.tamaño(); i++) {
            Cliente cdb = (Cliente)datos.Buscar(i);
            if (cdb.getEstado()==1) {
                data.agregar(cdb);
            }
        }
        return data;
    
    }
    public static ListaEnlazada listarInactivos(){
    ListaEnlazada datos = consultarTodo();
    ListaEnlazada data = new ListaEnlazada();
        
        for (int i = 0; i < datos.tamaño(); i++) {
            Cliente cdb = (Cliente)datos.Buscar(i);
            if (cdb.getEstado()!= 1) {
                data.agregar(cdb);
            }
        }
        return data;
    
    }
    
    public static float[] porcentajeClientesActivosHyM(){
        ListaEnlazada LO=Cliente.listarActivos();
        int muj=0;
        int hom=0;
        int total=0;
        float porcen[]= new float[2];
        for(int i=0;i<LO.tamaño();i++){
            Cliente objC= (Cliente) LO.Buscar(i);
            if(objC.getSexo().equalsIgnoreCase("Masculino")){
                hom++;
            }else{
                muj++;
            } 
        }
        total = LO.tamaño();
        
        if (total>0) {
        porcen[0]=(hom*100)/total;
        porcen[1]=(muj*100)/total;
        }else{
        porcen[0]=0;
        porcen[1]=0;
        }
        return porcen;
    }
    public static float[] porcentajeClientesInactivosHyM(){
        ListaEnlazada LO=Cliente.listarInactivos();
        int muj=0;
        int hom=0;
        int total=0;
        float porcen[]= new float[2];
        for(int i=0;i<LO.tamaño();i++){
            Cliente objC= (Cliente) LO.Buscar(i);
            if(objC.getSexo().equalsIgnoreCase("Masculino")){
                hom++;
            }else{
                muj++;
            } 
        }
        total = LO.tamaño();
        
       
      
        if (total>0) {
        porcen[0]=(hom*100)/total;
        porcen[1]=(muj*100)/total;
        }else{
        porcen[0]=0;
        porcen[1]=0;
        }
        return porcen;
    }
    

    
    public static int [] clientesDepartamento(){
    int numClientes[]= new int [25];
    ListaEnlazada lista= Lista_Clientes.consultar();
     for (int i = 0; i < lista.tamaño(); i++) {
        Cliente objC = (Cliente)lista.Buscar(i);
            if (objC.getDep().equalsIgnoreCase("Amazonas")) {
                numClientes[0]++;
            }else{if(objC.getDep().equalsIgnoreCase("Ancash")){
               numClientes[1]++;
                }else{if (objC.getDep().equalsIgnoreCase("Apurimac")) {
                numClientes[2]++;
                }else{if (objC.getDep().equalsIgnoreCase("Arequipa")) {
                        numClientes[3]++;
                        }else{if (objC.getDep().equalsIgnoreCase("Ayacucho")) {
                                 numClientes[4]++;
                            }else{if (objC.getDep().equalsIgnoreCase("Cajamarca")) {
                                       numClientes[5]++;
                                    }else{if (objC.getDep().equalsIgnoreCase("Callao")) {
                                             numClientes[6]++;
                                          }else{if (objC.getDep().equalsIgnoreCase("Cusco")) {
                                                    numClientes[7]++;
                                                        }else{if (objC.getDep().equalsIgnoreCase("Huancavelica")) {
                                                                 numClientes[8]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Huanuco")) {
                                                                        numClientes[9]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Ica")) {
                                                                        numClientes[10]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Junín")) {
                                                                        numClientes[11]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("La Libertad")) {
                                                                        numClientes[12]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Lambayeque")) {
                                                                        numClientes[13]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Lima")) {
                                                                        numClientes[14]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Loreto")) {
                                                                        numClientes[15]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Madre de Dios")) {
                                                                        numClientes[16]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Moquegua")) {
                                                                        numClientes[17]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Pasco")) {
                                                                        numClientes[18]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Piura")) {
                                                                        numClientes[19]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Puno")) {
                                                                        numClientes[20]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("San Martín")) {
                                                                        numClientes[21]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Tacna")) {
                                                                        numClientes[22]++;
                                                                    }else{if (objC.getDep().equalsIgnoreCase("Tumbes")) {
                                                                        numClientes[23]++;
                                                                    }else{
                                                                        //Ucayali
                                                                        numClientes[24]++;
                                                                 
                                                                    }                                                               
                                                                    }                                                                                                                                     
                                                                    }                                                            
                                                                    }                                                                   
                                                                    }                                                                  
                                                                    }                                                                
                                                                    }                                                            
                                                                    }                                                                  
                                                                    }                                                              
                                                                    }       
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
                                                                    }
               }
            }
        }

        return numClientes;
    }
    public static ListaEnlazada clientesMayorCompra(String departamento){
    ListaEnlazada lista = Lista_Clientes.consultar();
    ListaEnlazada LF = new ListaEnlazada();
    float mayor = 0;
        for (int i = 0; i < lista.tamaño(); i++) {
           Cliente objC= (Cliente)lista.Buscar(i);
            if (objC.getDep().equalsIgnoreCase(departamento)) {
                if (objC.getTotalVenta()>mayor) {
                    mayor=objC.getTotalVenta();
                }
            }
        }
        
        for (int i = 0; i < lista.tamaño(); i++) {
           Cliente objC= (Cliente)lista.Buscar(i);
            if (objC.getDep().equalsIgnoreCase(departamento)) {
                if (objC.getTotalVenta()==mayor) {
                    LF.agregar(objC);
                }
            }
        }
        
        return LF;
    }
    
    
    
    
}
