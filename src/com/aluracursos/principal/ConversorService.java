package com.aluracursos.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorService {
    private final String apiKey = "e20a4a101bc207bf6b344197";

    /**
     * Obtiene la tasa de cambio entre dos monedas usando la API de ExchangeRate.
     *
     * @param monedaOrigen  Código de la moneda de origen (ej: "USD").
     * @param monedaDestino Código de la moneda destino (ej: "ARS").
     * @return Tasa de cambio como valor decimal.
     * @throws Exception Si hay errores de conexión o en el formato de la respuesta.
     */
    public double obtenerTasa(String monedaOrigen, String monedaDestino) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;
        URL url = new URL(urlStr);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            respuesta.append(linea);
        }
        reader.close();

        JsonObject json = JsonParser.parseString(respuesta.toString()).getAsJsonObject();

        // Validar que el campo exista
        if (!json.has("conversion_rates")) {
            throw new RuntimeException("Respuesta inválida de la API");
        }

        return json.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
    }
}

