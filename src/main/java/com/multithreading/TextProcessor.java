package com.multithreading;

import java.util.ArrayList;
import java.util.List;

class Capitalize implements Runnable {
    private volatile boolean isRunning = true;
    private char character;

    public Capitalize(char character) {
        this.character = character;
    }

    @Override
    public void run() {
        if (isRunning) {
            this.character = Character.toUpperCase(character);
        }
    }

    public void stop() {
        this.isRunning = false;
    }

    public char getChar() {
        return character;
    }
}

public class TextProcessor {

    private final List<Thread> threads;
    private final List<Capitalize> capitalizeTasks;

    TextProcessor() {
        this.capitalizeTasks = new ArrayList<>();
        this.threads = new ArrayList<>();
    }

    public String process(String input) throws InterruptedException {
        int inputLength = input.length();

        char[] chars = input.toCharArray();
        char[] capitalizedChars = new char[inputLength];

        for (int i = 0; i < inputLength; i++) {
            capitalizeTasks.add(new Capitalize(chars[i]));
            threads.add(new Thread(capitalizeTasks.getLast()));
            threads.getLast().start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        for (int i = 0; i < inputLength; i++) {
            capitalizedChars[i] = capitalizeTasks.get(i).getChar();
        }

        return new String(capitalizedChars);
    }

    public void stop() {
        for (Capitalize task : capitalizeTasks) {
            task.stop();
        }
    }
}
