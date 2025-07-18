package com.mycompany.docepimenta.console;

import java.util.Scanner;

public class ConsoleApp {

    private final UserStore userStore;
    private final Scanner scanner;

    public ConsoleApp(UserStore userStore) {
        this.userStore = userStore;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        UserStore store = new UserStore();
        ConsoleApp app = new ConsoleApp(store);
        app.run();
    }

    public void run() {
        System.out.println("Bem-vindo ao sistema Doce Pimenta");
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Cadastrar\n2 - Login\n0 - Sair");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> handleRegister();
                case "2" -> handleLogin();
                case "0" -> exit = true;
                default -> System.out.println("Opção inválida");
            }
        }
        System.out.println("Até logo!");
    }

    private void handleRegister() {
        System.out.print("Usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        System.out.print("Tipo (admin/func1): ");
        String type = scanner.nextLine();
        UserRole role = type.equalsIgnoreCase("admin") ? UserRole.ADMIN : UserRole.FUNC1;
        boolean ok = userStore.register(username, password, role);
        if (ok) {
            System.out.println("Usuário cadastrado!");
        } else {
            System.out.println("Usuário já existe!");
        }
    }

    private void handleLogin() {
        System.out.print("Usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        User user = userStore.login(username, password);
        if (user != null) {
            System.out.println("Login bem-sucedido! Tipo: " + user.getRole());
        } else {
            System.out.println("Usuário ou senha incorretos.");
        }
    }
}
