package Vista;

import java.awt.BorderLayout; // Asegúrate de que esta clase exista en tu paquete Modelo
import java.awt.Color;
import java.awt.Cursor; // Import necesario para el borde
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Modelo.Comensal;

public class vistaComensal extends JFrame {

    public vistaComensal(Comensal a) {
        setTitle("Menú Principal del Restaurante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);

        // Se obtiene el panel principal de la ventana (contentPane) para cambiar su color.
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        // --- 1. Panel de Botone ---
        JPanel panelBotones = new JPanel();

        // Se usan gaps razonables (10px). Los valores 700, 400 eran para el tamaño, no para los gaps.
        panelBotones.setLayout(new GridLayout(5, 1, 20, 20));
        panelBotones.setBackground(Color.DARK_GRAY); // Fondo del panel de botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Un pequeño margen

        // --- Crear y estilizar los botones ---
        JButton btnVerMenu = new JButton("Ver Menú semanal");

        estilizarBoton(btnVerMenu);

        panelBotones.add(btnVerMenu);
        add(panelBotones);

        btnVerMenu.addActionListener(e -> {
            vistaMenu panelContenidoMenu = new vistaMenu(a);
            panelContenidoMenu.setVisible(true);
            setVisible(false);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void estilizarBoton(JButton boton) {
    // Estilo de fuente: Arial Negrita tamaño 20
    boton.setFont(new Font("Segoe UI", Font.BOLD, 22));
    boton.setBackground(new Color(33, 150, 243));
    boton.setForeground(Color.BLACK);
    boton.setFocusPainted(false);
    boton.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    boton.setOpaque(true);
    boton.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true));

    boton.setBackground(Color.WHITE);

    boton.setOpaque(true);
    
    boton.setFocusPainted(false);
    
    boton.setPreferredSize(new Dimension(100, 60));
    
    boton.setHorizontalAlignment(SwingConstants.CENTER);
    boton.setVerticalAlignment(SwingConstants.CENTER);
    
    boton.setMargin(new Insets(10, 20, 10, 20)); 
}

}