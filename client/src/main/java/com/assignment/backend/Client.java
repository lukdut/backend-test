package com.assignment.backend;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private final static String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) throws IOException, InterruptedException {
        final String baseUrl = System.getProperty("baseUrl", BASE_URL);

        if (args.length != 3) {
            System.err.println("Specify space-separated source account number, target account number anb amount value");
            return;
        }

        Long from;
        Long to;
        long amount;

        try {
            from = Long.valueOf(args[0]);
            to = Long.valueOf(args[1]);
            amount = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("One of specified values is not a valid number");
            return;
        }

        HttpClient client = HttpClient.newHttpClient();

        System.out.println("Before transfer: ");
        printAccounts(baseUrl, client);
        performTransfer(baseUrl, from, to, amount, client);
        System.out.println("After transfer: ");
        printAccounts(baseUrl, client);
    }

    private static void performTransfer(String baseUrl, Long from, Long to, long amount, HttpClient client) throws IOException, InterruptedException {
        HttpRequest transferRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("%s/transfer?from=%d&to=%d&amount=%d", baseUrl, from, to, amount)))
                .build();
        client.send(transferRequest, HttpResponse.BodyHandlers.ofString());
    }

    private static void printAccounts(String baseUrl, HttpClient client) throws IOException, InterruptedException {
        HttpRequest accountsRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/accounts"))
                .build();
        HttpResponse<String> response = client.send(accountsRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
