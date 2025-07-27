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
    private JRadioButton radioMenu1, radioMenu2;
    private JTable tablaMenu;
    private JLabel labelSeleccionTurno, labelSeleccionMenu;
    private JPanel panelMenuSeleccion;
    private JScrollPane panelTabla;
    private ActionListener controlador;
    private final String[] columnas = { "Dia De La Semana", "Plato", "Precio" };


    private final Color colorFondo = new Color(230, 230, 230);
    private final Color colorMorado = new Color(128, 0, 128);
    private final Border bordeConPadding = new EmptyBorder(2, 2, 10, 10);


    private final Object[][] menuManana1 = {
            {"Lunes","Sandwich y malta y/o jugo de naranja", "$5"},
            {"Martes","Empanadas de pollo/carne y malta", "$2"},
            {"Miercoles","Arepa con papelon con limon ", "$3"},
            {"Jueves","Pan tostado con revoltillo, tocineta, aguacate y cafe", "$4"},
            {"Viernes","Tortillas de espinaca y queso", "$4"},
    };

    private final Object[][] menuManana2 = {
            {"Lunes","Desayuno Americano", "$6"},
            {"Martes","Omelette de Jamon y Queso", "$4"},
            {"Miercoles","Panquecas con sirope", "$4"},
            {"Jueves","Croissant con cafe", "$3"},
            {"Viernes","Yogurt con granola y frutas", "$3"},
    };

    private final Object[][] menuTarde1 = {
            {"Lunes","Pollo a la plancha con arroz integral y ensalada mixta", "$7"},
            {"Martes","Pescado al horno con pure y esparragos salteados", "$10"},
            {"Miercoles","Sopa de verduras", "$1"},
            {"Jueves","Ensalada" ,"$2"},
            {"Viernes","Carne con ensalada", "$5 "},
    };

    private final Object[][] menuTarde2 = {
            {"Lunes","Pasta con salsa de tomate y albahaca", "$8"},
            {"Martes","Lentejas con arroz", "$6"},
            {"Miercoles","Crema de auyama", "$5"},
            {"Jueves","Tacos de carne", "$7"},
            {"Viernes","Hamburguesa con papas fritas", "$9"},
    };

    public vistaMenu(Comensal a, ActionListener controlador) {

        this.controlador = controlador;
        setTitle("Menú Estudiantil");
        setSize(650, 420);
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

        panelMenuSeleccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelMenuSeleccion.setBackground(colorFondo);
        labelSeleccionMenu = new JLabel("Seleccione un menú:");
        radioMenu1 = new JRadioButton("Menu 1");
        radioMenu2 = new JRadioButton("Menu 2");

        ButtonGroup grupoMenus = new ButtonGroup();
        grupoMenus.add(radioMenu1);
        grupoMenus.add(radioMenu2);

        panelMenuSeleccion.add(labelSeleccionMenu);
        panelMenuSeleccion.add(radioMenu1);
        panelMenuSeleccion.add(radioMenu2);
        panelMenuSeleccion.setVisible(false);

        JPanel panelControles = new JPanel(new GridLayout(2, 1));
        panelControles.setBackground(colorFondo);
        panelControles.add(panelSuperior);
        panelControles.add(panelMenuSeleccion);
        panelControles.setBorder(new EmptyBorder(5, 5, 5, 5));

        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][]{}, columnas);
        tablaMenu = new JTable(modeloTabla);
        tablaMenu.setEnabled(false);
        tablaMenu.setRowHeight(25);
        tablaMenu.getTableHeader().setReorderingAllowed(false);
        tablaMenu.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tablaMenu.setShowGrid(true);
        tablaMenu.setGridColor(new Color(220, 220, 220)); 

        ((DefaultTableCellRenderer) tablaMenu.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        TableColumnModel columnModel = tablaMenu.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(50);

        panelTabla = new JScrollPane(tablaMenu);
        panelTabla.setVisible(false);

        ActionListener listenerTurno = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMenuSeleccion.setVisible(true);
                grupoMenus.clearSelection();
                panelTabla.setVisible(false);
                revalidate();
                repaint();
            }
        };
        radioManana.addActionListener(listenerTurno);
        radioTarde.addActionListener(listenerTurno);

        ActionListener listenerMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
                panelTabla.setVisible(true);
                revalidate();
                repaint();
            }
        };
        radioMenu1.addActionListener(listenerMenu);
        radioMenu2.addActionListener(listenerMenu);

        
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
        add(panelTabla, BorderLayout.CENTER);
        ((JPanel) getContentPane()).setBorder(bordeConPadding);
    }

    private void actualizarTabla() {
        DefaultTableModel modelo;
        Object[][] datos = {};

        if (radioManana.isSelected()) {
            if (radioMenu1.isSelected()) {
                datos = menuManana1;
            } else if (radioMenu2.isSelected()) {
                datos = menuManana2;
            }
        } else if (radioTarde.isSelected()) {
            if (radioMenu1.isSelected()) {
                datos = menuTarde1;
            } else if (radioMenu2.isSelected()) {
                datos = menuTarde2;
            }
        }

        modelo = new DefaultTableModel(datos, columnas);
        tablaMenu.setModel(modelo);

        TableColumnModel columnModel = tablaMenu.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(50);
    }
    
}