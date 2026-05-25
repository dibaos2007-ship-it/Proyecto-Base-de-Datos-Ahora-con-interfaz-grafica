package modelo;

public class Planeta {
    private String nombre;
    private int id;
    private String fechaDeDescubierto;
    private String tipoPlaneta;
    private int numeroDeSatelitesNaturales;
    private boolean sistemaDeAnillos;
    private String galaxia;
    private boolean podriaContenerVida;
    private int temperaturaMedia;
    private double periodoOrbital;
    private double anosLuzDeLaTierra;

    public Planeta() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaDeDescubierto() {
        return fechaDeDescubierto;
    }

    public void setFechaDeDescubierto(String fechaDeDescubierto) {
        this.fechaDeDescubierto = fechaDeDescubierto;
    }

    public String getTipoPlaneta() {
        return tipoPlaneta;
    }

    public void setTipoPlaneta(String tipoPlaneta) {
        this.tipoPlaneta = tipoPlaneta;
    }

    public int getNumeroDeSatelitesNaturales() {
        return numeroDeSatelitesNaturales;
    }

    public void setNumeroDeSatelitesNaturales(int numeroDeSatelitesNaturales) {
        this.numeroDeSatelitesNaturales = numeroDeSatelitesNaturales;
    }

    public boolean isSistemaDeAnillos() {
        return sistemaDeAnillos;
    }

    public void setSistemaDeAnillos(boolean sistemaDeAnillos) {
        this.sistemaDeAnillos = sistemaDeAnillos;
    }

    public String getGalaxia() {
        return galaxia;
    }

    public void setGalaxia(String galaxia) {
        this.galaxia = galaxia;
    }

    public boolean isPodriaContenerVida() {
        return podriaContenerVida;
    }

    public void setPodriaContenerVida(boolean podriaContenerVida) {
        this.podriaContenerVida = podriaContenerVida;
    }

    public int getTemperaturaMedia() {
        return temperaturaMedia;
    }

    public void setTemperaturaMedia(int temperaturaMedia) {
        this.temperaturaMedia = temperaturaMedia;
    }

    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    public void setPeriodoOrbital(double periodoOrbital) {
        this.periodoOrbital = periodoOrbital;
    }

    public double getAnosLuzDeLaTierra() {
        return anosLuzDeLaTierra;
    }

    public void setAnosLuzDeLaTierra(double anosLuzDeLaTierra) {
        this.anosLuzDeLaTierra = anosLuzDeLaTierra;
    }
}