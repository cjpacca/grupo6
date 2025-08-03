package Modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculoCCBTest {
    
    private MenuModelo menu;

    @BeforeEach
    void setUp() {
    menu = new MenuModelo();
    menu.setCostoFijo(100.0);    // Costo fijo: $100
    menu.setCostoVariable(50.0); // Costo variable: $50
    menu.setNumeroBandejas(10);  // 10 bandejas
    menu.setMerma(0.1);          // 10% de merma
}

//calculo basico
    @Test
    void calcularCCB_valoresCorrectos() {
        //calcula el metodo CCB
        menu.calcularCCB(); 
        
        // Verificación
        double ccbEsperado = 16.5;
        assertEquals(ccbEsperado, menu.getCCB(), 0.01, "El CCB calculado es incorrecto");
    }

    //No hay merma
    @Test
    void calcularCCB_sinMerma() {
        menu.setMerma(0.0);
        menu.calcularCCB();
        
        assertEquals(15.0, menu.getCCB(), 0.01);
    }

    // Merma Alta
    @Test
    void calcularCCB_conMermaAlta() {
        menu.setMerma(0.5); // 50%
        menu.calcularCCB();
        
        assertEquals(22.5, menu.getCCB(), 0.01);
    }
}