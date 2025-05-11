package com.aluracursos.conversor.controlador;


import com.aluracursos.conversor.modelo.ClienteServidor;
import com.aluracursos.conversor.modelo.Moneda;
import com.google.gson.JsonObject;

public class MonedaService {
    private final ClienteServidor clienteServidor = new ClienteServidor();

    public Moneda convertir(String origen, String destino, double monto) throws Exception {
        JsonObject json = clienteServidor.obtenerConversiones(origen);

        if (!json.has("conversion_rates")) {
            throw new Exception("No se encontraron tasas de conversión en la respuesta.");
        }

        JsonObject tasas = json.getAsJsonObject("conversion_rates");

        if (!tasas.has(destino)) {
            throw new Exception("No se encontró la tasa para la moneda destino: " + destino);
        }

        double tasa = tasas.get(destino).getAsDouble();
        return new Moneda(origen, destino, monto, tasa);
    }
}
