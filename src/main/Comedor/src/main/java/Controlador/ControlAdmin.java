package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControlAdmin implements ActionListener {
    private AdminMenuView adminMenuView;
    private AgregarMenuVista vistaAgregar;
    private ModificarMenuVista vistaModificar;
    private EliminarMenuVista vistaEliminar;
    private final GestorArchivos modelo;
    private boolean procesandoReconocimiento = false;

    public ControlAdmin(AdminMenuView adminMenuView, GestorArchivos modelo) {
        this.modelo = modelo;
        this.adminMenuView = adminMenuView;
        
        // Remover listeners existentes primero para evitar duplicados
        
        // Agregar listeners una sola vez
        this.adminMenuView.btnAgregarMenu.addActionListener(this);
        this.adminMenuView.btnReconocimiento.addActionListener(this);
        this.adminMenuView.btnModificarMenu.addActionListener(this);
        this.adminMenuView.btnCerrarSesion.addActionListener(this);
        this.adminMenuView.btnEliminarMenu.addActionListener(this);
    }
    
    @Override
public void actionPerformed(ActionEvent e) {
    // Obtenemos el comando de acción en lugar de la fuente del evento
    String command = e.getActionCommand();

    switch (command) {
        case "AgregarMenu":
            if (vistaAgregar == null || !vistaAgregar.isVisible()) {
                abrirVentanaAgregar();
            }
            break;
        case "ReconocimientoFacial":
            procesarComparacionFoto();
            break;
        case "ModificarMenu":
             abrirVentanaModificar();
            break;

        case "EliminarMenu":
            abrirVentanaEliminar();
             break;
        case "Cerrar sesion":
            cerrarSesion();
            break;
    }
}

private void cerrarSesion() {
    // 1. Cerrar la ventana actual (AdminMenuView)
    if (adminMenuView != null) {
        adminMenuView.dispose();
    }
    // 2. Abrir la ventana inicial (Inicial)
    Inicial vistaInicial = new Inicial();
    Control controlInicial = new Control(modelo, vistaInicial);
    controlInicial.iniciar();  // Mostrar la ventana inicial
}

private void abrirVentanaEliminar() {
    if (vistaEliminar == null || !vistaEliminar.isVisible()) {
        vistaEliminar = new EliminarMenuVista();
        vistaEliminar.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (adminMenuView != null) {  // ← Verificar nulidad
                    adminMenuView.setVisible(true);
                }
                vistaEliminar = null;
            }
        });
        if (adminMenuView != null) {  // ← Verificar nulidad
            adminMenuView.setVisible(false);
        }
    }
}

private void abrirVentanaModificar() {
    if (vistaModificar == null || !vistaModificar.isVisible()) {
        vistaModificar = new ModificarMenuVista();
        vistaModificar.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (adminMenuView != null) {  // ← Verificar nulidad
                    adminMenuView.setVisible(true);
                }
                vistaModificar = null;
            }
        });
        if (adminMenuView != null) {  // ← Verificar nulidad
            adminMenuView.setVisible(false);
        }
    }
}
        
    private void procesarComparacionFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione la foto a verificar");
        int resultado = fileChooser.showOpenDialog(adminMenuView);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File fotoSeleccionada = fileChooser.getSelectedFile();
            String rutaFotoAComparar = fotoSeleccionada.getAbsolutePath();

            // Llamamos al NUEVO método del modelo que no requiere cédula
            Comensal aux= modelo.verificarFotoContraTodos(rutaFotoAComparar);

            if (aux!=null) {
                // Lógica para cuando la comparación es exitosa
                JOptionPane.showMessageDialog(adminMenuView, "¡Verificación Exitosa! La foto coincide con un comensal registrado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                // Mensaje cuando no hay coincidencias
                JOptionPane.showMessageDialog(adminMenuView, "La foto no coincide con ningún comensal en la base de datos.", "Verificación Fallida", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }

    private void abrirVentanaAgregar() {
        if (vistaAgregar != null && vistaAgregar.isVisible()) {
            return; // Ya hay una ventana abierta
        }
        
        vistaAgregar = new AgregarMenuVista();
        vistaAgregar.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMenu(vistaAgregar);
                vistaAgregar.dispose();
                vistaAgregar = null; // Liberar referencia
                adminMenuView.setVisible(true);
            }
        });
        
        vistaAgregar.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                vistaAgregar = null; // Liberar referencia al cerrar
                adminMenuView.setVisible(true);
            }
        });
        
        adminMenuView.setVisible(false);
    }

    private void guardarMenu(AgregarMenuVista vista) { // Recibir la vista como parámetro
        String turno = (String) vista.comboTurno.getSelectedItem();
        String dia = (String) vista.combodia.getSelectedItem();
        String nombre = vista.txtNombre.getText().trim();
        String costoFijoStr = vista.txtCostoFijo.getText().trim();
        String costoVariableStr = vista.txtCostoVariable.getText().trim();
        String numeroBandejasStr = vista.txtNumeroBandejas.getText().trim();
        String mermaStr = vista.txtMerma.getText().trim();

        // Resto del código de guardarMenu() permanece igual...
        // Verificación de nombre existente
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
        menu.setDia(dia);
        menu.setCCB(ccb);
        menu.setPrecioEstudiante(precioEstudiante);
        menu.setPrecioProfesor(precioProfesor);
        menu.setPrecioExterno(precioEmpleado);

        // Guardar en archivo Menus.txt
        String linea = String.format("%s, %.2f, %.2f, %.2f, %.2f, %s, %s, %.2f, %.2f, %d, %.2f\n",
            menu.getNombre(), menu.getCCB(), menu.getPrecioEstudiante(), menu.getPrecioProfesor(), menu.getPrecioExterno(),
            menu.getTurno(), menu.getDia(), menu.getCostoFijo(), menu.getCostoVariable(), menu.getNumeroBandejas(), menu.getMerma());
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

