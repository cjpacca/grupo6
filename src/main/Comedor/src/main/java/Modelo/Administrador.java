package Modelo;

public class Administrador extends Usuario {
    private String cargo;

    public Administrador(String nombre, String cedula, String contrasena, String cargo) {
        super(nombre, cedula, contrasena);
        this.cargo = cargo;
    }

    @Override
    public String getTipo() {
        return "ADMIN";
    }

    public String getCargo() {
        return cargo;
    }
}
    

