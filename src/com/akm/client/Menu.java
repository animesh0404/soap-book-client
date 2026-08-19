package com.akm.client;

import java.util.List;
import java.util.Scanner;
import com.akm.client.model.Book;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);


    public void pause() {

        System.out.println();
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    public void clearScreen() {

        try {

            if (System.getProperty("os.name").contains("Windows")) {

                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();

            } else {

                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

        } catch (Exception e) {

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public int showMainMenu() {

        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println(" SOAP BOOK CLIENT ");
            System.out.println("=================================");
            System.out.println("1. Get Book");
            System.out.println("2. Get All Books");
            System.out.println("3. Create Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("0. Exit");
            System.out.println();

            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
            }
        }
    }

    public Scanner scanner() {
        return scanner;
    }

    public Long readBookId() {

        System.out.print("Book Id: ");

        return Long.parseLong(scanner.nextLine());
    }

    public Book readBook() {

        System.out.print("Id: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Year: ");
        Integer year = Integer.parseInt(scanner.nextLine());

        return new Book(
                id,
                isbn,
                title,
                author,
                year
        );
    }

    public static void printBook(Book book) {

        System.out.println();
        System.out.println("ID      : " + book.id());
        System.out.println("ISBN    : " + book.isbn());
        System.out.println("TITLE   : " + book.title());
        System.out.println("AUTHOR  : " + book.author());
        System.out.println("YEAR    : " + book.publicationYear());
        System.out.println();
    }

    public static void printBooks(List<Book> books) {

        System.out.println();

        System.out.printf(
                "%-5s %-18s %-30s %-25s %-5s%n",
                "ID",
                "ISBN",
                "TITLE",
                "AUTHOR",
                "YEAR"
        );

        System.out.println(
                "-------------------------------------------------------------------------------"
        );

        for (Book book : books) {

            System.out.printf(
                    "%-5d %-18s %-30s %-25s %-5d%n",
                    book.id(),
                    book.isbn(),
                    book.title(),
                    book.author(),
                    book.publicationYear()
            );
        }

        System.out.println();
    }

}