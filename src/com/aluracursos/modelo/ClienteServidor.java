package com.aluracursos.modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteServidor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/e20a4a101bc207bf6b344197/latest/";
    private final Gson gson = new Gson();

    public void convertir(String monedaOrigen, String monedaDestino, double monto) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + monedaOrigen))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (!jsonObject.has("conversion_rates")) {
                System.out.println("❌ No se encontró el objeto 'conversion_rates' en la respuesta.");
                return;
            }

            JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");

            if (!tasas.has(monedaDestino)) {
                System.out.println("❌ No se encontró la tasa para la moneda destino: " + monedaDestino);
                return;
            }

            double tasa = tasas.get(monedaDestino).getAsDouble();
            double resultado = monto * tasa;

            System.out.printf("💱 1 %s = %.2f %s%n", monedaOrigen, tasa, monedaDestino);

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error al conectar con la API: " + e.getMessage());
        }
    }
}
