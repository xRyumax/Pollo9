package Bebidas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class BoletaPrinter {

    public static void imprimirBoleta(String tipo, String bebida, int cantidad, String estado, double temperatura) {
        Document document = new Document();
        try {
            // Crear un escritor PDF
            PdfWriter.getInstance(document, new FileOutputStream("boleta.pdf"));
            
            // Abrir el documento para escritura
            document.open();
            
            // Añadir contenido al documento
            document.add(new Paragraph("Boleta de Compra"));
            document.add(new Paragraph("Fecha y Hora: " + java.time.LocalDateTime.now()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tipo: " + tipo));
            document.add(new Paragraph("Bebida: " + bebida));
            document.add(new Paragraph("Cantidad: " + cantidad));
            document.add(new Paragraph("Estado: " + estado));
            document.add(new Paragraph("Temperatura: " + temperatura + " °C"));
            
            // Cerrar el documento
            document.close();
            
            JOptionPane.showMessageDialog(null, "Boleta generada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la boleta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}