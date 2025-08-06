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
     * Test of calculateTotal method, of class SaleCalculator.
     */
    @Test
    public void testCalculateTotal_ZeroValues() {
        System.out.println("calculateTotal - Zero values");
        double unitPrice = 0.0;
        int quantity = 0;
        double expResult = 0.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        assertEquals(expResult, result, 0.001, "Expected total to be 0.0 when price and quantity are 0");
    }

    @Test
    public void testCalculateTotal_NormalValues() {
        System.out.println("calculateTotal - Normal values");
        double unitPrice = 2.5;
        int quantity = 4;
        double expResult = 10.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        assertEquals(expResult, result, 0.001, "Expected total to be 10.0 for unitPrice 2.5 and quantity 4");
    }

    @Test
    public void testCalculateTotal_NegativeQuantity() {
        System.out.println("calculateTotal - Negative quantity");
        double unitPrice = 3.0;
        int quantity = -2;
        double expResult = -6.0;
        double result = SaleCalculator.calculateTotal(unitPrice, quantity);
        assertEquals(expResult, result, 0.001, "Expected total to be negative when quantity is negative");
    }
    
}

