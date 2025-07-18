package com.mycompany.docepimenta.console;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleAppTest {

    @Test
    void testRunRegisterAndLoginFlow() {
        String input = "1\nuser1\npass1\nfunc1\n2\nuser1\npass1\n0\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            UserStore store = new UserStore();
            ConsoleApp app = new ConsoleApp(store);
            app.run();
            assertEquals(1, store.getAllUsers().size());
            assertNotNull(store.login("user1", "pass1"));
        } finally {
            System.setIn(stdin);
        }
    }
}
