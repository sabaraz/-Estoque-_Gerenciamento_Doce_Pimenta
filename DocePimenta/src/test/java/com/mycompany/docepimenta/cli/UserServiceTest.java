package com.mycompany.docepimenta.cli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    void registerAndLogin() {
        assertTrue(service.registerUser("novo", "123", "FUNC1"));
        assertFalse(service.registerUser("novo", "outro", "FUNC1"));
        User user = service.login("novo", "123");
        assertNotNull(user);
        assertEquals("FUNC1", user.getRole());
    }

    @Test
    void loginInvalid() {
        assertNull(service.login("naoexiste", "123"));
    }
}
