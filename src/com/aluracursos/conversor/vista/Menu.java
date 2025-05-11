package com.aluracursos.conversor.vista;

import com.aluracursos.conversor.controlador.MonedaService;
import com.aluracursos.conversor.modelo.Moneda;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private final Scanner teclado = new Scanner(System.in);
    private final MonedaService monedaService = new MonedaService();

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 7) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Dólar a Pesos Argentinos");
            System.out.println("2. Pesos Argentinos a Dólar");
            System.out.println("3. Dólar a Reales Brasileños");
            System.out.println("4. Reales Brasileños a Dólar");
            System.out.println("5. Dólar a Pesos Colombianos");
            System.out.println("6. Pesos Colombianos a Dólar");
            System.out.println("7. Salir");
            System.out.print("👉 Ingrese su opción: ");

            if (!teclado.hasNextInt()) {
                System.out.println("❌ Entrada inválida. Ingrese un número.");
                teclado.nextLine();
                continue;
            }

            opcion = teclado.nextInt();
            teclado.nextLine();  // Limpieza de buffer

            if (opcion >= 1 && opcion <= 6) {
                try {
                    System.out.print("💰 Ingrese el monto a convertir: ");
                    String entrada = teclado.nextLine().replace(".", "").replace(",", ".");
                    double monto = Double.parseDouble(entrada);

                    String origen = "", destino = "";
                    switch (opcion) {
                        case 1 -> { origen = "USD"; destino = "ARS"; }
                        case 2 -> { origen = "ARS"; destino = "USD"; }
                        case 3 -> { origen = "USD"; destino = "BRL"; }
                        case 4 -> { origen = "BRL"; destino = "USD"; }
                        case 5 -> { origen = "USD"; destino = "COP"; }
                        case 6 -> { origen = "COP"; destino = "USD"; }
                    }

                    Moneda moneda = monedaService.convertir(origen, destino, monto);

                    NumberFormat formatoLatino = NumberFormat.getNumberInstance(new Locale("es", "AR"));
                    String montoOriginal = formatoLatino.format(moneda.getMontoOriginal());
                    String tasa = formatoLatino.format(moneda.getTasa());
                    String resultado = formatoLatino.format(moneda.getMontoConvertido());

                    System.out.println("💱 1 " + origen + " = " + tasa + " " + destino);
                    System.out.println("💱 " + montoOriginal + " " + origen + " = " + resultado + " " + destino);

                } catch (NumberFormatException e) {
                    System.out.println("❌ Monto inválido. Asegúrese de ingresar un número.");
                } catch (Exception e) {
                    System.out.println("❌ Error: " + e.getMessage());
                }
            } else if (opcion == 7) {
                System.out.println("👋 ¡Gracias por usar el conversor!");
            } else {
                System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }
}