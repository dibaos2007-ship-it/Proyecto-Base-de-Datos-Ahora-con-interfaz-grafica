package principal;

import javax.swing.*;
import dao.PlanetaDAO;
import dao.impl.PlanetaDAOImpl;
import modelo.Planeta;
import java.awt.*;
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
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setTitle("Gestion de Planetas");
        setSize(850, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel1 = new JPanel(new BorderLayout(10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelIzquierdo = new JPanel(new GridLayout(8, 2, 5, 8));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Datos del Planeta"));
        panelIzquierdo.setPreferredSize(new Dimension(480, 0));

        panelIzquierdo.add(new JLabel("ID:"));
        txtidTextField = new JTextField();
        panelIzquierdo.add(txtidTextField);

        panelIzquierdo.add(new JLabel("Nombre:"));
        txtNombreTextField = new JTextField();
        panelIzquierdo.add(txtNombreTextField);

        panelIzquierdo.add(new JLabel("Galaxia:"));
        txtGalaxiaTextField = new JTextField();
        panelIzquierdo.add(txtGalaxiaTextField);

        panelIzquierdo.add(new JLabel("Tipo Planeta:"));
        txtTipoTextField = new JTextField();
        panelIzquierdo.add(txtTipoTextField);

        panel1.add(panelIzquierdo, BorderLayout.WEST);

        panelDerecho = new JPanel(new GridLayout(5, 1, 10, 12));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        panelDerecho.setPreferredSize(new Dimension(220, 0));

        btnBuscarPorId = new JButton("Buscar por ID");
        btnVerTodos = new JButton("Ver Todos");
        btnAgregar = new JButton("Agregar Nuevo");
        btnFiltrarGalaxia = new JButton("Filtrar por Galaxia");
        BtnFiltrarVida = new JButton("Filtrar por Vida");

        panelDerecho.add(btnBuscarPorId);
        panelDerecho.add(btnVerTodos);
        panelDerecho.add(btnAgregar);
        panelDerecho.add(btnFiltrarGalaxia);
        panelDerecho.add(BtnFiltrarVida);

        panel1.add(panelDerecho, BorderLayout.EAST);

        panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Resultados"));

        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(textArea1);
        scrollPane.setPreferredSize(new Dimension(0, 110));
        panelInferior.add(scrollPane, BorderLayout.CENTER);

        panel1.add(panelInferior, BorderLayout.SOUTH);

        setContentPane(panel1);
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
                    textArea1.append("Tipo: " + encontrado.getTipoPlaneta() + "\n");
                    textArea1.append("Descubierto: " + encontrado.getFechaDeDescubierto() + "\n");
                    textArea1.append("Satelites: " + encontrado.getNumeroDeSatelitesNaturales() + "\n");
                    textArea1.append("Anillos: " + (encontrado.isSistemaDeAnillos() ? "Si" : "No") + "\n");
                    textArea1.append("Posible Vida: " + (encontrado.isPodriaContenerVida() ? "Si" : "No") + "\n");
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
                nuevo.setTipoPlaneta(tipo);

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
            Object[] opciones = {"Si", "No"};
            int seleccion = JOptionPane.showOptionDialog(null,
                    "Selecciona si tiene posible vida:",
                    "Filtrar por Vida",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (seleccion != -1) {
                boolean buscarVida = (seleccion == 0);
                PlanetaDAO dao = new PlanetaDAOImpl();
                List<Planeta> lista = dao.filtrarPorPosibleVida(buscarVida);

                if (lista.isEmpty()) {
                    textArea1.setText("No hay planetas con esa caracteristica.");
                } else {
                    textArea1.setText("PLANETAS (Posible vida = " + (buscarVida ? "Si" : "No") + "):\n");
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