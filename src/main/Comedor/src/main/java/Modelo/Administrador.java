package Modelo;

public class Administrador extends Usuario {
<<<<<<< HEAD
    private String cargo;

    public Administrador(String nombre, String cedula, String contrasena, String cargo, String rutaFoto) {
        super(nombre, cedula, contrasena);
        this.cargo = cargo;
        this.rutaFoto=rutaFoto;
    }

    @Override
    public String getTipo() {
        return "ADMIN";
=======
    String tipoDeUsuario;
    String cargo;

    public Administrador(String tipoDeUsuario, String cargo, String nombre, String cedula, String contrasena){
         super(nombre, cedula, contrasena);
        this.tipoDeUsuario=tipoDeUsuario;
        this.cargo=cargo;
       
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
>>>>>>> temporal
    }

    public String getCargo() {
        return cargo;
    }
<<<<<<< HEAD
}
=======

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

>>>>>>> temporal
    

