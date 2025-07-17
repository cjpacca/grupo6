package Controlador;
import Modelo.costos_modelo;
import Vista.costos_vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class costos_controlador {
    private costos_modelo model;
    private costos_vista view;

    public costos_controlador(costos_modelo model, costos_vista view) {
        this.model = model;
        this.view = view;

        view.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCostos();
            }
        });
    }

    private void guardarCostos() {
        try {
            double costosFijos = Double.parseDouble(view.txtCostosFijos.getText());
            double costosVariables = Double.parseDouble(view.txtCostosVariables.getText());

            model.setCostosFijos(costosFijos);
            model.setCostosVariables(costosVariables);

            JOptionPane.showMessageDialog(view, "Costos guardados correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
