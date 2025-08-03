package Controlador;

import Vista.ModificarMenuVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ControlModificarMenu implements ActionListener {
    private ModificarMenuVista vista;

    public ControlModificarMenu(ModificarMenuVista vista) {
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.listaMenus.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String seleccionado = vista.listaMenus.getSelectedValue();
                if (seleccionado != null && seleccionado.contains(". ")) {
                    String nombre = seleccionado.substring(seleccionado.indexOf(". ")+2);
                    vista.txtNombreModificar.setText(nombre);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
        modificarMenu();
        } else if (e.getSource() == vista.btnSalir) {
        vista.dispose();  // Cierra la ventana actual
        }
    }

    private void modificarMenu() {
        String nombre = vista.txtNombreModificar.getText().trim();
        String campo = (String) vista.comboModificar.getSelectedItem();
        String nuevoValor = vista.txtNuevoValor.getText().trim();

        if (nombre.isEmpty() || campo == null || nuevoValor.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar tipo de dato según campo seleccionado
        switch (campo) {
            case "Costo Fijo":
            case "Costo Variable":
            case "%merma":
                try {
                    Double.parseDouble(nuevoValor);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "Debes ingresar un número decimal para '" + campo + "'.", "Error de tipo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Número de bandejas":
                try {
                    Integer.parseInt(nuevoValor);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "Debes ingresar un número entero para '" + campo + "'.", "Error de tipo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Dia de la semana":
                String dia = nuevoValor.trim();
                String[] diasValidos = {"Lunes", "Martes", "Miercoles", "Miércoles", "Jueves", "Viernes"};
                boolean valido = false;
                for (String d : diasValidos) {
                    if (dia.equals(d)) {
                        valido = true;
                        break;
                    }
                }
                if (!valido) {
                    JOptionPane.showMessageDialog(vista, "Debes ingresar el día con la primera letra en mayúscula entre Lunes y Viernes", "Error de tipo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "Turno":
                // No validar, es texto
                break;
        }

        try {
            List<String> lineas = Files.readAllLines(Paths.get("Menus.txt"));
            boolean encontrado = false;
            List<String> nuevasLineas = new ArrayList<>();
            for (String l : lineas) {
                String[] partes = l.split(",");
                if (partes.length < 11) {
                    nuevasLineas.add(l);
                    continue;
                }
                if (partes[0].trim().equalsIgnoreCase(nombre)) {
                    encontrado = true;
                    // Actualizar campo seleccionado
                    switch (campo) {
                        case "Costo Fijo":
                            partes[7] = " " + nuevoValor;
                            break;
                        case "Costo Variable":
                            partes[8] = " " + nuevoValor;
                            break;
                        case "Número de bandejas":
                            partes[9] = " " + nuevoValor;
                            break;
                        case "%merma":
                            partes[10] = " " + nuevoValor;
                            break;
                        case "Turno":
                            partes[5] = " " + nuevoValor;
                            break;
                        case "Dia de la semana":
                            partes[6] = " " + nuevoValor;
                            break;
                    }
                    // Recalcular CCB y precios si se modificó costo fijo, costo variable, número de bandejas o merma
                    try {
                        double costoFijo = Double.parseDouble(partes[7].trim());
                        double costoVariable = Double.parseDouble(partes[8].trim());
                        int numeroBandejas = Integer.parseInt(partes[9].trim());
                        double merma = Double.parseDouble(partes[10].trim());
                        double ccb = ((costoFijo + costoVariable) / numeroBandejas) * (1 + merma);
                        double precioEstudiante = ccb * 0.25;
                        double precioProfesor = ccb * 0.80;
                        double precioEmpleado = ccb * 0.90;
                        partes[1] = String.format(" %.2f", ccb);
                        partes[2] = String.format(" %.2f", precioEstudiante);
                        partes[3] = String.format(" %.2f", precioProfesor);
                        partes[4] = String.format(" %.2f", precioEmpleado);
                    } catch (Exception ex) {
                        // Si hay error en el parseo, dejar los valores como están
                    }
                    nuevasLineas.add(String.join(",", partes));
                } else {
                    nuevasLineas.add(l);
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(vista, "No se encontró el menú especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Files.write(Paths.get("Menus.txt"), nuevasLineas);
            JOptionPane.showMessageDialog(vista, "Menú modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al modificar el menú: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

