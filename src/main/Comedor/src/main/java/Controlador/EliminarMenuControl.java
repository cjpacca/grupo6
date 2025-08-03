package Controlador;

import Vista.EliminarMenuVista;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EliminarMenuControl {
    private EliminarMenuVista vista;
    private String nombreMenu;

    public EliminarMenuControl(EliminarMenuVista vista, String nombreMenu) {
        this.vista = vista;
        this.nombreMenu = nombreMenu;
    }

    public void eliminarMenu() {
        try {
            List<String> lineas = Files.readAllLines(Paths.get("Menus.txt"));
            List<String> nuevasLineas = new ArrayList<>();
            boolean eliminado = false;
            for (String l : lineas) {
                String[] partes = l.split(",");
                if (partes.length > 0 && partes[0].trim().equalsIgnoreCase(nombreMenu)) {
                    eliminado = true;
                    continue; // No agregar la línea, se elimina
                }
                nuevasLineas.add(l);
            }
            if (!eliminado) {
                JOptionPane.showMessageDialog(vista, "No se encontró el menú especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Files.write(Paths.get("Menus.txt"), nuevasLineas);
            JOptionPane.showMessageDialog(vista, "Menú eliminado exitosamente.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
            new EliminarMenuVista(); // Recargar vista
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar el menú: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
