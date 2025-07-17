package Modelo;

public abstract class Usuario {
    protected String nombre;
    protected String cedula;
    protected String contrasena;

    public Usuario(String nombre, String cedula, String contrasena) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    
    public abstract String getTipo();
}
