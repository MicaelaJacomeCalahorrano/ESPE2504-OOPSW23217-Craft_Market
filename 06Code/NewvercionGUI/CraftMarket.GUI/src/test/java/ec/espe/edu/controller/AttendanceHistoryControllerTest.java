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
public class AttendanceHistoryControllerTest {
    
    public AttendanceHistoryControllerTest() {
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
     * Test of getArtisanAttendanceHistory method, of class AttendanceHistoryController.
     */
    @Test
    public void testGetArtisanAttendanceHistory() {
        System.out.println("getArtisanAttendanceHistory");
        String artisanName = "";
        AttendanceHistoryController instance = new AttendanceHistoryController();
        List<Document> expResult = null;
        List<Document> result = instance.getArtisanAttendanceHistory(artisanName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printAttendanceHistory method, of class AttendanceHistoryController.
     */
    @Test
    public void testPrintAttendanceHistory() {
        System.out.println("printAttendanceHistory");
        AttendanceHistoryController instance = new AttendanceHistoryController();
        boolean expResult = false;
        boolean result = instance.printAttendanceHistory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
