package com.aluracursos.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorApp {

    public static void main(String[] args) {
        String apiKey = "e20a4a101bc207bf6b344197";
        String baseCurrency = "USD";
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

        try {
            JsonObject json = obtenerJsonDesdeApi(urlStr);

            double tasaARS = obtenerTasa("ARS", json);
            System.out.println("1 USD = " + tasaARS + " ARS");

            double tasaEUR = obtenerTasa("EUR", json);
            System.out.println("1 USD = " + tasaEUR + " EUR");

        } catch (Exception e) {
            System.out.println("❌ Error: No se pudo obtener la respuesta de la API.");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    public static JsonObject obtenerJsonDesdeApi(String urlStr) throws Exception {
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

        return JsonParser.parseString(respuesta.toString()).getAsJsonObject();
    }

    public static double obtenerTasa(String codigoMoneda, JsonObject json) {
        return json.getAsJsonObject("conversion_rates").get(codigoMoneda).getAsDouble();
    }
}
