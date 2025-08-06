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
     * Test of registerAttendance method, of class AttendanceController.
     */
    @Test
    public void testRegisterAttendanceValid() {
        System.out.println("registerAttendance");
        String artisanName = "guido";
        String dateString = "06/08/2025";
        boolean confirmed = true;
        AttendanceController instance = new AttendanceController();
        AttendanceController.RegisterAttendanceResult expResult = null;
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testRegisterAttendanceInvalid() {
        System.out.println("registerAttendance");
        String artisanName = " ";
        String dateString = " ";
        boolean confirmed = false;
        AttendanceController instance = new AttendanceController();
        AttendanceController.RegisterAttendanceResult expResult = null;
        AttendanceController.RegisterAttendanceResult result = instance.registerAttendance(artisanName, dateString, confirmed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAttendanceHistory method, of class AttendanceController.
     */
    @Test
    public void testGetAttendanceHistory() {
        System.out.println("getAttendanceHistory");
        String artisanName = "";
        AttendanceController instance = new AttendanceController();
        List<Document> expResult = null;
        List<Document> result = instance.getAttendanceHistory(artisanName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
