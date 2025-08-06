/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

import ec.espe.edu.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jorge
 */

@ExtendWith(MockitoExtension.class)
public class ProductManagerTest {
    
    public ProductManagerTest() {
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
     * Test of updateProductName method, of class ProductManager.
     */
    @Test
    public void testUpdateProductName() {
        System.out.println("updateProductName");
        int productId = 0;
        String newName = "";
        boolean expResult = false;
        boolean result = ProductManager.updateProductName(productId, newName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProductPrice method, of class ProductManager.
     */
    @Test
    public void testUpdateProductPrice() {
        System.out.println("updateProductPrice");
        int productId = 0;
        double newPrice = 0.0;
        boolean expResult = false;
        boolean result = ProductManager.updateProductPrice(productId, newPrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProductStock method, of class ProductManager.
     */
    @Test
    public void testUpdateProductStock() {
        System.out.println("updateProductStock");
        int productId = 0;
        int newStock = 0;
        boolean expResult = false;
        boolean result = ProductManager.updateProductStock(productId, newStock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    @Test
    public void testUpdateProductName_ProductExists_ReturnsTrue() {
        int productId = 1;
        String newName = "Nuevo Producto";

        Product mockProduct = mock(Product.class);

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(mockProduct);

        boolean result = ProductManager.updateProductName(productId, newName);

        verify(mockProduct).setName(newName);
        verifyStatic(Product.class);
        Product.updateProduct(mockProduct);

        assertTrue(result);
    }

    @Test
    public void testUpdateProductName_ProductNotFound_ReturnsFalse() {
        int productId = 999;
        String newName = "Nombre";

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(null);

        boolean result = ProductManager.updateProductName(productId, newName);

        assertFalse(result);
    }

    @Test
    public void testUpdateProductPrice_ProductExists_ReturnsTrue() {
        int productId = 2;
        double newPrice = 19.99;

        Product mockProduct = mock(Product.class);

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(mockProduct);

        boolean result = ProductManager.updateProductPrice(productId, newPrice);

        verify(mockProduct).setUnitPrice(newPrice);
        verifyStatic(Product.class);
        Product.updateProduct(mockProduct);

        assertTrue(result);
    }

    @Test
    public void testUpdateProductPrice_ProductNotFound_ReturnsFalse() {
        int productId = 888;
        double newPrice = 9.99;

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(null);

        boolean result = ProductManager.updateProductPrice(productId, newPrice);

        assertFalse(result);
    }

    @Test
    public void testUpdateProductStock_ProductExists_ReturnsTrue() {
        int productId = 3;
        int newStock = 50;

        Product mockProduct = mock(Product.class);

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(mockProduct);

        boolean result = ProductManager.updateProductStock(productId, newStock);

        verify(mockProduct).setStock(newStock);
        verifyStatic(Product.class);
        Product.updateProduct(mockProduct);

        assertTrue(result);
    }

    @Test
    public void testUpdateProductStock_ProductNotFound_ReturnsFalse() {
        int productId = 777;
        int newStock = 20;

        mockStatic(Product.class);
        when(Product.findById(productId)).thenReturn(null);

        boolean result = ProductManager.updateProductStock(productId, newStock);

        assertFalse(result);
    }
    
}
