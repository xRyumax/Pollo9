package pgm.logicasp_pgm;

import Interfaz.BebidasGUI;
import Perceptron.Perceptron;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlatoGUI extends JFrame {
    private List<String> itemsAñadidos;
    private JComboBox<String> cbPorcion, cbContextura, cbSabor;
    private JTextField tfNumeroPollos;
    private JLabel lblTiempoEstimado, lblPrecio;
    private JButton btnVerIngredientes, btnModificarIngredientes, btnVerPreparacion, btnModificarPreparacion;
    private JButton btnPreparar, btnAñadir, btnSiguiente;
    private Perceptron perceptron;

    private int tiempoTotal = 0;
    private double precioTotal = 0.0;

    public PlatoGUI() {
        perceptron = new Perceptron();
        itemsAñadidos = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Simulador de Recetas de Pollo");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel principal con fondo rojo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.RED);
        panelPrincipal.setLayout(new GridBagLayout());
        add(panelPrincipal, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Imágenes a la izquierda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        JLabel lblAnimacion = new JLabel(new ImageIcon(getClass().getResource("/Pollo vivo.gif")));
        panelPrincipal.add(lblAnimacion, gbc);

        gbc.gridy = 2;
        JLabel lblPollo = new JLabel(new ImageIcon(getClass().getResource("/Pollo gif.gif")));
        panelPrincipal.add(lblPollo, gbc);

        // Ajustes para centrar verticalmente y horizontalmente los componentes de la derecha
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Porción:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        cbPorcion = new JComboBox<>(new String[]{"1", "1/2", "1/4", "1/8"});
        panelPrincipal.add(cbPorcion, gbc);

        // Campo de Cantidad
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Cantidad:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        tfNumeroPollos = new JTextField(10);
        tfNumeroPollos.setEditable(true); // Inicialmente editable
        panelPrincipal.add(tfNumeroPollos, gbc);

        // Campo de Contextura
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Contextura:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        cbContextura = new JComboBox<>(new String[]{"Crocante", "Tierno", "Seco"});
        panelPrincipal.add(cbContextura, gbc);

        // Campo de Sabor
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Sabor:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        cbSabor = new JComboBox<>(new String[]{"Jugoso", "Agridulce", "Picante"});
        panelPrincipal.add(cbSabor, gbc);

        // Botones de Ingredientes
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Ingredientes:"), gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        btnVerIngredientes = new JButton("Ver Ingredientes");
        btnModificarIngredientes = new JButton("Modificar Ingredientes");
        panelPrincipal.add(btnVerIngredientes, gbc);

        gbc.gridy = 5;
        panelPrincipal.add(btnModificarIngredientes, gbc);

        // Botones de Preparación
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Preparación:"), gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        btnVerPreparacion = new JButton("Bebidas");
        btnVerPreparacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BebidasGUI bebidasGUI = new BebidasGUI();
                bebidasGUI.setVisible(true);
                PlatoGUI.this.setVisible(false);
            }
        });
        btnModificarPreparacion = new JButton("Modificar Preparación");
        panelPrincipal.add(btnVerPreparacion, gbc);

        gbc.gridy = 7;
        panelPrincipal.add(btnModificarPreparacion, gbc);

        // Tiempo Estimado y Precio
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Tiempo estimado:"), gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        lblTiempoEstimado = new JLabel("0 minutos");
        panelPrincipal.add(lblTiempoEstimado, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        lblPrecio = new JLabel("$0.00");
        panelPrincipal.add(lblPrecio, gbc);

        // Panel para los botones de acción
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPreparar = new JButton("Preparar");
        btnAñadir = new JButton("Añadir");
        btnSiguiente = new JButton("→");
        panelBotones.setBackground(Color.red);
        panelBotones.add(btnPreparar);
        panelBotones.add(btnAñadir);
        panelBotones.add(btnSiguiente);
        panelPrincipal.add(panelBotones, gbc);

        // Acciones de los botones
        btnPreparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararPlato();
                tfNumeroPollos.setEditable(false); // Deshabilitar la edición
            }
        });

        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadirPlato();
                tfNumeroPollos.setEditable(true); // Habilitar la edición
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguientePaso();
            }
        });
    }

    private void prepararPlato() {
        // Obtener los datos seleccionados por el usuario
        String porcion = (String) cbPorcion.getSelectedItem();
        String contextura = (String) cbContextura.getSelectedItem();
        String sabor = (String) cbSabor.getSelectedItem();
        String cantidadStr = tfNumeroPollos.getText();

        // Validar que la cantidad sea un número entero positivo
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ejemplo simple de cálculo de tiempo estimado y precio
        int tiempoEstimado = calcularTiempoEstimado(porcion, contextura, sabor, cantidad);
        double precio = calcularPrecio(porcion, contextura, sabor, cantidad);

        // Actualizar las etiquetas con los nuevos valores
        lblTiempoEstimado.setText(tiempoTotal + tiempoEstimado + " minutos");
        lblPrecio.setText("$" + String.format("%.2f", precioTotal + precio));
    }

    private void añadirPlato() {
        // Obtener los datos seleccionados por el usuario
        String porcion = (String) cbPorcion.getSelectedItem();
        String contextura = (String) cbContextura.getSelectedItem();
        String sabor = (String) cbSabor.getSelectedItem();
        String cantidadStr = tfNumeroPollos.getText();

        // Validar que la cantidad sea un número entero positivo
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calcular tiempo estimado y precio para los nuevos datos
        int tiempoEstimado = calcularTiempoEstimado(porcion, contextura, sabor, cantidad);
        double precio = calcularPrecio(porcion, contextura, sabor, cantidad);

        // Sumar al total existente
        tiempoTotal += tiempoEstimado;
        precioTotal += precio;

        // Actualizar las etiquetas con los nuevos valores
        lblTiempoEstimado.setText(tiempoTotal + " minutos");
        lblPrecio.setText("$" + String.format("%.2f", precioTotal));

        // Limpiar los campos del formulario para la siguiente entrada
        cbPorcion.setSelectedIndex(0);
        tfNumeroPollos.setText("");
        cbContextura.setSelectedIndex(0);
        cbSabor.setSelectedIndex(0);
    }

    private int calcularTiempoEstimado(String porcion, String contextura, String sabor, int cantidad) {
        // Lógica para calcular el tiempo estimado
        int tiempoBase = 30; // Tiempo base en minutos
        if (contextura.equals("Crocante")) {
            tiempoBase += 10;
        } else if (contextura.equals("Tierno")) {
            tiempoBase += 5;
        }
        return tiempoBase + (cantidad * 5); // Tiempo adicional por cada pollo
    }

    private double calcularPrecio(String porcion, String contextura, String sabor, int cantidad) {
        // Lógica para calcular el precio
        double precioBase = 10.0; // Precio base en dólares
        if (contextura.equals("Crocante")) {
            precioBase += 2.0;
        } else if (contextura.equals("Tierno")) {
            precioBase += 1.0;
        }
        return precioBase * cantidad; // Precio total por la cantidad de pollos
    }

    private void siguientePaso() {
        generarBoletaPDF();
    }

    private void generarBoletaPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("boleta_plato.pdf"));
            document.open();
            document.add(new Paragraph("Boleta de Preparación de Plato"));
            document.add(new Paragraph("-----------------------------"));
            document.add(new Paragraph("Porción: " + cbPorcion.getSelectedItem().toString()));
            document.add(new Paragraph("Cantidad: " + tfNumeroPollos.getText()));
            document.add(new Paragraph("Contextura: " + cbContextura.getSelectedItem().toString()));
            document.add(new Paragraph("Sabor: " + cbSabor.getSelectedItem().toString()));
            document.add(new Paragraph("Tiempo estimado: " + lblTiempoEstimado.getText()));
            document.add(new Paragraph("Precio: " + lblPrecio.getText()));
            document.add(new Paragraph("-----------------------------"));
            document.add(new Paragraph("Gracias por su preferencia."));
            document.close();
            JOptionPane.showMessageDialog(this, "Boleta generada exitosamente en boleta_plato.pdf", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar la boleta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PlatoGUI().setVisible(true);
        });
    }
}