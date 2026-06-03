package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarTodosListener implements ActionListener {
    public final InterPlanetas ventana;

    public ConsultarTodosListener(InterPlanetas ventana) {
        this.ventana = ventana;
    }
//KJBJ
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DriverManager.getConnection(InterPlanetas.URL, InterPlanetas.USUARIO, InterPlanetas.CLAVE);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, nombre, tipo_planeta, galaxia FROM planetas");

            StringBuilder sb = new StringBuilder("=== TODOS LOS PLANETAS ===\n\n");
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(" | Nombre: ").append(rs.getString("nombre"))
                        .append(" | Tipo: ").append(rs.getString("tipo_planeta"))
                        .append(" | Galaxia: ").append(rs.getString("galaxia")).append("\n");
            }

            ventana.contenidoReporte = sb.toString();
            ventana.resultados.setText(ventana.contenidoReporte);
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ventana.resultados.setText("ERROR: " + ex.getMessage());
        }
    }
}