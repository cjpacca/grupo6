
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

public class Registro extends JFrame {
    public JTextField txtCedula, txtNombre;
    public JPasswordField txtPassword;
    public JTextField txtCampoExtra; // Será "Facultad" o "Cargo"
    public JButton btnRegistrar;
    // Referencia a la ventana principal para regresar
    private Inicial ventanaInicial;

    public Registro(boolean esAdmin, Inicial ventanaInicial) {
        this.ventanaInicial = ventanaInicial;
        String userType = esAdmin ? "Administrador" : "Comensal";
        String labelExtra = esAdmin ? "Cargo:" : "Facultad:";

        setTitle("Registro - " + userType);
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color fondo = new Color(227, 242, 253);
        Color colorBoton = new Color(33, 150, 243);
        Color colorBotonHover = new Color(25, 118, 210);
        Color colorTexto = new Color(33, 33, 33);
        Color colorCampo = Color.WHITE;

        JPanel fondoPanel = new JPanel(new GridBagLayout());
        fondoPanel.setBackground(fondo);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        panel.setPreferredSize(new Dimension(600, 420));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Registro de " + userType);
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
        txtCedula.setPreferredSize(new Dimension(320, 40));
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
        txtPassword.setPreferredSize(new Dimension(320, 40));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblNombre, gbc);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtNombre.setBackground(colorCampo);
        txtNombre.setPreferredSize(new Dimension(320, 40));
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(txtNombre, gbc);

        JLabel lblExtra = new JLabel(labelExtra);
        lblExtra.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblExtra.setForeground(colorTexto);
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(lblExtra, gbc);

        txtCampoExtra = new JTextField();
        txtCampoExtra.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        txtCampoExtra.setBackground(colorCampo);
        txtCampoExtra.setPreferredSize(new Dimension(320, 40));
        txtCampoExtra.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(txtCampoExtra, gbc);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnRegistrar.setBackground(colorBoton);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setOpaque(true);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(colorBoton, 2, true));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(colorBotonHover);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(colorBoton);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnCancelar.setBackground(new Color(220, 53, 69)); // Rojo suave
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setOpaque(true);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2, true));
        btnCancelar.addActionListener(e -> {
            dispose();
            if (this.ventanaInicial != null) {
                this.ventanaInicial.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 0));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        fondoPanel.add(panel);
        this.add(fondoPanel);
    }

    public void setControlador(ActionListener controlador) {
        btnRegistrar.addActionListener(controlador);
        
    }
}
