package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Registro extends JFrame {
    public JTextField txtCedula, txtNombre;
    public JPasswordField txtPassword;
    public JComboBox<String> CampoExtra; // Para Comensal
    public JTextField txtCampoExtra; // Para Administrador
    public JButton btnRegistrar;
    public JButton btnSalir;

    public Registro(boolean esAdmin) {
        String userType = esAdmin ? "Administrador" : "Comensal";
        String labelExtra = esAdmin ? "Cargo:" : "Tipo:";

        setTitle("Registro - " + userType);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Cédula/ID:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        panel.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);
        
        panel.add(new JLabel(labelExtra));
        
        if (esAdmin) {
            // Campo de texto para Administrador
            txtCampoExtra = new JTextField();
            panel.add(txtCampoExtra);
        } else {
            // ComboBox para Comensal
            CampoExtra = new JComboBox<>(new String[]{"Estudiante", "Profesor", "Empleado"});
            panel.add(CampoExtra);
        }
        
        btnSalir = new JButton("Salir");
        btnSalir.setActionCommand("Cerrar Sesion");
        panel.add(btnSalir);

        btnRegistrar = new JButton("Registrar");
        //btnRegistrar.setActionCommand("Registrar");
        panel.add(btnRegistrar);

        this.add(panel);
    }

    // Método para obtener el valor del campo extra según el tipo de usuario
    public String getCampoExtra() {
        if (CampoExtra != null) {
            return CampoExtra.getSelectedItem().toString();
        } else {
            return txtCampoExtra.getText();
        }
    }

    public void setControlador(ActionListener controlador) {
        btnRegistrar.addActionListener(controlador);
        btnSalir.addActionListener(controlador);
    }
}