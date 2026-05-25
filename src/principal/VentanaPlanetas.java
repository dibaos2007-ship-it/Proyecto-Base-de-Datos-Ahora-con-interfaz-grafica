package principal;

import javax.swing.*;
import dao.PlanetaDAO;
import dao.impl.PlanetaDAOImpl;
import modelo.Planeta;
import java.util.List;

public class VentanaPlanetas extends JFrame {
    private JPanel panel1;
    private JPanel panelIzquierdo;
    private JTextField txtidTextField;
    private JTextField txtNombreTextField;
    private JTextField txtGalaxiaTextField;
    private JTextField txtTipoTextField;
    private JPanel panelDerecho;
    private JButton btnBuscarPorId;
    private JButton btnVerTodos;
    private JButton btnAgregar;
    private JButton btnFiltrarGalaxia;
    private JButton BtnFiltrarVida;
    private JPanel panelInferior;
    private JTextArea textArea1;

    public VentanaPlanetas() {
        setTitle("Gestion de Planetas");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panel1);
        configurarEventos();
    }

    private void configurarEventos() {
        btnBuscarPorId.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtidTextField.getText());
                PlanetaDAO dao = new PlanetaDAOImpl();
                Planeta encontrado = dao.consultarPorId(id);

                if (encontrado != null) {
                    textArea1.setText("PLANETA ENCONTRADO:\n");
                    textArea1.append("ID: " + encontrado.getId() + "\n");
                    textArea1.append("Nombre: " + encontrado.getNombre() + "\n");
                    textArea1.append("Galaxia: " + encontrado.getGalaxia() + "\n");
                    textArea1.append("Tipo: " + encontrado.getTipo() + "\n");
                    textArea1.append("Satelites: " + encontrado.getNumeroSatelites() + "\n");
                    textArea1.append("Tiene anillos: " + (encontrado.isTieneAnillos() ? "Si" : "No") + "\n");
                } else {
                    textArea1.setText("No existe ningun planeta con ese ID.");
                }
            } catch (NumberFormatException ex) {
                textArea1.setText("Error: Escribe solo numeros en el ID.");
            }
        });

        btnVerTodos.addActionListener(e -> {
            PlanetaDAO dao = new PlanetaDAOImpl();
            List<Planeta> lista = dao.consultarTodos();

            if (lista.isEmpty()) {
                textArea1.setText("No hay planetas registrados en la base de datos.");
            } else {
                textArea1.setText("LISTA COMPLETA DE PLANETAS:\n");
                for (Planeta p : lista) {
                    textArea1.append("ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Galaxia: " + p.getGalaxia() + "\n");
                }
            }
        });

        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombreTextField.getText();
                String galaxia = txtGalaxiaTextField.getText();
                String tipo = txtTipoTextField.getText();

                Planeta nuevo = new Planeta();
                nuevo.setNombre(nombre);
                nuevo.setGalaxia(galaxia);
                nuevo.setTipo(tipo);

                PlanetaDAO dao = new PlanetaDAOImpl();
                if (dao.agregar(nuevo)) {
                    textArea1.setText("Planeta agregado correctamente.");
                    limpiarCampos();
                } else {
                    textArea1.setText("Error: No se pudo guardar el planeta.");
                }
            } catch (Exception ex) {
                textArea1.setText("Error: Revisa que los datos esten bien escritos.");
            }
        });

        btnFiltrarGalaxia.addActionListener(e -> {
            String galaxiaBuscada = JOptionPane.showInputDialog("Escribe el nombre de la galaxia:");

            if (galaxiaBuscada != null && !galaxiaBuscada.isEmpty()) {
                PlanetaDAO dao = new PlanetaDAOImpl();
                List<Planeta> lista = dao.filtrarPorGalaxia(galaxiaBuscada);

                if (lista.isEmpty()) {
                    textArea1.setText("No hay planetas en esa galaxia.");
                } else {
                    textArea1.setText("PLANETAS EN " + galaxiaBuscada.toUpperCase() + ":\n");
                    for (Planeta p : lista) {
                        textArea1.append("- " + p.getNombre() + "\n");
                    }
                }
            }
        });

        BtnFiltrarVida.addActionListener(e -> {
            String respuesta = JOptionPane.showInputDialog("Con posible vida? (true / false):");

            if (respuesta != null && !respuesta.isEmpty()) {
                boolean buscarVida = Boolean.parseBoolean(respuesta);
                PlanetaDAO dao = new PlanetaDAOImpl();
                List<Planeta> lista = dao.filtrarPorPosibleVida(buscarVida);

                if (lista.isEmpty()) {
                    textArea1.setText("No hay planetas con esa caracteristica.");
                } else {
                    textArea1.setText("PLANETAS (Posible vida = " + buscarVida + "):\n");
                    for (Planeta p : lista) {
                        textArea1.append("- " + p.getNombre() + " | " + p.getGalaxia() + "\n");
                    }
                }
            }
        });
    }

    private void limpiarCampos() {
        txtidTextField.setText("");
        txtNombreTextField.setText("");
        txtGalaxiaTextField.setText("");
        txtTipoTextField.setText("");
    }
}