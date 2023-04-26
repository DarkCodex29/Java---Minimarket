/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Datos.Lista_Productos;
import ListasAux.ListaEnlazada;
import java.io.Serializable;


/**
 *
 * @author Gianx
 */
public class Producto implements Serializable{
    private String codProducto;
    private String nombre;
    private int cantidad;
    private String unidad;
    private String categoria;
    private float precio;
    private String estado;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the codProducto
     */
    public String getCodProducto() {
        return codProducto;
    }

    /**
     * @param codProducto the codProducto to set
     */
    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }
   

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public static ListaEnlazada consultarTodo()  {
        return Lista_Productos.consultar();
    }
    
    public static void modificarProducto(String codProducto, Producto objProducto){
        for (int i = 0; i <Lista_Productos.consultar().tamaño(); i++) {
            Producto  obj = (Producto) Lista_Productos.consultar().Buscar(i);
            if(obj.getCodProducto().equalsIgnoreCase(codProducto)){
                Lista_Productos.consultar().Modificar(objProducto, i);
                return;   
             
            }
        }
        
    }
    
    public static ListaEnlazada productoactivos(){
        ListaEnlazada lista = Producto.consultarTodo();
        ListaEnlazada LF = new ListaEnlazada();
        
        for(int i=0;i<lista.tamaño();i++){
            Producto obj= (Producto)lista.Buscar(i);
  
            if(obj.getEstado().equalsIgnoreCase("Activo"))
                LF.agregar(obj);
        }
        return LF;
    }
    
    
       public static ListaEnlazada ProductosMayorPrecio(){
        ListaEnlazada LP= Producto.consultarTodo();
        ListaEnlazada LF= new ListaEnlazada();
        float mayor=0;
        for(int i=0;i<LP.tamaño();i++){
            Producto objP=(Producto) LP.Buscar(i);
            if(objP.getPrecio()>mayor){
                mayor=objP.getPrecio();
            }
        }
        for(int i=0;i<LP.tamaño();i++){
            Producto objP= (Producto) LP.Buscar(i);
            if(mayor==objP.getPrecio()){
                LF.agregar(objP);
            }
        }
        return LF;
    }
    public static ListaEnlazada Productosxestado(String estado){
        ListaEnlazada lista= Producto.consultarTodo();
        ListaEnlazada LF= new ListaEnlazada();

        for(int i=0;i<lista.tamaño();i++){
            Producto objP= (Producto) lista.Buscar(i);
            if(objP.getEstado().equalsIgnoreCase(estado)){
                LF.agregar(objP);
            }
        }
        return LF;
    }
    
     public static double[] porcentaxcat(String estado){
    ListaEnlazada LP= Producto.Productosxestado(estado);
    int cont[] = new int[5];
    double porc[] = new double[5];
    int total;
    for(int i=0;i<LP.tamaño();i++){
            Producto obj= (Producto) LP.Buscar(i);
            if(obj.getCategoria().equalsIgnoreCase("Abarrotes")){
                cont[0]++;
               
            }else if(obj.getCategoria().equalsIgnoreCase("Bebidas")){
                cont[1]++;
               
            }else if(obj.getCategoria().equalsIgnoreCase("Condimentos y Sales")){
                cont[2]++;
               
            }else if(obj.getCategoria().equalsIgnoreCase("Golosinas y Postres")){
                cont[3]++;
               
            }else if(obj.getCategoria().equalsIgnoreCase("Lacteos")){
                cont[4]++;             
            }
           total = cont[0]+cont[1]+cont[2]+cont[3]+cont[4];
           porc[0]=(cont[0]*100)/total;
           porc[1]=(cont[1]*100)/total;
           porc[2]=(cont[2]*100)/total;
           porc[3]=(cont[3]*100)/total;
           porc[4]=(cont[4]*100)/total;
           
    }
        return porc;
    }
     public static double[] porcentajexestado(){
    ListaEnlazada LP= Producto.consultarTodo();
    int cont[] = new int[2];
    double porc[] = new double[2];
    int total;
    for(int i=0;i<LP.tamaño();i++){
            Producto obj= (Producto) LP.Buscar(i);
            if(obj.getEstado().equalsIgnoreCase("Activo")){
                cont[0]++;
                ;
            }else{
                cont[1]++;             
            }
           total = cont[0]+cont[1];
           porc[0]=(cont[0]*100)/total;
           porc[1]=(cont[1]*100)/total;
           
    }
        return porc;
    }
    
    public static ListaEnlazada Productosxestadoycategoria(String estado, String cate){
        ListaEnlazada lista= Producto.Productosxestado(estado);
        ListaEnlazada LF = new ListaEnlazada();

        for(int i=0;i<lista.tamaño();i++){
            Producto objP= (Producto) lista.Buscar(i);
            if(objP.getCategoria().equalsIgnoreCase(cate)){
                LF.agregar(objP);
            }
        }
        return LF;
    }
    
}
