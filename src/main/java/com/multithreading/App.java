package com.multithreading;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();

        TextProcessor processor = new TextProcessor(input);

        try {
            System.out.println("Capitalized String: " + processor.process());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Processing interrupted. Exiting...");
        }
    }
}
