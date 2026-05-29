package principal;

import dao.PlanetaDAO;
import dao.impl.PlanetaDAOImpl;
import modelo.Planeta;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PlanetaDAO dao = new PlanetaDAOImpl();

    public static void main(String[] args) {
        System.out.println("SISTEMA DE GESTIÓN DE PLANETAS");


        mostrarMenuPrincipal();
//dvhgfhgtnvfv
        scanner.close();
    }



    private static void mostrarMenuPrincipal() {
        boolean activo = true;

        while (activo) {

            System.out.println("\n          MENU PRINCIPAL            ");
            System.out.println("1. Consultar planeta (por Nombre)");
            System.out.println("2. Consultar TODOS los planetas");
            System.out.println("3. Agregar NUEVO planeta");
            System.out.println("4. Filtrar planetas por criterio");
            System.out.println("5. Salir del sistema");
            System.out.print("Elige una opción: ");


            int opcion = leerOpcion();
            activo = ejecutarAccion(opcion);
        }
    }

    // ak hace el metodo de leer y que se pueda dsigitar
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Error: Ingresa un número válido.");
            return 0;
        }
    }

    // ak el metodo llama la accion correspondiente
    private static boolean ejecutarAccion(int opcion) {
        switch (opcion) {
            case 1 -> consultarPlanetaPorNombre();
            case 2 -> listarTodosLosPlanetas();
            case 3 -> agregarNuevoPlaneta();
            case 4 -> filtrarPlanetas();
            case 5 -> {
                System.out.println(" el sistema se cerro bye");
                return false;
            }
            default -> System.out.println(" Opción no válida presione entre 1 y 5.");
        }
        return true;
    }


    //  Consultar un planeta por nombre
    private static void consultarPlanetaPorNombre() {
        System.out.println("\n CONSULTAR PLANETA");
        System.out.print("Ingresa el NOMBRE del planeta: ");
        String nombre = scanner.nextLine();

        Planeta encontrado = dao.consultarPorNombre(nombre);
        if (encontrado != null) {
            System.out.println("\nPlaneta encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No existe ningún planeta con ese nombre.");
        }
    }


    //Listar todos los planetas
    private static void listarTodosLosPlanetas() {

        List<Planeta> lista = dao.consultarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay planetas registrados.");
        } else {

            System.out.printf("%-5s %-20s %-15s %-10s %-10s %-12s %-15s %-10s %-10s %-15s %-15s%n",
                    "ID", "NOMBRE", "TIPO", "SATÉLITES", "ANILLOS", "GALAXIA", "HABITABLE", "TEMP °C", "PERIODO", "DISTANCIA (a.l.)", "FECHA DESCUB.");

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


            for (Planeta p : lista) {
                // valores booleanos a texto
                String anillos = p.isSistemaDeAnillos() ? "SÍ" : "NO";
                String habitable = p.isPodriaContenerVida() ? "SÍ" : "NO";

                // Imprimimos cada dato en su columna
                System.out.printf("%-5d %-20s %-15s %-10d %-10s %-12s %-15s %-10d %-10.2f %-15.4f %-15s%n",
                        p.getId(),
                        p.getNombre(),
                        p.getTipoPlaneta(),
                        p.getNumeroSatelites(),
                        anillos,
                        p.getGalaxia(),
                        habitable,
                        p.getTemperaturaMedia(),
                        p.getPeriodoOrbital(),
                        p.getAniosLuzTierra(),
                        p.getFechaDescubierto().toString());
            }

            System.out.println(" Total: " + lista.size() + " planetas");
        }
    }

    // Agregar nuevo planeta
    private static void agregarNuevoPlaneta() {
        System.out.println("\nAGREGAR NUEVO PLANETA");
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("ID numérico: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Fecha de descubrimiento (AAAA-MM-DD): ");
            String fechaStr = scanner.nextLine();

            java.time.LocalDate fecha = java.time.LocalDate.parse(fechaStr);

            System.out.print("Tipo de planeta: ");
            String tipo = scanner.nextLine();

            System.out.print("Número de satélites: ");
            int satelites = Integer.parseInt(scanner.nextLine());

            System.out.print("¿Tiene sistema de anillos? (true/false): ");
            boolean anillos = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Galaxia: ");
            String galaxia = scanner.nextLine();

            System.out.print("¿Podría contener vida? (true/false): ");
            boolean vida = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Temperatura media (°C): ");
            int temperatura = Integer.parseInt(scanner.nextLine());

            System.out.print("Periodo orbital (días): ");
            double periodo = Double.parseDouble(scanner.nextLine());

            System.out.print("Distancia a la Tierra (años luz): ");
            double distancia = Double.parseDouble(scanner.nextLine());

            // Creamos el objeto y lo guardamos
            Planeta nuevo = new Planeta(nombre, id, fecha, tipo, satelites, anillos, galaxia, vida, temperatura, periodo, distancia);
            boolean guardado = dao.agregarPlaneta(nuevo);

            if (guardado) {
                System.out.println("Planeta agregado correctamente.");
            } else {
                System.out.println(" No se pudo guardar el planeta.");
            }

        } catch (Exception e) {
            System.out.println("Datos inválidos: " + e.getMessage());
            System.out.println("Usa el formato correcto (ej: fecha = 2000-01-01)");
        }
    }

    // Filtrar planetas
    private static void filtrarPlanetas() {
        System.out.println("\nFILTRAR PLANETAS");
        System.out.println("Criterios: tipo | galaxia | vida | anillos | satelites | temperatura | distancia");
        System.out.print("Escribe el criterio: ");
        String criterio = scanner.nextLine().toLowerCase();

        System.out.print("Escribe el valor a buscar: ");
        String valor = scanner.nextLine();

        List<Planeta> resultados = dao.filtrarPorCriterio(criterio, valor);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron coincidencias");
        } else {
            System.out.println("\n Resultados:");
            for (Planeta p : resultados) {
                System.out.println(p);
            }
        }
    }
}