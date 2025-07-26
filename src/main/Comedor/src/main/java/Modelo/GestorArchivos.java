package Modelo;

public class GestorArchivos {
    private AStrategy authStrategy;

    public GestorArchivos() {
        this.authStrategy = new fileStrategy(); // Inyección de dependencia
    }

    // Métodos delegados a la estrategia
    public EstadoRegistro registrarUsuario(Usuario usuario, String a) {
        return authStrategy.registrarUsuario(usuario, a);
    }

    public Usuario validarLogin(String cedula, String password) {
        return authStrategy.validarLogin(cedula, password);
    }

    public boolean estaAutorizadoParaRegistrar(String nombre, String cedula, boolean esAdmin) {
        return authStrategy.estaAutorizadoParaRegistrar(nombre, cedula, esAdmin);
    }
}
