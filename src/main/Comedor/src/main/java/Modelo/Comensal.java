package Modelo;

public class Comensal extends Usuario {
    private String facultad;
    private float saldo = 0.00f;

    public Comensal(String nombre, String cedula, String contrasena, String facultad) {
        super(nombre, cedula, contrasena);
        this.facultad = facultad;
    }

    @Override
    public String getTipo() {
        return "COMENSAL";
    }

    public float getSaldo() {
        return saldo;
    }

    public String getFacultad() {
        return facultad;
    }
}
