/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.Venta;
import ListasAux.ListaEnlazada;

/**
 *
 * @author Gianx
 */
public class Lista_Ventas {
    private static String nameLista = "Lista_ventas";
   private static ListaEnlazada objetos = new ListaEnlazada();
    public static void adicionar(Object C) {
        objetos.agregar(C);
      saveLista();
    }
    public static void  obtainLista(){
      objetos = ArchivoBaseDate.ObtenerLista(nameLista);
        
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(objetos,nameLista);
    }
    public static ListaEnlazada obtener() {
        return objetos;
    }
    
    public static Object obtener(int pos) {
        return objetos.Buscar(pos);
    }
    

    public static void eliminar(int pos)
    {
        objetos.Eliminar(pos);
        saveLista();
    }
    
    public static void modificar(Object C , int pos){
        objetos.Modificar(C, pos);
        saveLista();
    }
        public static Venta buscarVenta(String codVenta){
        for (int i = 0; i <  objetos.tamaño(); i++) {
            Venta obVenta =  (Venta) objetos.Buscar(i);
            if(obVenta.getCodVenta().equalsIgnoreCase(codVenta)){
                 return obVenta;
            }
        }
      return null;
    }
    
}
