
package Vista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
    // Referencia a la ventana principal para regresar
    private Inicial ventanaInicial;

    public Login(Inicial ventanaInicial) {
        this.ventanaInicial = ventanaInicial;
        setTitle("Inicio de Sesión");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color fondo = new Color(227, 242, 253); // Azul claro
        Color colorBoton = new Color(33, 150, 243); // Azul más suave
        Color colorBotonHover = new Color(25, 118, 210); // Azul oscuro
        Color colorTexto = new Color(33, 33, 33); // Gris oscuro
        Color colorCampo = Color.WHITE;

        JPanel fondoPanel = new JPanel(new GridBagLayout());
        fondoPanel.setBackground(fondo);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        panel.setPreferredSize(new Dimension(420, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Inicio de Sesión");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(colorBotonHover);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        JLabel lblCedula = new JLabel("Cédula/ID:");
        lblCedula.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCedula.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblCedula, gbc);

        txtCedula = new JTextField();
        txtCedula.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtCedula.setBackground(colorCampo);
        txtCedula.setPreferredSize(new Dimension(260, 40));
        txtCedula.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtCedula, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPassword.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtPassword.setBackground(colorCampo);
        txtPassword.setPreferredSize(new Dimension(260, 40));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnLogin.setBackground(colorBoton);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setOpaque(true);
        btnLogin.setBorder(BorderFactory.createLineBorder(colorBoton, 2, true));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(colorBotonHover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(colorBoton);
            }
        });

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnRegresar.setBackground(new Color(220, 53, 69)); // Rojo suave
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setOpaque(true);
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2, true));
        btnRegresar.addActionListener(e -> {
            dispose();
            if (this.ventanaInicial != null) {
                this.ventanaInicial.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 0));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnLogin);
        panelBotones.add(btnRegresar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        fondoPanel.add(panel);
        this.add(fondoPanel);
    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
    }
}