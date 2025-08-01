package Controlador;

import Modelo.MenuModelo;
import Vista.AgregarMenuVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.AdminMenuView;

public class ControlAdmin implements ActionListener {
    private AgregarMenuVista vista;
    private AdminMenuView adminMenuView;

    public ControlAdmin(AdminMenuView adminMenuView) {
        this.adminMenuView = adminMenuView;
        this.adminMenuView.btnAgregarMenu.addActionListener(e -> {
            new AgregarMenuVista();
        });
    }

    // Constructor para AgregarMenuVista (para compatibilidad)
    public ControlAdmin(AgregarMenuVista vista) {
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardarMenu();
        }
    }

    private void guardarMenu() {
        String turno = (String) vista.comboTurno.getSelectedItem();
        int numeroMenu = (Integer) vista.comboNumeroMenu.getSelectedItem();
        String nombre = vista.txtNombre.getText().trim();
        String costoFijoStr = vista.txtCostoFijo.getText().trim();
        String costoVariableStr = vista.txtCostoVariable.getText().trim();
        String numeroBandejasStr = vista.txtNumeroBandejas.getText().trim();
        String mermaStr = vista.txtMerma.getText().trim();

        // Verificar si el nombre ya existe en Menus.txt
        java.nio.file.Path path = java.nio.file.Paths.get("Menus.txt");
        if (java.nio.file.Files.exists(path)) {
            try {
                java.util.List<String> lineas = java.nio.file.Files.readAllLines(path);
                for (String l : lineas) {
                    String[] partes = l.split(",");
                    if (partes.length > 0 && partes[0].trim().equalsIgnoreCase(nombre)) {
                        JOptionPane.showMessageDialog(vista, "Menu ya existente", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al leer Menus.txt: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Validar campos vacíos
        if (nombre.isEmpty() || costoFijoStr.isEmpty() || costoVariableStr.isEmpty() ||
            numeroBandejasStr.isEmpty() || mermaStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double costoFijo, costoVariable, merma;
        int numeroBandejas;
        try {
            costoFijo = Double.parseDouble(costoFijoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un valor válido para Costo fijo.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            costoVariable = Double.parseDouble(costoVariableStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un valor válido para Costo variable.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            numeroBandejas = Integer.parseInt(numeroBandejasStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un valor válido para Número de bandejas.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            merma = Double.parseDouble(mermaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingrese un valor válido para % Merma.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double ccb = ((costoFijo + costoVariable) / numeroBandejas) * (1 + merma);
        double precioEstudiante = ccb * 0.25;
        double precioProfesor = ccb * 0.80;
        double precioEmpleado = ccb * 0.90;
        // Si todo es correcto, crear el modelo y mostrar mensaje de éxito

        MenuModelo menu = new MenuModelo();
        menu.setNombre(nombre);
        menu.setCostoFijo(costoFijo);
        menu.setCostoVariable(costoVariable);
        menu.setNumeroBandejas(numeroBandejas);
        menu.setMerma(merma);
        menu.setTurno(turno);
        menu.setNumeroMenu(numeroMenu);
        menu.setCCB(ccb);
        menu.setPrecioEstudiante(precioEstudiante);
        menu.setPrecioProfesor(precioProfesor);
        menu.setPrecioExterno(precioEmpleado);

        // Guardar en archivo Menus.txt
        String linea = String.format("%s, %.2f, %.2f, %.2f, %.2f, %s, %d, %.2f, %.2f, %d, %.2f\n",
            menu.getNombre(), menu.getCCB(), menu.getPrecioEstudiante(), menu.getPrecioProfesor(), menu.getPrecioExterno(),
            menu.getTurno(), menu.getNumeroMenu(), menu.getCostoFijo(), menu.getCostoVariable(), menu.getNumeroBandejas(), menu.getMerma());
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get("Menus.txt"),
                linea.getBytes(),
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
            JOptionPane.showMessageDialog(vista, "Menú guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar el menú: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
