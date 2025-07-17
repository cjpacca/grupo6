package Modelo;

import Modelo.Usuario;

public interface AStrategy {
    Usuario validarLogin(String cedula, String password);
    boolean registrarUsuario(Usuario usuario,String a);
}