package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ExportarListener implements ActionListener {
    public final InterPlanetas ventana;

    public ExportarListener(InterPlanetas ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileWriter archivo = new FileWriter("reporte_planetas.txt");
            archivo.write(ventana.contenidoReporte);
            archivo.close();
            ventana.resultados.setText("REPORTE EXPORTADO CORRECTAMENTE");
        } catch (IOException ex) {
            ventana.resultados.setText("ERROR AL EXPORTAR: " + ex.getMessage());
        }
    }
}