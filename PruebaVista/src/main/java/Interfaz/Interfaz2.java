package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interfaz2 extends JFrame {

    private JLabel imagenComida;
    private JComboBox<String> porcion1, porcion2, contextura, sabor;
    private JTextField tiempoEstimado, precio;
    private JButton verIngredientes, modificarIngredientes, bebidas, modificarPreparacion;

    public Interfaz2() {
        super("Interfaz de Comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Aumenta el tamaño de la ventana
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Crear un panel con imagen de fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel("PruebaVista\\src/img/FUEGO.jpg"); // Ruta de la imagen de fondo
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcBackground = new GridBagConstraints();
        gbcBackground.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Imagen de la comida
        imagenComida = new JLabel(new ImageIcon("PruebaVista\\src/img/gif.gif")); // Asegúrate de que la ruta sea correcta
        gbcBackground.gridx = 0;
        gbcBackground.gridy = 0;
        gbcBackground.gridwidth = 2; // Ocupa dos columnas
        gbcBackground.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(imagenComida, gbcBackground);

         // Panel para la columna izquierda
         JPanel leftPanel = new JPanel();
         leftPanel.setLayout(new GridLayout(6, 1, 10, 10));
         leftPanel.setBackground(Color.BLACK); // Fondo negro para el panel izquierdo
 
         // Estilo de los componentes internos
         Color textColor = Color.BLACK;
         Color comboBoxBackground = Color.ORANGE;
         Color textFieldBackground = Color.GRAY;
 
         // Porción 1
         JPanel porcion1Panel = new JPanel(new BorderLayout());
         JLabel labelPorcion1 = new JLabel("Porción");
         labelPorcion1.setForeground(textColor); // Color del texto
         porcion1Panel.add(labelPorcion1, BorderLayout.WEST);
         porcion1 = new JComboBox<>(new String[]{"1", "1/2", "1/4", "1/8"});
         porcion1.setBackground(comboBoxBackground);
         porcion1.setForeground(textColor);
         porcion1Panel.add(porcion1, BorderLayout.CENTER);
         leftPanel.add(porcion1Panel);
 
         // Porción 2
         JPanel porcion2Panel = new JPanel(new BorderLayout());
         JLabel labelPorcion2 = new JLabel("Cantidad");
         labelPorcion2.setForeground(textColor); // Color del texto
         porcion2Panel.add(labelPorcion2, BorderLayout.WEST);
         porcion2 = new JComboBox<>(new String[]{"1", "2", "3", "4"});
         porcion2.setBackground(comboBoxBackground);
         porcion2.setForeground(textColor);
         porcion2Panel.add(porcion2, BorderLayout.CENTER);
         leftPanel.add(porcion2Panel);
 
         // Contextura
         JPanel contexturaPanel = new JPanel(new BorderLayout());
         JLabel labelContextura = new JLabel("Contextura");
         labelContextura.setForeground(textColor); // Color del texto
         contexturaPanel.add(labelContextura, BorderLayout.WEST);
         contextura = new JComboBox<>(new String[]{"Crocante", "Tierno", "Seco"});
         contextura.setBackground(comboBoxBackground);
         contextura.setForeground(textColor);
         contexturaPanel.add(contextura, BorderLayout.CENTER);
         leftPanel.add(contexturaPanel);
 
         // Sabor
         JPanel saborPanel = new JPanel(new BorderLayout());
         JLabel labelSabor = new JLabel("Sabor");
         labelSabor.setForeground(textColor); // Color del texto
         saborPanel.add(labelSabor, BorderLayout.WEST);
         sabor = new JComboBox<>(new String[]{"Jugoso", "Agridulce", "Picante"});
         sabor.setBackground(comboBoxBackground);
         sabor.setForeground(textColor);
         saborPanel.add(sabor, BorderLayout.CENTER);
         leftPanel.add(saborPanel);
 
         // Tiempo Estimado
         JPanel tiempoEstimadoPanel = new JPanel(new BorderLayout());
         JLabel labelTiempoEstimado = new JLabel("Tiempo estimado");
         labelTiempoEstimado.setForeground(textColor); // Color del texto
         tiempoEstimadoPanel.add(labelTiempoEstimado, BorderLayout.WEST);
         tiempoEstimado = new JTextField("12000000");
         tiempoEstimado.setBackground(textFieldBackground);
         tiempoEstimado.setForeground(textColor);
         tiempoEstimadoPanel.add(tiempoEstimado, BorderLayout.CENTER);
         leftPanel.add(tiempoEstimadoPanel);
 
         gbcBackground.gridx = 0;
         gbcBackground.gridy = 1;
         gbcBackground.gridwidth = 1; // Ocupa una columna
         gbcBackground.fill = GridBagConstraints.BOTH;
         backgroundPanel.add(leftPanel, gbcBackground);

        // Panel para la columna derecha
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbcRight.fill = GridBagConstraints.BOTH;
        leftPanel.setBackground(Color.BLACK);

        // Ingredientes
        JLabel labelIngredientes = new JLabel("Ingredientes");
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(labelIngredientes, gbcRight);

        verIngredientes = createStyledButton("Ver ingredientes", Color.WHITE, Color.BLUE);
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(verIngredientes, gbcRight);

        // Botón "Modificar ingredientes" con estilo
        modificarIngredientes = createStyledButton("Modificar ingredientes", Color.WHITE, Color.GREEN);
        gbcRight.gridx = 1;
        gbcRight.gridy = 1;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(modificarIngredientes, gbcRight);

        // Botón "Modificar ingredientes" con ActionListener
        modificarIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la interfaz Interfaz1
                new Interfaz1();
            }
        });

        // Preparación
        JLabel labelPreparacion = new JLabel("Preparación");
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(labelPreparacion, gbcRight);

        // Botón "Bebidas" con estilo
        bebidas = createStyledButton("Bebidas", Color.WHITE, Color.RED);
        gbcRight.gridx = 0;
        gbcRight.gridy = 3;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(bebidas, gbcRight);

        // Botón "Bebidas" con ActionListener
        bebidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la interfaz BebidasGUI
                new BebidasGUI();
            }
        });

        // Botón "Modificar preparación" con estilo
        modificarPreparacion = createStyledButton("Modificar preparación", Color.WHITE, Color.ORANGE);
        gbcRight.gridx = 1;
        gbcRight.gridy = 3;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(modificarPreparacion, gbcRight);

        // Precio
        JLabel labelPrecio = new JLabel("Precio");
        gbcRight.gridx = 0;
        gbcRight.gridy = 4;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(labelPrecio, gbcRight);

        precio = new JTextField("12000000");
        gbcRight.gridx = 0;
        gbcRight.gridy = 5;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(precio, gbcRight);

        gbcBackground.gridx = 1;
        gbcBackground.gridy = 1;
        gbcBackground.gridwidth = 1; // Ocupa una columna
        backgroundPanel.add(rightPanel, gbcBackground);

        // Agrega el panel de fondo al JFrame
        add(backgroundPanel);

        // Ajuste automático del tamaño de la ventana
        pack();

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color textColor, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Cambia la fuente
        button.setForeground(textColor); // Color del texto
        button.setBackground(backgroundColor); // Color de fondo
        button.setOpaque(true); // Necesario para mostrar el color de fondo
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde negro del botón
        button.setPreferredSize(new Dimension(200, 50)); // Tamaño preferido del botón

        // Cambiar color cuando el mouse pasa sobre el botón
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker()); // Oscurecer el color de fondo
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor); // Restaurar el color original
            }
        });

        return button;
    }

    // Clase para un panel con imagen de fondo
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaz2());
    }
}
