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
public class ProductValidatorTest {
    
    public ProductValidatorTest() {
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
     * Test of isValidName method with valid name.
     * Validates that non-empty names return true.
     */
    @Test
    public void testIsValidNameValid() {
        System.out.println("testIsValidNameValid");
        
        String name = "Valid Product Name";
        boolean expResult = true;
        boolean result = ProductValidator.isValidName(name);
        
        assertEquals(expResult, result, "Valid name should return true");
    }
    
    /**
     * Test of isValidName method with empty name.
     * Validates that empty names return false.
     */
    @Test
    public void testIsValidNameEmpty() {
        System.out.println("testIsValidNameEmpty");
        
        String name = "";
        boolean expResult = false;
        boolean result = ProductValidator.isValidName(name);
        
        assertEquals(expResult, result, "Empty name should return false");
    }
    
    /**
     * Test of isValidName method with null name.
     * Validates that null names return false.
     */
    @Test
    public void testIsValidNameNull() {
        System.out.println("testIsValidNameNull");
        
        String name = null;
        boolean expResult = false;
        boolean result = ProductValidator.isValidName(name);
        
        assertEquals(expResult, result, "Null name should return false");
    }
    
    /**
     * Test of isValidName method with whitespace only name.
     * Validates that whitespace-only names return false.
     */
    @Test
    public void testIsValidNameWhitespace() {
        System.out.println("testIsValidNameWhitespace");
        
        String name = "   ";
        boolean expResult = false;
        boolean result = ProductValidator.isValidName(name);
        
        assertEquals(expResult, result, "Whitespace-only name should return false");
    }

    /**
     * Test of isValidPrice method with valid positive price.
     * Validates that positive prices return true.
     */
    @Test
    public void testIsValidPriceValid() {
        System.out.println("testIsValidPriceValid");
        
        double price = 25.99;
        boolean expResult = true;
        boolean result = ProductValidator.isValidPrice(price);
        
        assertEquals(expResult, result, "Valid positive price should return true");
    }
    
    /**
     * Test of isValidPrice method with zero price.
     * Validates that zero price returns false.
     */
    @Test
    public void testIsValidPriceZero() {
        System.out.println("testIsValidPriceZero");
        
        double price = 0.0;
        boolean expResult = false;
        boolean result = ProductValidator.isValidPrice(price);
        
        assertEquals(expResult, result, "Zero price should return false");
    }
    
    /**
     * Test of isValidPrice method with negative price.
     * Validates that negative prices return false.
     */
    @Test
    public void testIsValidPriceNegative() {
        System.out.println("testIsValidPriceNegative");
        
        double price = -10.50;
        boolean expResult = false;
        boolean result = ProductValidator.isValidPrice(price);
        
        assertEquals(expResult, result, "Negative price should return false");
    }

    /**
     * Test of isValidStock method with valid positive stock.
     * Validates that positive stock values return true.
     */
    @Test
    public void testIsValidStockValid() {
        System.out.println("testIsValidStockValid");
        
        int stock = 50;
        boolean expResult = true;
        boolean result = ProductValidator.isValidStock(stock);
        
        assertEquals(expResult, result, "Valid positive stock should return true");
    }
    
    /**
     * Test of isValidStock method with zero stock.
     * Validates that zero stock returns true (zero stock is allowed).
     */
    @Test
    public void testIsValidStockZero() {
        System.out.println("testIsValidStockZero");
        
        int stock = 0;
        boolean expResult = true; // Note: Zero stock is valid according to ProductValidator
        boolean result = ProductValidator.isValidStock(stock);
        
        assertEquals(expResult, result, "Zero stock should return true");
    }
    
    /**
     * Test of isValidStock method with negative stock.
     * Validates that negative stock values return false.
     */
    @Test
    public void testIsValidStockNegative() {
        System.out.println("testIsValidStockNegative");
        
        int stock = -5;
        boolean expResult = false;
        boolean result = ProductValidator.isValidStock(stock);
        
        assertEquals(expResult, result, "Negative stock should return false");
    }
    
}
