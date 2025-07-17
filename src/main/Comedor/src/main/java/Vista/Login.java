package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public JTextField txtCedula;
    public JPasswordField txtPassword;
    public JButton btnLogin;

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panel.add(new JLabel("Cédula/ID:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Iniciar Sesión");
        panel.add(new JLabel()); // Placeholder
        panel.add(btnLogin);

        this.add(panel);
    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
    }
}