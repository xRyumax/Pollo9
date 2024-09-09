package Bebidas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoletaPrinter {

    private static List<String> items = new ArrayList<>();

    public static void añadirItem(String tipo, String bebida, int cantidad, String estado, double temperatura) {
        String item = String.format("Tipo: %s\nBebida: %s\nCantidad: %d\nEstado: %s\nTemperatura: %.2f °C\n\n",
                                    tipo, bebida, cantidad, estado, temperatura);
        items.add(item);
    }

    public static void imprimirBoleta() {
        Document document = new Document();
        try {
            // Crear un escritor PDF
            PdfWriter.getInstance(document, new FileOutputStream("boletabebidas.pdf"));

            // Abrir el documento para escritura
            document.open();

            // Añadir contenido al documento
            document.add(new Paragraph("Boleta de Compra"));
            document.add(new Paragraph("Fecha y Hora: " + java.time.LocalDateTime.now()));
            document.add(new Paragraph(" "));

            // Añadir cada ítem de la lista al documento
            for (String item : items) {
                document.add(new Paragraph(item));
            }

            // Cerrar el documento
            document.close();

            JOptionPane.showMessageDialog(null, "Boleta generada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la boleta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
