package Modelo;

public class MenuModelo {
    private String nombre;
    private double CCB;
    private String turno;
    private int numeroMenu;
    private double precioEstudiante;
    private double precioProfesor;
    private double precioExterno;
    private double costoFijo;
    private double costoVariable;
    private int numeroBandejas;
    private double merma;

    public MenuModelo() {}

    public MenuModelo(String nombre, double precioEstudiante, double precioProfesor, double precioExterno,
                    double costoFijo, double costoVariable, int numeroBandejas, double merma, String turno, int numeroMenu, double CCB) {
        this.nombre = nombre;
        this.precioEstudiante = precioEstudiante;
        this.precioProfesor = precioProfesor;
        this.precioExterno = precioExterno;
        this.costoFijo = costoFijo;
        this.costoVariable = costoVariable;
        this.numeroBandejas = numeroBandejas;
        this.merma = merma;
        this.turno = turno;
        this.numeroMenu = numeroMenu;
        this.CCB = CCB;
    }
    public double getCCB() {
        return CCB;
    }
    public void setCCB(double CCB) {
        this.CCB = CCB;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public int getNumeroMenu() {
        return numeroMenu;
    }
    public void setNumeroMenu(int numeroMenu) {
        this.numeroMenu = numeroMenu;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecioEstudiante() {
        return precioEstudiante;
    }
    public void setPrecioEstudiante(double precioEstudiante) {
        this.precioEstudiante = precioEstudiante;
    }
    public double getPrecioProfesor() {
        return precioProfesor;
    }
    public void setPrecioProfesor(double precioProfesor) {
        this.precioProfesor = precioProfesor;
    }
    public double getPrecioExterno() {
        return precioExterno;
    }
    public void setPrecioExterno(double precioExterno) {
        this.precioExterno = precioExterno;
    }
    public double getCostoFijo() {
        return costoFijo;
    }
    public void setCostoFijo(double costoFijo) {
        this.costoFijo = costoFijo;
    }
    public double getCostoVariable() {
        return costoVariable;
    }
    public void setCostoVariable(double costoVariable) {
        this.costoVariable = costoVariable;
    }
    public int getNumeroBandejas() {
        return numeroBandejas;
    }
    public void setNumeroBandejas(int numeroBandejas) {
        this.numeroBandejas = numeroBandejas;
    }
    public double getMerma() {
        return merma;
    }
    public void setMerma(double merma) {
        this.merma = merma;
    }

    //calcular CCB
    public void calcularCCB() {
    this.CCB = ((costoFijo + costoVariable) / numeroBandejas) * (1 + merma);
}

}

