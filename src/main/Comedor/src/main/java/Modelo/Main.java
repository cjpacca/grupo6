package Modelo;
<<<<<<< HEAD
import Vista.*;
=======
>>>>>>> temporal

import Controlador.Control;
import Vista.Inicial;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Crear el Modelo
<<<<<<< HEAD

            GestorArchivos modelo = new GestorArchivos();

            // 2. Crear la Vista Principal
            Inicial vista = new Inicial();

            // 3. Crear el Controlador y conectarlo todo
            Control controlador = new Control(modelo, vista);

            // 4. Iniciar la aplicación
            controlador.iniciar();
            
=======
            
            GestorArchivos modelo = new GestorArchivos();
            
            // 2. Crear la Vista Principal
            Inicial vista = new Inicial();
            
            // 3. Crear el Controlador y conectarlo todo
            Control controlador = new Control(modelo, vista);
            
            // 4. Iniciar la aplicación
            controlador.iniciar();
>>>>>>> temporal
        });
    }
}