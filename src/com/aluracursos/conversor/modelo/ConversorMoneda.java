package com.aluracursos.conversor.modelo;

import com.google.gson.JsonObject;

import java.text.NumberFormat;
import java.util.Locale;

public class ConversorMoneda {
    private final ClienteServidor clienteServidor = new ClienteServidor();

    public void convertir(String origen, String destino, double monto) {
        try {
            JsonObject json = clienteServidor.obtenerConversiones(origen);

            if (!json.has("conversion_rates")) {
                System.out.println("❌ Error: La respuesta no contiene 'conversion_rates'.");
                return;
            }

            JsonObject tasas = json.getAsJsonObject("conversion_rates");

            if (!tasas.has(destino)) {
                System.out.println("❌ No se encontró la tasa para la moneda destino: " + destino);
                return;
            }

            double tasa = tasas.get(destino).getAsDouble();
            double convertido = monto * tasa;

            NumberFormat formatoLatino = NumberFormat.getNumberInstance(new Locale("es", "AR"));
            String montoFormateado = formatoLatino.format(convertido);
            String tasaFormateada = formatoLatino.format(tasa);
            String montoOriginalFormateado = formatoLatino.format(monto);

            System.out.println("💱 1 " + origen + " = " + tasaFormateada + " " + destino);
            System.out.println("💱 " + montoOriginalFormateado + " " + origen + " = " + montoFormateado + " " + destino);

        } catch (Exception e) {
            System.out.println("❌ Error durante la conversión: " + e.getMessage());
        }
    }
}
