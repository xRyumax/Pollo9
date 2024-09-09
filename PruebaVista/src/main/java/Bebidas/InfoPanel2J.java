package Bebidas;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InfoPanel2J extends JPanel {

    private JLabel tipoLabel, bebidaLabel, cantidadLabel, estadoLabel, temperaturaLabel;
    private JComboBox<String> tipoComboBox, bebidaComboBox, estadoComboBox;
    private JSpinner cantidadSpinner;
    private String estadoSeleccionado = "-Seleccionar-";
    private InfoPanel1J infoPanel1;
    private JLabel horaLabel; 
    private Thread actualizarHilo; // Hilo para actualizar temperatura y hora
    private PerceptronBebida perceptron;
    
    public InfoPanel2J(InfoPanel1J infoPanel1) {
        this.infoPanel1 = infoPanel1;
        this.perceptron = new PerceptronBebida();
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(236, 166, 28, 150));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para los controles (norte)
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridBagLayout());
        controlsPanel.setBackground(new Color(236, 166, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ajustar tamaño horizontal

        // Labels
        tipoLabel = new JLabel("Tipo:");
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tipoLabel.setForeground(new Color(0, 0, 128));

        bebidaLabel = new JLabel("Bebida:");
        bebidaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bebidaLabel.setForeground(new Color(0, 0, 128));

        cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cantidadLabel.setForeground(new Color(0, 0, 128));

        estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        estadoLabel.setForeground(new Color(0, 0, 128));

        temperaturaLabel = new JLabel("Temperatura:");
        temperaturaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        temperaturaLabel.setForeground(new Color(0, 0, 128));
        temperaturaLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        // Nueva etiqueta para mostrar la hora
        horaLabel = new JLabel("Hora: ");
        horaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        horaLabel.setForeground(new Color(0, 0, 128));
        horaLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Combo Box para el tipo de bebida
        String[] tipos = {"-Seleccionar-", "Gaseosa", "Alcohol", "Refrescos", "Infusiones"};
        tipoComboBox = new JComboBox<>(tipos);
        tipoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo Box para la bebida
        bebidaComboBox = new JComboBox<>();
        bebidaComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Spinner para la cantidad
        SpinnerNumberModel cantidadModel = new SpinnerNumberModel(1, 1, 100, 1);
        cantidadSpinner = new JSpinner(cantidadModel);
        cantidadSpinner.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo Box para el estado
        String[] estados = {"-Seleccionar-", "Helada", "Al tiempo"};
        estadoComboBox = new JComboBox<>(estados);
        estadoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Ajustar el tamaño preferido de los JComboBox
        Dimension comboBoxSize = new Dimension(150, 30); // Ajusta el ancho y alto según lo necesites
        tipoComboBox.setPreferredSize(comboBoxSize);
        bebidaComboBox.setPreferredSize(comboBoxSize);
        estadoComboBox.setPreferredSize(comboBoxSize);

        // Añadir componentes al panel con GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlsPanel.add(tipoLabel, gbc);
        gbc.gridx = 1;
        controlsPanel.add(tipoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        controlsPanel.add(bebidaLabel, gbc);
        gbc.gridx = 1;
        controlsPanel.add(bebidaComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        controlsPanel.add(cantidadLabel, gbc);
        gbc.gridx = 1;
        controlsPanel.add(cantidadSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        controlsPanel.add(estadoLabel, gbc);
        gbc.gridx = 1;
        controlsPanel.add(estadoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Ocupa dos columnas
        controlsPanel.add(temperaturaLabel, gbc);

        gbc.gridy = 5;
        controlsPanel.add(horaLabel, gbc); // Añadir la etiqueta de la hora

        // Panel de botones (sur)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(0, 0, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones
        JButton sugerirButton = new JButton("Sugerir");
        sugerirButton.setFont(new Font("Arial", Font.BOLD, 14));
        sugerirButton.setBackground(new Color(0, 0, 0));
        sugerirButton.setForeground(Color.WHITE);

        // Agregar listener al botón "Sugerir"
        sugerirButton.addActionListener(e -> sugerirBebida());

        JButton añadirButton = new JButton("Añadir");
        añadirButton.setFont(new Font("Arial", Font.BOLD, 14));
        añadirButton.setBackground(new Color(0, 0, 0));
        añadirButton.setForeground(Color.WHITE);

        JButton nextButton = new JButton("Imprimir Boleta");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(0, 0, 0));
        nextButton.setForeground(Color.WHITE);

        // Agregar botones al panel de botones
        buttonPanel.add(sugerirButton);
        buttonPanel.add(añadirButton);
        buttonPanel.add(nextButton);

        // Agregar paneles al panel de información
        this.add(controlsPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar listener para los combo boxes
        tipoComboBox.addActionListener(e -> tipoComboBoxActionPerformed());
        estadoComboBox.addActionListener(e -> {
            estadoSeleccionado = (String) estadoComboBox.getSelectedItem();
            updateImage();
        });

        // Agregar listener para el comboBox bebida
        bebidaComboBox.addActionListener(e -> updateImage());

        // Iniciar hilo para actualizar temperatura y hora
        iniciarActualizacionClimaYHora();

        // Detectar y mostrar el sistema operativo en la consola
        String os = System.getProperty("os.name");
        System.out.println("Sistema Operativo: " + os);
    }

    private void iniciarActualizacionClimaYHora() {
        actualizarHilo = new Thread(() -> {
            while (true) {
                try {
                    // Actualizar la temperatura cada minuto
                   Api servicioClima = new Api();
                    double temperatura = servicioClima.obtenerTemperatura();
                    SwingUtilities.invokeLater(() -> temperaturaLabel.setText("Temperatura: " + temperatura + " °C"));

                    // Actualizar la hora cada segundo
                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String horaFormateada = ahora.format(formatoHora);
                    SwingUtilities.invokeLater(() -> horaLabel.setText("Hora: " + horaFormateada));

                    // Esperar 1 segundo antes de actualizar nuevamente la hora
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        actualizarHilo.start(); // Iniciar el hilo
    }

    private void tipoComboBoxActionPerformed() {
        updateBebidaComboBox();
        estadoSeleccionado = (String) estadoComboBox.getSelectedItem();
        if (estadoSeleccionado == null) {
            estadoSeleccionado = "-Seleccionar-";
        }
        updateImage();
    }

    private void updateBebidaComboBox() {
    bebidaComboBox.removeAllItems(); // Limpiar ítems previos

    // Mapa con tipos de bebida y sus opciones
    Map<String, String[]> bebidasPorTipo = new HashMap<>();
    bebidasPorTipo.put("Gaseosa", new String[]{"Coca Cola", "Inka Kola", "Fanta"});
    bebidasPorTipo.put("Alcohol", new String[]{"Cusqueña", "Arequipeña", "Pilsen"});
    bebidasPorTipo.put("Refrescos", new String[]{"Chicha morada", "Limonada", "Maracuya"});
    bebidasPorTipo.put("Infusiones", new String[]{"Manzanilla", "Te", "Anis"});
    String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();

    if (bebidasPorTipo.containsKey(tipoSeleccionado)) {
        for (String bebida : bebidasPorTipo.get(tipoSeleccionado)) {
            bebidaComboBox.addItem(bebida);
        }
    }
    
    // Establecer el estado de las bebidas después de seleccionar el tipo
    estadoComboBox.removeAllItems();
    estadoComboBox.addItem("-Seleccionar-");
    estadoComboBox.addItem("Helada");
    estadoComboBox.addItem("Al tiempo");
    estadoComboBox.addItem("Caliente");
}

    private void updateImage() {
        String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
        String bebidaSeleccionada = (String) bebidaComboBox.getSelectedItem();
        if (estadoSeleccionado == null) {
            estadoSeleccionado = "-Seleccionar-";
        }
        infoPanel1.updateImageBasedOnSelection(tipoSeleccionado, bebidaSeleccionada, estadoSeleccionado);
    }

   private void sugerirBebida() {
    try {
        Api servicioClima = new Api();
        double temperatura = servicioClima.obtenerTemperatura();

        // Obtener sugerencias del perceptrón
        String tipoBebida = perceptron.sugerirTipo(temperatura);
        String estadoBebida = perceptron.predecirEstado(temperatura); // Usar la función mejorada para predecir el estado

        // Actualiza los JComboBox según las sugerencias del perceptrón
        tipoComboBox.setSelectedItem(tipoBebida);

        // Selecciona una bebida al azar dentro del tipo sugerido
        int itemCount = bebidaComboBox.getItemCount();
        if (itemCount > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(itemCount);  // Índice aleatorio basado en el número de ítems en el combo box
            bebidaComboBox.setSelectedIndex(randomIndex); // Seleccionar una bebida al azar
        }

        // Selecciona el estado basado en la predicción mejorada del perceptrón
        estadoComboBox.setSelectedItem(estadoBebida);

        // Mostrar la sugerencia al usuario
        JOptionPane.showMessageDialog(this, 
            "Sugerencia de bebida para " + temperatura + " °C: " + tipoBebida + " - " + estadoBebida, 
            "Sugerencia", 
            JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al obtener la temperatura.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
