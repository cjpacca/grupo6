package Modelo;

public interface AStrategy {
    Usuario validarLogin(String cedula, String password);
    EstadoRegistro registrarUsuario(Usuario usuario, String a);
    boolean estaAutorizadoParaRegistrar(String nombre, String cedula, boolean esAdmin);
}