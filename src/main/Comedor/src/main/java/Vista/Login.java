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

public class Login extends JFrame {
    public JTextField txtCedula;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public JButton btnExit;

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 filas, 2 columnas
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panel.add(new JLabel("Cédula/ID:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);
        
        btnExit = new JButton("Salir");
        panel.add(btnExit);
        btnExit.setActionCommand(("Cerrar Sesion"));

        btnLogin = new JButton("Iniciar Sesión");
        panel.add(btnLogin); // Va a la celda (2,0)


        this.add(panel);

    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
        btnExit.addActionListener(controlador);
    }
}