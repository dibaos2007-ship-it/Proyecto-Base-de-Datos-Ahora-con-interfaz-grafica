package interfaz;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InterPlanetas {
    // COMPONENTES
    public JPanel panelPrincipal;

    public JLabel etq_id;
    public JLabel etq_nombre;
    public JLabel etq_fechaDescubierto;
    public JLabel etq_tipoPlaneta;
    public JLabel etq_numeroSatelites;
    public JLabel etq_sistemaDeAnillos;
    public JLabel etq_galaxia;
    public JLabel etq_podriaContenerVida;
    public JLabel etq_temperaturaMedia;
    public JLabel etq_periodoOrbital;
    public JLabel etq_aniosLuzTierra;

    public JTextField id;
    public JTextField nombre;
    public JTextField fechaDescubierto;
    public JTextField tipoPlaneta;
    public JTextField numeroSatelites;
    public JTextField sistemaDeAnillos;
    public JTextField galaxia;
    public JTextField podriaContenerVida;
    public JTextField temperaturaMedia;
    public JTextField periodoOrbital;
    public JTextField aniosLuzTierra;

    public JTextArea resultados;

    public JButton agregar;
    public JButton consultarUno;
    public JButton consultarTodos;
    public JButton filtrar;
    public JButton exportar;

    // DATOS DE CONEXIÓN
    public static final String URL = "jdbc:postgresql://ep-winter-paper-aqrrjk8y-pooler.c-8.us-east-1.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require";//mn,jmn
    public static final String USUARIO = "neondb_owner";
    public static final String CLAVE = "npg_ygEpOdj56UMl";

    public String contenidoReporte = "";

    public InterPlanetas() {
        crearInterfaz(); // ✅ Creamos todo aquí
        asignarEventos();
    }

    private void crearInterfaz() {
        panelPrincipal = new JPanel(new GridLayout(12, 2, 5, 5));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FILA 1
        etq_id = new JLabel("ID:");
        id = new JTextField(10);
        panelPrincipal.add(etq_id);
        panelPrincipal.add(id);

        // FILA 2
        etq_nombre = new JLabel("Nombre:");
        nombre = new JTextField(10);
        panelPrincipal.add(etq_nombre);
        panelPrincipal.add(nombre);

        // FILA 3
        etq_fechaDescubierto = new JLabel("Fecha Descubierto:");
        fechaDescubierto = new JTextField(10);
        panelPrincipal.add(etq_fechaDescubierto);
        panelPrincipal.add(fechaDescubierto);

        // FILA 4
        etq_tipoPlaneta = new JLabel("Tipo Planeta:");
        tipoPlaneta = new JTextField(10);
        panelPrincipal.add(etq_tipoPlaneta);
        panelPrincipal.add(tipoPlaneta);

        // FILA 5
        etq_numeroSatelites = new JLabel("Número Satélites:");
        numeroSatelites = new JTextField(10);
        panelPrincipal.add(etq_numeroSatelites);
        panelPrincipal.add(numeroSatelites);

        // FILA 6
        etq_sistemaDeAnillos = new JLabel("Tiene Anillos (true/false):");
        sistemaDeAnillos = new JTextField(10);
        panelPrincipal.add(etq_sistemaDeAnillos);
        panelPrincipal.add(sistemaDeAnillos);

        // FILA 7
        etq_galaxia = new JLabel("Galaxia:");
        galaxia = new JTextField(10);
        panelPrincipal.add(etq_galaxia);
        panelPrincipal.add(galaxia);

        // FILA 8
        etq_podriaContenerVida = new JLabel("Puede tener Vida (true/false):");
        podriaContenerVida = new JTextField(10);
        panelPrincipal.add(etq_podriaContenerVida);
        panelPrincipal.add(podriaContenerVida);

        // FILA 9
        etq_temperaturaMedia = new JLabel("Temperatura Media:");
        temperaturaMedia = new JTextField(10);
        panelPrincipal.add(etq_temperaturaMedia);
        panelPrincipal.add(temperaturaMedia);

        // FILA 10
        etq_periodoOrbital = new JLabel("Periodo Orbital:");
        periodoOrbital = new JTextField(10);
        panelPrincipal.add(etq_periodoOrbital);
        panelPrincipal.add(periodoOrbital);

        // FILA 11
        etq_aniosLuzTierra = new JLabel("Distancia Años Luz:");
        aniosLuzTierra = new JTextField(10);
        panelPrincipal.add(etq_aniosLuzTierra);
        panelPrincipal.add(aniosLuzTierra);

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout());
        agregar = new JButton("Agregar");
        consultarUno = new JButton("Consultar Uno");
        consultarTodos = new JButton("Consultar Todos");
        filtrar = new JButton("Filtrar");
        exportar = new JButton("Exportar TXT");

        panelBotones.add(agregar);
        panelBotones.add(consultarUno);
        panelBotones.add(consultarTodos);
        panelBotones.add(filtrar);
        panelBotones.add(exportar);

        // AREA DE TEXTO
        resultados = new JTextArea(10, 30);
        resultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultados);

        // ARMAR VENTANA
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(panelPrincipal, BorderLayout.NORTH);
        centro.add(panelBotones, BorderLayout.CENTER);
        centro.add(scroll, BorderLayout.SOUTH);

        panelPrincipal = centro;
    }

    private void asignarEventos() {
        agregar.addActionListener(new AgregarListener(this));
        consultarUno.addActionListener(new ConsultarUnoListener(this));
        consultarTodos.addActionListener(new ConsultarTodosListener(this));
        filtrar.addActionListener(new FiltrarListener(this));
        exportar.addActionListener(new ExportarListener(this));
    }

    public static void abrirVentana() {
        JFrame ventana = new JFrame("InterPlanetas");
        ventana.setContentPane(new InterPlanetas().panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}