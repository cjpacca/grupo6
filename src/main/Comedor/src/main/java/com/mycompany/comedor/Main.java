import Modelo.costos_modelo;
import Vista.costos_vista;
import Controlador.costos_controlador;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            costos_modelo model = new costos_modelo();
            costos_vista view = new costos_vista();
            costos_controlador controller = new costos_controlador(model, view);
            view.setVisible(true);
        });
    }
}