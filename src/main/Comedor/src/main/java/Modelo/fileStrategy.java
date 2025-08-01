package Modelo;

import java.io.*;

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
                    writer.write(b.cedula + "," + b.contrasena + "," + b.nombre + "," + b.getCargo() + "," + "ADMIN");
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
}
