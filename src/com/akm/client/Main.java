package com.akm.client;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("SOAP Server (ip:port): ");
        String server = scanner.nextLine();

        SoapClient client = new SoapClient(server);
        Menu menu = new Menu();
        while (true) {
            menu.clearScreen();
            int choice = menu.showMainMenu();
            switch (choice) {

                case 1 -> {
                    Long id = menu.readBookId();
                    client.getBook(id);
                    menu.pause();
                }

                case 2 -> {
                    client.getAllBooks();
                    menu.pause();
                }

                case 3 -> {
                    client.createBook(menu.readBook());
                    menu.pause();
                }

                case 4 -> {
                    client.updateBook(menu.readBook());
                    menu.pause();
                }

                case 5 -> {
                    Long id = menu.readBookId();
                    client.deleteBook(id);
                    menu.pause();
                }

                case 0 -> System.exit(0);

                default -> System.out.println("Invalid choice");
            }
        }

    }
}