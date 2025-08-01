package Modelo;

public class Administrador extends Usuario {
    private String cargo;

    public Administrador(String nombre, String cedula, String contrasena, String cargo, String rutaFoto) {
        super(nombre, cedula, contrasena);
        this.cargo = cargo;
        this.rutaFoto=rutaFoto;
    }

    @Override
    public String getTipo() {
        return "ADMIN";
    }

    public String getCargo() {
        return cargo;
    }
}
    

