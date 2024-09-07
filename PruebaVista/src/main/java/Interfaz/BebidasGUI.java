package Interfaz;


import Api2.ServicioClima;
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
    private String estadoSeleccionado = "-Seleccionar-"; // Variable para almacenar el estado seleccionado

    public BebidasGUI() {
        super("Interfaz de Bebidas");
        setSize(800, 400); // Tamaño del frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de información y botones (este)
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
        String[] tipos = {"-Seleccionar-", "Gaseosa", "Alcohol", "Refrescos","Infusiones"};
        tipoComboBox = new JComboBox<>(tipos);
        tipoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo Box para la bebida
        String[] gaseosa = {"-Seleccionar-", "Coca Cola", "Inka Kola", "Fanta"};
        String[] alcohol = {"-Seleccionar-", "Cusqueña", "Arequipeña", "Pilsen"};
        String[] refrescos = {"-Seleccionar-", "Chicha morada", "Limonada", "Maracuya"};
        String[] infusiones = {"-Seleccionar-", "Manzanilla", "Te", "Anis"};
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

        // Panel de botones (sur)
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
                bebidaComboBox.removeAllItems(); // Limpiar ítems previos

                if (seleccionado.equals("Gaseosa")) {
                    for (String gaseosa : new String[]{"-Seleccionar-", "Coca Cola", "Inka Kola", "Fanta"}) {
                        bebidaComboBox.addItem(gaseosa);
                    }
                } else if (seleccionado.equals("Alcohol")) {
                    for (String alcohol : new String[]{"-Seleccionar-", "Cusqueña", "Arequipeña", "Pilsen"}) {
                        bebidaComboBox.addItem(alcohol);
                    }
                } else if (seleccionado.equals("Refrescos")) {
                    for (String refresco : new String[]{"-Seleccionar-", "Chicha morada", "Limonada", "Maracuya"}) {
                        bebidaComboBox.addItem(refresco);
                    }
                } else if (seleccionado.equals("Infusiones")) {
                    for (String infusiones : new String[]{"-Seleccionar-", "Manzanilla", "Te", "Anis"}) {
                        bebidaComboBox.addItem(infusiones);
                    }
                }

                updateImage("src/images/default_image.jpg"); // Imagen por defecto cuando se cambia el tipo
            }
        });

        estadoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estadoSeleccionado = (String) estadoComboBox.getSelectedItem();
                updateImageBasedOnSelection(); // Actualiza la imagen basada en la selección actual
            }
        });

        bebidaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImageBasedOnSelection(); // Actualiza la imagen basada en la selección actual
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

        // Mostrar la ventana
        setVisible(true);
    }

    private void updateImageBasedOnSelection() {
        String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
        String bebidaSeleccionada = (String) bebidaComboBox.getSelectedItem();
        String imagePath = "src/images/default_image.jpg"; // Imagen por defecto

        if (tipoSeleccionado != null && bebidaSeleccionada != null) {
            if (tipoSeleccionado.equals("Gaseosa")) {
                switch (bebidaSeleccionada) {
                    case "Coca Cola":
                        imagePath = estadoSeleccionado.equals("Helada") ? "PruebaVista\\src/images/coca_helada.jpg" : "PruebaVista\\src/images/coca_cola.png";
                        break;
                    case "Inka Kola":
                        imagePath = estadoSeleccionado.equals("Helada") ? "PruebaVista\\src/images/inka_helada.jfif" : "PruebaVista\\src/images/inka.jpg";
                        break;
                    case "Fanta":
                        imagePath = estadoSeleccionado.equals("Helada") ? "PruebaVista\\src/images/fanta_helada.jpg" : "PruebaVista\\src/images/fanta.jpg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Alcohol")) {
                switch (bebidaSeleccionada) {
                    case "Cusqueña":
                        imagePath = "PruebaVista\\src/images/cusquena.png";
                        break;
                    case "Arequipeña":
                        imagePath = "PruebaVista\\src/images/arequipena.jfif";
                        break;
                    case "Pilsen":
                        imagePath = "PruebaVista\\src/images/pilsen.jpg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Refrescos")) {
                switch (bebidaSeleccionada) {
                    case "Chicha morada":
                        imagePath = "PruebaVista\\src/images/chichamorada.jpeg";
                        break;
                    case "Limonada":
                        imagePath = "PruebaVista\\src/images/limonada.jpeg";
                        break;
                    case "Maracuya":
                        imagePath = "PruebaVista\\src/images/maracuya.jpeg";
                        break;
                }
            } else if (tipoSeleccionado.equals("Infusiones")) {
                switch (bebidaSeleccionada) {
                    case "Manzanilla":
                        imagePath = "PruebaVista\\src/images/manzanilla.jpg";
                        break;
                    case "Te":
                        imagePath = "PruebaVista\\src/images/te.jfif";
                        break;
                    case "Anis":
                        imagePath = "PruebaVista\\src/images/anis.jpg";
                        break;
                }
            }
            updateImage(imagePath);
        }
    }

    private void updateImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath); // Carga la imagen desde la ruta especificada
        Image image = icon.getImage(); // Obtiene la imagen en su tamaño original

        // Escala la imagen para que tenga un tamaño de 300x200 píxeles, utilizando suavizado
        Image scaledImage = image.getScaledInstance(285, 360, Image.SCALE_SMOOTH); 

        // Crea un nuevo ImageIcon con la imagen escalada
        imageIcon = new ImageIcon(scaledImage);

        // Establece el ImageIcon en el JLabel para mostrar la imagen
        imageLabel.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BebidasGUI());
    }
}
