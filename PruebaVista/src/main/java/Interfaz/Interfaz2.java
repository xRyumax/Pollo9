package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz2 extends JFrame {

    private JLabel imagenComida;
    private JComboBox<String> porcion1, porcion2, contextura, sabor;
    private JTextField tiempoEstimado, precio;
    private JButton verIngredientes, modificarIngredientes, bebidas, modificarPreparacion;

    public Interfaz2() {
        super("Interfaz de Comida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Imagen de la comida
        imagenComida = new JLabel(new ImageIcon("ruta/de/la/imagen.jpg"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(imagenComida, gbc);

        // Panel para la columna izquierda
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));

        // Porción 1
        JPanel porcion1Panel = new JPanel(new BorderLayout());
        JLabel labelPorcion1 = new JLabel("Porción");
        porcion1Panel.add(labelPorcion1, BorderLayout.WEST);
        porcion1 = new JComboBox<>(new String[]{"1", "1/2", "1/4", "1/8"});
        porcion1Panel.add(porcion1, BorderLayout.CENTER);
        leftPanel.add(porcion1Panel);

        // Porción 2
        JPanel porcion2Panel = new JPanel(new BorderLayout());
        JLabel labelPorcion2 = new JLabel("Cantidad");
        porcion2Panel.add(labelPorcion2, BorderLayout.WEST);
        porcion2 = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        porcion2Panel.add(porcion2, BorderLayout.CENTER);
        leftPanel.add(porcion2Panel);

        // Contextura
        JPanel contexturaPanel = new JPanel(new BorderLayout());
        JLabel labelContextura = new JLabel("Contextura");
        contexturaPanel.add(labelContextura, BorderLayout.WEST);
        contextura = new JComboBox<>(new String[]{"Crocante","Tierno","Seco"});
        contexturaPanel.add(contextura, BorderLayout.CENTER);
        leftPanel.add(contexturaPanel);

        // Sabor
        JPanel saborPanel = new JPanel(new BorderLayout());
        JLabel labelSabor = new JLabel("Sabor");
        saborPanel.add(labelSabor, BorderLayout.WEST);
        sabor = new JComboBox<>(new String[]{"Jugoso", "Agridulce", "Picante"});
        saborPanel.add(sabor, BorderLayout.CENTER);
        leftPanel.add(saborPanel);

        // Tiempo Estimado
        JPanel tiempoEstimadoPanel = new JPanel(new BorderLayout());
        JLabel labelTiempoEstimado = new JLabel("Tiempo estimado");
        tiempoEstimadoPanel.add(labelTiempoEstimado, BorderLayout.WEST);
        tiempoEstimado = new JTextField("12000000");
        tiempoEstimadoPanel.add(tiempoEstimado, BorderLayout.CENTER);
        leftPanel.add(tiempoEstimadoPanel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Ocupa una columna
        gbc.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbc);

        // Panel para la columna derecha
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbcRight.fill = GridBagConstraints.BOTH;

        // Ingredientes
        JLabel labelIngredientes = new JLabel("Ingredientes");
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(labelIngredientes, gbcRight);

        verIngredientes = new JButton("Ver ingredientes");
        verIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver ingredientes
            }
        });
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(verIngredientes, gbcRight);

        modificarIngredientes = new JButton("Modificar ingredientes");
        modificarIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la interfaz Interfaz1
                new Interfaz1();
            }
        });
        gbcRight.gridx = 1;
        gbcRight.gridy = 1;
        rightPanel.add(modificarIngredientes, gbcRight);

        // Preparación
        JLabel labelPreparacion = new JLabel("Preparación");
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.gridwidth = 2; // Ocupa dos columnas
        rightPanel.add(labelPreparacion, gbcRight);

        bebidas = new JButton("Bebidas");
        bebidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la interfaz BebidasGUI
                new BebidasGUI();
            }
        });
        gbcRight.gridx = 0;
        gbcRight.gridy = 3;
        gbcRight.gridwidth = 1; // Ocupa una columna
        rightPanel.add(bebidas, gbcRight);

        modificarPreparacion = new JButton("Modificar preparación");
        modificarPreparacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar preparación
            }
        });
        gbcRight.gridx = 1;
        gbcRight.gridy = 3;
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

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Ocupa una columna
        add(rightPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaz2());
    }
}
