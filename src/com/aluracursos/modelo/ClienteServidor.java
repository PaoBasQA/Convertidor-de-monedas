package com.aluracursos.modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ClienteServidor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/";
    private static final String[] MONEDAS_PERMITIDAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};
    private final Gson gson = new Gson();

    public Map<String, Double> obtenerTasasDeCambio(String monedaBase) {
        Map<String, Double> tasasFiltradas = new HashMap<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + monedaBase))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (!jsonObject.has("conversion_rates")) {
                System.out.println("Error: No se encontró el objeto 'conversion_rates' en la respuesta.");
                return tasasFiltradas;
            }

            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            for (String moneda : MONEDAS_PERMITIDAS) {
                if (conversionRates.has(moneda)) {
                    tasasFiltradas.put(moneda, conversionRates.get(moneda).getAsDouble());
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error al conectarse con la API: " + e.getMessage());
        }

        return tasasFiltradas;
    }
}
