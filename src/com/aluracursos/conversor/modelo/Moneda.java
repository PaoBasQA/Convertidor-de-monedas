package com.aluracursos.conversor.modelo;

public class Moneda {
    private String origen;
    private String destino;
    private double montoOriginal;
    private double tasa;
    private double montoConvertido;

    public Moneda(String origen, String destino, double montoOriginal, double tasa) {
        this.origen = origen;
        this.destino = destino;
        this.montoOriginal = montoOriginal;
        this.tasa = tasa;
        this.montoConvertido = montoOriginal * tasa;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getMontoOriginal() {
        return montoOriginal;
    }

    public double getTasa() {
        return tasa;
    }

    public double getMontoConvertido() {
        return montoConvertido;
    }
}
