package markobe.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion(){
        Connection miConexion = null;

        var baseDatos = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        //var password = "admin";

        //Cargamos la clase del driver de mysql en memoria
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            miConexion = DriverManager.getConnection(url, usuario, "");

        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Ocurrio un error en la conexion: " + e);
        }

        return miConexion;
    }

}
