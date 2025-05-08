package com.aluracursos.conversor.servicio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Scanner;

//estructura base de la clase - listo para usar HttpClient
public class ConversorService {
    private final String apiKey = "e20a4a101bc207bf6b344197";
    private final HttpClient client;

    public ConversorService() {
        this.client = HttpClient.newHttpClient(); // Creamos el cliente una sola vez
    }
}

//construir la solicitud HttpRequest
public double obtenerTasa(String monedaOrigen, String monedaDestino) throws Exception {
    String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;

    // Creamos la solicitud con HttpRequest
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlStr))
            .GET()
            .build();

    // Enviamos la solicitud y obtenemos la respuesta
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    // Parseamos el JSON
    JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

    // Validamos y obtenemos la tasa
    if (!json.has("conversion_rates")) {
        throw new RuntimeException("Respuesta inválida de la API");
    }

    return json.getAsJsonObject("conversion_rates")
            .get(monedaDestino)
            .getAsDouble();
}

//conversión por consola
public void mostrarConversion(String monedaOrigen, String monedaDestino) {
    Scanner scanner = new Scanner(System.in);
    try {
        double tasa = obtenerTasa(monedaOrigen, monedaDestino);
        System.out.printf("💱 Tasa de cambio %s -> %s: %.2f%n", monedaOrigen, monedaDestino, tasa);

        System.out.print("Ingrese la cantidad de " + monedaOrigen + " que desea convertir: ");
        double cantidad = scanner.nextDouble();

        double resultado = cantidad * tasa;
        System.out.printf("✅ %.2f %s equivalen a %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);
    } catch (Exception e) {
        System.out.println("❌ Error durante la conversión.");
        System.out.println("Detalles: " + e.getMessage());
    }
}
