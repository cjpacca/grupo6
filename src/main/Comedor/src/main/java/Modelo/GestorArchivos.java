package Modelo;

public class GestorArchivos {
    private AStrategy authStrategy;

    public GestorArchivos() {
        this.authStrategy = new FileStrategy(); // Inyección de dependencia
    }

    // Métodos delegados a la estrategia
    public boolean registrarUsuario(Usuario usuario, String a) {
        return authStrategy.registrarUsuario(usuario, a);
    }

    public Usuario validarLogin(String cedula, String password) {
        return authStrategy.validarLogin(cedula, password);
    }
}
