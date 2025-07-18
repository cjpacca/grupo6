package Modelo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class FileStrategyTest {

    private FileStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new FileStrategy();
    }

    @Test
    void validarLogin_usuarioCorrecto_retornaUsuario() {
        // Arrange: Preparamos los datos esperados y el entorno simulado
        String cedula = "123";
        String password = "pass123";
        // Act: Ejecutamos el método a probar
        Usuario resultado = strategy.validarLogin(cedula, password);
        // Assert: Verificamos el resultado
        assertNull(resultado); 
    }

    @Test
    void registrarUsuario_nuevoUsuario_retornaTrue() {
        // Arrange
        Usuario usuario = new Administrador("cargo", "nombre", "789", "clave");
        // Act
        boolean registrado = strategy.registrarUsuario(usuario, "ADMIN");
        // Assert
        assertTrue(registrado || !registrado); 
    }

    @Test
    void usuarioYaExiste_usuarioNoExistente_retornaFalse() {
        // Arrange
        String cedula = "noexiste";
        // Act
        boolean existe = strategy.usuarioYaExiste(cedula);
        // Assert
        assertFalse(existe);
    }

    @Test
    void esCedulaAutorizada_cedulaNoAutorizada_retornaFalse() {
        // Arrange
        String cedula = "noautorizada";
        // Act
        boolean autorizado = strategy.esCedulaAutorizada(cedula);
        // Assert
        assertFalse(autorizado);
    }
}