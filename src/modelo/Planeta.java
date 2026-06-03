package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Planeta {
    // Atributos sgerhtrjyfvffrgf
    private String nombre;
    private int id;
    private LocalDate fechaDescubierto;
    private String tipoPlaneta;
    private int numeroSatelites;
    private boolean sistemaDeAnillos;
    private String galaxia;
    private boolean podriaContenerVida;
    private int temperaturaMedia;
    private double periodoOrbital;
    private double aniosLuzTierra;
    private LocalDateTime registro;

    // Constructor
    public Planeta() {}

    // Constructor con todos los campos
    public Planeta(String nombre, int id, LocalDate fechaDescubierto, String tipoPlaneta,
                   int numeroSatelites, boolean sistemaDeAnillos, String galaxia,
                   boolean podriaContenerVida, int temperaturaMedia, double periodoOrbital,
                   double aniosLuzTierra) {
        this.nombre = nombre;
        this.id = id;
        this.fechaDescubierto = fechaDescubierto;
        this.tipoPlaneta = tipoPlaneta;
        this.numeroSatelites = numeroSatelites;
        this.sistemaDeAnillos = sistemaDeAnillos;
        this.galaxia = galaxia;
        this.podriaContenerVida = podriaContenerVida;
        this.temperaturaMedia = temperaturaMedia;
        this.periodoOrbital = periodoOrbital;
        this.aniosLuzTierra = aniosLuzTierra;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFechaDescubierto() { return fechaDescubierto; }
    public void setFechaDescubierto(LocalDate fechaDescubierto) { this.fechaDescubierto = fechaDescubierto; }

    public String getTipoPlaneta() { return tipoPlaneta; }
    public void setTipoPlaneta(String tipoPlaneta) { this.tipoPlaneta = tipoPlaneta; }

    public int getNumeroSatelites() { return numeroSatelites; }
    public void setNumeroSatelites(int numeroSatelites) { this.numeroSatelites = numeroSatelites; }

    public boolean isSistemaDeAnillos() { return sistemaDeAnillos; }
    public void setSistemaDeAnillos(boolean sistemaDeAnillos) { this.sistemaDeAnillos = sistemaDeAnillos; }

    public String getGalaxia() { return galaxia; }
    public void setGalaxia(String galaxia) { this.galaxia = galaxia; }

    public boolean isPodriaContenerVida() { return podriaContenerVida; }
    public void setPodriaContenerVida(boolean podriaContenerVida) { this.podriaContenerVida = podriaContenerVida; }

    public int getTemperaturaMedia() { return temperaturaMedia; }
    public void setTemperaturaMedia(int temperaturaMedia) { this.temperaturaMedia = temperaturaMedia; }

    public double getPeriodoOrbital() { return periodoOrbital; }
    public void setPeriodoOrbital(double periodoOrbital) { this.periodoOrbital = periodoOrbital; }

    public double getAniosLuzTierra() { return aniosLuzTierra; }
    public void setAniosLuzTierra(double aniosLuzTierra) { this.aniosLuzTierra = aniosLuzTierra; }

    public LocalDateTime getRegistro() { return registro; }
    public void setRegistro(LocalDateTime registro) { this.registro = registro; }

   //mostrar la información formateada
    @Override
    public String toString() {
        return "=====================================" +
                "\nNombre: " + nombre +
                "\nID: " + id +
                "\nFecha de descubrimiento: " + fechaDescubierto +
                "\nTipo: " + tipoPlaneta +
                "\nNúmero de satélites: " + numeroSatelites +
                "\nTiene sistema de anillos: " + (sistemaDeAnillos ? "Sí" : "No") +
                "\nGalaxia: " + galaxia +
                "\n¿Podría contener vida? " + (podriaContenerVida ? "Sí" : "No") +
                "\nTemperatura media: " + temperaturaMedia + " °C" +
                "\nPeriodo orbital: " + periodoOrbital + " días" +
                "\nDistancia desde la Tierra: " + aniosLuzTierra + " años luz" +
                "\nFecha de registro: " + registro +
                "\n=====================================";
    }
}//IUHJBIKBG