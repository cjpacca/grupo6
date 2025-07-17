package Modelo;

public class Administrador extends Usuario {
    String tipoDeUsuario;
    String cargo;

    public Administrador(String tipoDeUsuario, String cargo, String nombre, String cedula, String contrasena){
         super(nombre, cedula, contrasena);
        this.tipoDeUsuario=tipoDeUsuario;
        this.cargo=cargo;
       
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public String getCargo() {
        return cargo;
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

}

    

