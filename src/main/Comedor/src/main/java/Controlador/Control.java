package Controlador;

<<<<<<< HEAD
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

=======
import Modelo.GestorArchivos;
import Vista.*;
import Modelo.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> temporal

public class Control implements ActionListener {

    private final GestorArchivos modelo;
<<<<<<< HEAD
    public final Inicial vistaPrincipal;

    public Login vistaLogin;
    public Registro vistaRegistro;
    Usuario a;
    Administrador b;
    Comensal c;

    public Control(GestorArchivos modelo, Inicial vistaPrincipal) {
        this.modelo = modelo;
        this.vistaPrincipal = vistaPrincipal;
=======
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
        
>>>>>>> temporal
        this.vistaPrincipal.setControlador(this);
    }

    public void iniciar() {
        vistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
=======
        // Usamos el texto del botón como comando para identificar la acción
>>>>>>> temporal
        String comando = e.getActionCommand();

        switch (comando) {
            case "Iniciar Sesión":
                abrirVentanaLogin();
                break;
            case "Registrarme como Comensal":
                abrirVentanaRegistro(false);
<<<<<<< HEAD
=======
                
>>>>>>> temporal
                break;
            case "Registrarme como Administrador":
                abrirVentanaRegistro(true);
                break;
<<<<<<< HEAD
            case "AccionLogin":
                procesarLogin();
                break;
            case "AccionRegistro":
                procesarRegistro();
                break;
            case "Cerrar Sesion":
                Component sourceComponent = (Component) e.getSource();
            // Buscamos la ventana (JFrame) que contiene a ese botón
            JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(sourceComponent);
            
            // Cerramos la ventana actual (la de vistaComensal)
            if (frameActual != null) {
                frameActual.dispose();
            }
            vistaPrincipal.setVisible(true);
            break;
        }
    }

    public void abrirVentanaLogin() {
=======
            case "AccionLogin": // Comando interno para el botón de la ventana de login
                procesarLogin();
                break;
            case "AccionRegistro": // Comando interno para el botón de la ventana de registro
                procesarRegistro();
                break;
        }
    }

    private void abrirVentanaLogin() {
>>>>>>> temporal
        vistaLogin = new Login();
        vistaLogin.btnLogin.setActionCommand("AccionLogin");
        vistaLogin.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaLogin.setVisible(true);
    }

<<<<<<< HEAD
    public void abrirVentanaRegistro(boolean esAdmin) {
=======
    private void abrirVentanaRegistro(boolean esAdmin) {
>>>>>>> temporal
        vistaRegistro = new Registro(esAdmin);
        vistaRegistro.btnRegistrar.setActionCommand("AccionRegistro");
        vistaRegistro.setControlador(this);
        vistaPrincipal.setVisible(false);
        vistaRegistro.setVisible(true);
    }
<<<<<<< HEAD
     // Valida que la cédula tenga entre 5 y 8 dígitos y que solo contenga números
    
    private boolean validarCedula(String cedula) {
        if (cedula.length() < 5 || cedula.length() > 8) {
            return false;
        }
        return cedula.matches("\\d+"); // Expresión regular para verificar si son solo dígitos
    }

    //Valida que el texto solo contenga letras y espacios.
    private boolean validarTextoSimple(String texto) {
        if (texto.trim().isEmpty()) {
            return false;
        }
        return texto.matches("[a-zA-Z ]+"); // Expresión regular para letras y espacios
    }

    /**
     * Valida la contraseña:
     * - Longitud entre 5 y 10 caracteres.
     * - No puede ser solo letras.
     * - No puede ser solo números.
     */
    private boolean validarPassword(String password) {
        if (password.length() < 5 || password.length() > 10) {
            return false;
        }
        boolean tieneLetra = password.matches(".*[a-zA-Z].*");
        boolean tieneNumero = password.matches(".*\\d.*");
        
        return tieneLetra && tieneNumero;
    }


    public boolean procesarLogin() {
        String cedula = vistaLogin.txtCedula.getText();
        String password = new String(vistaLogin.txtPassword.getPassword());

        // **Validación de campos de Login**
        if (!validarCedula(cedula)) {
            JOptionPane.showMessageDialog(vistaLogin, "Formato de cédula incorrecto.\nDebe tener entre 5 y 8 dígitos numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (password.isEmpty()) {
             JOptionPane.showMessageDialog(vistaLogin, "El campo contraseña no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
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
                vistaComensal comen = new vistaComensal(c, this);
                comen.setVisible(true);
                vistaLogin.dispose();
                return true;
=======
    
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
>>>>>>> temporal
            case 3:
                JOptionPane.showMessageDialog(vistaLogin, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                vistaLogin.dispose();
                vistaPrincipal.setVisible(true);
<<<<<<< HEAD
                return false;
        }
        return false;
    }

    public boolean procesarRegistro() {
        String nombre = vistaRegistro.txtNombre.getText();
        String cedula = vistaRegistro.txtCedula.getText();
        String password = new String(vistaRegistro.txtPassword.getPassword());
        String campoExtra="";
        String campo="";
        
        if(vistaRegistro.getTitle().equals("Registro - Comensal")){
            campo = vistaRegistro.getCampoExtra();
        }else{
            campoExtra = vistaRegistro.txtCampoExtra.getText();
        }
        // **Validaciones robustas para el registro**
        if (nombre.isEmpty() || cedula.isEmpty() || password.isEmpty() || (campoExtra.isEmpty() && !campoExtra.equals(""))) {
            JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarCedula(cedula)) {
            JOptionPane.showMessageDialog(vistaRegistro, "La cédula debe tener entre 5 y 8 dígitos numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarTextoSimple(nombre)) {
            JOptionPane.showMessageDialog(vistaRegistro, "El nombre solo puede contener letras y espacios.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarTextoSimple(campoExtra) && !campoExtra.equals("")) {
            JOptionPane.showMessageDialog(vistaRegistro, "El campo de cargo/facultad solo puede contener letras y espacios.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarPassword(password)) {
            JOptionPane.showMessageDialog(vistaRegistro, "La contraseña debe tener entre 5 y 10 caracteres y contener letras y números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean esAdmin = vistaRegistro.getTitle().contains("Administrador");
        Administrador a1 = null;
        Comensal c1 = null;

        if (esAdmin) {
            a1 = new Administrador(nombre, cedula, password, campoExtra, "default");
        } else {
            c1 = new Comensal(nombre, cedula, password, campo, "default");
        }

        boolean exito;
        if (esAdmin) {
            exito = modelo.registrarUsuario(a1, "ADMIN");
            if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, 
                    "Error: No estás autorizado como administrador o la cédula ya está registrada.", 
                 "Error de Registro", JOptionPane.ERROR_MESSAGE);
                vistaPrincipal.setVisible(true);
             return false;
            }
        } else {
            exito = modelo.registrarUsuario(c1, "COMENSAL");
            if (!exito) {
                JOptionPane.showMessageDialog(vistaRegistro, 
                "Error: No estás en la lista de comensales o la cédula ya está registrada.", 
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
=======
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
>>>>>>> temporal
