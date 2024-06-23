package com.multithreading;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();

        TextProcessor processor = new TextProcessor();

        try {
            String output = processor.process(input);
            System.out.println("Capitalized String: " + output);
        } catch (InterruptedException e) {
            processor.stop();
            System.err.println("Processing interrupted. Exiting...");
        }
    }
}
