/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

import java.util.HashMap;
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

    @Test
    public void testCalcularTotalMensual() {
        System.out.println("testCalcularTotalMensual");
        
        Map<String, Float> artisanSalesMap = Map.of(
            "Ana", 150.0f,
            "Luis", 200.5f,
            "Carlos", 100.0f
        );
        float expectedResult = 450.5f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "Should correctly sum all artisan sales");
    }
    @Test
    public void testCalcularTotalMensual_WithNullValues() {
        System.out.println("testCalcularTotalMensual_WithNullValues");
        
        // Using HashMap to allow null values
        Map<String, Float> artisanSalesMap = new HashMap<>();
        artisanSalesMap.put("Ana", 150.0f);
        artisanSalesMap.put("Luis", null);
        artisanSalesMap.put("Carlos", 100.0f);
        
        float expectedResult = 250.0f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "Should ignore null values and sum only valid ones");
    }
    
    @Test
    public void testCalcularTotalMensual_EmptyMap() {
        System.out.println("testCalcularTotalMensual_EmptyMap");
        
        Map<String, Float> artisanSalesMap = new HashMap<>();
        
        float expectedResult = 0.0f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "Empty map should return 0");
    }
    
    @Test
    public void testCalcularTotalMensual_AllNullValues() {
        System.out.println("testCalcularTotalMensual_AllNullValues");
        
        Map<String, Float> artisanSalesMap = new HashMap<>();
        artisanSalesMap.put("Ana", null);
        artisanSalesMap.put("Luis", null);
        artisanSalesMap.put("Carlos", null);
        
        float expectedResult = 0.0f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "All null values should return 0");
    }
    
    @Test
    public void testCalcularTotalMensual_WithZeroValues() {
        System.out.println("testCalcularTotalMensual_WithZeroValues");
        
        Map<String, Float> artisanSalesMap = Map.of(
            "Ana", 150.0f,
            "Luis", 0.0f,
            "Carlos", 100.0f
        );
        
        float expectedResult = 250.0f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "Should include zero values in calculation");
    }
    
    @Test
    public void testCalcularTotalMensual_WithNegativeValues() {
        System.out.println("testCalcularTotalMensual_WithNegativeValues");
        
        Map<String, Float> artisanSalesMap = Map.of(
            "Ana", 150.0f,
            "Luis", -50.0f,
            "Carlos", 100.0f
        );
        
        float expectedResult = 200.0f;
        float result = MonthlyReportController.calcularTotalMensual(artisanSalesMap);
        
        assertEquals(expectedResult, result, 0.01f, "Should include negative values in calculation");
    }
}
