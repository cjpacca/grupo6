package Controlador;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import Modelo.*;
import Vista.*;

class costos_controladorTest {

    private Costos_modelo model;
    private Costos_vista view;
    private Costos_controlador controlador;

    @BeforeEach
    void setUp() {
        model = new Costos_modelo();
        view = new Costos_vista();
        controlador = new Costos_controlador(model, view);
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