package com.aluracursos.conversor.vista;

import com.aluracursos.conversor.modelo.ConversorMoneda;

import java.util.Scanner;

public class Menu {
    private final Scanner teclado = new Scanner(System.in);
    private final ConversorMoneda conversor = new ConversorMoneda();

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 7) {
            System.out.println("\n🌍 Conversor de Monedas - Seleccione una opción:");
            System.out.println("1. Dólar (USD) a Peso Argentino (ARS)");
            System.out.println("2. Peso Argentino (ARS) a Dólar (USD)");
            System.out.println("3. Dólar (USD) a Real Brasileño (BRL)");
            System.out.println("4. Real Brasileño (BRL) a Dólar (USD)");
            System.out.println("5. Dólar (USD) a Peso Colombiano (COP)");
            System.out.println("6. Peso Colombiano (COP) a Dólar (USD)");
            System.out.println("7. Salir");
            System.out.print("👉 Ingrese su opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                if (opcion >= 1 && opcion <= 6) {
                    System.out.print("💰 Ingrese el monto a convertir: ");
                    String entrada = teclado.nextLine().replace(".", "").replace(",", ".");
                    double monto = Double.parseDouble(entrada);

                    switch (opcion) {
                        case 1 -> conversor.convertir("USD", "ARS", monto);
                        case 2 -> conversor.convertir("ARS", "USD", monto);
                        case 3 -> conversor.convertir("USD", "BRL", monto);
                        case 4 -> conversor.convertir("BRL", "USD", monto);
                        case 5 -> conversor.convertir("USD", "COP", monto);
                        case 6 -> conversor.convertir("COP", "USD", monto);
                    }
                } else if (opcion == 7) {
                    System.out.println("👋 ¡Gracias por usar el conversor!");
                } else {
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }
}
