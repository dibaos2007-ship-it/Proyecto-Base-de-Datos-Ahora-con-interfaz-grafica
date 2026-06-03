package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FiltrarListener implements ActionListener {
    public final InterPlanetas ventana;

    public FiltrarListener(InterPlanetas ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String tipoBuscar = ventana.tipoPlaneta.getText().trim();
            if (tipoBuscar.isBlank()) {
                ventana.resultados.setText("ESCRIBE UN TIPO PARA FILTRAR");
                return;
            }

            Connection con = DriverManager.getConnection(InterPlanetas.URL, InterPlanetas.USUARIO, InterPlanetas.CLAVE);

            String sql = "SELECT * FROM planetas WHERE tipo_planeta = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipoBuscar);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder("=== FILTRADO: " + tipoBuscar + " ===\n\n");
            while (rs.next()) {
                sb.append("• ").append(rs.getString("nombre")).append(" (Galaxia: ").append(rs.getString("galaxia")).append(")\n");
            }

            ventana.contenidoReporte = sb.toString();
            ventana.resultados.setText(ventana.contenidoReporte);
            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            ventana.resultados.setText("ERROR: " + ex.getMessage());
        }
    }
}