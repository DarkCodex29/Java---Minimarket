/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.Proveedor;
import ListasAux.ListaEnlazada;


/**
 *
 * @author Gianx
 */
public class Lista_Compras {
      //   ENLAZA PORQUE SE VA A RECIBIR VARIOS PRODUCTOS
   //  Y SE VA A MANEJAR VARIOS DATOS.
    private static final String namelista = "Lista_Compras";
    private static ListaEnlazada lista =  new ListaEnlazada();
      
    public static void  obtainLista(){
      lista = ArchivoBaseDate.ObtenerLista(namelista);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(lista,namelista);
    }
    public static void adicionar(Object C){
    lista.agregar(C);
    saveLista();
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
    
     public static int buscarProveedor(String ruc) {
        for (int i = 0; i < lista.tamaño(); i++) {
            Proveedor obj = (Proveedor) lista.Buscar(i);
            if (obj.getRUC().equalsIgnoreCase(ruc)) {
                return i;
            }
        }
        return -1;
    } 
     
     public static int Cantcompras(){
        return lista.tamaño();
    }
}
