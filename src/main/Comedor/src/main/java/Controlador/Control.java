package Controlador;

import Modelo.*;
import Vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {

    private final GestorArchivos modelo;
    public final Inicial vistaPrincipal;

    public Login vistaLogin;
    public Registro vistaRegistro;
    Usuario a;
    Administrador b;
    Comensal c;

    public Control(GestorArchivos modelo, Inicial vistaPrincipal) {
        this.modelo = modelo;
        this.vistaPrincipal = vistaPrincipal;

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

    public void abrirVentanaLogin() {
        vistaLogin = new Login();
        vistaLogin.btnLogin.setActionCommand("AccionLogin");
        vistaLogin.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaLogin.setVisible(true);
    }

    public void abrirVentanaRegistro(boolean esAdmin) {
        vistaRegistro = new Registro(esAdmin);
        vistaRegistro.btnRegistrar.setActionCommand("AccionRegistro");
        vistaRegistro.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaRegistro.setVisible(true);
    }

    public boolean procesarLogin() {
        String cedula = vistaLogin.txtCedula.getText();
        String password = new String(vistaLogin.txtPassword.getPassword());

        if (cedula.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(vistaLogin, "Todos los campos son obligatorios.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        a = modelo.validarLogin(cedula, password);
        int tipo = 0;
        if (a instanceof Administrador) {
            b = (Administrador) a;
            tipo = 1;
        } else if (a instanceof Comensal) {
            c = (Comensal) a;
            tipo = 2;
        } else {
            tipo = 3;
        }

        switch (tipo) {
            case 1:

                costos_vista view = new costos_vista();
                view.setVisible(true);
                vistaLogin.dispose();
                return true;
            case 2:
                vistaComensal comen = new vistaComensal(c);
                comen.setVisible(true);
                vistaLogin.dispose();
                return true;
                
            case 3:
                JOptionPane.showMessageDialog(vistaLogin, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                vistaLogin.dispose();
                vistaPrincipal.setVisible(true);
                return false;
        }
        return false;
    }

    public boolean procesarRegistro() {
        // Determinamos si es registro de admin por el título de la ventana
        boolean esAdmin = vistaRegistro.getTitle().contains("Administrador");
        Administrador a1 = null;
        Comensal c1 = null;
        if (esAdmin) {
            a1 = new Administrador(vistaRegistro.txtNombre.getText(), vistaRegistro.txtCedula.getText(),
                    new String(vistaRegistro.txtPassword.getPassword()), vistaRegistro.txtCampoExtra.getText());
            if (a1.getCedula().isEmpty() || a1.getContrasena().isEmpty() || a1.getNombre().isEmpty()
                    || a1.getCargo().isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            c1 = new Comensal(vistaRegistro.txtNombre.getText(), vistaRegistro.txtCedula.getText(),
                    new String(vistaRegistro.txtPassword.getPassword()), vistaRegistro.txtCampoExtra.getText());
            if (c1.getCedula().isEmpty() || c1.getContrasena().isEmpty() || c1.getNombre().isEmpty()
                    || c1.getFacultad().isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        boolean exito;
        if (esAdmin) {
            exito = modelo.registrarUsuario(a1, "ADMIN");
            if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: Cédula no autorizada o ya registrada.",
                        "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
                return false;
            }
        } else {
            exito = modelo.registrarUsuario(c1, "COMENSAL");
            if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: La cédula ya está registrada.",
                        "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
                return false;
            }
        }

        if (exito) {
            JOptionPane.showMessageDialog(vistaRegistro, "¡Registro exitoso!, inicie sesión");
            vistaPrincipal.setVisible(true);
            vistaRegistro.dispose();
            return true;
        }
        return false;
    }
}
