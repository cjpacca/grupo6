package Modelo;

<<<<<<< HEAD
public abstract class Usuario {
    protected String nombre;
    protected String cedula;
    protected String contrasena;
    protected String rutaFoto;

    public Usuario(String nombre, String cedula, String contrasena) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
=======
public class Usuario {
    public 
    String nombre;
    String cedula;
    String contrasena;

    public Usuario(String nombre, String cedula, String contrasena ){
        this.nombre=nombre;
        this.cedula=cedula;
        this.contrasena=contrasena;
>>>>>>> temporal
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
    
<<<<<<< HEAD
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public String getRutaFoto() {
        return rutaFoto;
    }
    
    public abstract String getTipo();
}
=======
}

>>>>>>> temporal
