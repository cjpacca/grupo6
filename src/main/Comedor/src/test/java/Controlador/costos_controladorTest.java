package Controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.costos_modelo;
import Vista.costos_vista;

class costos_controladorTest {

    private costos_modelo model;
    private costos_vista view;
    private costos_controlador controlador;

    @BeforeEach
    void setUp() {
        model = new costos_modelo();
        view = new costos_vista();
        controlador = new costos_controlador(model, view);
    }

    @Test
    void guardarCostos_valoresCorrectos_guardadoExitoso() {
        // Arrange
        view.txtCostosFijos.setText("100");
        view.txtCostosVariables.setText("200");
        // Act
        view.btnGuardar.doClick();
        // Assert
        assertTrue(controlador.guardarCostos());
        assertTrue(controlador.guardarCostos());
    }

    @Test
    void guardarCostos_valoresInvalidos_GuerdadoErroneo() {
        // Arrange
        view.txtCostosFijos.setText("dihfruh");
        view.txtCostosVariables.setText("ifhuygw");
        // Act
        view.btnGuardar.doClick();
        // Assert
        assertFalse(controlador.guardarCostos());
        assertFalse(controlador.guardarCostos());
    }

    //verificar que el controlador de costos maneje correctamente valores decimales y actualice el modelo (costos_modelo)
    @Test
    void guardarCostos_valoresDecimales_actualizaModelo() {
    // 1. Arrange (Preparación)
    view.txtCostosFijos.setText("150.75");    // Input con decimales
    view.txtCostosVariables.setText("89.50"); // Input con decimales
    
    // 2. Act (Ejecución)
    controlador.guardarCostos(); // Llama al método bajo prueba
    
    // 3. Assert (Verificación)
    assertEquals(150.75, model.getCostosFijos(), 0.01);  // Verifica con margen de error ±0.01
    assertEquals(89.50, model.getCostosVariables(), 0.01);
}
}