package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;

public class Interfaz1 extends JFrame {

    private JLabel labelIngredientes;
    private JButton buttonAñadir;
    private JButton buttonModificar;
    private JButton buttonImprimirBoleta;
    private JPanel panelImagenes;
    private JCheckBox[] checkBoxes;
    private String[] imagenesCheckBoxes = {
        "Pollo.jpeg", "Sal.jpeg", "pimienta.jpg", "Ajo en polvo.jpeg",
        "Comino.jpeg", "Paprika.jpeg", "Cilantro.jpeg", "Orégano.jpeg",
        "Vinagre.png", "Aceite.jpg", "Limón.png", "Salsa de soya.jpeg",
        "Ajo.jpeg", "Cebolla.jpg", "Pimienta roja.jpg", "Curry.jpg",
        "Tomate.jpeg", "Chimichurri.jpg", "Pimienta negra.jpg", "Romero.jpeg",
        "tomillo.jpg", "Laurel.jpg", "Pasta de ají.jpg", "Cebollín.jpeg"
    };

    public Interfaz1() {
        super("Pollería Senati");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta "Ingredientes"
        labelIngredientes = new JLabel("Ingredientes:");
        labelIngredientes.setBounds(10, 10, 300, 20);
        labelIngredientes.setFont(new Font("Arial", Font.BOLD, 18));
        labelIngredientes.setForeground(new Color(128, 64, 0));  // Color marrón
        add(labelIngredientes);

        // Botón "Añadir"
        buttonAñadir = new JButton("Añadir");
        buttonAñadir.setBounds(270, 10, 160, 40);
        estilizarBoton(buttonAñadir);
        add(buttonAñadir);

        // Botón "Modificar"
        buttonModificar = new JButton("Modificar");
        buttonModificar.setBounds(270, 60, 160, 40);
        estilizarBoton(buttonModificar);
        add(buttonModificar);

        // Panel para las imágenes
        panelImagenes = new JPanel();
        panelImagenes.setBounds(10, 110, 760, 200);
        panelImagenes.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelImagenes.setBackground(new Color(255, 245, 230));  // Color de fondo crema
        add(panelImagenes);

        // Panel para los JCheckBox
        JPanel panelCheckBoxes = new JPanel();
        panelCheckBoxes.setLayout(new GridLayout(5, 4));
        panelCheckBoxes.setBounds(10, 330, 600, 200);
        panelCheckBoxes.setBackground(new Color(255, 235, 205)); // Fondo tono madera claro
        add(panelCheckBoxes);

        // Define los nombres específicos para los JCheckBox
        String[] nombresCheckBoxes = {
            "Pollo", "Sal", "Pimienta", "Ajo en polvo",
            "Comino", "Paprika", "Cilantro", "Orégano",
            "Vinagre", "Aceite", "Limón", "Salsa de soya",
            "Ajo", "Cebolla", "Pimienta roja", "Curry",
            "Tomate", "Chimichurri", "Pimienta negra", "Romero",
            "Tomillo", "Laurel", "Pasta de ají", "Cebollín"
        };

        // Inicializa los JCheckBox y les asigna imágenes
        checkBoxes = new JCheckBox[nombresCheckBoxes.length];
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(nombresCheckBoxes[i]);
            checkBoxes[i].setFont(new Font("Arial", Font.PLAIN, 14));
            checkBoxes[i].setBackground(new Color(255, 245, 230));
            // Agrega un ActionListener a cada JCheckBox
            checkBoxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarImagenes();
                }
            });
            panelCheckBoxes.add(checkBoxes[i]);
        }

        // Acción del botón "Modificar"
        buttonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Borra las selecciones actuales
                for (JCheckBox checkBox : checkBoxes) {
                    checkBox.setSelected(false);
                }
                // Borra las imágenes mostradas
                panelImagenes.removeAll();
                panelImagenes.revalidate();
                panelImagenes.repaint();
            }
        });

        setVisible(true);
    }

    private void actualizarImagenes() {
        // Borra las imágenes existentes
        panelImagenes.removeAll();

        // Tamaño máximo para las imágenes
        int maxWidth = 80;
        int maxHeight = 60;

        // Agrega nuevas imágenes basadas en las selecciones
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isSelected()) {
                String imagenPath = "src/images/img/" + imagenesCheckBoxes[i];
                try {
                    // Cargar la imagen original
                    BufferedImage img = ImageIO.read(new File(imagenPath));
                    // Redimensionar la imagen manteniendo la relación de aspecto
                    Image resizedImage = redimensionarImagen(img, maxWidth, maxHeight);
                    ImageIcon icon = new ImageIcon(resizedImage);
                    JLabel labelImagen = new JLabel(icon);
                    panelImagenes.add(labelImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Actualiza el panel para mostrar las nuevas imágenes
        panelImagenes.revalidate();
        panelImagenes.repaint();
    }

    private Image redimensionarImagen(BufferedImage img, int width, int height) {
        // Calcula el nuevo tamaño manteniendo la relación de aspecto
        double aspectRatio = (double) img.getWidth() / img.getHeight();
        int newWidth, newHeight;

        if (img.getWidth() > img.getHeight()) {
            newWidth = width;
            newHeight = (int) (width / aspectRatio);
        } else {
            newHeight = height;
            newWidth = (int) (height * aspectRatio);
        }

        // Redimensiona la imagen
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Crea un BufferedImage con el nuevo tamaño
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    private void estilizarBoton(JButton button) {
        button.setBackground(new Color(198, 40, 40)); // Color rojo fuego
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBorder(new LineBorder(new Color(139, 0, 0), 2, true)); // Bordes redondeados y oscuros
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a "mano"
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaz1());
    }
}
