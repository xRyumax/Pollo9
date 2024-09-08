package Bebidas;
import javax.swing.*;
import java.awt.*;

public class InfoPanel1J extends JPanel {

    private JLabel imageLabel;
    private Image image;

    public InfoPanel1J() {
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(false);
        this.setPreferredSize(new Dimension(300, 200));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(255, 255, 255)); // Fondo semi-transparente

        imageLabel = new JLabel();
        this.add(imageLabel, BorderLayout.CENTER);
    }

    private void updateImage() {
        if (image == null) {
            imageLabel.setIcon(null);
            return;
        }

        // Obtener dimensiones del panel y de la imagen
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // Calcular la escala de la imagen para mantener la proporción
        double scaleX = (double) panelWidth / imageWidth;
        double scaleY = (double) panelHeight / imageHeight;
        double scale = Math.min(scaleX, scaleY);

        // Calcular las nuevas dimensiones de la imagen
        int newWidth = (int) (scale * imageWidth);
        int newHeight = (int) (scale * imageHeight);

        // Crear una imagen escalada
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crear un ImageIcon y centrar la imagen en el JLabel
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // No llamar a updateImage aquí
    }

    public void updateImageBasedOnSelection(String tipoSeleccionado, String bebidaSeleccionada, String estadoSeleccionado) {
        String imagePath = "src/images/default_image.jpg"; // Imagen por defecto
        if (tipoSeleccionado == null) {
            tipoSeleccionado = "-Seleccionar-";
        }
        if (bebidaSeleccionada == null) {
            bebidaSeleccionada = "-Seleccionar-";
        }
        if (estadoSeleccionado == null) {
            estadoSeleccionado = "-Seleccionar-";
        }

        if (tipoSeleccionado != null && bebidaSeleccionada != null) {
            if (tipoSeleccionado.equals("Gaseosa")) {
                switch (bebidaSeleccionada) {
                    case "Coca Cola":
                        imagePath = estadoSeleccionado.equals("Helada") ? "src/images/coca_helada.jpg" : "src/images/coca_cola.png";
                        break;
                    case "Inka Kola":
                        imagePath = estadoSeleccionado.equals("Helada") ? "src/images/inka_helada.jfif" : "src/images/inka.jpg";
                        break;
                    case "Fanta":
                        imagePath = estadoSeleccionado.equals("Helada") ? "src/images/fanta_helada.jpg" : "src/images/fanta.jpg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Alcohol")) {
                switch (bebidaSeleccionada) {
                    case "Cusqueña":
                        imagePath = "src/images/cusquena.jpg";
                        break;
                    case "Arequipeña":
                        imagePath = "src/images/arequipena.jfif";
                        break;
                    case "Pilsen":
                        imagePath = "src/images/pilsen.jpg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Refrescos")) {
                switch (bebidaSeleccionada) {
                    case "Chicha morada":
                        imagePath = "src/images/chichamorada.jpeg";
                        break;
                    case "Limonada":
                        imagePath = "src/images/limonada.jpeg";
                        break;
                    case "Maracuya":
                        imagePath = "src/images/maracuya.jpeg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Infusiones")) {
                switch (bebidaSeleccionada) {
                    case "Manzanilla":
                        imagePath = "src/images/manzanilla.jpg";
                        break;
                    case "Te":
                        imagePath = "src/images/te.jfif";
                        break;
                    case "Anis":
                        imagePath = "src/images/anis.jpg";
                        break;
                }
            }
           
            // Actualizar la imagen y repaint
            image = new ImageIcon(imagePath).getImage();
            SwingUtilities.invokeLater(() -> {
                updateImage(); // Llama a updateImage en el hilo de eventos
                revalidate(); // Recalcula el diseño del panel
                repaint();    // Llama a paintComponent para actualizar la imagen
            });
        }
    }
}
