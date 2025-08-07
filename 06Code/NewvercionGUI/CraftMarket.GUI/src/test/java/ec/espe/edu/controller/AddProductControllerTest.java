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

    @Test
    public void testAddProductValidParams() {
        System.out.println("testAddProductValidParams");
        
        long timestamp = System.currentTimeMillis();
        String idText = String.valueOf(timestamp % 100000);
        String name = "Chocolate";
        String priceText = "25.00";
        String stockText = "10";
        String owner = "lourdes";
        Component parent = null;
        
        boolean expResult = true;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Product with valid parameters should be added correctly");
    }
    
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
    
    @Test
    public void testAddProductInvalidNumericFormat() {
        System.out.println("testAddProductInvalidNumericFormat");
        
        String idText = "abc";
        String name = "Test Product";
        String priceText = "xyz";
        String stockText = "def";
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Invalid numeric formats should return false");
    }
    
    @Test
    public void testAddProductNegativeValues() {
        System.out.println("testAddProductNegativeValues");
        
        String idText = "123";
        String name = "Test Product";
        String priceText = "-10.50";
        String stockText = "-5";
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Negative values for price and stock should return false");
    }
    
    @Test
    public void testAddProductZeroValues() {
        System.out.println("testAddProductZeroValues");
        
        String idText = "456";
        String name = "Test Product";
        String priceText = "0";
        String stockText = "0";
        String owner = "TestOwner";
        Component parent = null;
        
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        
        assertEquals(expResult, result, "Zero values for price and stock should return false");
    }
    
}
