package Modelo;

public abstract class Usuario {
    protected String nombre;
    protected String cedula;
    protected String contrasena;
    protected String rutaFoto;

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
    
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public String getRutaFoto() {
        return rutaFoto;
    }
    
    public abstract String getTipo();
}
