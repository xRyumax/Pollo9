package pgm.logicasp_pgm;

import Api.ServicioClima;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class BebidasGUI extends JFrame {

    private JLabel tipoLabel, bebidaLabel, cantidadLabel, estadoLabel, temperaturaLabel, imageLabel;
    private JComboBox<String> tipoComboBox, bebidaComboBox, estadoComboBox;
    private JSpinner cantidadSpinner;
    private JButton sugerirButton, añadirButton, nextButton;
    private ImageIcon imageIcon;
    private PerceptronBebidas perceptronBebidas; // Agrega esta línea

    public BebidasGUI() {
        super("Interfaz de Bebidas");
        setSize(800, 400); // Tamaño del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar PerceptronBebidas
        perceptronBebidas = new PerceptronBebidas(); // Agrega esta línea

        // Panel de información y botones
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout()); // Usar BorderLayout para distribuir componentes
        infoPanel.setBackground(new Color(173, 216, 230)); // Color de fondo celeste claro
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes

        // Panel para los controles (norte)
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 2, 10, 10));
        controlsPanel.setBackground(new Color(173, 216, 230)); // Color de fondo celeste claro
        
        // Labels
        tipoLabel = new JLabel("Tipo:");
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tipoLabel.setForeground(new Color(0, 0, 128)); // Color de texto azul oscuro
        bebidaLabel = new JLabel("Bebida:");
        bebidaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bebidaLabel.setForeground(new Color(0, 0, 128)); // Color de texto azul oscuro
        cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cantidadLabel.setForeground(new Color(0, 0, 128)); // Color de texto azul oscuro
        estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        estadoLabel.setForeground(new Color(0, 0, 128)); // Color de texto azul oscuro
        temperaturaLabel = new JLabel("Temperatura:"); // Nueva etiqueta para la temperatura
        temperaturaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        temperaturaLabel.setForeground(new Color(0, 0, 128)); // Color de texto azul oscuro

        // Combo Box para el tipo de bebida
        String[] tipos = {"-Seleccionar-", "Gaseosa", "Alcohol"};
        tipoComboBox = new JComboBox<>(tipos);
        tipoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo Box para la bebida
        String[] gaseosa = {"-Seleccionar-", "Coca Cola", "Inka Kola", "Fanta"};
        String[] alcohol = {"-Seleccionar-", "Cusqueña", "Arequipeña", "Pilsen"};
        bebidaComboBox = new JComboBox<>(gaseosa);
        bebidaComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Spinner para la cantidad
        SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 100, 1); // Inicial, mínimo, máximo, incremento
        cantidadSpinner = new JSpinner(cantidadModel);
        cantidadSpinner.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo Box para el estado con dos opciones
        String[] estados = {"-Seleccionar-", "Helada", "Tiempo"};
        estadoComboBox = new JComboBox<>(estados);
        estadoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Agregar componentes al panel de controles
        controlsPanel.add(tipoLabel);
        controlsPanel.add(tipoComboBox);
        controlsPanel.add(bebidaLabel);
        controlsPanel.add(bebidaComboBox);
        controlsPanel.add(cantidadLabel);
        controlsPanel.add(cantidadSpinner);
        controlsPanel.add(estadoLabel);
        controlsPanel.add(estadoComboBox);
        controlsPanel.add(temperaturaLabel); // Añadir etiqueta de temperatura

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(135, 206, 235)); // Color de fondo celeste medio
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes

        // Botones
        sugerirButton = new JButton("Sugerir");
        sugerirButton.setFont(new Font("Arial", Font.BOLD, 14));
        sugerirButton.setBackground(new Color(135, 206, 235)); // Color de fondo celeste medio
        sugerirButton.setForeground(Color.WHITE); // Color de texto blanco
        añadirButton = new JButton("Añadir");
        añadirButton.setFont(new Font("Arial", Font.BOLD, 14));
        añadirButton.setBackground(new Color(135, 206, 235)); // Color de fondo celeste medio
        añadirButton.setForeground(Color.WHITE); // Color de texto blanco
        nextButton = new JButton("→");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(135, 206, 235)); // Color de fondo celeste medio
        nextButton.setForeground(Color.WHITE); // Color de texto blanco

        // Agregar botones al panel de botones
        buttonPanel.add(sugerirButton);
        buttonPanel.add(añadirButton);
        buttonPanel.add(nextButton);

        // Agregar paneles al panel de información
        infoPanel.add(controlsPanel, BorderLayout.CENTER);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Panel de imagen (oeste)
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 200)); // Establece el tamaño preferido del JLabel
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes
        updateImage("src/images/default_image.jpg"); // Imagen por defecto
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Agregar componentes al frame
        add(imagePanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);

        // Agregar listeners para los combo boxes
        tipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) tipoComboBox.getSelectedItem();
                if (seleccionado.equals("Gaseosa")) {
                    bebidaComboBox.removeAllItems();
                    for (String gaseosa : new String[]{"-Seleccionar-", "Coca Cola", "Inka Kola", "Fanta"}) {
                        bebidaComboBox.addItem(gaseosa);
                    }
                } else if (seleccionado.equals("Alcohol")) {
                    bebidaComboBox.removeAllItems();
                    for (String alcohol : new String[]{"-Seleccionar-", "Cusqueña", "Arequipeña", "Pilsen"}) {
                        bebidaComboBox.addItem(alcohol);
                    }
                }
                updateImage("src/images/default_image.jpg"); // Imagen por defecto
            }
        });

        bebidaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) bebidaComboBox.getSelectedItem();
                String imagePath = "src/images/default_image.jpg"; // Imagen por defecto

                if (tipoComboBox.getSelectedItem().equals("Gaseosa")) {
                    switch (seleccion) {
                        case "Coca Cola":
                            imagePath = "src/images/coca_cola.jpg";
                            break;
                        case "Inka Kola":
                            imagePath = "src/images/inka.jpeg";
                            break;
                        case "Fanta":
                            imagePath = "src/images/fanta.jpg";
                            break;
                    }
                } else if (tipoComboBox.getSelectedItem().equals("Alcohol")) {
                    switch (seleccion) {
                        case "Cusqueña":
                            imagePath = "src/images/cusquena.jpg";
                            break;
                        case "Arequipeña":
                            imagePath = "src/images/arequipena.jpg";
                            break;
                        case "Pilsen":
                            imagePath = "src/images/pilsen.jpeg";
                            break;
                    }
                }
                updateImage(imagePath);
            }
        });

        // Obtener y mostrar la temperatura al iniciar
        SwingUtilities.invokeLater(() -> {
            try {
                ServicioClima servicioClima = new ServicioClima();
                double temperatura = servicioClima.obtenerTemperatura();
                temperaturaLabel.setText("Temperatura: " + temperatura + " °C");
            } catch (Exception ex) {
                ex.printStackTrace();
                temperaturaLabel.setText("Error al obtener temperatura");
            }
        });

        // Listener para el botón "Sugerir"
        sugerirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener la temperatura actual
                    ServicioClima servicioClima = new ServicioClima();
                    double temperatura = servicioClima.obtenerTemperatura();

                    // Usar el perceptrón para sugerir una bebida
                    String bebidaSugerida = perceptronBebidas.sugerirBebida(temperatura);

                    // Actualizar el JComboBox con la bebida sugerida
                    bebidaComboBox.setSelectedItem(bebidaSugerida);
                    
                    // Mostrar la bebida sugerida en la interfaz gráfica
                    updateImage("src/images/" + bebidaSugerida.toLowerCase().replace(" ", "_") + ".jpg");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar errores de manera apropiada
                }
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    private void updateImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage(); // Obtén la imagen
        Image scaledImage = image.getScaledInstance(300, 200, Image.SCALE_SMOOTH); // Redimensiona la imagen
        imageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BebidasGUI());
    }
}

