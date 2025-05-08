package com.aluracursos.conversor.servicio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;

//estructura base de la clase
public class ConversorService {

    private final String apiKey = "e20a4a101bc207bf6b344197";

    public void convertir(String monedaOrigen, String monedaDestino) {
        try {
            double tasa = obtenerTasa(monedaOrigen, monedaDestino);

            System.out.printf("💱 Tasa de cambio %s -> %s: %.2f%n", monedaOrigen, monedaDestino, tasa);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la cantidad de " + monedaOrigen + " que desea convertir: ");
            double cantidad = scanner.nextDouble();

            double resultado = cantidad * tasa;
            System.out.printf("✅ %.2f %s equivalen a %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);

        } catch (Exception e) {
            System.out.println("❌ Error al obtener la tasa de cambio.");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    public double obtenerTasa(String monedaOrigen, String monedaDestino) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;

        // Crear cliente
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlStr))
                .GET()
                .build();

        // Enviar solicitud y obtener respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parsear JSON
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        if (!json.has("conversion_rates")) {
            throw new RuntimeException("Respuesta inválida de la API");
        }

        return json.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
    }
}
