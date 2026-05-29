package dao.impl;

import dao.PlanetaDAO;
import modelo.Planeta;
import conexion.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlanetaDAOImpl implements PlanetaDAO {

    // 1. CONSULTAR UN PLANETA POR NOMBRE (ya que es la clave primaria)
    @Override
    public Planeta consultarPorNombre(String nombrePlaneta) {
        // Usamos el nombre exacto del campo en la BD
        String sql = "SELECT * FROM planetas WHERE \"NOMBRE\" = ?";
        Planeta planeta = null;

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombrePlaneta);
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                planeta = new Planeta();
                // Asignamos cada valor con el nombre correcto de la columna
                planeta.setNombre(resultado.getString("NOMBRE"));
                planeta.setId(resultado.getInt("id"));

                // Convertimos DATE de la BD a LocalDate de Java
                Date fechaDesc = resultado.getDate("FECHA DE DESCUBIERTO");
                if (fechaDesc != null) {
                    planeta.setFechaDescubierto(fechaDesc.toLocalDate());
                }

                planeta.setTipoPlaneta(resultado.getString("TIPO PLANETA"));
                planeta.setNumeroSatelites(resultado.getInt("numero de satelites naturales"));
                planeta.setSistemaDeAnillos(resultado.getBoolean("Sistema de anillos"));
                planeta.setGalaxia(resultado.getString("galaxia"));
                planeta.setPodriaContenerVida(resultado.getBoolean("Podria contener vida"));
                planeta.setTemperaturaMedia(resultado.getInt("Temperatura media"));
                planeta.setPeriodoOrbital(resultado.getDouble("periodo orbital"));
                planeta.setAniosLuzTierra(resultado.getDouble("años luz de la tierra"));

                // Convertimos TIMESTAMP a LocalDateTime
                Timestamp registro = resultado.getTimestamp("REGISTRO");
                if (registro != null) {
                    planeta.setRegistro(registro.toLocalDateTime());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar planeta: " + e.getMessage());
        }
        return planeta;
    }


    // 2. CONSULTAR TODOS LOS PLANETAS
    @Override
    public List<Planeta> consultarTodos() {
        String sql = "SELECT * FROM planetas ORDER BY \"NOMBRE\" ASC";
        List<Planeta> listaPlanetas = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(sql)) {

            while (resultado.next()) {
                Planeta planeta = new Planeta();
                planeta.setNombre(resultado.getString("NOMBRE"));
                planeta.setId(resultado.getInt("id"));

                Date fechaDesc = resultado.getDate("FECHA DE DESCUBIERTO");
                if (fechaDesc != null) {
                    planeta.setFechaDescubierto(fechaDesc.toLocalDate());
                }

                planeta.setTipoPlaneta(resultado.getString("TIPO PLANETA"));
                planeta.setNumeroSatelites(resultado.getInt("numero de satelites naturales"));
                planeta.setSistemaDeAnillos(resultado.getBoolean("Sistema de anillos"));
                planeta.setGalaxia(resultado.getString("galaxia"));
                planeta.setPodriaContenerVida(resultado.getBoolean("Podria contener vida"));
                planeta.setTemperaturaMedia(resultado.getInt("Temperatura media"));
                planeta.setPeriodoOrbital(resultado.getDouble("periodo orbital"));
                planeta.setAniosLuzTierra(resultado.getDouble("años luz de la tierra"));

                Timestamp registro = resultado.getTimestamp("REGISTRO");
                if (registro != null) {
                    planeta.setRegistro(registro.toLocalDateTime());
                }

                listaPlanetas.add(planeta);
            }

        } catch (SQLException e) {
            System.out.println(" Error al listar planetas: " + e.getMessage());
        }
        return listaPlanetas;
    }


    // 3. AGREGAR UN NUEVO PLANETA
    @Override
    public boolean agregarPlaneta(Planeta nuevoPlaneta) {
        // El campo REGISTRO se genera automáticamente en la BD
        String sql = "INSERT INTO planetas (" +
                "\"NOMBRE\", id, \"FECHA DE DESCUBIERTO\", \"TIPO PLANETA\", " +
                "\"numero de satelites naturales\", \"Sistema de anillos\", galaxia, " +
                "\"Podria contener vida\", \"Temperatura media\", \"periodo orbital\", " +
                "\"años luz de la tierra\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            // Asignamos cada valor en el orden correcto
            pstmt.setString(1, nuevoPlaneta.getNombre());
            pstmt.setInt(2, nuevoPlaneta.getId());
            pstmt.setDate(3, Date.valueOf(nuevoPlaneta.getFechaDescubierto()));
            pstmt.setString(4, nuevoPlaneta.getTipoPlaneta());
            pstmt.setInt(5, nuevoPlaneta.getNumeroSatelites());
            pstmt.setBoolean(6, nuevoPlaneta.isSistemaDeAnillos());
            pstmt.setString(7, nuevoPlaneta.getGalaxia());
            pstmt.setBoolean(8, nuevoPlaneta.isPodriaContenerVida());
            pstmt.setInt(9, nuevoPlaneta.getTemperaturaMedia());
            pstmt.setDouble(10, nuevoPlaneta.getPeriodoOrbital());
            pstmt.setDouble(11, nuevoPlaneta.getAniosLuzTierra());


            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println(" Error al agregar planeta: " + e.getMessage());
            return false;
        }
    }


    // 4. FILTRAR PLANETAS POR CRITERIO
    @Override
    public List<Planeta> filtrarPorCriterio(String criterio, String valor) {
        // Mapeamos los criterios a los nombres reales de las columnas
        String columna = switch (criterio.toLowerCase()) {
            case "tipo" -> "\"TIPO PLANETA\"";
            case "galaxia" -> "galaxia";
            case "vida" -> "\"Podria contener vida\"";
            case "anillos" -> "\"Sistema de anillos\"";
            case "satelites" -> "\"numero de satelites naturales\"";
            case "temperatura" -> "\"Temperatura media\"";
            case "distancia" -> "\"años luz de la tierra\"";
            default -> "";
        };

        List<Planeta> resultado = new ArrayList<>();


        if (columna.isEmpty()) {
            System.out.println(" Criterios: tipo, galaxia, vida, anillos, satelites, temperatura, distancia");
            return resultado;
        }

        // Consulta SQL con el nombre de columna correcto
        String sql = "SELECT * FROM planetas WHERE " + columna + " = ? ORDER BY \"NOMBRE\"";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            // Asignamos el valor según el tipo de dato
            switch (criterio.toLowerCase()) {
                case "vida", "anillos" ->
                        pstmt.setBoolean(1, Boolean.parseBoolean(valor));
                case "satelites", "temperatura", "distancia" ->
                        pstmt.setDouble(1, Double.parseDouble(valor));
                default ->
                        pstmt.setString(1, valor);
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Planeta p = new Planeta();
                p.setNombre(rs.getString("NOMBRE"));
                p.setId(rs.getInt("id"));

                Date fechaDesc = rs.getDate("FECHA DE DESCUBIERTO");
                if (fechaDesc != null) {
                    p.setFechaDescubierto(fechaDesc.toLocalDate());
                }

                p.setTipoPlaneta(rs.getString("Tipo de planetaa"));
                p.setNumeroSatelites(rs.getInt("numero de satelites naturales"));
                p.setSistemaDeAnillos(rs.getBoolean("Sistema de anillos"));
                p.setGalaxia(rs.getString("galaxia"));
                p.setPodriaContenerVida(rs.getBoolean("Podria contener vida"));
                p.setTemperaturaMedia(rs.getInt("Temperatura media"));
                p.setPeriodoOrbital(rs.getDouble("periodo orbital"));
                p.setAniosLuzTierra(rs.getDouble("años luz de la tierra"));

                Timestamp registro = rs.getTimestamp("REGISTRO");
                if (registro != null) {
                    p.setRegistro(registro.toLocalDateTime());
                }

                resultado.add(p);
            }
//necesito que el src se vea en git hub
        } catch (SQLException | NumberFormatException e) {
            System.out.println(" Error al filtrar " + e.getMessage());
        }
        return resultado;
    }
}