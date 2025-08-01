package Modelo;

public class Comensal extends Usuario {
    private String tipo;
    private float saldo = 0.00f;

    public Comensal(String nombre, String cedula, String contrasena, String tipo, String rutaFoto) {
        super(nombre, cedula, contrasena);
        this.tipo = tipo;
        this.rutaFoto= rutaFoto;
    }
    @Override
    public String getTipo() {
        return "COMENSAL";
    }

    public float getSaldo() {
        return saldo;
    }

    public String getType() {
        return tipo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
