package Controlador;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Control implements ActionListener {

    private final GestorArchivos modelo;
    public final Inicial vistaPrincipal;

    public Login vistaLogin;
    public Registro vistaRegistro;
    Usuario a;
    Administrador b;
    Comensal c;
    public MostrarMenuControl mostrarMenuControl;

    public Control(GestorArchivos modelo, Inicial vistaPrincipal) {
        this.modelo = modelo;
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.setControlador(this);
        this.mostrarMenuControl = new MostrarMenuControl();
    }

    public void iniciar() {
        vistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        // ✅ INICIO DE LA CORRECCIÓN
        if (a instanceof Administrador) {
            b = (Administrador) a;
            AdminMenuView adminView = new AdminMenuView();
            ControlAdmin adminController = new ControlAdmin(adminView, modelo);
            adminView.setVisible(true);
            vistaLogin.dispose();
            return true;
        // ✅ FIN DE LA CORRECCIÓN
        } else if (a instanceof Comensal) {
            c = (Comensal) a;
            // Guardar el tipo de comensal en MostrarMenuControl
            mostrarMenuControl.setTipoComensal(c);
            tipo = 2;
        } else {
            tipo = 3;
        }

        switch (tipo) {
            case 2:
                vistaComensal comen = new vistaComensal(c, this);
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