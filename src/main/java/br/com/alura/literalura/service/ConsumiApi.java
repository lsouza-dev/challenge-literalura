package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumiApi {
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response = null;

    public String consumir (String url){
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(url);
            System.out.println(response.statusCode());
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
