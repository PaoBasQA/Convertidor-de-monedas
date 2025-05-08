package com.aluracursos.vista;

import com.aluracursos.conversor.servicio.ConversorService;

import java.util.Scanner;

public class Menu {
    private final Scanner teclado = new Scanner(System.in);
    private final ConversorService conversor = new ConversorService();

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

            switch (opcion) {
                case 1 -> conversor.convertir("USD", "ARS");
                case 2 -> conversor.convertir("ARS", "USD");
                case 3 -> conversor.convertir("USD", "BRL");
                case 4 -> conversor.convertir("BRL", "USD");
                case 5 -> conversor.convertir("USD", "COP");
                case 6 -> conversor.convertir("COP", "USD");
                case 7 -> System.out.println("👋 ¡Gracias por usar el conversor!");
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }
}
