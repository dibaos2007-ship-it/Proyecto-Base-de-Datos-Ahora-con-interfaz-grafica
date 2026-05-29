package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
//fdhtrjtyj
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    ConfigBD.URL,
                    ConfigBD.USUARIO,
                    ConfigBD.CONTRASEÑA
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Controlador no encontrado: " + e.getMessage());
        }
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}