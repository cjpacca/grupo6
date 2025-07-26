package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileStrategy implements AStrategy {
    private final String usuariosDbPath;
    private final String cedulasAutorizadasDbPath;
    private final String comensalesAutorizadosDbPath;

    /**
     * Constructor para producción. Usa las rutas de archivo predeterminadas.
     * El GestorArchivos usa este constructor.
     */
    public fileStrategy() {
        // Usar rutas absolutas para evitar problemas con el directorio de trabajo.
        String dataDir = "d:/Codigos/grupo6/src/main/Comedor/";
        this.usuariosDbPath = dataDir + "usuarios.txt";
        this.cedulasAutorizadasDbPath = dataDir + "cedulas_autorizadas.txt";
        this.comensalesAutorizadosDbPath = dataDir + "Comensales.txt";
    }

    /**
     * Constructor para pruebas. Permite inyectar rutas de archivo personalizadas.
     */
    public fileStrategy(String usuariosDbPath, String cedulasAutorizadasDbPath, String comensalesAutorizadosDbPath) {
        this.usuariosDbPath = usuariosDbPath;
        this.cedulasAutorizadasDbPath = cedulasAutorizadasDbPath;
        this.comensalesAutorizadosDbPath = comensalesAutorizadosDbPath;
    }

    @Override
    public Usuario validarLogin(String cedula, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.usuariosDbPath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",", -1); // -1 para no descartar campos vacíos
                if (datos.length == 5 && datos[0].equals(cedula)) {
                    // Cédula encontrada, ahora verificamos la contraseña.
                    if (datos[1].equals(password)) {
                        // Contraseña correcta. Creamos el objeto de usuario con los datos correctos.
                        // Formato archivo: cedula,contrasena,nombre,cargo/facultad,TIPO
                        // Constructor Admin: nombre, cedula, contrasena, cargo
                        // Constructor Comensal: nombre, cedula, contrasena, facultad
                        if (datos[4].equals("ADMIN")) {
                            return new Administrador(datos[2], datos[0], datos[1], datos[3]);
                        } else if (datos[4].equals("COMENSAL")) {
                            return new Comensal(datos[2], datos[0], datos[1], datos[3]);
                        }
                    }
                    // Si la contraseña es incorrecta, dejamos de buscar y retornamos null.
                    return null; 
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null; // El bucle terminó, no se encontró la cédula.
    }

    @Override
    public EstadoRegistro registrarUsuario(Usuario a, String tipo) {
        if (usuarioYaExiste(a.getCedula())) {
            return EstadoRegistro.USUARIO_YA_EXISTE;
        }

        if (tipo.equals("ADMIN")) {
            if (!esCedulaAutorizada(a.getCedula())) {
                return EstadoRegistro.CEDULA_NO_AUTORIZADA;
            }
        } else if (tipo.equals("COMENSAL")) {
            if (!esComensalAutorizado(a.getCedula())) {
                return EstadoRegistro.CEDULA_NO_AUTORIZADA;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.usuariosDbPath, true))) {
            String campoExtra = "";
            if (a instanceof Administrador) {
                campoExtra = ((Administrador) a).getCargo();
            } else if (a instanceof Comensal) {
                campoExtra = ((Comensal) a).getFacultad();
            }

            String lineaParaGuardar = String.join(",",
                    a.getCedula(), a.getContrasena(), a.getNombre(), campoExtra, a.getTipo()
            );

            writer.write(lineaParaGuardar);
            writer.newLine(); // Usa newLine() para ser compatible con cualquier sistema operativo.
            return EstadoRegistro.EXITO;
        } catch (IOException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return EstadoRegistro.ERROR_ESCRITURA;
        }
    }

    public boolean usuarioYaExiste(String cedula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.usuariosDbPath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 0 && datos[0].equals(cedula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe, ningún usuario puede existir.
            return false;
        }
        return false;
    }

    private boolean esCedulaEnArchivo(String cedula, String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.err.println("Advertencia: No se encontró el archivo " + nombreArchivo);
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().equals(cedula)) return true;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }
        return false;
    }

    public boolean esCedulaAutorizada(String cedula) {
        return esCedulaEnArchivo(cedula, this.cedulasAutorizadasDbPath);
    }

    public boolean esComensalAutorizado(String cedula) {
        return esCedulaEnArchivo(cedula, this.comensalesAutorizadosDbPath);
    }
}
