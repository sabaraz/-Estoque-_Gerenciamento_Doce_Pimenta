package com.mycompany.docepimenta.cli;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginCLITest {
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    void loginSuccess() {
        String input = "admin\nadmin\n";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new LoginCLI().run(service, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new java.io.PrintStream(out));
        assertTrue(out.toString().contains("Bem-vindo ADMIN admin"));
    }

    @Test
    void loginFail() {
        String input = "admin\nerrado\n";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new LoginCLI().run(service, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new java.io.PrintStream(out));
        assertTrue(out.toString().contains("Usuário ou senha inválidos"));
    }
}
