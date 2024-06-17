package com.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TextProcessorTest {

    @Test
    public void testProcess() throws InterruptedException {
        String input = "hello";
        TextProcessor processor = new TextProcessor(input);
        String result = processor.process();
        assertEquals("HELLO", result);
    }

    @Test
    public void testEmptyString() throws InterruptedException {
        String input = "";
        TextProcessor processor = new TextProcessor(input);
        String result = processor.process();
        assertEquals("", result);
    }

    @Test
    public void testSingleCharacter() throws InterruptedException {
        String input = "a";
        TextProcessor processor = new TextProcessor(input);
        String result = processor.process();
        assertEquals("A", result);
    }

    @Test
    public void testLongString() throws InterruptedException {
        String input = "abcdefghijklmnopqrstuvwxyz";
        TextProcessor processor = new TextProcessor(input);
        String result = processor.process();
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", result);
    }
}
