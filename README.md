# Hello Threads

A small Java program that takes a string input (from the command line) and capitalizes it: "hello" -> "HELLO". Each character processing must be done in parallel by a thread, then compiled back into the final output and returned on the console. This must be achieved using Java Threads and Mutex, without using Executors.

Stretch goal #1: Limit the number of threads to N (e.g., N=3, where N is less than the string length). The threads should be recycled.

Stretch goal #2: Make the program reusable: it should keep accepting inputs even while the current input is processing, and only terminate when the user requests the program to quit.

Stretch goal #3: Use Java Process instead of threads (relying on process input/output streams for communication).
