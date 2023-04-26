
package Capa_Datos;

import Capa_Logica.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Gianx
 */
public class mysql {
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public static String insertarMysql(Object cdb){
        
        Cliente datos[] = new Cliente[13] ;
        
        datos = (Cliente[]) cdb;
        for (int i = 0; i < datos.length; i++) {
            System.out.println(datos[i]);
        }
        return "OK";
    }
}
