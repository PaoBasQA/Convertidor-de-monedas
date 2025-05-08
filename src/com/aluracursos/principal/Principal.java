package com.aluracursos.principal;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Principal {
    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println("** Bienvenidos al Conversor de Monedas **");
        System.out.println("*****************************************");
        Menu menu = new Menu();
        menu.mostrarMenu();


        // Crear un objeto JSON simulando datos de conversión de moneda
        JsonObject moneda = new JsonObject();
        moneda.addProperty("monedaOrigen", "USD");
        moneda.addProperty("monedaDestino", "ARS");
        moneda.addProperty("valor", 1050.75);

        // Crear instancia de Gson
        Gson gson = new Gson();

        // Serializar el objeto a JSON (convertir a String)
        String json = gson.toJson(moneda);
        System.out.println("JSON generado: " + json);

        // Deserializar (convertir de String a objeto JSON)
        JsonObject deserializado = gson.fromJson(json, JsonObject.class);
        System.out.println("Moneda origen deserializada: " + deserializado.get("monedaOrigen").getAsString());
    }
}

