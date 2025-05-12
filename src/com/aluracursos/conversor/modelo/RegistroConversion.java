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

    public LocalDateTime getFechaHora() {
        return fechaHora; // Agregamos este getter para acceder a la fecha sin formato
    }

    public String getFechaHoraFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHora.format(formatter); // Este método sigue existiendo para obtener la fecha formateada
    }

    public void setFechaHora(LocalDateTime fechaHora) {
    }
}
