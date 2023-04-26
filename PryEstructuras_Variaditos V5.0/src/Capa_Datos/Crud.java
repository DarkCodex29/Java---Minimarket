
package Capa_Datos;

import Capa_Logica.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gianx
 */
public class Crud {
    
    
    
    public static int registrarCliente(Cliente cdb){
    Connection con = null;
    PreparedStatement ps;    
        conexion BD = new conexion();
        ps = null;
        con = BD.conectado();        
        String sql = "INSERT INTO estudiante (nombre,apellidos, dni, sexo, telefono, correo, fnac, dist, direccion, estado) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?)";        
        
       
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cdb.getNombre());
            ps.setString(2, cdb.getApellidos());
            ps.setString(3, cdb.getDNI());
            ps.setString(4, cdb.getSexo());
            ps.setString(5, cdb.getCelular());
            ps.setString(6, cdb.getCorreo());
            ps.setDate(7, null);
            ps.setString(8, cdb.getDist());
            ps.setString(9, cdb.getDireccion());
            ps.setInt(10, cdb.getEstado());
            ps.execute();            
            return 1;            
        } catch (Exception e) {
            System.err.println(e);
            return 0;
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
          
    }

   
public static ArrayList<Object[]> ListarCliente(){
        Connection con = null;
        PreparedStatement ps;  
        ResultSet rs = null;
        conexion BD = new conexion();
        ps = null;
        con = BD.conectado();  
       
        String sql = "SELECT * FROM estudiante";
        ArrayList<Object[]> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();            
            while (rs.next()) {
                Object[] filas = new Object[10];
                for(int i = 0;i<10;i++){
                    filas[i]= rs.getObject(i+1);
                }
                datos.add(filas);
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;      
        
    }

public static ArrayList<Object[]> ListarClienteSexo(String sexo){
        Connection con = null;
        PreparedStatement ps;  
        ResultSet rs = null;
        conexion BD = new conexion();
        ps = null;
        con = BD.conectado();  
       
        String sql = "SELECT * FROM estudiante WHERE sexo = '"+sexo+"'";;
        ArrayList<Object[]> datos = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();            
            while (rs.next()) {
                Object[] filas = new Object[10];
                for(int i = 0;i<10;i++){
                    filas[i]= rs.getObject(i+1);
                }
                datos.add(filas);
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;      
        
    }
}

