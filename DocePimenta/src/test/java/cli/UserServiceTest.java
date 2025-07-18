package cli;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private Path tempFile;
    private UserService service;

    @BeforeEach
    public void setup() throws IOException {
        tempFile = Files.createTempFile("users", ".txt");
        service = new UserService(tempFile);
    }

    @Test
    public void testRegisterAndLoginAdmin() throws IOException {
        assertTrue(service.register("admin", "123", "admin"));
        User user = service.authenticate("admin", "123");
        assertNotNull(user);
        assertEquals("admin", user.getRole());
    }

    @Test
    public void testRegisterAndLoginFunc1() throws IOException {
        assertTrue(service.register("func", "abc", "func1"));
        User user = service.authenticate("func", "abc");
        assertNotNull(user);
        assertEquals("func1", user.getRole());
    }

    @Test
    public void testLoginFail() throws IOException {
        service.register("user", "pass", "func1");
        User user = service.authenticate("user", "wrong");
        assertNull(user);
    }
}
