package Modelo;

import java.io.*;

public class FileStrategy implements AStrategy {
    private static final String USUARIOS_DB = "usuarios.txt";
    private static final String CEDULAS_AUTORIZADAS_DB = "cedulas_autorizadas.txt";

    @Override
    public Usuario validarLogin(String cedula, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_DB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5 && datos[0].equals(cedula) && datos[1].equals(password)) {
                    if (datos[4].equals("ADMIN")) {
                        Usuario a = new Administrador(datos[3], datos[2], datos[0], datos[1]);
                        return a;
                    } else if (datos[4].equals("COMENSAL")) {
                        Usuario a = new Comensal(datos[3], datos[2], datos[0], datos[1]);
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
        if (tipo.equals("ADMIN")) {
            if (!esCedulaAutorizada(a.getCedula()) || usuarioYaExiste(a.getCedula())) {
                return false;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_DB, true))) {
            switch (a.getTipo()) {
                case "ADMIN":
                    writer.write(a.cedula + "," + a.contrasena + "," + a.nombre + "," + ((Administrador) a).getCargo()
                            + "," + "ADMIN");
                    break;
                case "COMENSAL":
                    writer.write(a.cedula + "," + a.contrasena + "," + a.nombre + "," + ((Comensal) a).getFacultad()
                            + "," + "COMENSAL");
                    break;
            }
            writer.write("\n");
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
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
    File archivo = new File(CEDULAS_AUTORIZADAS_DB);
    BufferedReader reader = new BufferedReader(new FileReader(archivo));
    String linea;
    while ((linea = reader.readLine()) != null) {
        if (linea.trim().equals(cedula)) {
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
