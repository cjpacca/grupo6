package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Inicial extends JFrame {
    public JButton btnLogin, btnRegistroComensal, btnRegistroAdmin;

    public Inicial() {
        setTitle("Sistema de Comedor Universitario");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(20, 0));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.DARK_GRAY);

        // --- PANEL IZQUIERDO (IMÁGENES) ---
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.DARK_GRAY);

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
        panelIzquierdo.add(UCV);

        // --- PANEL DERECHO (BOTONES) ---
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(Color.DARK_GRAY);

        btnLogin = new JButton("Iniciar Sesión");
        btnRegistroComensal = new JButton("Registrarme como Comensal");
        btnRegistroAdmin = new JButton("Registrarme como Administrador");

        Font botonFont = new Font("Arial", Font.BOLD, 20);

        for (JButton btn : new JButton[]{btnLogin, btnRegistroComensal, btnRegistroAdmin}) {
            btn.setFont(botonFont);
                btn.setBackground(Color.white);
            btn.setForeground(Color.DARK_GRAY);
            btn.setMaximumSize(new Dimension(420, 200));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
            panelDerecho.add(btn);
            panelDerecho.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        panelDerecho.add(Box.createVerticalGlue(), 0);
        panelDerecho.add(Box.createVerticalGlue());
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        this.add(panelPrincipal);
    }

    public void setControlador(ActionListener controlador) {
        btnLogin.addActionListener(controlador);
        btnRegistroComensal.addActionListener(controlador);
        btnRegistroAdmin.addActionListener(controlador);
    }
}