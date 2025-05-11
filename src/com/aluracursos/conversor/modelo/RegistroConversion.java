package com.aluracursos.conversor.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroConversion {
    private final Moneda moneda;
    private final LocalDateTime fechaHora;

    public RegistroConversion(Moneda moneda) {
        this.moneda = moneda;
        this.fechaHora = LocalDateTime.now();
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public String getFechaHoraFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }
}
