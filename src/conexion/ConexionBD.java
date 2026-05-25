package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://ep-winter-paper-aqrrjk8y-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require";
    private static final String USUARIO = "neondb_owner";
    private static final String CONTRASENA = "npq_yqEp8djS6UML";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}