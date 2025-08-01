package Modelo;

public interface AStrategy {
    Usuario validarLogin(String cedula, String password);

    boolean registrarUsuario(Usuario usuario, String a);
    
    Comensal verificarFotoContraTodos(String rutaFotoAComparar);

    boolean actualizarSaldo(String cedula, float nuevoSaldo);
}