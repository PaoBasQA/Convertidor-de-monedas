package com.aluracursos.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorMoneda {
    public static void main(String[] args) {
        String apiKey = "e20a4a101bc207bf6b344197";
        String baseCurrency = "USD";
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            //BufferedReader es una clase de Java que lee texto de una fuente (como archivos o redes)
            //de manera eficiente, línea por línea o carácter por carácter.
            //en cambio FileReader	Lee caracteres directamente de un archivo de texto.
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            //Mientras haya una línea para leer (no sea null),
            //guardar en la variable linea y ejecutar el bloque de código dentro del while
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();

            System.out.println("✅ Respuesta recibida:");
            System.out.println(respuesta.toString());

            //Parsear JSON con Gson: sirve para analizar y convertir una cadena JSON en un objeto
            JsonObject json = JsonParser.parseString(respuesta.toString()).getAsJsonObject();

            // Mostrar la tasa de cambio de USD a ARS
            double tasaARS = json.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();
            System.out.println("1 USD = " + tasaARS + " ARS");

            //Obtener tasa de cambio a EUR
            double tasaEuro = json.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
            System.out.println("Tasa de cambio USD -> EUR: " + tasaEuro);

        } catch (Exception e) {
            System.out.println("❌ Error: No se pudo obtener la respuesta de la API.");
            System.out.println("Detalles: " + e.getMessage());
           // e.printStackTrace();
        }
    }
}
