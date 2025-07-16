package clases;

public class Administrador extends Usuario {
    String tipoDeUsuario;
    String cargo;

    public Administrador(String tipoDeUsuario, String cargo, String nombre, int cedula, String contrasena){
         super(nombre, cedula, contrasena);
        this.tipoDeUsuario=tipoDeUsuario;
        this.cargo=cargo;
       
    }

}

    

