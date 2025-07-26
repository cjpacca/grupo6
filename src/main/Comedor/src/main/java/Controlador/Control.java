package Controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Modelo.Administrador;
import Modelo.Comensal;
import Modelo.EstadoRegistro;
import Modelo.GestorArchivos;
import Modelo.Usuario;
import Vista.Inicial;
import Vista.Login;
import Vista.Registro;
import Vista.costos_vista;
import Vista.vistaComensal;


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

        if (a instanceof Administrador) {
            b = (Administrador) a;
            costos_vista view = new costos_vista();
            view.setVisible(true);
            vistaLogin.dispose();
            return true;
        } else if (a instanceof Comensal) {
            c = (Comensal) a;
            vistaComensal comen = new vistaComensal(c, this);
            comen.setVisible(true);
            vistaLogin.dispose();
            return true;
        } else {
            JOptionPane.showMessageDialog(vistaLogin, "Cédula o contraseña incorrectas.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            // No cerramos la ventana de login para que el usuario pueda intentarlo de nuevo.
            // vistaLogin.dispose();
            // vistaPrincipal.setVisible(true);
            return false;
        }
    }

    public boolean procesarRegistro() {
        String nombre = vistaRegistro.txtNombre.getText();
        String cedula = vistaRegistro.txtCedula.getText();
        String password = new String(vistaRegistro.txtPassword.getPassword());
        String campoExtra = vistaRegistro.txtCampoExtra.getText();

        // **Validaciones robustas para el registro**
        if (nombre.isEmpty() || cedula.isEmpty() || password.isEmpty() || campoExtra.isEmpty()) {
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
        if (!validarTextoSimple(campoExtra)) {
            JOptionPane.showMessageDialog(vistaRegistro, "El campo de cargo/facultad solo puede contener letras y espacios.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarPassword(password)) {
            JOptionPane.showMessageDialog(vistaRegistro, "La contraseña debe tener entre 5 y 10 caracteres y contener letras y números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean esAdmin = vistaRegistro.getTitle().contains("Administrador");
        Usuario nuevoUsuario;

        if (esAdmin) {
            nuevoUsuario = new Administrador(nombre, cedula, password, campoExtra);
        } else {
            nuevoUsuario = new Comensal(nombre, cedula, password, campoExtra);
        }

        EstadoRegistro resultado = modelo.registrarUsuario(nuevoUsuario, nuevoUsuario.getTipo());

        switch (resultado) {
            case EXITO:
                JOptionPane.showMessageDialog(vistaRegistro, "¡Registro exitoso! Ahora puede iniciar sesión.", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
                vistaRegistro.dispose();
                vistaPrincipal.setVisible(true);
                return true;
            case USUARIO_YA_EXISTE:
                JOptionPane.showMessageDialog(vistaRegistro, "Error: La cédula ya se encuentra registrada.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                return false;
            case CEDULA_NO_AUTORIZADA:
                JOptionPane.showMessageDialog(vistaRegistro, "Error: Su cédula no está en la lista de usuarios autorizados para el registro.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                return false;
            case ERROR_ESCRITURA:
                JOptionPane.showMessageDialog(vistaRegistro, "Ocurrió un error inesperado al guardar los datos. Contacte al administrador.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
                return false;
            default:
                JOptionPane.showMessageDialog(vistaRegistro, "Ocurrió un error desconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }
}