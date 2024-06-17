package com.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThreadPoolTest {
    private ThreadPool threadPool;
    private static final int NUM_THREADS = 4;

    @BeforeEach
    void setUp() {
        threadPool = new ThreadPool(NUM_THREADS);
    }

    @AfterEach
    void tearDown() {
        threadPool.shutdown();
    }

    @Test
    void testSubmitSingleTask() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        threadPool.submit(counter::incrementAndGet);

        // Wait for a bit to allow the task to complete
        Thread.sleep(100);

        assertEquals(1, counter.get());
    }

    @Test
    void testSubmitMultipleTasks() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int numTasks = 10;

        for (int i = 0; i < numTasks; i++) {
            threadPool.submit(counter::incrementAndGet);
        }

        // Wait for a bit to allow all tasks to complete
        Thread.sleep(500);

        assertEquals(numTasks, counter.get());
    }

    @Test
    void testShutdown() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            threadPool.submit(counter::incrementAndGet);
        }

        threadPool.shutdown();

        // Attempt to submit another task after shutdown
        assertThrows(IllegalStateException.class, () -> threadPool.submit(counter::incrementAndGet));

        // Wait for a bit to allow all tasks to complete
        Thread.sleep(500);

        assertEquals(10, counter.get());
    }

    @Test
    void testShutdownCompletesAllTasks() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int numTasks = 10;

        for (int i = 0; i < numTasks; i++) {
            threadPool.submit(() -> {
                try {
                    Thread.sleep(100); // Simulate long-running task
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                counter.incrementAndGet();
            });
        }

        threadPool.shutdown();

        // Wait for a bit to allow all tasks to complete
        Thread.sleep(1500);

        assertEquals(numTasks, counter.get());
    }
}
