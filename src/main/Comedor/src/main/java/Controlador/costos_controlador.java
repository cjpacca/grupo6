package Controlador;

import Modelo.*;
import Vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Costos_controlador {
    private Costos_modelo model;
    private Costos_vista view;

    public Costos_controlador(Costos_modelo model, Costos_vista view) {
        this.model = model;
        this.view = view;

        view.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCostos();
            }
        });
    }

    public boolean guardarCostos() {
        try {
            double costosFijos = Double.parseDouble(view.txtCostosFijos.getText());
            double costosVariables = Double.parseDouble(view.txtCostosVariables.getText());

            model.setCostosFijos(costosFijos);
            model.setCostosVariables(costosVariables);

            JOptionPane.showMessageDialog(view, "Costos guardados correctamente.");
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor ingrese valores numéricos válidos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
