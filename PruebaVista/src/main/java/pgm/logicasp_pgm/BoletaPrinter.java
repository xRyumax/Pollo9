package pgm.logicasp_pgm;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class BoletaPrinter {

    private String tipoPreparacion;
    private String sabor;
    private String contextura;
    private double cantidad;
    private int numeroPollos;
    private double precio;

    public BoletaPrinter(String tipoPreparacion, String sabor, String contextura, double cantidad, int numeroPollos, double precio) {
        this.tipoPreparacion = tipoPreparacion;
        this.sabor = sabor;
        this.contextura = contextura;
        this.cantidad = cantidad;
        this.numeroPollos = numeroPollos;
        this.precio = precio;
    }

    public void imprimir() {
        String boletaTexto = generarBoletaTexto();

        // Crear y mostrar una nueva ventana para la boleta
        BoletaViewer viewer = new BoletaViewer(boletaTexto);
        viewer.setVisible(true);

        // Exportar a archivo de texto
        exportarTexto(boletaTexto);
    }

    private String generarBoletaTexto() {
        return "Boleta de Pedido\n" +
               "Tipo de Preparación: " + tipoPreparacion + "\n" +
               "Sabor: " + sabor + "\n" +
               "Contextura: " + contextura + "\n" +
               "Cantidad: " + cantidad + " porciones\n" +
               "Número de Pollos: " + numeroPollos + "\n" +
               "Precio Estimado: S/." + precio;
    }

    private void exportarTexto(String boletaTexto) {
        try (FileWriter writer = new FileWriter("boleta.txt")) {
            writer.write(boletaTexto);
            JOptionPane.showMessageDialog(null, "La boleta ha sido exportada exitosamente como 'boleta.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al exportar la boleta: " + e.getMessage());
        }
    }
}