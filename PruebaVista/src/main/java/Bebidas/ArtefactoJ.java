package Bebidas;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ArtefactoJ extends JFrame {

    private InfoPanel1J infoPanel1;
    private InfoPanel2J infoPanel2;
    private BackgroundPanelJ backgroundPanel;

    public ArtefactoJ() {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
            ex.printStackTrace();
        }

        this.setTitle("Bebidas");
        

        this.setSize(1200, 800);
        this.setResizable(true);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        
        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);

        
        backgroundPanel = new BackgroundPanelJ("src/images/fondo.jpg");
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

      
        infoPanel1 = new InfoPanel1J();
        infoPanel2 = new InfoPanel2J(infoPanel1);

        // Agregar los paneles a una capa superior
        layeredPane.add(infoPanel1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(infoPanel2, JLayeredPane.PALETTE_LAYER);

        // Configurar el Layout Manager para el ajuste automático
        layeredPane.setLayout(null); // Usar null layout para fijar posiciones y tamaños

        // Listener para ajustar el tamaño y la posición de los componentes cuando cambia el tamaño de la ventana
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponents();
            }
        });

        adjustComponents(); // Ajustar inicialmente los tamaños y posiciones

        this.setVisible(true); // Asegurarse de que la ventana sea visible
    }

    private void adjustComponents() {
        // Ajustar el tamaño del fondo para que cubra toda la ventana
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());

        // Definir el tamaño de los paneles
        int panelWidth = 500;  
        int panelHeight = getHeight() - 60; 
        // Calcular la posición horizontal de los paneles para que estén centrados
        int totalWidth = panelWidth * 2;
        int xOffset = (getWidth() - totalWidth) / 2; 

        // Ajustar el tamaño y la posición de los paneles
        infoPanel1.setBounds(xOffset, 10, panelWidth, panelHeight); // Panel 1 a la izquierda
        infoPanel2.setBounds(xOffset + panelWidth, 10, panelWidth, panelHeight); // Panel 2 a la derecha
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArtefactoJ());
    }
}
