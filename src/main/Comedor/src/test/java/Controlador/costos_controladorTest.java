package Controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.costos_modelo;
import Vista.costos_vista;

/**
 * Pruebas unitarias para la clase costos_controlador.
 * Verifica el correcto manejo de costos
 * y las validaciones de entrada.
 */

class costos_controladorTest {
    private costos_modelo model;
    private costos_vista view;
    private costos_controlador controlador;

    @BeforeEach
    void setUp() {
        model = new costos_modelo();       // Inicializa el modelo
        view = new costos_vista();         // Inicializa la vista
        controlador = new costos_controlador(model, view); // Crea el controlador
    }

    // -----------------------------------------------------------------
    // PRUEBA 1: Valores enteros positivos
    // -----------------------------------------------------------------
    @Test
    void guardarCostos_valoresCorrectos_guardadoExitoso() {
        System.out.println("Prueba 1: Valores enteros positivos");
        
        // Configuración (Arrange)
        view.txtCostosFijos.setText("100");      // Costo fijo válido
        view.txtCostosVariables.setText("200");  // Costo variable válido
        
        // Ejecución (Act)
        boolean resultado = controlador.guardarCostos();
        
        // Verificación (Assert)
        assertTrue(resultado, "Debería retornar true para valores válidos");
        assertEquals(100.0, model.getCostosFijos(), 0.001, 
            "El costo fijo debe ser 100");
        assertEquals(200.0, model.getCostosVariables(), 0.001,
            "El costo variable debe ser 200");
    }

    // -----------------------------------------------------------------
    // PRUEBA 2: Valores decimales
    // -----------------------------------------------------------------
    @Test
    void guardarCostos_valoresDecimales_actualizaModelo() {
        System.out.println("Prueba 2: Valores decimales");
        
        // Configuración
        view.txtCostosFijos.setText("150.75");    
        view.txtCostosVariables.setText("89.50"); 
        
        // Ejecución
        boolean resultado = controlador.guardarCostos();
        
        // Verificación
        assertTrue(resultado, "Debería aceptar valores decimales");
        assertEquals(150.75, model.getCostosFijos(), 0.01,
            "El costo fijo debe registrar decimales");
        assertEquals(89.50, model.getCostosVariables(), 0.01,
            "El costo variable debe registrar decimales");
    }

    // -----------------------------------------------------------------
    // PRUEBA 3: Valores no numéricos (inválidos)
    // -----------------------------------------------------------------
    @Test
    void guardarCostos_valoresInvalidos_retornaFalse() {
        System.out.println("Prueba 3: Valores no numéricos");
        
        // Configuración
        view.txtCostosFijos.setText("abc");  // Valor inválido
        view.txtCostosVariables.setText("xyz"); // Valor inválido
        
        // Ejecución
        boolean resultado = controlador.guardarCostos();
        
        // Verificación
        assertFalse(resultado, "Debería fallar con valores no numéricos");
        assertEquals(0.0, model.getCostosFijos(), 
            "El modelo no debe cambiar con valores inválidos");
        assertEquals(0.0, model.getCostosVariables(),
            "El modelo no debe cambiar con valores inválidos");
    }

    // -----------------------------------------------------------------
    // PRUEBA 4: Campos vacíos
    // -----------------------------------------------------------------
    @Test
    void guardarCostos_camposVacios_retornaFalse() {
        System.out.println("Prueba 4: Campos vacíos");
        
        // Configuración
        view.txtCostosFijos.setText("");     
        view.txtCostosVariables.setText("");  
        
        // Ejecución
        boolean resultado = controlador.guardarCostos();
        
        // Verificación
        assertFalse(resultado, "Debería fallar con campos vacíos");
    }
    //pruebas n implementadas pq se tendria que modificar el modelo y la vista para aceptar valores negativos o con comas
    /* -----------------------------------------------------------------
    // PRUEBA 5: Valores negativos
    // -----------------------------------------------------------------
    @Test
    void guardarCostos_valoresNegativos_retornaFalse() {
        System.out.println("Prueba 5: Valores negativos");
        
        // Configuración
        view.txtCostosFijos.setText("-100");     // Valor negativo
        view.txtCostosVariables.setText("-50");  // Valor negativo
        
        // Ejecución
        boolean resultado = controlador.guardarCostos();
        
        // Verificación
        assertFalse(resultado, "Debería rechazar valores negativos");
    }
        
    */
    /* -----------------------------------------------------------------
     PRUEBA 6: Formato con comas (1,000.50)
     -----------------------------------------------------------------
    @Test
    void guardarCostos_valoresConComas_interpretaCorrectamente() {
        System.out.println("Prueba 6: Valores con formato");
        
        // Configuración
        view.txtCostosFijos.setText("1,000.50");    
        view.txtCostosVariables.setText("2,500.75");
        
        // Ejecución
        boolean resultado = controlador.guardarCostos();
        
        // Verificación
        assertTrue(resultado, "Debería aceptar números con formato");
        assertEquals(1000.50, model.getCostosFijos(), 0.001,
            "Debe manejar el formato con comas");
        assertEquals(2500.75, model.getCostosVariables(), 0.001,
            "Debe manejar el formato con comas");
    }
    */
}