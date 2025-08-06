/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jorge
 */
public class MonthlyReportControllerTest {
    
    public MonthlyReportControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calcularTotalMensual method, of class MonthlyReportController.
     */
    @Test
    public void testCalcularTotalMensual() {
        System.out.println("calcularTotalMensual");
        Map<String, Float> artisanSalesMap = Map.of(
        "Ana", 150.0f,
        "Luis", 200.5f,
        "Carlos", 100.0f
        );
        float resultadoEsperado = 450.5f;
        float resultado = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        assertEquals(resultadoEsperado, resultado, 0.01f);
        }
    @Test
    public void testCalcularTotalMensual_ConValoresNulos() {
        Map<String, Float> artisanSalesMap = Map.of(
        "Ana", 150.0f,
        "Luis", null,
        "Carlos", 100.0f
        );

        float resultadoEsperado = 300.0f;
        float resultado = MonthlyReportController.calcularTotalMensual(artisanSalesMap);

        assertEquals(resultadoEsperado, resultado, 0.01f);
    }
}
