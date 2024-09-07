/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perceptron;

/**
 *
 * @author Usuario
 */

public class Perceptron {
    // El perceptrón calcula el tiempo de cocción basado en tipo de preparación, sabor, contextura, cantidad y número de pollos
    public int calcularTiempoCoccion(String tipoPreparacion, String sabor, String contextura, double cantidad, int numeroPollos) {
        // Calcula el tiempo basándose en reglas simples: ajuste de factores de multiplicación según combinaciones
        int tiempoBase = 15; // Tiempo base en minutos
        int factorPreparacion = tipoPreparacion.equals("brasa") ? 3 : tipoPreparacion.equals("horno") ? 2 : 1;
        int factorSabor = sabor.equals("picante") ? 2 : 1;
        int factorContextura = contextura.equals("seco") ? 1 : 2; // Jugoso puede tomar más tiempo

        // Ajuste por cantidad y número de pollos
        double factorCantidad = cantidad; // Ej. 1/8 = 0.125, 1 = 1.0
        double factorPollos = numeroPollos; // Ej. 1, 2, 3...

        return (int) (tiempoBase * factorPreparacion * factorSabor * factorContextura * factorCantidad * factorPollos);
    }
}