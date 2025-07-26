package Modelo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@DisplayName("Pruebas para fileStrategy")
class fileStrategyTest {

    // @TempDir crea una carpeta temporal para cada ejecución de prueba, asegurando aislamiento.
    @TempDir
    Path tempDir;

    private fileStrategy strategy;
    private Path usuariosFile;
    private Path cedulasAdminFile;
    private Path cedulasComensalFile;

    @BeforeEach
    void setUp() throws IOException {
        // Creamos rutas a archivos temporales dentro de la carpeta temporal.
        usuariosFile = tempDir.resolve("usuarios.txt");
        cedulasAdminFile = tempDir.resolve("cedulas_autorizadas.txt");
        cedulasComensalFile = tempDir.resolve("comensales.txt");

        // Creamos los archivos para evitar FileNotFoundException
        Files.createFile(usuariosFile);
        Files.createFile(cedulasAdminFile);
        Files.createFile(cedulasComensalFile);

        // Inyectamos las rutas de los archivos temporales en nuestra estrategia.
        strategy = new fileStrategy(
                usuariosFile.toString(),
                cedulasAdminFile.toString(),
                cedulasComensalFile.toString()
        );
    }

    @Test
    @DisplayName("Debe retornar un objeto Usuario cuando las credenciales son correctas")
    void validarLogin_usuarioCorrecto_retornaUsuario() throws IOException {
        // Arrange: Escribimos un usuario de prueba en el archivo temporal.
        String lineaUsuario = "12345,pass123,Usuario de Prueba,Facultad de Test,COMENSAL";
        Files.write(usuariosFile, List.of(lineaUsuario));

        // Act: Ejecutamos el método a probar.
        Usuario resultado = strategy.validarLogin("12345", "pass123");

        // Assert: Verificamos que se retornó un usuario y que sus datos son correctos.
        assertNotNull(resultado, "El usuario no debería ser nulo para credenciales correctas.");
        assertEquals("12345", resultado.getCedula());
        assertEquals("Usuario de Prueba", resultado.getNombre());
        assertInstanceOf(Comensal.class, resultado, "El usuario debería ser de tipo Comensal.");
    }

    @Test
    @DisplayName("Debe retornar null si la contraseña es incorrecta")
    void validarLogin_passwordIncorrecto_retornaNull() throws IOException {
        // Arrange
        String lineaUsuario = "12345,pass123,Usuario de Prueba,Facultad de Test,COMENSAL";
        Files.write(usuariosFile, List.of(lineaUsuario));

        // Act
        Usuario resultado = strategy.validarLogin("12345", "password_incorrecto");

        // Assert
        assertNull(resultado, "El resultado debe ser nulo si la contraseña es incorrecta.");
    }

    @Test
    @DisplayName("Debe registrar un administrador si la cédula está autorizada")
    void registrarUsuario_adminAutorizado_retornaExito() throws IOException {
        // Arrange: Autorizamos la cédula y creamos el objeto Administrador.
        Files.write(cedulasAdminFile, List.of("789"));
        Usuario usuario = new Administrador("Admin Test", "789", "claveSegura1", "Super Admin");

        // Act: Intentamos registrar el usuario.
        EstadoRegistro resultado = strategy.registrarUsuario(usuario, "ADMIN");

        // Assert: Verificamos que el registro fue exitoso.
        assertEquals(EstadoRegistro.EXITO, resultado);
        
        // Verificamos que el usuario fue escrito correctamente en el archivo.
        String contenido = Files.readString(usuariosFile);
        assertTrue(contenido.contains("789,claveSegura1,Admin Test,Super Admin,ADMIN"), "El archivo de usuarios no contiene al nuevo administrador registrado.");
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