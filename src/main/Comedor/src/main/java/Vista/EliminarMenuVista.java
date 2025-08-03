package Vista;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EliminarMenuVista extends JFrame {
    public JList<String> listaMenus;
    public JButton btnEliminar;
    public DefaultListModel<String> modeloLista;

    public EliminarMenuVista() {
        setTitle("Eliminar Menú");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        try {
            java.util.List<String> lineas = Files.readAllLines(Paths.get("Menus.txt"));
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
        listaMenus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaMenus);
        scrollLista.setPreferredSize(new Dimension(250, 0));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(139, 0, 0));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminar.setVisible(false);

        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(580, 300));
        scrollLista.setBounds(20, 20, 540, 220); // Lista ocupa todo el ancho arriba
        btnEliminar.setBounds(220, 250, 150, 40); // Botón centrado debajo de la lista
        panel.add(scrollLista);
        panel.add(btnEliminar);

        add(panel);

        listaMenus.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listaMenus.getSelectedIndex() != -1) {
                btnEliminar.setVisible(true);
                // El botón se mantiene fijo a la derecha, no se mueve con la selección
            } else {
                btnEliminar.setVisible(false);
            }
        });

        btnEliminar.addActionListener(e -> {
            int idx = listaMenus.getSelectedIndex();
            if (idx == -1) return;
            String nombre = modeloLista.getElementAt(idx);
            if (nombre.contains(". ")) nombre = nombre.substring(nombre.indexOf(". ")+2);
            int res = JOptionPane.showOptionDialog(
                this,
                "¿Está seguro de querer eliminar el menú '" + nombre + "'?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new Object[] {"Sí estoy seguro", "Cancelar"},
                "Cancelar"
            );
            if (res == 0) {
                new Controlador.EliminarMenuControl(this, nombre).eliminarMenu();
            }
        });

        setVisible(true);
    }
}
