/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

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
public class StockManagerTest {
    
    public StockManagerTest() {
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
     * Test of updateStockAfterSale method, of class StockManager.
     */
    @Test
    public void testUpdateStock_ProductNotFound() {
        int productId = -1; // ID inválido o inexistente
        int quantityVendida = 5;
        boolean expResult = false;

        boolean result = StockManager.updateStockAfterSale(productId, quantityVendida);
        assertEquals(expResult, result, "Product not found should return false");
    }

    @Test
    public void testUpdateStock_StockInsuficiente() {
        int productId = 1; // Asegúrate que el producto 1 existe y tiene stock < 1000
        int quantityVendida = 1000;
        boolean expResult = false;

        boolean result = StockManager.updateStockAfterSale(productId, quantityVendida);
        assertEquals(expResult, result, "Insufficient stock should return false");
    }

    @Test
    public void testUpdateStock_SuccessfulUpdate() {
        int productId = 1; // Asegúrate que existe en MongoDB y tiene stock suficiente
        int quantityVendida = 1;
        boolean expResult = true;

        boolean result = StockManager.updateStockAfterSale(productId, quantityVendida);
        assertEquals(expResult, result, "Successful update should return true");
    }
    
}

