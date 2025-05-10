package com.aluracursos.conversor.modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteServidor {
    private static final String API_KEY = "e20a4a101bc207bf6b344197"; // Coloca tu clave real aquí
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private final Gson gson = new Gson();

    public JsonObject obtenerConversiones(String monedaBase) throws Exception {
        String url = API_URL + monedaBase;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), JsonObject.class);
    }
}
