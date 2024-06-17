package com.multithreading;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<WorkerThread> threads;
    private final List<Runnable> taskQueue;
    private boolean isShutdown = false;

    public ThreadPool(int numThreads) {
        this.threads = new LinkedList<>();
        this.taskQueue = new LinkedList<>();

        for (int i = 0; i < numThreads; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threads.add(thread);
        }
    }

    public void submit(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("Thread pool is shut down");
        }
        synchronized (taskQueue) {
            taskQueue.addLast(task);
            taskQueue.notify();
        }
    }

    public void shutdown() {
        synchronized (taskQueue) {
            isShutdown = true;
            taskQueue.notifyAll();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (isShutdown && taskQueue.isEmpty()) {
                        return;
                    }
                    task = taskQueue.removeFirst();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.err.println("Task threw an exception: " + e.getMessage());
                }

            }
        }
    }
}
