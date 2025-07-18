package com.mycompany.docepimenta.console;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStoreTest {

    @Test
    void testRegisterAndLogin() {
        UserStore store = new UserStore();
        assertTrue(store.register("admin", "123", UserRole.ADMIN));
        assertFalse(store.register("admin", "123", UserRole.ADMIN));

        User user = store.login("admin", "123");
        assertNotNull(user);
        assertEquals(UserRole.ADMIN, user.getRole());

        assertNull(store.login("admin", "wrong"));
        assertNull(store.login("ghost", "123"));
    }
}
