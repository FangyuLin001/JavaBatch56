package day5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Assignment4 {
    public static void main(String [] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);

        ReaderThread reader = new ReaderThread(queue, "input.txt");
        WriterThread writer = new WriterThread(queue, "output.txt");

        new Thread(reader).start();
        new Thread(writer).start();
    }
}
