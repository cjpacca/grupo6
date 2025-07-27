package Vista;

import java.awt.BorderLayout; // Asegúrate de que esta clase exista en tu paquete Modelo
import java.awt.Color;
import java.awt.Dimension; // Import necesario para el borde
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Modelo.Comensal;

public class vistaComensal extends JFrame {

    public vistaComensal(Comensal a, ActionListener controlador) {
        // --- Configuración de la ventana ---
        setTitle("Menú Principal del Comensal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // **CAMBIO 1: Tamaño fijo de la ventana**
        // Se establece un tamaño fijo de 700x400 y se elimina pack().
        setSize(700, 400);

        // **CAMBIO 2: Fondo de la ventana principal**
        // Se obtiene el panel principal de la ventana (contentPane) para cambiar su color.
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        // --- 1. Panel de Botones (a la izquierda) ---
        JPanel panelBotones = new JPanel();

        // **CAMBIO 3: Corregir GridLayout y establecer fondo**
        // Se usan gaps razonables (10px). Los valores 700, 400 eran para el tamaño, no para los gaps.
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));
        panelBotones.setBackground(Color.DARK_GRAY); // Fondo del panel de botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Un pequeño margen

        // --- Crear y estilizar los botones ---
        JButton btnVerMenu = new JButton("Ver Menú semanal");
        JButton btnRecargarSaldo = new JButton("Recargar Saldo");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        estilizarBoton(btnVerMenu);
        estilizarBoton(btnRecargarSaldo);
        estilizarBoton(btnCerrarSesion);

        panelBotones.add(btnVerMenu);
        panelBotones.add(btnRecargarSaldo);
        panelBotones.add(btnCerrarSesion);
        add(panelBotones);

        btnVerMenu.addActionListener(e -> {
            vistaMenu panelContenidoMenu = new vistaMenu(a, controlador);
            panelContenidoMenu.setVisible(true);
            setVisible(false);
        });

        btnRecargarSaldo.addActionListener(e -> {
            String montoStr = JOptionPane.showInputDialog(this, "Ingrese el monto a recargar:", "Recargar Saldo", JOptionPane.PLAIN_MESSAGE);
            if (montoStr != null && !montoStr.trim().isEmpty()) {
                try {
                    Float monto = Float.parseFloat(montoStr);
                    if (monto > 0) {
                        a.setSaldo(a.getSaldo() + monto);
                        JOptionPane.showMessageDialog(this, "Saldo recargado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese solo números.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnCerrarSesion.setActionCommand("Cerrar Sesion");
        btnCerrarSesion.addActionListener(controlador);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void estilizarBoton(JButton boton) {
    // Estilo de fuente: Arial Negrita tamaño 20
    boton.setFont(new Font("Arial", Font.BOLD, 20));
    
    Border bordeMagenta = BorderFactory.createLineBorder(Color.MAGENTA, 2);
    boton.setBorder(bordeMagenta);

    boton.setForeground(Color.DARK_GRAY);

    boton.setBackground(Color.WHITE);

    boton.setOpaque(true);
    
    boton.setFocusPainted(false);
    
    boton.setPreferredSize(new Dimension(100, 60));
    
    boton.setHorizontalAlignment(SwingConstants.CENTER);
    boton.setVerticalAlignment(SwingConstants.CENTER);
    
    boton.setMargin(new Insets(10, 20, 10, 20)); 
}

}