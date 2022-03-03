package day6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Homework5 {

    public static void main(String [] args) throws InterruptedException, ExecutionException {
        // scenario 1
        Future<String> completableFuture = calculateAsync();
        String result = completableFuture.get();
        assert "Hello".equals(result);

        // scenario 2
        // Processing Results of Asynchronous Computations
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture2
                .thenRun(() -> System.out.println("Computation finished."));

        future.get();

        // scenario 3
        // Combining Futures
        CompletableFuture<String> completableFuture3
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        assert "Hello World".equals(completableFuture3.get());
    }

    /**
     * The CompletableFuture class implements the Future interface,
     * so we can use it as a Future implementation,
     * but with additional completion logic.
     * @return Feature<String>
     */
    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }
}
