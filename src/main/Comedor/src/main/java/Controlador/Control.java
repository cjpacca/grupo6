package Controlador;

import Modelo.GestorArchivos;
import Vista.Login;
import Vista.Inicial;
import Vista.Registro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {

    private final GestorArchivos modelo;
    private final Inicial vistaPrincipal;
    
    // Las ventanas de login y registro se crean bajo demanda para evitar errores
    private Login vistaLogin;
    private Registro vistaRegistro;
    
    public Control(GestorArchivos modelo, Inicial vistaPrincipal) {
        this.modelo = modelo;
        this.vistaPrincipal = vistaPrincipal;
        
        // El controlador se suscribe a los eventos de la vista principal
        this.vistaPrincipal.setControlador(this);
    }

    public void iniciar() {
        vistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Usamos el texto del botón como comando para identificar la acción
        String comando = e.getActionCommand();

        switch (comando) {
            case "Iniciar Sesión":
                abrirVentanaLogin();
                break;
            case "Registrarme como Comensal":
                abrirVentanaRegistro(false);
                
                break;
            case "Registrarme como Administrador":
                abrirVentanaRegistro(true);
                break;
            case "AccionLogin": // Comando interno para el botón de la ventana de login
                procesarLogin();
                break;
            case "AccionRegistro": // Comando interno para el botón de la ventana de registro
                procesarRegistro();
                break;
        }
    }

    private void abrirVentanaLogin() {
        vistaLogin = new Login();
        vistaLogin.btnLogin.setActionCommand("AccionLogin");
        vistaLogin.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaLogin.setVisible(true);
    }

    private void abrirVentanaRegistro(boolean esAdmin) {
        vistaRegistro = new Registro(esAdmin);
        vistaRegistro.btnRegistrar.setActionCommand("AccionRegistro");
        vistaRegistro.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaRegistro.setVisible(true);
    }
    
    private void procesarLogin() {
        String cedula = vistaLogin.txtCedula.getText();
        String password = new String(vistaLogin.txtPassword.getPassword());

        if (cedula.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(vistaLogin, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String tipoUsuario = modelo.validarLogin(cedula, password);

        switch (tipoUsuario) {
            case "ADMIN":
                JOptionPane.showMessageDialog(vistaLogin, "¡Bienvenido Administrador!");
                vistaLogin.dispose(); // Cierra la ventana de login
                break;
            case "COMENSAL":
                JOptionPane.showMessageDialog(vistaLogin, "¡Bienvenido Comensal!");
                vistaLogin.dispose();
                break;
            default: // "INVALIDO"
                JOptionPane.showMessageDialog(vistaLogin, "Error: Cédula o contraseña incorrecta.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void procesarRegistro() {
        String cedula = vistaRegistro.txtCedula.getText();
        String password = new String(vistaRegistro.txtPassword.getPassword());
        String nombre = vistaRegistro.txtNombre.getText();
        String campoExtra = vistaRegistro.txtCampoExtra.getText();
        
        if(cedula.isEmpty() || password.isEmpty() || nombre.isEmpty() || campoExtra.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Determinamos si es registro de admin por el título de la ventana
        boolean esAdmin = vistaRegistro.getTitle().contains("Administrador");
        
        boolean exito;
        if (esAdmin) {
            exito = modelo.registrarAdmin(cedula, password, nombre, campoExtra);
             if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: Cédula no autorizada o ya registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
             }
        } else {
            exito = modelo.registrarComensal(cedula, password, nombre, campoExtra);
             if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: La cédula ya está registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
             }
        }

        if (exito) {
            JOptionPane.showMessageDialog(vistaRegistro, "¡Registro exitoso!");
            vistaRegistro.dispose();
        }
    }
}
