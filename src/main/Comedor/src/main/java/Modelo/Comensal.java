package Modelo;

public class Comensal extends Usuario {
    String TipoDeUsuario;
    String Facultad;
    float saldo=0.00f;


    public Comensal(String tipoDeUsuario, String facultad, String nombre, String cedula, String contrasena){
        super( nombre, cedula, contrasena);
        this.TipoDeUsuario=tipoDeUsuario;
        this.Facultad=facultad;

    }

    public String getTipoDeUsuario() {
        return TipoDeUsuario;
    }

    public String getFacultad() {
        return Facultad;
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

    public float getSaldo() {
        return saldo;
    }
}
