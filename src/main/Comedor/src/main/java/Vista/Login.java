package Vista;

<<<<<<< HEAD
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

>>>>>>> temporal
public class Login extends JFrame {
    public JTextField txtCedula;
    public JPasswordField txtPassword;
    public JButton btnLogin;
<<<<<<< HEAD
    public JButton btnExit;
=======
>>>>>>> temporal

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
<<<<<<< HEAD
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 filas, 2 columnas
=======
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
>>>>>>> temporal
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panel.add(new JLabel("Cédula/ID:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);
<<<<<<< HEAD
        
        btnExit = new JButton("Salir");
        panel.add(btnExit);
        btnExit.setActionCommand(("Cerrar Sesion"));

        btnLogin = new JButton("Iniciar Sesión");
        panel.add(btnLogin); // Va a la celda (2,0)


        this.add(panel);

=======

        btnLogin = new JButton("Iniciar Sesión");
        panel.add(new JLabel()); // Placeholder
        panel.add(btnLogin);

        this.add(panel);
>>>>>>> temporal
    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
<<<<<<< HEAD
        btnExit.addActionListener(controlador);
=======
>>>>>>> temporal
    }
}