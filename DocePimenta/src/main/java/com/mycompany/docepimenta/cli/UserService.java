package com.mycompany.docepimenta.cli;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public UserService() {
        // create default users
        registerUser("admin", "admin", "ADMIN");
        registerUser("func1", "func1", "FUNC1");
    }

    public boolean registerUser(String username, String password, String role) {
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
}
