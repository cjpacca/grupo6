package Modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompararFotosTest {
    private fileStrategy strategy;
    private File fotoOriginal;     // Foto de referencia
    private File fotoCopia;        // Foto igual a la original
    private File fotoDiferente;    // Foto distinta a la original

    // Este método se ejecuta ANTES de cada prueba
    @BeforeEach
    void prepararFotos() throws IOException {
        strategy = new fileStrategy();
        
        // Crear fotos temporales para las pruebas
        fotoOriginal = File.createTempFile("original", ".jpg");
        fotoCopia = File.createTempFile("copia", ".jpg");
        fotoDiferente = File.createTempFile("diferente", ".jpg");
        
        // Llenar las fotos con datos:
        // - original y copia tendrán los mismos bytes (1, 2, 3)
        // - diferente tendrá bytes distintos (4, 5, 6)
        try (FileOutputStream fos = new FileOutputStream(fotoOriginal);
             FileOutputStream fosCopia = new FileOutputStream(fotoCopia);
             FileOutputStream fosDiff = new FileOutputStream(fotoDiferente)) {
            
            // Foto original y copia (iguales)
            fos.write(new byte[]{1, 2, 3});
            fosCopia.write(new byte[]{1, 2, 3});
            
            // Foto diferente
            fosDiff.write(new byte[]{4, 5, 6});
        }
    }

    // Este método se ejecuta DESPUÉS de cada prueba
    @AfterEach
    void limpiarFotos() {
        fotoOriginal.delete();
        fotoCopia.delete();
        fotoDiferente.delete();
    }

    /* ------------------------------------ PRUEBAS ------------------------------ */

    // Prueba 1: Cuando las fotos son iguales
    @Test
    void fotosIguales_devuelveTrue() {
        // Ejecutar el método a probar
        boolean resultado = strategy.compararImagenes(
            fotoOriginal.getAbsolutePath(), 
            fotoCopia.getAbsolutePath()
        );
        
        // Verificar que devuelva true (son iguales)
        assertTrue(resultado);
    }

    // Prueba 2: Cuando las fotos son diferentes
    @Test
    void fotosDiferentes_devuelveFalse() {
        // Ejecutar el método a probar
        boolean resultado = strategy.compararImagenes(
            fotoOriginal.getAbsolutePath(), 
            fotoDiferente.getAbsolutePath()
        );
        
        // Verificar que devuelva false (son diferentes)
        assertFalse(resultado);
    }

    // Prueba 3: Cuando una foto no existe
    @Test
    void fotoInexistente_devuelveFalse() {
        // Ejecutar con una ruta que no existe
        boolean resultado = strategy.compararImagenes(
            "ruta/falsa/noexiste.jpg", 
            fotoOriginal.getAbsolutePath()
        );
        
        // Verificar que devuelva false
        assertFalse(resultado);
    }
}