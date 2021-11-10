
package com.emergente.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
     static String driver="com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_eventos";
    static String usuario ="root";
    static String password = "";
    
    Connection conn = null;
    
    public ConexionDB() throws SQLException{
        try{
            //especificacion del driver
            Class.forName(driver);
            //establece la conexion a la basede datos
            conn=DriverManager.getConnection(url, usuario , password);
            //Verificar si la conexion fue exitosa
            if(conn !=null){
                System.out.println("Conexion OK:  "+ conn);
            }
        } catch (ClassNotFoundException ex){
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

public void desconectar(){
    try{
        conn.close();
    }catch (SQLException ex){
        Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE,null,ex);
    }
    
}

    public Connection conectar() {
    return conn;    
    }
}
