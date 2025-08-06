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
     * Test of addProduct method, of class AddProductController.
     */
    @Test
    public void testAddProductValidParams() {
        System.out.println("addProduct");
        String idText = "30";
        String name = "Chocolate";
        String priceText = "25.00";
        String stockText = "10";
        String owner = "lourdes";
        Component parent = null;
        boolean expResult = true;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testAddProductInvalidParams() {
        System.out.println("addProduct");
        String idText = " ";
        String name = " ";
        String priceText = " ";
        String stockText = "0";
        String owner = "";
        Component parent = null;
        boolean expResult = false;
        boolean result = AddProductController.addProduct(idText, name, priceText, stockText, owner, parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
