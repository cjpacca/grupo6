package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostrarMenuControl {

    private final String rutaMenus = "Menus.txt";
    private String tipoComensal;

    /**
     * Guarda el tipo de comensal usando getType de Comensal
     */
    public void setTipoComensal(Modelo.Comensal comensal) {
        if (comensal != null) {
            this.tipoComensal = comensal.getType();
        }
    }

    public String getTipoComensal() {
        return tipoComensal;
    }

    /**
     * Devuelve una lista de menús filtrados por turno y día.
     * @param turno El turno a filtrar (ejemplo: "Mañana" o "Tarde")
     * @param dia El día a filtrar (ejemplo: "Lunes", "Martes", ...)
     * @return Lista de menús que cumplen con los filtros
     */
    public List<String[]> obtenerMenusPorTurnoYDia(String turno, String dia) {
        List<String[]> menusFiltrados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaMenus))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                // El turno es el campo 5 (índice 5) y el día es el campo 6 (índice 6)
                if (campos.length > 6) {
                    String campoTurno = campos[5].trim();
                    String campoDia = campos[6].trim();
                    if (campoTurno.equalsIgnoreCase(turno) && campoDia.equalsIgnoreCase(dia)) {
                        menusFiltrados.add(campos);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menusFiltrados;
    }
}
