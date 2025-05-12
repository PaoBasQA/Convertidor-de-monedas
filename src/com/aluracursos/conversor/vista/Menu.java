package com.aluracursos.conversor.vista;

import com.aluracursos.conversor.controlador.HistorialConversionService;
import com.aluracursos.conversor.controlador.MonedaService;
import com.aluracursos.conversor.modelo.CodigoMoneda;
import com.aluracursos.conversor.modelo.FormatoMonedaUtil;
import com.aluracursos.conversor.modelo.Moneda;
import com.aluracursos.conversor.modelo.RegistroConversion;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner teclado = new Scanner(System.in);
    private final MonedaService monedaService = new MonedaService();
    private final HistorialConversionService historialService = new HistorialConversionService();
    private List<RegistroConversion> historial;

    public Menu() {
        // Cargar historial desde el archivo al inicio
        this.historial = historialService.cargarHistorial();
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 8) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Dólar a Pesos Argentinos");
            System.out.println("2. Pesos Argentinos a Dólar");
            System.out.println("3. Dólar a Reales Brasileños");
            System.out.println("4. Reales Brasileños a Dólar");
            System.out.println("5. Dólar a Pesos Colombianos");
            System.out.println("6. Pesos Colombianos a Dólar");
            System.out.println("7. Ver historial de conversiones");
            System.out.println("8. Salir");
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

                    CodigoMoneda origen = null, destino = null;
                    switch (opcion) {
                        case 1 -> { origen = CodigoMoneda.USD; destino = CodigoMoneda.ARS; }
                        case 2 -> { origen = CodigoMoneda.ARS; destino = CodigoMoneda.USD; }
                        case 3 -> { origen = CodigoMoneda.USD; destino = CodigoMoneda.BRL; }
                        case 4 -> { origen = CodigoMoneda.BRL; destino = CodigoMoneda.USD; }
                        case 5 -> { origen = CodigoMoneda.USD; destino = CodigoMoneda.COP; }
                        case 6 -> { origen = CodigoMoneda.COP; destino = CodigoMoneda.USD; }
                    }

                    Moneda moneda = monedaService.convertir(origen, destino, monto);

                    String montoOriginal = FormatoMonedaUtil.formatear(moneda.getMontoOriginal());
                    String tasa = FormatoMonedaUtil.formatear(moneda.getTasa());
                    String resultado = FormatoMonedaUtil.formatear(moneda.getMontoConvertido());

                    System.out.println("💱 1 " + origen + " = " + tasa + " " + destino);
                    System.out.println("💱 " + montoOriginal + " " + origen + " = " + resultado + " " + destino);

                    // Agregar el nuevo registro de conversión al historial
                    RegistroConversion registro = new RegistroConversion(moneda);
                    historial.add(registro);

                    // Guardar el historial actualizado en el archivo
                    historialService.guardarHistorial(historial);

                } catch (NumberFormatException e) {
                    System.out.println("❌ Monto inválido. Asegúrese de ingresar un número.");
                } catch (Exception e) {
                    System.out.println("❌ Error: " + e.getMessage());
                }
            } else if (opcion == 7) {
                if (historial.isEmpty()) {
                    System.out.println("📭 No hay conversiones registradas.");
                } else {
                    System.out.println("\n🕘 Historial de conversiones:");
                    for (RegistroConversion registro : historial) {
                        Moneda m = registro.getMoneda();
                        System.out.println("🗓 " + registro.getFechaHoraFormateada() + " - " +
                                FormatoMonedaUtil.formatear(m.getMontoOriginal()) + " " + m.getOrigen() +
                                " => " + FormatoMonedaUtil.formatear(m.getMontoConvertido()) + " " + m.getDestino() +
                                " (Tasa: " + FormatoMonedaUtil.formatear(m.getTasa()) + ")");
                    }
                }
            } else if (opcion == 8) {
                System.out.println("👋 ¡Gracias por usar el conversor!");
            } else {
                System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }
}