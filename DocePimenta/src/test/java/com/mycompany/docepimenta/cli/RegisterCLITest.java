package com.mycompany.docepimenta.cli;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterCLITest {
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    void registerNewUser() {
        String input = "novo\n123\nFUNC1\n";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new RegisterCLI().run(service, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new java.io.PrintStream(out));
        assertTrue(out.toString().contains("Usuário cadastrado com sucesso"));
        assertNotNull(service.login("novo", "123"));
    }

    @Test
    void registerExistingUser() {
        String input = "admin\n123\nADMIN\n";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new RegisterCLI().run(service, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new java.io.PrintStream(out));
        assertTrue(out.toString().contains("Usuário já existe"));
    }
}
