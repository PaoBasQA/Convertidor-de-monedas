package com.aluracursos.conversor.modelo;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatoMonedaUtil {
    public static String formatear(double valor) {
        NumberFormat formatoLatino = NumberFormat.getNumberInstance(new Locale("es", "AR"));
        return formatoLatino.format(valor);
    }
}
