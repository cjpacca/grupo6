package Vista;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class costos_vista extends JFrame {
    public JTextField txtNombreMenu;
    public JTextField txtCostosFijos;
    public JTextField txtCostosVariables;
    public JButton btnGuardar;

    public costos_vista() {
        setTitle("Ingreso de Costos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel fondoPanel = new JPanel(null);
        fondoPanel.setBackground(Color.DARK_GRAY);
        fondoPanel.setBounds(0, 0, 700, 400);

        Font titulosFont = new Font("Arial", Font.BOLD, 16);

        ImageIcon icono = new ImageIcon("Logo_Costos.png");
        JLabel lblImagen = new JLabel(icono);
        lblImagen.setBounds(350, 20, 300, 300);
        fondoPanel.add(lblImagen);


        JLabel lblNombreMenu = new JLabel("Nombre del menú:");
        lblNombreMenu.setBounds(50, 30, 200, 25);
        lblNombreMenu.setForeground(Color.WHITE);
        lblNombreMenu.setFont(titulosFont);
        fondoPanel.add(lblNombreMenu);

        txtNombreMenu = new JTextField();
        txtNombreMenu.setBounds(50, 60, 220, 25);
        fondoPanel.add(txtNombreMenu);

        JLabel lblCostosFijos = new JLabel("Costos Fijos Totales:");
        lblCostosFijos.setBounds(50, 110, 200, 25);
        lblCostosFijos.setForeground(Color.WHITE);
        lblCostosFijos.setFont(titulosFont);
        fondoPanel.add(lblCostosFijos);

        txtCostosFijos = new JTextField();
        txtCostosFijos.setBounds(50, 140, 220, 25);
        fondoPanel.add(txtCostosFijos);

        JLabel lblCostosVariables = new JLabel("Costos Variables Totales:");
        lblCostosVariables.setBounds(50, 190, 200, 25);
        lblCostosVariables.setForeground(Color.WHITE);
        lblCostosVariables.setFont(titulosFont);
        fondoPanel.add(lblCostosVariables);

        txtCostosVariables = new JTextField();
        txtCostosVariables.setBounds(50, 220, 220, 25);
        fondoPanel.add(txtCostosVariables);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(90, 290, 120, 30);
        btnGuardar.setFont(titulosFont);
        fondoPanel.add(btnGuardar);

        setContentPane(fondoPanel);
    }
}