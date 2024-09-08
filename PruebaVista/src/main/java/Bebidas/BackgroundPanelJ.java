
package Bebidas;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanelJ extends JPanel {
    private Image backgroundImage;

    public BackgroundPanelJ(String filePath) {
        // Cargar la imagen desde el archivo
        ImageIcon icon = new ImageIcon(filePath);
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Escalar la imagen para que ocupe todo el panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

