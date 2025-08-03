package Vista;

import Modelo.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vistaMenu extends JFrame {
    private JRadioButton radioManana, radioTarde;
    private JLabel labelSeleccionTurno;
    private ActionListener controlador;
    private final Color colorFondo = new Color(230, 230, 230);
    private final Color colorMorado = new Color(128, 0, 128);
    private final Border bordeConPadding = new EmptyBorder(2, 2, 10, 10);

    private JTable tablaMenus;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;

    public vistaMenu(Comensal a, ActionListener controlador) {
        this.controlador = controlador;
        setTitle("Menú Estudiantil");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelSuperior.setBackground(colorFondo);
        labelSeleccionTurno = new JLabel("Seleccione un turno:");
        radioManana = new JRadioButton("Mañana");
        radioTarde = new JRadioButton("Tarde");

        ButtonGroup grupoTurnos = new ButtonGroup();
        grupoTurnos.add(radioManana);
        grupoTurnos.add(radioTarde);

        panelSuperior.add(labelSeleccionTurno);
        panelSuperior.add(radioManana);
        panelSuperior.add(radioTarde);

        // Panel para los días de la semana con el mismo estilo
        JPanel panelDiasSemana = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelDiasSemana.setBackground(colorFondo);
        JLabel labelSeleccionDia = new JLabel("Seleccione un día:");
        JRadioButton radioLunes = new JRadioButton("Lunes");
        JRadioButton radioMartes = new JRadioButton("Martes");
        JRadioButton radioMiercoles = new JRadioButton("Miércoles");
        JRadioButton radioJueves = new JRadioButton("Jueves");
        JRadioButton radioViernes = new JRadioButton("Viernes");
        ButtonGroup grupoDiasSemana = new ButtonGroup();
        grupoDiasSemana.add(radioLunes);
        grupoDiasSemana.add(radioMartes);
        grupoDiasSemana.add(radioMiercoles);
        grupoDiasSemana.add(radioJueves);
        grupoDiasSemana.add(radioViernes);
        panelDiasSemana.add(labelSeleccionDia);
        panelDiasSemana.add(radioLunes);
        panelDiasSemana.add(radioMartes);
        panelDiasSemana.add(radioMiercoles);
        panelDiasSemana.add(radioJueves);
        panelDiasSemana.add(radioViernes);

        JPanel panelControles = new JPanel(new GridLayout(2, 1));
        panelControles.setBackground(colorFondo);
        panelControles.add(panelSuperior);
        panelControles.add(panelDiasSemana);
        panelControles.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panelSuperiorGeneral = new JPanel(new BorderLayout());
        panelSuperiorGeneral.setBackground(colorFondo);
        JLabel labelSaldo = new JLabel("Saldo actual: $" + a.getSaldo());
        labelSaldo.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelSaldo.setBorder(new EmptyBorder(10, 20, 10, 10));

        panelSuperiorGeneral.add(panelControles, BorderLayout.CENTER);
        panelSuperiorGeneral.add(labelSaldo, BorderLayout.WEST);

        JButton botonVolver = new JButton("← Volver");
        botonVolver.setFont(new Font("SansSerif", Font.BOLD, 20));
        botonVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        botonVolver.setContentAreaFilled(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVolver.setToolTipText("Volver");

        JPanel panelBotonVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonVolver.setBackground(colorFondo); 
        panelBotonVolver.add(botonVolver);

        botonVolver.addActionListener(e -> {
            Window ventanaActual = SwingUtilities.getWindowAncestor(botonVolver);
            ventanaActual.dispose();
            vistaComensal vista = new vistaComensal(a, this.controlador); 
            vista.setVisible(true);
        });

        JPanel panelNorteCompleto = new JPanel(new BorderLayout());
        panelNorteCompleto.setBackground(colorFondo);
        panelNorteCompleto.add(panelBotonVolver, BorderLayout.NORTH);
        panelNorteCompleto.add(panelSuperiorGeneral, BorderLayout.CENTER);

        add(panelNorteCompleto, BorderLayout.NORTH);
        ((JPanel) getContentPane()).setBorder(bordeConPadding);

        // --- TABLA DE MENUS SOLO NOMBRE Y PRECIO ---
        String[] columnas = {"Nombre del Menú", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMenus = new JTable(modeloTabla);
        tablaMenus.setFillsViewportHeight(true);
        scrollTabla = new JScrollPane(tablaMenus);
        scrollTabla.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollTabla, BorderLayout.CENTER);

        // --- CONTROLADOR DE SELECCIÓN ---
        ActionListener actualizarTablaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String turno = radioManana.isSelected() ? "Mañana" : (radioTarde.isSelected() ? "Tarde" : null);
                String dia = null;
                if (radioLunes.isSelected()) dia = "Lunes";
                else if (radioMartes.isSelected()) dia = "Martes";
                else if (radioMiercoles.isSelected()) dia = "Miercoles";
                else if (radioJueves.isSelected()) dia = "Jueves";
                else if (radioViernes.isSelected()) dia = "Viernes";

                modeloTabla.setRowCount(0); // Limpiar tabla
                if (turno != null && dia != null) {
                    // Usar el MostrarMenuControl del controlador principal si está disponible
                    Controlador.MostrarMenuControl menuControl = null;
                    if (controlador instanceof Controlador.Control) {
                        menuControl = ((Controlador.Control) controlador).mostrarMenuControl;
                    } else {
                        menuControl = new Controlador.MostrarMenuControl();
                    }
                    java.util.List<String[]> menus = menuControl.obtenerMenusPorTurnoYDia(turno, dia);
                    String tipoComensal = menuControl.getTipoComensal();
                    for (String[] fila : menus) {
                        String[] filaTabla = new String[2];
                        filaTabla[0] = (fila.length > 0) ? fila[0].trim() : "";
                        // Determinar el precio según el tipo de comensal
                        String precio = "";
                        if (tipoComensal != null) {
                            switch (tipoComensal) {
                                case "Estudiante":
                                    if (fila.length > 2) precio = fila[2].trim();
                                    break;
                                case "Profesor":
                                    if (fila.length > 3) precio = fila[3].trim();
                                    break;
                                case "Empleado":
                                    if (fila.length > 4) precio = fila[4].trim();
                                    break;
                            }
                        }
                        filaTabla[1] = precio;
                        modeloTabla.addRow(filaTabla);
                    }
                }
            }
        };

        radioManana.addActionListener(actualizarTablaListener);
        radioTarde.addActionListener(actualizarTablaListener);
        radioLunes.addActionListener(actualizarTablaListener);
        radioMartes.addActionListener(actualizarTablaListener);
        radioMiercoles.addActionListener(actualizarTablaListener);
        radioJueves.addActionListener(actualizarTablaListener);
        radioViernes.addActionListener(actualizarTablaListener);
    }
}