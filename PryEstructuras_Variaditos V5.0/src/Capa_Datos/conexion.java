/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gianx
 */
public class conexion {
  
 //private final String url = "jdbc:mysql://localhost/matricula?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
    
   // PreparedStatement psPrepararSentencia;          
    //Connection con = null;          
    
    
   //static String bd = "matricula";
    
    
    //El método getConnection de la clase DriverManager
    //necesita 3 argumentos:
    //url, user y password 
    static String url = "jdbc:mysql://localhost:3306/ubigeo_peru";
    static String user = "root";
    static String password = "*********";
 
    //static String user = "root";
    //static String password = "";
    
    static public Connection cnx = null;
    //Para hacer las consultas
    static public Statement st = null;
    //Para recibir las consultas
    static public ResultSet rs = null;
    
    
    //Para realizar consultas preparadas
    static public PreparedStatement pst = null;

    public conexion() {
     try{   
         
         Class.forName("com.mysql.cj.jdbc.Driver");      
         cnx = DriverManager.getConnection(url,user,password);    

        if (cnx!=null){                       
            System.out.println("Conexión a base de datos funcionando");                
         }
      }
         catch(SQLException e)        
         {
         System.out.println(e);          
         }
         catch(ClassNotFoundException e)       
         {
          System.out.println(e);               
         }
    }
    
 public Connection conectado(){  
      return cnx;
}

    public void desconectar(){     //Por seguridad, cuando terminemos sentencias, cerramos la conexion o si la necesitamos cerrar por otro caso
      cnx = null;                  //Ahora de nuevo con sera null
      System.out.println("La conexion la BD se ha cerrado");

    } 

   
   
}

