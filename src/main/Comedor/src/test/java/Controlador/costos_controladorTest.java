package Controlador;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import Modelo.*;
import Vista.*;

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
}