package clases;

public class Comensal extends Usuario {
    String TipoDeUsuario;
    String Facultad;


    public Comensal(String tipoDeUsuario, String facultad, String nombre, int cedula, String contrasena){
        super( nombre, cedula, contrasena);
        this.TipoDeUsuario=tipoDeUsuario;
        this.Facultad=facultad;

    }

    
}
