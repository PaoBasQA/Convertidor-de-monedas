package com.aluracursos.vista;

import com.aluracursos.modelo.ClienteServidor;

import java.util.Scanner;

public class Menu {
    private final Scanner teclado = new Scanner(System.in);
    private final ClienteServidor conversor = new ClienteServidor();

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
            opcion = teclado.nextInt();
            teclado.nextLine();  // Limpieza de buffer

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("💰 Ingrese el monto a convertir: ");
                double monto = teclado.nextDouble();
                teclado.nextLine(); //Limpia el Buffer

                switch (opcion) {
                    case 1 -> conversor.convertir("USD", "ARS", monto);
                    case 2 -> conversor.convertir("ARS", "USD", monto);
                    case 3 -> conversor.convertir("USD", "BRL", monto);
                    case 4 -> conversor.convertir("BRL", "USD", monto);
                    case 5 -> conversor.convertir("USD", "COP", monto);
                    case 6 -> conversor.convertir("COP", "USD", monto);
                    case 7 -> System.out.println("👋 ¡Gracias por usar el conversor!");
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } else if (opcion == 7) {
                System.out.println("👋 ¡Gracias por usar el conversor!");
            } else {
                System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }
}
