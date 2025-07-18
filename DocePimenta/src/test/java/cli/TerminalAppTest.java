package cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerminalAppTest {
    @Test
    public void testRegisterAndLogin() {
        assertTrue(TerminalApp.registerUser("user1", "123", "func1"));
        assertEquals("func1", TerminalApp.loginUser("user1", "123"));
    }
}
