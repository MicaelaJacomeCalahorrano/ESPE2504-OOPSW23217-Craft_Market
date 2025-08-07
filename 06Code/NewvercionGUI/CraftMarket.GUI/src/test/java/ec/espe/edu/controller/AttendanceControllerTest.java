/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.espe.edu.controller;

import java.util.List;
import org.bson.Document;
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
public class AttendanceControllerTest {
    
    public AttendanceControllerTest() {
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
     * Test of registerAttendance method with valid parameters.
     * Validates that registration with valid parameters is successful.
     */
    @Test
    public void testRegisterAttendanceValid() {
        System.out.println("testRegisterAttendanceValid");
        
        String artisanName = "guido";
        String dateString = "06/08/2025";
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        
        // Verify that result is not null and is successful
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isSuccess(), "Registration with valid parameters should be successful");
        assertNotNull(result.getMessage(), "Should have a response message");
    }
    
    /**
     * Test of registerAttendance method with empty artisan name.
     * Validates that an empty artisan name returns error.
     */
    @Test
    public void testRegisterAttendanceEmptyArtisanName() {
        System.out.println("testRegisterAttendanceEmptyArtisanName");
        
        String artisanName = "";
        String dateString = "06/08/2025";
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isSuccess(), "Empty name should fail");
        assertEquals("El nombre del artesano no puede estar vacío.", result.getMessage());
    }
    
    /**
     * Test of registerAttendance method with empty date.
     * Validates that an empty date returns error.
     */
    @Test
    public void testRegisterAttendanceEmptyDate() {
        System.out.println("testRegisterAttendanceEmptyDate");
        
        String artisanName = "guido";
        String dateString = "";
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isSuccess(), "Empty date should fail");
        assertEquals("La fecha no puede estar vacía.", result.getMessage());
    }
    
    /**
     * Test of registerAttendance method with invalid date format.
     * Validates that an invalid date format returns error.
     */
    @Test
    public void testRegisterAttendanceInvalidDateFormat() {
        System.out.println("testRegisterAttendanceInvalidDateFormat");
        
        String artisanName = "guido";
        String dateString = "2025-08-06"; // Wrong format (should be dd/MM/yyyy)
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isSuccess(), "Invalid date format should fail");
        assertEquals("Formato de fecha inválido. Use dd/MM/yyyy.", result.getMessage());
    }
    
    /**
     * Test of registerAttendance method with null parameters.
     * Validates that null parameters return error.
     */
    @Test
    public void testRegisterAttendanceNullParameters() {
        System.out.println("testRegisterAttendanceNullParameters");
        
        String artisanName = null;
        String dateString = null;
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isSuccess(), "Null parameters should fail");
    }

    /**
     * Test of getAttendanceHistory method, of class AttendanceController.
     * Validates that the method returns a list (may be empty if no records).
     */
    @Test
    public void testGetAttendanceHistory() {
        System.out.println("testGetAttendanceHistory");
        
        String artisanName = "guido";
        AttendanceController instance = new AttendanceController();
        
        List<Document> result = instance.getAttendanceHistory(artisanName);
        
        // Result should not be null, though it may be an empty list
        assertNotNull(result, "Result should not be null");
        // We don't verify specific content because it depends on database state
        assertTrue(result instanceof List, "Should return a List instance");
    }
    
    /**
     * Test of getAttendanceHistory method with empty artisan name.
     * Validates that it works even with empty name (returns empty list).
     */
    @Test
    public void testGetAttendanceHistoryEmptyName() {
        System.out.println("testGetAttendanceHistoryEmptyName");
        
        String artisanName = "";
        AttendanceController instance = new AttendanceController();
        
        List<Document> result = instance.getAttendanceHistory(artisanName);
        
        assertNotNull(result, "Result should not be null");
        assertTrue(result instanceof List, "Should return a List instance");
        // With empty name, probably returns empty list
    }
    
}
