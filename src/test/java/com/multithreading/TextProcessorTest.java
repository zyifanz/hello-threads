package com.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TextProcessorTest {

    @Test
    public void testProcess() throws InterruptedException {
        String input = "hello";
        TextProcessor processor = new TextProcessor();
        String result = processor.process(input);
        assertEquals("HELLO", result);
    }

    @Test
    public void testEmptyString() throws InterruptedException {
        String input = "";
        TextProcessor processor = new TextProcessor();
        String result = processor.process(input);
        assertEquals("", result);
    }

    @Test
    public void testSingleCharacter() throws InterruptedException {
        String input = "a";
        TextProcessor processor = new TextProcessor();
        String result = processor.process(input);
        assertEquals("A", result);
    }

    @Test
    public void testLongString() throws InterruptedException {
        String input = "abcdefghijklmnopqrstuvwxyz";
        TextProcessor processor = new TextProcessor();
        String result = processor.process(input);
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", result);
    }

    @Test
    public void testStringWithSymbols() throws InterruptedException {
        String input = "hello_world";
        TextProcessor processor = new TextProcessor();
        String result = processor.process(input);
        assertEquals("HELLO_WORLD", result);
    }
}
