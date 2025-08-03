package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class AdminMenuView extends JFrame {
    public JButton btnAgregarMenu;
    public JButton btnModificarMenu;
    public JButton btnEliminarMenu;
    public JButton btnReconocimiento;
    public JButton btnCerrarSesion;

    public AdminMenuView() {
        // --- Configuración de la ventana ---
        setTitle("Menú Principal del Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tamaño fijo
        setSize(700, 400);

        // Fondo de la ventana principal
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        // --- Panel de Botones (a la izquierda) ---
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));
        panelBotones.setBackground(Color.DARK_GRAY);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Crear y estilizar los botones ---
        btnAgregarMenu = new JButton("Agregar menú");
        btnModificarMenu = new JButton("Modificar menú");
        btnEliminarMenu = new JButton("Eliminar menú");
        btnReconocimiento = new JButton("Reconocimiento facial");
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnAgregarMenu.setActionCommand("AgregarMenu");
        btnEliminarMenu.setActionCommand("EliminarMenu");
        btnReconocimiento.setActionCommand("ReconocimientoFacial");
        btnModificarMenu.setActionCommand("ModificarMenu");
        btnCerrarSesion.setActionCommand("Cerrar sesion");

        estilizarBoton(btnAgregarMenu);
        estilizarBoton(btnModificarMenu);
        estilizarBoton(btnEliminarMenu);
        estilizarBoton(btnReconocimiento);
        estilizarBoton(btnCerrarSesion);

        panelBotones.add(btnAgregarMenu);
        panelBotones.add(btnModificarMenu);
        panelBotones.add(btnEliminarMenu);
        panelBotones.add(btnReconocimiento);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void estilizarBoton(JButton boton) {
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

    public void setControlador(ActionListener controlador) {
        btnAgregarMenu.addActionListener(controlador);
        btnModificarMenu.addActionListener(controlador);
        btnEliminarMenu.addActionListener(controlador);
        btnReconocimiento.addActionListener(controlador);
        btnCerrarSesion.addActionListener(controlador);
    }
}