/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Capa_Datos;

import ListasAux.ListaEnlazada;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 */
public class ArchivoBaseDate {
    public static void GuardarLista(ListasAux.ListaEnlazada lista, String nameLista) {
        try {
            FileOutputStream flujoSalida = new FileOutputStream(nameLista + ".bin");
            ObjectOutputStream flujo = new ObjectOutputStream(flujoSalida);
            for (int i = 0; i < lista.tamaño(); i++) {
                Object objeto = lista.Buscar(i);
                flujo.writeObject(objeto);
            }
            flujo.close();
            flujoSalida.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Error"+ex);
        } catch (IOException ex) {
        System.err.println("Error"+ex);
        }
    }

    public static ListasAux.ListaEnlazada ObtenerLista(String nameLista) {
       ListasAux.ListaEnlazada obj = new ListaEnlazada();
        try {
            FileInputStream flujoEntrada = new FileInputStream(nameLista + ".bin");
            ObjectInputStream flujo = new ObjectInputStream(flujoEntrada);
            while (true) {
                Object objProyecto = flujo.readObject();
                obj.agregar(objProyecto);
            }
        } catch (EOFException ex) {
            return obj;

        } catch (FileNotFoundException ex) {
          System.err.println("Error"+ex);
        } catch (IOException ex) {
          System.err.println("Error"+ex);
        } catch (ClassNotFoundException ex) {
           System.err.println("Error"+ex);
        }
        return obj;
    }
}
