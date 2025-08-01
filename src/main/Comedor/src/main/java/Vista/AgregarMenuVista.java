package Vista;

import javax.swing.*;
import java.awt.*;

public class AgregarMenuVista extends JFrame {
    public JTextField txtNombre;
    public JTextField txtCostoFijo;
    public JTextField txtCostoVariable;
    public JTextField txtNumeroBandejas;
    public JTextField txtMerma;
    public JComboBox<String> comboTurno;
    public JComboBox<Integer> comboNumeroMenu;
    public JButton btnGuardar;

    public AgregarMenuVista() {
        setTitle("Agregar Menú");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 370);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Costo Fijo:"));
        txtCostoFijo = new JTextField();
        panel.add(txtCostoFijo);

        panel.add(new JLabel("Costo Variable:"));
        txtCostoVariable = new JTextField();
        panel.add(txtCostoVariable);

        panel.add(new JLabel("Número de Bandejas:"));
        txtNumeroBandejas = new JTextField();
        panel.add(txtNumeroBandejas);

        panel.add(new JLabel("% Merma:"));
        txtMerma = new JTextField();
        panel.add(txtMerma);

        panel.add(new JLabel("Turno:"));
        comboTurno = new JComboBox<>(new String[] {"Mañana", "Tarde"});
        panel.add(comboTurno);

        panel.add(new JLabel("Número de menú:"));
        comboNumeroMenu = new JComboBox<>(new Integer[] {1, 2});
        panel.add(comboNumeroMenu);

        btnGuardar = new JButton("Guardar");
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnGuardar);

        add(panel);
        setVisible(true);
    }
}
