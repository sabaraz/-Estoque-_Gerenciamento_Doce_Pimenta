package com.mycompany.docepimenta.console;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private final Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password, UserRole role) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password, role));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // For testing convenience
    public Map<String, User> getAllUsers() {
        return users;
    }
}
