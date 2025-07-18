package cli;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class TerminalApp {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserService service = new UserService(Paths.get("users.txt"));

        while (true) {
            System.out.println("\n1. Cadastrar Usuário");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            String opt = scanner.nextLine();

            switch (opt) {
                case "1":
                    cadastrar(scanner, service);
                    break;
                case "2":
                    login(scanner, service);
                    break;
                case "3":
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void cadastrar(Scanner scanner, UserService service) throws IOException {
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Tipo (admin/func1): ");
        String tipo = scanner.nextLine();

        if (!tipo.equals("admin") && !tipo.equals("func1")) {
            System.out.println("Tipo inválido");
            return;
        }

        if (service.register(usuario, senha, tipo)) {
            System.out.println("Usuário cadastrado!");
        } else {
            System.out.println("Usuário já existe!");
        }
    }

    private static void login(Scanner scanner, UserService service) {
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        User user = service.authenticate(usuario, senha);
        if (user != null) {
            System.out.println("Login realizado! Tipo: " + user.getRole());
        } else {
            System.out.println("Usuário ou senha incorretos");
        }
    }
}
