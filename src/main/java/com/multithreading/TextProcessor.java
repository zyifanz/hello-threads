package com.multithreading;

class Capitalize implements Runnable {
    private final char[] chars;
    private final int index;

    public Capitalize(char[] chars, int index) {
        this.chars = chars;
        this.index = index;
    }

    @Override
    public void run() {
        synchronized (chars) {
            chars[index] = Character.toUpperCase(chars[index]);
        }
    }
}

public class TextProcessor {

    public String process(String input) throws InterruptedException {
        Thread[] threads = new Thread[input.length()];

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            threads[i] = new Thread(new Capitalize(chars, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return new String(chars);
    }
}
