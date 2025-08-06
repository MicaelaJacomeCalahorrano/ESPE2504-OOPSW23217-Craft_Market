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
public class UserControllerTest {
    static String validUsername = "lourdes";
    static String validPassword = "password";
    static String wrongPassword = "wrongpass";
    static String nonexistentUser = "ghost";
    
    public UserControllerTest() {
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

    @BeforeAll
    public static void setup() {
    }

    @Test
    public void testLogin_ValidCredentials() {
        boolean result = UserController.login(validUsername, validPassword);
        assertTrue(result, "Should log in with valid credentials");
    }

    @Test
    public void testLogin_InvalidPassword() {
        boolean result = UserController.login(validUsername, wrongPassword);
        assertFalse(result, "Should not log in with incorrect password");
    }

    @Test
    public void testLogin_NonexistentUser() {
        boolean result = UserController.login(nonexistentUser, validPassword);
        assertFalse(result, "Should not log in with nonexistent user");
    }

    @Test
    public void testGetArtisanName_ValidUser() {
        String name = UserController.getArtisanName(validUsername, validPassword);
        assertNotEquals("Artesano desconocido", name, "Should return artisan name if credentials are valid");
    }

    @Test
    public void testGetArtisanName_InvalidUser() {
        String name = UserController.getArtisanName(nonexistentUser, wrongPassword);
        assertEquals("Artesano desconocido", name, "Should return 'Artesano desconocido' if user does not exist");
    }

    @Test
    public void testProcessLogin_EmptyFields() {
        UserController.LoginResult result = UserController.processLogin("", "");
        assertFalse(result.isSuccess());
        assertEquals("Por favor, ingrese usuario y contraseña.", result.getMessage());
    }

    @Test
    public void testProcessLogin_InvalidCredentials() {
        UserController.LoginResult result = UserController.processLogin(nonexistentUser, wrongPassword);
        assertFalse(result.isSuccess());
        assertEquals("Usuario o contraseña incorrectos.", result.getMessage());
    }

    @Test
    public void testProcessLogin_ValidCredentials() {
        UserController.LoginResult result = UserController.processLogin(validUsername, validPassword);
        assertTrue(result.isSuccess(), "Should succeed with valid credentials");
        assertTrue(result.getMessage().contains("Bienvenido"), "Message should contain 'Bienvenido'");
        assertNotNull(result.getArtisanName(), "Artisan name should not be null");
    }

    
}

