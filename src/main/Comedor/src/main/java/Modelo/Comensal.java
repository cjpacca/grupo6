package Modelo;

public class Comensal extends Usuario {
<<<<<<< HEAD
    private String tipo;
    private float saldo = 0.00f;

    public Comensal(String nombre, String cedula, String contrasena, String tipo, String rutaFoto) {
        super(nombre, cedula, contrasena);
        this.tipo = tipo;
        this.rutaFoto= rutaFoto;
    }
    @Override
    public String getTipo() {
        return "COMENSAL";
=======
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
>>>>>>> temporal
    }

    public float getSaldo() {
        return saldo;
    }
<<<<<<< HEAD

    public String getType() {
        return tipo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
=======
>>>>>>> temporal
}
