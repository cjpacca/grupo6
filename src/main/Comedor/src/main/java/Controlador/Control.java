package Controlador;

import Modelo.GestorArchivos;
import Vista.*;
import Modelo.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control implements ActionListener {

    private final GestorArchivos modelo;
    private final Inicial vistaPrincipal;
    private vistaComensal comen;
    
    private Login vistaLogin;
    private Registro vistaRegistro;
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
        a = modelo.validarLogin(cedula, password);
        int tipo=0;
        if( a instanceof Administrador){
            b= (Administrador)a;
            tipo=1;
        } else if(a instanceof Comensal){
            c= (Comensal)a;
            tipo=2;
        }else{
            tipo=3;
        }
            
        switch(tipo){
            case 1:
                costos_modelo model = new costos_modelo();
                costos_vista view = new costos_vista();
                costos_controlador controller = new costos_controlador(model, view);
                view.setVisible(true);
                vistaLogin.dispose();
                break;
            case 2:
                vistaComensal comen = new vistaComensal(c);
                comen.setVisible(true);
                vistaLogin.dispose();
                break;
            case 3:
                JOptionPane.showMessageDialog(vistaLogin, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                vistaLogin.dispose();
                vistaPrincipal.setVisible(true);
                break;
        }
    }

    private void procesarRegistro() {        
        // Determinamos si es registro de admin por el título de la ventana
        boolean esAdmin = vistaRegistro.getTitle().contains("Administrador");
        Administrador a1=null;
        Comensal c1=null;
        if(esAdmin){
            a1 = new Administrador("ADMIN",vistaRegistro.txtCampoExtra.getText(), vistaRegistro.txtNombre.getText(), vistaRegistro.txtCedula.getText(),new String(vistaRegistro.txtPassword.getPassword()));
            if(a1.getCedula().isEmpty() || a1.getContrasena().isEmpty() || a1.getNombre().isEmpty() || a1.getCargo().isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else{
            c1 = new Comensal("COMENSAL",vistaRegistro.txtCampoExtra.getText(), vistaRegistro.txtNombre.getText(),vistaRegistro.txtCedula.getText(),new String(vistaRegistro.txtPassword.getPassword()));
            if(c1.getCedula().isEmpty() || c1.getContrasena().isEmpty() || c1.getNombre().isEmpty() || c1.getFacultad().isEmpty()) {
                JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        boolean exito;
        if (esAdmin) {
            exito = modelo.registrarAdmin(a1);
             if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: Cédula no autorizada o ya registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
             }
        } else {
            exito = modelo.registrarComensal(c1);
             if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, "Error: La cédula ya está registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
             }
        }

        if (exito) {
            JOptionPane.showMessageDialog(vistaRegistro, "¡Registro exitoso!, inicie sesión");
            vistaPrincipal.setVisible(true);
            vistaRegistro.dispose();
        }
    }
}
