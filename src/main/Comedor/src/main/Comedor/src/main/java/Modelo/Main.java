package Modelo;

import Controlador.Control;
import Vista.Inicial;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Se ejecuta en el Hilo de Despacho de Eventos de Swing para seguridad
        SwingUtilities.invokeLater(() -> {
            // 1. Crear el Modelo

            GestorArchivos modelo = new GestorArchivos();

            // 2. Crear la Vista Principal
            Inicial vista = new Inicial();

            // 3. Crear el Controlador y conectarlo todo
            Control controlador = new Control(modelo, vista);

            // 4. Iniciar la aplicación
            controlador.iniciar();
        });
    }
}