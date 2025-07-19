package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicial extends JFrame {
    public JButton btnLogin, btnRegistroComensal, btnRegistroAdmin;

    public Inicial() {
        setTitle("Sistema de Comedor Universitario");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color fondo = new Color(227, 242, 253);
        Color colorBoton = new Color(33, 150, 243);
        Color colorBotonHover = new Color(25, 118, 210);

        JPanel fondoPanel = new JPanel(new GridBagLayout());
        fondoPanel.setBackground(fondo);

        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 0));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        // --- PANEL IZQUIERDO (IMÁGENES) ---
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.WHITE);

        ImageIcon logoOriginal = new ImageIcon("logoUCV.jpg");
        Image logoImage = logoOriginal.getImage();
        Image logo = logoImage.getScaledInstance(150, 140, Image.SCALE_SMOOTH);
        ImageIcon logoFinal = new ImageIcon(logo);
        JLabel LogoU = new JLabel(logoFinal);
        LogoU.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon ucvOriginal = new ImageIcon("UCV.jpg");
        Image ucvImage = ucvOriginal.getImage(); // Extraer la imagen
        Image ucv = ucvImage.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        ImageIcon ucvFinal = new ImageIcon(ucv);
        JLabel UCV = new JLabel(ucvFinal);
        UCV.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelIzquierdo.add(LogoU);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(UCV);

        // --- PANEL DERECHO (BOTONES) ---
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Sistema de Comedor Universitario");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(colorBotonHover);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDerecho.add(lblTitulo);
        panelDerecho.add(Box.createVerticalStrut(30));

        btnLogin = new JButton("Iniciar Sesión");
        btnRegistroComensal = new JButton("Registrarme como Comensal");
        btnRegistroAdmin = new JButton("Registrarme como Administrador");

        Font botonFont = new Font("Segoe UI", Font.BOLD, 18);

        for (JButton btn : new JButton[]{btnLogin, btnRegistroComensal, btnRegistroAdmin}) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
            btn.setBackground(colorBoton);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setOpaque(true);
            btn.setBorder(BorderFactory.createLineBorder(colorBoton, 2, true));
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(colorBotonHover);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(colorBoton);
                }
            });
            btn.setMaximumSize(new Dimension(400, 60));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelDerecho.add(btn);
            panelDerecho.add(Box.createVerticalStrut(28));
        }
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);

        fondoPanel.add(panelPrincipal);
        this.add(fondoPanel);
    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
        btnRegistroComensal.addActionListener(controlador);
        btnRegistroAdmin.addActionListener(controlador);
    }
}