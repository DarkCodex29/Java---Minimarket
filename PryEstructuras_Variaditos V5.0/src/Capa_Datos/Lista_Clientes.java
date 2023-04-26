/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import Capa_Logica.Cliente;
import ListasAux.ListaEnlazada;
/**
 *
 * @author Gianx
 */
public class Lista_Clientes {
       //   ENLAZA PORQUE SE VA A RECIBIR VARIOS PRODUCTOS
   //  Y SE VA A MANEJAR VARIOS DATOS.
    private static final String namelista = "Lista_Clientes";
    private static ListaEnlazada lista =  new ListaEnlazada();
    public static void  obtainLista(){
      lista = ArchivoBaseDate.ObtenerLista(namelista);   
    }
     public static void saveLista(){
        ArchivoBaseDate.GuardarLista(lista,namelista);
    }
    public static void adicionar(Object C){
    lista.agregar(C);
     //update list
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
    
   public static int buscarCliente(String dni) {
        for (int i = 0; i < lista.tamaño(); i++) {
            Cliente obj = (Cliente) lista.Buscar(i);
            if (obj.getDNI().equalsIgnoreCase(dni)) {
                return i;
            }
        }
        return -1;
    } 
   
    public static Cliente buscarClienteLista(String dni) {
    Cliente objC= new Cliente();
        for (int i = 0; i < lista.tamaño(); i++) {
            Cliente obj = (Cliente) lista.Buscar(i);
            if (obj.getDNI().equalsIgnoreCase(dni)) {
                objC=obj;
            }
        }
        return objC; 
    } 
}
