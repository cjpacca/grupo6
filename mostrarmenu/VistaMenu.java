package mostrarmenu;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenu extends JFrame {

    private JRadioButton radioManana, radioTarde;
    private JRadioButton radioMenu1, radioMenu2;
    private JTable tablaMenu;
    private JLabel labelSeleccionTurno, labelSeleccionMenu;
    private JPanel panelMenuSeleccion;
    private JScrollPane panelTabla;
    private final String[] columnas = { "Dia De La Semana", "Plato", "Precio" };

    // --- Colores y Estilos ---
    private final Color colorFondo = new Color(245, 245, 245);
    private final Border bordeConPadding = new EmptyBorder(10, 10, 10, 10);

    // Datos de ejemplo para los menús
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

    public VistaMenu() {
        // --- Configuración de la Ventana Principal ---
        setTitle("Menú Estudiantil");
        setSize(650, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        // --- Panel Superior para los Botones de Selección de Turno ---
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

        // --- Panel para Selección de Menú (inicialmente oculto) ---
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

        // Panel contenedor para turno y menu
        JPanel panelControles = new JPanel(new GridLayout(2, 1));
        panelControles.setBackground(colorFondo);
        panelControles.add(panelSuperior);
        panelControles.add(panelMenuSeleccion);
        panelControles.setBorder(new EmptyBorder(5, 5, 5, 5));

        // --- Tabla para mostrar el Menú ---
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][]{}, columnas);
        tablaMenu = new JTable(modeloTabla);
        tablaMenu.setEnabled(false);
        tablaMenu.setRowHeight(25);
        tablaMenu.getTableHeader().setReorderingAllowed(false);
        tablaMenu.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tablaMenu.setShowGrid(true);
        tablaMenu.setGridColor(new Color(220, 220, 220)); // Un gris claro para las líneas

        // Centrar los encabezados de la tabla
        ((DefaultTableCellRenderer) tablaMenu.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        TableColumnModel columnModel = tablaMenu.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(50);

        panelTabla = new JScrollPane(tablaMenu);
        panelTabla.setVisible(false);

        // --- Lógica para actualizar la tabla ---
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

        // --- Añadir los componentes a la ventana ---
        add(panelControles, BorderLayout.NORTH);
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

        // Re-aplicar el tamaño de las columnas
        TableColumnModel columnModel = tablaMenu.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(50);
    }

    public static void main(String[] args) {
        try {
            // Set Nimbus Look and Feel for a modern appearance
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, the application will fall back to the default L&F.
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaMenu().setVisible(true);
            }
        });
    }
}