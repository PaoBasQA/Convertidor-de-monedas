package com.aluracursos.conversor.principal;

import com.aluracursos.conversor.vista.Menu;

public class Principal {
    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println("** Bienvenidos al Conversor de Monedas **");
        System.out.println("*****************************************");

        Menu menu = new Menu();
        menu.mostrarMenu();

        System.out.println("\nPrograma finalizado.");
    }
}