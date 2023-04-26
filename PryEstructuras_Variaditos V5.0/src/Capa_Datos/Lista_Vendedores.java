/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.Vendedor;
import ListasAux.ListaEnlazada;
/**
 *
 * @author Gianx
 */
public class Lista_Vendedores {
    private final static String nameList  ="Lista_vendedores";
    private static ListaEnlazada objetos = new ListaEnlazada();

    public static void  obtainLista(){
      objetos = ArchivoBaseDate.ObtenerLista(nameList);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(objetos,nameList);
    }
    public static void adicionar(Object C) {
        objetos.agregar(C);
      saveLista();
    }

    public static ListaEnlazada consultar() {
        return objetos;
    }
    
    public static void eliminar(int i){
        objetos.Eliminar(i);
        saveLista();
    }
    
    public static void modificar(Object C, int i){
        objetos.Modificar(C, i);
     saveLista();
    }
    
   public static int buscarVendedor(String dni) {
        for (int i = 0; i < objetos.tamaño(); i++) {
            Vendedor obj = (Vendedor) objetos.Buscar(i);
            if (obj.getDNI().equalsIgnoreCase(dni)) {
                return i;
            }
        }
        return -1;
    } 
   
        public static Vendedor buscarVendedorLista(String dni) {
    Vendedor objC= new Vendedor();
        for (int i = 0; i < objetos.tamaño(); i++) {
            Vendedor obj = (Vendedor) objetos.Buscar(i);
            if (obj.getDNI().equalsIgnoreCase(dni)) {
                objC=obj;
            }
        }
        return objC; 
    }
    
}
