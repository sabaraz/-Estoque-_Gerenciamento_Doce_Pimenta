package cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Path storageFile;
    private final Map<String, User> users = new HashMap<>();

    public UserService(Path storageFile) throws IOException {
        this.storageFile = storageFile;
        load();
    }

    private void load() throws IOException {
        if (!Files.exists(storageFile)) {
            return;
        }
        for (String line : Files.readAllLines(storageFile)) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                users.put(parts[0], new User(parts[0], parts[1], parts[2]));
            }
        }
    }

    private void save(User user) throws IOException {
        String line = user.getUsername() + "," + user.getPassword() + "," + user.getRole() + System.lineSeparator();
        Files.writeString(storageFile, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public boolean register(String username, String password, String role) throws IOException {
        if (users.containsKey(username)) {
            return false;
        }
        User user = new User(username, password, role);
        users.put(username, user);
        save(user);
        return true;
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
