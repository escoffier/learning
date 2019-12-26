package com.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        Future<String> completableFuture = calculateAsync();
        String result = completableFuture.get();
        System.out.println("result");

        CompletableFuture<String> completableFuture1 =
                CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture1.thenApply(s -> s + "World");

        String result1 = future.get();
        System.out.println("result");

    }

    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(5000 * 1);
            } catch (InterruptedException ex) {
                //throw new InterruptedException();

                System.out.println(ex.getMessage());
                completableFuture.completeExceptionally(ex);
            }

            completableFuture.complete("Hello");
            //completableFuture.cancel(false);
            return;
        });
        return completableFuture;
    }
}
