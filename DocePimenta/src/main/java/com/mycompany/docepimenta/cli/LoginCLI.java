package com.mycompany.docepimenta.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LoginCLI {
    public void run(UserService service, InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        out.print("Usuário: ");
        String username = scanner.nextLine();
        out.print("Senha: ");
        String password = scanner.nextLine();
        User user = service.login(username, password);
        if (user != null) {
            out.println("Bem-vindo " + user.getRole() + " " + user.getUsername() + "!");
        } else {
            out.println("Usuário ou senha inválidos.");
        }
    }
}
