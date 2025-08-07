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
public class SaleCalculatorTest {
    
    public SaleCalculatorTest() {
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
     * Test of calculateTotal method with zero values.
     * Validates that zero price and quantity return zero total.
     */
    @Test
    public void testCalculateTotal_ZeroValues() {
        System.out.println("testCalculateTotal_ZeroValues");
        
        double unitPrice = 0.0;
        int quantity = 0;
        double expResult = 0.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be 0.0 when price and quantity are 0");
    }

    /**
     * Test of calculateTotal method with normal positive values.
     * Validates standard multiplication calculation.
     */
    @Test
    public void testCalculateTotal_NormalValues() {
        System.out.println("testCalculateTotal_NormalValues");
        
        double unitPrice = 2.5;
        int quantity = 4;
        double expResult = 10.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be 10.0 for unitPrice 2.5 and quantity 4");
    }

    /**
     * Test of calculateTotal method with negative quantity.
     * Validates that negative quantity produces negative total.
     */
    @Test
    public void testCalculateTotal_NegativeQuantity() {
        System.out.println("testCalculateTotal_NegativeQuantity");
        
        double unitPrice = 3.0;
        int quantity = -2;
        double expResult = -6.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be negative when quantity is negative");
    }
    
    /**
     * Test of calculateTotal method with zero price and positive quantity.
     * Validates that zero price results in zero total regardless of quantity.
     */
    @Test
    public void testCalculateTotal_ZeroPricePositiveQuantity() {
        System.out.println("testCalculateTotal_ZeroPricePositiveQuantity");
        
        double unitPrice = 0.0;
        int quantity = 5;
        double expResult = 0.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be 0.0 when price is 0 regardless of quantity");
    }
    
    /**
     * Test of calculateTotal method with positive price and zero quantity.
     * Validates that zero quantity results in zero total regardless of price.
     */
    @Test
    public void testCalculateTotal_PositivePriceZeroQuantity() {
        System.out.println("testCalculateTotal_PositivePriceZeroQuantity");
        
        double unitPrice = 15.50;
        int quantity = 0;
        double expResult = 0.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be 0.0 when quantity is 0 regardless of price");
    }
    
    /**
     * Test of calculateTotal method with decimal calculations.
     * Validates proper handling of decimal multiplication.
     */
    @Test
    public void testCalculateTotal_DecimalCalculation() {
        System.out.println("testCalculateTotal_DecimalCalculation");
        
        double unitPrice = 7.99;
        int quantity = 3;
        double expResult = 23.97;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.001, "Expected total to be 23.97 for unitPrice 7.99 and quantity 3");
    }
    
    /**
     * Test of calculateTotal method with large numbers.
     * Validates calculation with larger values.
     */
    @Test
    public void testCalculateTotal_LargeNumbers() {
        System.out.println("testCalculateTotal_LargeNumbers");
        
        double unitPrice = 999.99;
        int quantity = 100;
        double expResult = 99999.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        
        assertEquals(expResult, result, 0.01, "Expected total to be 99999.0 for unitPrice 999.99 and quantity 100");
    }
    
}

