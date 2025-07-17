package Modelo;

import java.io.*;
import javax.swing.JOptionPane;

public class GestorArchivos {

    private static final String USUARIOS_DB = "usuarios.txt";
    private static final String CEDULAS_AUTORIZADAS_DB = "cedulas_autorizadas.txt";

    /**
     * Revisa si la cédula está en el archivo de autorización de administradores.
     * @param cedula La cédula a verificar.
     * @return true si la cédula está autorizada, false en caso contrario.
     */
    
    private boolean esCedulaAutorizada(String cedula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CEDULAS_AUTORIZADAS_DB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().equals(cedula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe o hay un error, se asume que no está autorizado.
            System.err.println("Advertencia: No se encontró el archivo cedulas_autorizadas.txt");
            return false; 
        }
        return false;
    }

    /**
     * Verifica si una cédula ya está registrada en la base de datos de usuarios.
     * @param cedula La cédula a verificar.
     * @return true si el usuario ya existe, false en caso contrario.
     */
    private boolean usuarioYaExiste(String cedula) {
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

    /**
     * Registra un nuevo comensal en el archivo unificado.
     */
    public boolean registrarComensal(Comensal a) {
        if (usuarioYaExiste(a.getCedula())) {
            return false;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_DB, true))) {
            // Formato: cedula,password,nombre,universidad,TIPO
            writer.write(a.getCedula()+","+a.getContrasena()+","+a.getNombre()+","+a.getFacultad()+",COMENSAL");
            writer.write("\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registra un nuevo administrador, validando primero si la cédula está autorizada.
     */
    public boolean registrarAdmin(Administrador a) {
        if (!esCedulaAutorizada(a.getCedula()) || usuarioYaExiste(a.getCedula())) {
            return false;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_DB, true))) {
            // Formato: cedula,password,nombre,cargo,TIPO
            writer.write(a.getCedula()+","+a.getContrasena()+","+a.getNombre()+","+a.getCargo()+",ADMIN\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Valida las credenciales en el archivo unificado.
     * @return El tipo de usuario ("ADMIN", "COMENSAL") o "INVALIDO" si falla el login.
     */
    public Usuario validarLogin(String cedula, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_DB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5 && datos[0].equals(cedula) && datos[1].equals(password)) {
                    if(datos[4].equals("ADMIN")){
                        Usuario a = new Administrador("ADMIN", datos[3], datos[2], datos[0], datos[1]);
                        return a;
                    } else if(datos[4].equals("COMENSAL")){
                        Usuario a= new Comensal("COMENSAL", datos[3], datos[2], datos[0], datos[1]);
                        return a;
                    }    
                }else if(datos[0].equals(cedula) && !datos[1].equals(password)){
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null; // No se encontró el usuario o la contraseña no coincide
    }
}
