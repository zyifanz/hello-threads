package com.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

class Capitalize implements Runnable {
    private final char[] chars;
    private final int index;
    private final AtomicInteger numTasksLeft;

    public Capitalize(char[] chars, int index, AtomicInteger numTasksLeft) {
        this.chars = chars;
        this.index = index;
        this.numTasksLeft = numTasksLeft;
    }

    @Override
    public void run() {
        synchronized (chars) {
            chars[index] = Character.toUpperCase(chars[index]);
        }
        synchronized (numTasksLeft) {
            numTasksLeft.decrementAndGet();
            numTasksLeft.notifyAll();
        }
    }
}

public class TextProcessor {

    private final ThreadPool threadPool;

    public TextProcessor(int numThreads) {
        this.threadPool = new ThreadPool(numThreads);
    }

    public String process(String input) throws InterruptedException {
        char[] chars = input.toCharArray();
        AtomicInteger numTasksLeft = new AtomicInteger(input.length());

        for (int i = 0; i < chars.length; i++) {
            threadPool.submit(new Capitalize(chars, i, numTasksLeft));
        }

        synchronized (numTasksLeft) {
            while (numTasksLeft.get() > 0) {
                numTasksLeft.wait();
            }
        }

        threadPool.shutdown();

        return new String(chars);
    }
}
