package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarUnoListener implements ActionListener {
    public final InterPlanetas ventana;

    public ConsultarUnoListener(InterPlanetas ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String textoId = ventana.id.getText().trim();
            if (textoId.isEmpty()) {
                ventana.resultados.setText("ESCRIBE UN ID PARA BUSCAR");
                return;
            }

            int codigo = Integer.parseInt(textoId);
            Connection con = DriverManager.getConnection(InterPlanetas.URL, InterPlanetas.USUARIO, InterPlanetas.CLAVE);

            String sql = "SELECT * FROM planetas WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder("=== DATOS DEL PLANETA ===\n\n");
            if (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id")).append("\n");
                sb.append("Nombre: ").append(rs.getString("nombre")).append("\n");
                sb.append("Descubierto: ").append(rs.getDate("fecha_descubierto")).append("\n");
                sb.append("Tipo: ").append(rs.getString("tipo_planeta")).append("\n");
                sb.append("Satélites: ").append(rs.getInt("numero_satelites")).append("\n");
                sb.append("Tiene Anillos: ").append(rs.getBoolean("sistema_anillos") ? "SI" : "NO").append("\n");
                sb.append("Galaxia: ").append(rs.getString("galaxia")).append("\n");
                sb.append("Puede tener Vida: ").append(rs.getBoolean("puede_tener_vida") ? "SI" : "NO").append("\n");
                sb.append("Temperatura: ").append(rs.getInt("temperatura_media")).append("°C\n");
                sb.append("Periodo Orbital: ").append(rs.getDouble("periodo_orbital")).append(" días\n");
                sb.append("Distancia: ").append(rs.getDouble("anos_luz_tierra")).append(" años luz\n");
            } else {
                sb.append("NO EXISTE ESE ID");
            }

            ventana.contenidoReporte = sb.toString();
            ventana.resultados.setText(ventana.contenidoReporte);
            rs.close();
            ps.close();
            con.close();
        } catch (NumberFormatException ex) {
            ventana.resultados.setText("SOLO NÚMEROS EN EL ID");
        } catch (Exception ex) {
            ventana.resultados.setText("ERROR: " + ex.getMessage());
        }
    }
}//mnljkn