package Modelo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fileStrategy implements AStrategy {
    private static final String USUARIOS_DB = "usuarios.txt";
    private static final String COMENSALES_DB = "comensales.txt";
    private static final String ADMINISTRADORES_DB = "administradores.txt";

    @Override
    public Usuario validarLogin(String cedula, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_DB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6 && datos[0].equals(cedula) && datos[1].equals(password)) {
                    if (datos[5].equals("ADMIN")) {
                        Usuario a = new Administrador(datos[3], datos[2], datos[0], datos[1], "default");
                        return a;
                    } else if (datos[5].equals("COMENSAL")) {
                        Usuario a = new Comensal(datos[3], datos[2], datos[0], datos[1], "default");
                        return a;
                    }
                } else if (datos[0].equals(cedula) && !datos[1].equals(password)) {
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null; // No se encontró el usuario o la contraseña no coincide
    }

    @Override
    public boolean registrarUsuario(Usuario a, String tipo) {
        if (usuarioYaExiste(a.cedula))
            return false;
        
        // Verificar contra archivos según tipo
        if (tipo.equals("ADMIN")) {
            if (!esUsuarioValido(a.getNombre(), a.getCedula(), ADMINISTRADORES_DB)) {
                return false;
            }
        } else if (tipo.equals("COMENSAL")) {
            if (!esUsuarioValido(a.getNombre(), a.getCedula(), COMENSALES_DB)) {
                return false;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_DB, true))) {
            switch (a.getTipo()) {
                case "ADMIN":
                    Administrador b=(Administrador)a;
                    writer.write(b.cedula + "," + b.contrasena + "," + b.nombre + "," + b.getCargo() + ",0.0" + "ADMIN");
                    break;
                case "COMENSAL":
                    Comensal c=(Comensal)a;
                    writer.write(c.cedula + "," + c.contrasena + "," + c.nombre + "," + c.getType() + ","+ c.getSaldo()+ "," + "COMENSAL");
                    break;
            }
            writer.write("\n");
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    
    private boolean esUsuarioValido(String nombre, String cedula, String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    String nombreArchivo = datos[0].trim();
                    String cedulaArchivo = datos[1].trim();
                    
                    if (nombreArchivo.equalsIgnoreCase(nombre) && cedulaArchivo.equals(cedula)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo " + archivo + ": " + e.getMessage());
        }
        return false;
    }

    public boolean usuarioYaExiste(String cedula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_DB))) {
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

    public boolean esCedulaAutorizada(String cedula) {
        try {
    File archivo = new File(ADMINISTRADORES_DB);
    BufferedReader reader = new BufferedReader(new FileReader(archivo));
    String linea;
    while ((linea = reader.readLine()) != null) {
        String[] datos = linea.split(",");
        if (datos[1].equals(cedula)) {
            reader.close();
            return true;
        }
    }
    reader.close();
    } catch (IOException e) {
        System.err.println("Advertencia: No se encontró el archivo cedulas_autorizadas.txt");
        return false;
    }
        return false;
    }
    
    public boolean verificarFotoContraTodos(String rutaFotoAComparar) {
    try (BufferedReader reader = new BufferedReader(new FileReader(COMENSALES_DB))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length >= 3) { // Asumiendo que el tercer campo es la ruta de la foto
                String rutaFotoRegistrada = datos[2].trim();
                if (compararImagenes(rutaFotoRegistrada, rutaFotoAComparar)) {
                    return true;
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer archivo de comensales: " + e.getMessage());
    }
    return false;
}
    
        private boolean compararImagenes(String rutaImagen1, String rutaImagen2) {
        try {
            File f1 = new File(rutaImagen1);
            File f2 = new File(rutaImagen2);

            // Si los archivos no existen o tienen diferente tamaño, no pueden ser iguales.
            if (!f1.exists() || !f2.exists() || f1.length() != f2.length()) {
                return false;
            }

            // Compara el contenido de los archivos byte a byte.
            return Arrays.equals(Files.readAllBytes(f1.toPath()), Files.readAllBytes(f2.toPath()));

        } catch (IOException e) {
            System.err.println("Error al comparar las imágenes: " + e.getMessage());
            return false;
        }
    }
    private List<String> getAllFotoPaths() {
        List<String> paths = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COMENSALES_DB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                // Asumiendo formato: nombre,cedula,rutaFoto
                if (datos.length == 3 && !datos[2].trim().isEmpty()) {
                    paths.add(datos[2].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de comensales: " + e.getMessage());
        }
        return paths;
    }
}
