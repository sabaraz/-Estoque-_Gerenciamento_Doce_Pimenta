package com.mycompany.docepimenta.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RegisterCLI {
    public void run(UserService service, InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        out.print("Nome de usuário: ");
        String username = scanner.nextLine();
        out.print("Senha: ");
        String password = scanner.nextLine();
        out.print("Tipo (ADMIN/FUNC1): ");
        String role = scanner.nextLine();
        boolean success = service.registerUser(username, password, role);
        if (success) {
            out.println("Usuário cadastrado com sucesso!");
        } else {
            out.println("Usuário já existe!");
        }
    }
}
