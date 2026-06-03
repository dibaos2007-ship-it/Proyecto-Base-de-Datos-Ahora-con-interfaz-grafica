package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AgregarListener implements ActionListener {
    public final InterPlanetas ventana;

    public AgregarListener(InterPlanetas ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //  PRIMERO VERIFICAMOS QUE NINGÚN CAMPO ESTÉ VACÍO
            if (ventana.id.getText().isBlank() ||
                    ventana.nombre.getText().isBlank() ||
                    ventana.fechaDescubierto.getText().isBlank() ||
                    ventana.tipoPlaneta.getText().isBlank() ||
                    ventana.numeroSatelites.getText().isBlank() ||
                    ventana.sistemaDeAnillos.getText().isBlank() ||
                    ventana.galaxia.getText().isBlank() ||
                    ventana.podriaContenerVida.getText().isBlank() ||
                    ventana.temperaturaMedia.getText().isBlank() ||
                    ventana.periodoOrbital.getText().isBlank() ||
                    ventana.aniosLuzTierra.getText().isBlank()) {

                ventana.resultados.setText(" LLENA TODOS LOS CAMPOS, NO DEJES NINGUNO VACÍO");
                return;
            }


            int codigo;
            try {
                codigo = Integer.parseInt(ventana.id.getText().trim());
            } catch (NumberFormatException ex) {
                ventana.resultados.setText(" ERROR: En ID escribe SOLO NÚMEROS ENTEROS");
                return;
            }


            String nom = ventana.nombre.getText().trim();


            LocalDate fecha;
            try {
                fecha = LocalDate.parse(ventana.fechaDescubierto.getText().trim());
            } catch (DateTimeParseException ex) {
                ventana.resultados.setText(" FORMATO FECHA INCORRECTO.\nESCRIBE ASÍ: AÑO-MES-DÍA\nEjemplo: 1111-12-12");
                return;
            }


            String tipo = ventana.tipoPlaneta.getText().trim();


            int satelites;
            try {
                satelites = Integer.parseInt(ventana.numeroSatelites.getText().trim());
            } catch (NumberFormatException ex) {
                ventana.resultados.setText(" ERROR: En Número Satélites escribe SOLO NÚMEROS");
                return;
            }


            boolean anillos;
            String textoAnillos = ventana.sistemaDeAnillos.getText().trim().toLowerCase();
            if (textoAnillos.equals("true") || textoAnillos.equals("false")) {
                anillos = Boolean.parseBoolean(textoAnillos);
            } else {
                ventana.resultados.setText(" ERROR: En Tiene Anillos escribe SOLAMENTE: true O false");
                return;
            }


            String gal = ventana.galaxia.getText().trim();


            boolean vida;
            String textoVida = ventana.podriaContenerVida.getText().trim().toLowerCase();
            if (textoVida.equals("true") || textoVida.equals("false")) {
                vida = Boolean.parseBoolean(textoVida);
            } else {
                ventana.resultados.setText(" ERROR: En Puede tener Vida escribe SOLAMENTE: true O false");
                return;
            }


            int temp;
            try {
                temp = Integer.parseInt(ventana.temperaturaMedia.getText().trim());
            } catch (NumberFormatException ex) {
                ventana.resultados.setText(" ERROR: Temperatura debe ser NÚMERO (puede ser negativo)");
                return;
            }

            double periodo;
            try {
                periodo = Double.parseDouble(ventana.periodoOrbital.getText().trim());
            } catch (NumberFormatException ex) {
                ventana.resultados.setText("ERROR: Periodo Orbital debe ser NÚMERO (ej: 365.25)");
                return;
            }


            double aniosLuz;
            try {
                aniosLuz = Double.parseDouble(ventana.aniosLuzTierra.getText().trim());
            } catch (NumberFormatException ex) {
                ventana.resultados.setText(" ERROR: Distancia debe ser NÚMERO");
                return;
            }

            LocalDateTime fechaRegistro = LocalDateTime.now();

            // 🔌 CONEXIÓN Y ENVÍO
            Connection con = DriverManager.getConnection(InterPlanetas.URL, InterPlanetas.USUARIO, InterPlanetas.CLAVE);
            String sql = "INSERT INTO planetas (id, nombre, fecha_descubierto, tipo_planeta, numero_satelites, sistema_anillos, galaxia, puede_tener_vida, temperatura_media, periodo_orbital, anos_luz_tierra, registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, codigo);
            ps.setString(2, nom);
            ps.setDate(3, java.sql.Date.valueOf(fecha));
            ps.setString(4, tipo);
            ps.setInt(5, satelites);
            ps.setBoolean(6, anillos);
            ps.setString(7, gal);
            ps.setBoolean(8, vida);
            ps.setInt(9, temp);
            ps.setDouble(10, periodo);
            ps.setDouble(11, aniosLuz);
            ps.setTimestamp(12, java.sql.Timestamp.valueOf(fechaRegistro));

            ps.executeUpdate();
            ventana.resultados.setText(" PLANETA AGREGADO CORRECTAMENTE");
            ventana.contenidoReporte = ventana.resultados.getText();

            ps.close();
            con.close();

        } catch (Exception ex) {
            ventana.resultados.setText("  ERRORGENERAL: " + ex.getMessage());
        }
    }
}