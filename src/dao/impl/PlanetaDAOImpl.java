package dao.impl;

import dao.PlanetaDAO;
import modelo.Planeta;
import conexion.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetaDAOImpl implements PlanetaDAO {

    @Override
    public List<Planeta> consultarTodos() {
        String sql = "SELECT * FROM planetas";
        List<Planeta> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Planeta p = new Planeta();
                p.setNombre(rs.getString(1));
                p.setId(rs.getInt(2));
                p.setFechaDeDescubierto(rs.getString(3));
                p.setTipoPlaneta(rs.getString(4));
                p.setNumeroDeSatelitesNaturales(rs.getInt(5));
                p.setSistemaDeAnillos(rs.getBoolean(6));
                p.setGalaxia(rs.getString(7));
                p.setPodriaContenerVida(rs.getBoolean(8));
                p.setTemperaturaMedia(rs.getInt(9));
                p.setPeriodoOrbital(rs.getDouble(10));
                p.setAnosLuzDeLaTierra(rs.getDouble(11));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return lista;
    }

    @Override public Planeta consultarPorId(int id) { return null; }
    @Override public boolean agregar(Planeta planeta) { return false; }
    @Override public List<Planeta> filtrarPorGalaxia(String galaxia) { return null; }
    @Override public List<Planeta> filtrarPorPosibleVida(boolean tieneVida) { return null; }
}