package Controlador;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.GestorArchivos;
import Modelo.fileStrategy;
import Vista.Inicial;
import Vista.Login;
import Vista.Registro;

class ControlTest {

    private File usuariosFile;
    private File cedulasAutorizadasFile;
    private GestorArchivos modelo;
    private Inicial vistaPrincipal;
    private Control control;
    private fileStrategy file;
    private Registro vistaRegistro;

    @BeforeEach
    void setUp() {
        file = new fileStrategy();
        vistaRegistro = new Registro(false);
        vistaPrincipal = new Inicial();
        modelo = new GestorArchivos();
        control = new Control(modelo, vistaPrincipal);
    }

    @Test
    void procesarLogin_DatasIncorrectos(){
        assertFalse(file.usuarioYaExiste("7482827356856"));
    }

    @Test
    void Registro_Incorrecto_Comensal(){
        // Arrange:
        control.vistaRegistro = new Registro(false);
        control.vistaRegistro.txtCedula.setText("");
        control.vistaRegistro.txtPassword.setText(""); 
        control.vistaRegistro.txtCampoExtra.setText(""); 
        control.vistaRegistro.txtNombre.setText(""); 

        // Assert:
        assertFalse(control.procesarRegistro());
    }
    @Test
    void Registro_Incorrecto_admin(){
        // Arrange:
        control.vistaRegistro = new Registro(true);
        control.vistaRegistro.txtCedula.setText(""); 
        control.vistaRegistro.txtPassword.setText(""); 
        control.vistaRegistro.txtCampoExtra.setText("");
        control.vistaRegistro.txtNombre.setText(""); 

        // Assert:
        assertFalse(control.procesarRegistro());
    }
    @Test
    void Registro_Cedula_NoAutorizada(){
        // Arrange:
        control.vistaRegistro = new Registro(true);
        control.vistaRegistro.txtCedula.setText("987459827587"); 
        control.vistaRegistro.txtPassword.setText("ttt"); 
        control.vistaRegistro.txtCampoExtra.setText("Caraqueno"); 
        control.vistaRegistro.txtNombre.setText("Aaron"); 

        // Assert:
        assertFalse(control.procesarRegistro());
    }

    @Test
    void Cedula_Registrada(){
                // Arrange:
        control.vistaRegistro = new Registro(false);
        control.vistaRegistro.txtCedula.setText("31871275"); 
        control.vistaRegistro.txtPassword.setText("yuwery"); 
        control.vistaRegistro.txtCampoExtra.setText("kfjdf"); 
        control.vistaRegistro.txtNombre.setText("fdkjf"); 

        // Assert:
        assertFalse(control.procesarRegistro());
    }

    @Test
    void procesarLogin_conDatosIncorrectos_muestraErrorYRegresaAVistaPrincipal() {
        // Arrange:
        control.vistaLogin = new Login();
        control.vistaLogin.txtCedula.setText(""); 
        control.vistaLogin.txtPassword.setText(""); 


        // Assert:
        assertFalse(control.procesarLogin());
    }
}