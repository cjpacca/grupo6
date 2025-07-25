package Vista;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registro extends JFrame {
    public JTextField txtCedula, txtNombre;
    public JPasswordField txtPassword;
    public JTextField txtCampoExtra; // Será "Facultad" o "Cargo"
    public JButton btnRegistrar;
    public JButton btnSalir;

    public Registro(boolean esAdmin) {
        String userType = esAdmin ? "Administrador" : "Comensal";
        String labelExtra = esAdmin ? "Cargo:" : "Facultad:";

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
        txtCampoExtra = new JTextField();
        panel.add(txtCampoExtra);
        
        btnSalir = new JButton("Salir");
        panel.add(btnSalir);
        btnSalir.setActionCommand(("Cerrar Sesion"));

        btnRegistrar = new JButton("Registrar");
        panel.add(btnRegistrar);

        this.add(panel);
    }

    public void setControlador(ActionListener controlador) {
        btnRegistrar.addActionListener(controlador);
        btnSalir.addActionListener(controlador);
    }
}
