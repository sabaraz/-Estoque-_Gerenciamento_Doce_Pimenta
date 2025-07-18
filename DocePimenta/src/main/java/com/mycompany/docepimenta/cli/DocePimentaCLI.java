package com.mycompany.docepimenta.cli;

import java.util.Scanner;

public class DocePimentaCLI {
    public static void main(String[] args) {
        UserService service = new UserService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> new RegisterCLI().run(service, System.in, System.out);
                case "2" -> new LoginCLI().run(service, System.in, System.out);
                case "0" -> { System.out.println("Saindo..."); return; }
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
