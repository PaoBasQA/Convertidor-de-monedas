package com.aluracursos.conversor.controlador;

import com.aluracursos.conversor.modelo.RegistroConversion;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversionService {
    private static final String ARCHIVO = "historial.json";
    private final Gson gson;

    public HistorialConversionService() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(RegistroConversion.class, new RegistroConversionAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void guardarHistorial(List<RegistroConversion> historial) {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            gson.toJson(historial, writer);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el historial: " + e.getMessage());
        }
    }

    public List<RegistroConversion> cargarHistorial() {
        try (FileReader reader = new FileReader(ARCHIVO)) {
            RegistroConversion[] registros = gson.fromJson(reader, RegistroConversion[].class);
            List<RegistroConversion> historial = new ArrayList<>();
            if (registros != null) {
                for (RegistroConversion r : registros) {
                    historial.add(r);
                }
            }
            return historial;
        } catch (IOException e) {
            return new ArrayList<>(); // Si no existe el archivo, devolvemos una lista vacía
        }
    }

    // Adaptador para serializar/deserializar fechaHora de RegistroConversion
    private static class RegistroConversionAdapter implements JsonSerializer<RegistroConversion>, JsonDeserializer<RegistroConversion> {

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public JsonElement serialize(RegistroConversion src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.add("moneda", context.serialize(src.getMoneda()));
            obj.addProperty("fechaHora", src.getFechaHora().format(FORMATTER));
            return obj;
        }

        @Override
        public RegistroConversion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            RegistroConversion registro = new RegistroConversion(
                    context.deserialize(obj.get("moneda"), com.aluracursos.conversor.modelo.Moneda.class)
            );
            registro.setFechaHora(LocalDateTime.parse(obj.get("fechaHora").getAsString(), FORMATTER));
            return registro;
        }
    }
}