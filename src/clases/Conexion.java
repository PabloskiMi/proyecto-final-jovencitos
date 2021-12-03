package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion() {

        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=proyectoautos;"
                + "user=pabloski;"
                + "password=password;"
                + "loginTimeout=30;";

        try {

            Connection con = DriverManager.getConnection(conexionUrl);
            return con;

        } catch (SQLException ex) {

            System.out.println(ex.toString());
            return null;
        }

    }

}
