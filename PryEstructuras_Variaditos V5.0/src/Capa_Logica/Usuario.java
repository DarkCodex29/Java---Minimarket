
package Capa_Logica;

import Capa_Datos.ListUsuario;
import ListasAux.ListaEnlazada;

import java.io.Serializable;
/**
 *
 * @author Gianx
 */
public class Usuario implements Serializable{
   private String usuario;
   private String contraseña;
   private String tipoPermiso;
  
   private int codigo;
   
    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;  
        codigo = obtenerCodigo();
        codigo++;
    }

    public int getCodigo() {
        return codigo;
    }
   
    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }
    
    public static ListaEnlazada consultar(){
      return ListUsuario.consultar();
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }
    
    //***************gestionar Usuario****************************//
    public static void modificarUsuario(Usuario us, int cod){
        int pos = ListUsuario.buscarXcodigo(cod);
        ListUsuario.modificar(us, pos);    
        
    } 
    public static void eliminarUsuario(int cod){
         int pos = ListUsuario.buscarXcodigo(cod);
         ListUsuario.consultar().Eliminar(pos);
         ListUsuario.saveLista();
    }
    public static Usuario  buscarUsuario(int cod){
        int pos = ListUsuario.buscarXcodigo(cod);
        return (Usuario) ListUsuario.consultar().Buscar(pos);
    }
  //*****************************************************************************//   
  //adicionales**********************
   private int obtenerCodigo(){
       Usuario us = null;
       if(ListUsuario.consultar().tamaño() == 0)return 0;
       us =(Usuario) ListUsuario.consultar().Buscar(ListUsuario.consultar().tamaño()-1);
       if(us!=null)return us.getCodigo();       
        return 0;
   }

}
