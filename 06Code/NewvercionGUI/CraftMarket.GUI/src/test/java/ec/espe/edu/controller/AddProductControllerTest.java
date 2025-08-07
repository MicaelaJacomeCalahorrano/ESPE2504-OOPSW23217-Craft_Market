/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

import java.awt.Component;
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
public class AddProductControllerTest {
    
    public AddProductControllerTest() {
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
     * Test of addProduct method with valid parameters.
     * Uses unique timestamp-based ID to avoid conflicts with existing database.
     */
    @Test
    public void testAddProductValidParams() {
        System.out.println("testAddProductValidParams");
        
        // Generate unique ID to avoid conflicts with existing products in database
        long timestamp = System.currentTimeMillis();
        String idText = String.valueOf(timestamp % 100000); // Unique 5-digit ID
        String name = "Chocolate";        // Keep original name
        String priceText = "25.00";       // Keep original price  
        String stockText = "10";          // Keep original stock
        String owner = "lourdes";         // Keep original owner
        Component parent = null;
        
        boolean expResult = true;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        // Note: This test may fail if there's no MongoDB connection
        assertEquals(expResult, result, "Product with valid parameters should be added correctly");
    }
    
    /**
     * Test of addProduct method with empty fields.
     * This test validates that empty fields return false.
     */
    @Test
    public void testAddProductEmptyFields() {
        System.out.println("testAddProductEmptyFields");
        
        String idText = "";
        String name = "";
        String priceText = "";
        String stockText = "";
        String owner = "";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Empty fields should return false");
    }
    
    /**
     * Test of addProduct method with invalid numeric formats.
     * This test validates that invalid numeric formats return false.
     */
    @Test
    public void testAddProductInvalidNumericFormat() {
        System.out.println("testAddProductInvalidNumericFormat");
        
        String idText = "abc"; // Non-numeric ID
        String name = "Test Product";
        String priceText = "xyz"; // Non-numeric price
        String stockText = "def"; // Non-numeric stock
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Invalid numeric formats should return false");
    }
    
    /**
     * Test of addProduct method with negative values.
     * This test validates that negative values return false.
     */
    @Test
    public void testAddProductNegativeValues() {
        System.out.println("testAddProductNegativeValues");
        
        String idText = "123";
        String name = "Test Product";
        String priceText = "-10.50"; // Negative price
        String stockText = "-5"; // Negative stock
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Negative values for price and stock should return false");
    }
    
    /**
     * Test of addProduct method with zero values.
     * This test validates that zero values return false.
     */
    @Test
    public void testAddProductZeroValues() {
        System.out.println("testAddProductZeroValues");
        
        String idText = "456";
        String name = "Test Product";
        String priceText = "0"; // Zero price
        String stockText = "0"; // Zero stock
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Zero values for price and stock should return false");
    }
    
}
