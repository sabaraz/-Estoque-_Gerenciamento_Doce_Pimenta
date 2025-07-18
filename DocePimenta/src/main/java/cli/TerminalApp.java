package cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalApp {
    static final Map<String, String> userPasswords = new HashMap<>();
    static final Map<String, String> userRoles = new HashMap<>();

    static {
        userPasswords.put("admin", "admin123");
        userRoles.put("admin", "admin");
    }

    public static boolean registerUser(String user, String pass, String role) {
        if (!"admin".equals(role) && !"func1".equals(role)) {
            return false;
        }
        if (userPasswords.containsKey(user)) {
            return false;
        }
        userPasswords.put(user, pass);
        userRoles.put(user, role);
        return true;
    }

    public static String loginUser(String user, String pass) {
        String saved = userPasswords.get(user);
        if (saved != null && saved.equals(pass)) {
            return userRoles.getOrDefault(user, "func1");
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    handleRegister(sc);
                    break;
                case "2":
                    handleLogin(sc);
                    break;
                case "3":
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void handleRegister(Scanner sc) {
        System.out.print("Usuário: ");
        String user = sc.nextLine().trim();
        System.out.print("Senha: ");
        String pass = sc.nextLine().trim();
        System.out.print("Tipo (admin/func1): ");
        String role = sc.nextLine().trim();
        if (registerUser(user, pass, role)) {
            System.out.println("Usuário cadastrado.");
        } else {
            System.out.println("Falha ao cadastrar usuário.");
        }
    }

    private static void handleLogin(Scanner sc) {
        System.out.print("Usuário: ");
        String user = sc.nextLine().trim();
        System.out.print("Senha: ");
        String pass = sc.nextLine().trim();
        String role = loginUser(user, pass);
        if (role != null) {
            System.out.println("Login bem-sucedido. Perfil: " + role);
        } else {
            System.out.println("Usuário ou senha incorretos.");
        }
    }
}
