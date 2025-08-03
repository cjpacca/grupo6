package Vista;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ModificarMenuVista extends JFrame {
    public JList<String> listaMenus;
    public JTextField txtNombreModificar;
    public JComboBox<String> comboModificar;
    public JTextField txtNuevoValor;
    public JButton btnGuardar;
    public JButton btnSalir;

    public ModificarMenuVista() {
        setTitle("Modificar Menú");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        // ...sin fondo personalizado...

        // Panel izquierdo: lista de menús
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get("Menus.txt"));
            int idx = 1;
            for (String l : lineas) {
                String[] partes = l.split(",");
                if (partes.length > 0) {
                    modeloLista.addElement(idx + ". " + partes[0].trim());
                    idx++;
                }
            }
        } catch (Exception ex) {
            modeloLista.addElement("No hay menús disponibles");
        }
        listaMenus = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaMenus);
        scrollLista.setPreferredSize(new Dimension(200, 0));
        // ...sin fondo personalizado...
        panelPrincipal.add(scrollLista, BorderLayout.WEST);

        // Panel derecho con layout absoluto
        JPanel panelDerecho = new JPanel(null); // null layout para coordenadas
        panelDerecho.setPreferredSize(new Dimension(350, 300));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblNombre = new JLabel("Nombre del menú a modificar:");
        lblNombre.setBounds(10, 10, 180, 20);
        panelDerecho.add(lblNombre);

        txtNombreModificar = new JTextField();
        txtNombreModificar.setBounds(10, 35, 200, 25);
        panelDerecho.add(txtNombreModificar);

        JLabel lblModificar = new JLabel("¿Qué desea modificar?");
        lblModificar.setBounds(10, 70, 180, 20);
        panelDerecho.add(lblModificar);

        comboModificar = new JComboBox<>(new String[] {
            "Costo Fijo", "Costo Variable", "Número de bandejas", "%merma", "Turno", "Dia de la semana"
        });
        comboModificar.setBounds(10, 95, 200, 25);
        panelDerecho.add(comboModificar);

        JLabel lblNuevoValor = new JLabel("Nuevo valor:");
        lblNuevoValor.setBounds(10, 130, 180, 20);
        panelDerecho.add(lblNuevoValor);

        txtNuevoValor = new JTextField();
        txtNuevoValor.setBounds(10, 155, 200, 25);
        panelDerecho.add(txtNuevoValor);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 220, 120, 35); // Ubicación exacta abajo a la derecha
        panelDerecho.add(btnGuardar);
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 220, 120, 35);  // Posición junto a "Guardar"
        panelDerecho.add(btnSalir);

        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);

        add(panelPrincipal);
        // Conectar controlador
        new Controlador.ControlModificarMenu(this);
        setVisible(true);
    }
}
