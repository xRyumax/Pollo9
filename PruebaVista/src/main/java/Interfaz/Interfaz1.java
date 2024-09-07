package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz1 extends JFrame {

    private JLabel labelIngredientes;
    private JButton buttonAñadir;
    private JButton buttonModificar;
    private JLabel labelImagen;
    private JCheckBox[] checkBoxes; // Cambiado a JCheckBox

    public Interfaz1() {
        super("Interfaz");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Agranda el tamaño del panel
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiqueta "Ingredientes"
        labelIngredientes = new JLabel("Ingredientes");
        labelIngredientes.setBounds(10, 10, 150, 20);
        add(labelIngredientes);

        // Botón "Añadir"
        buttonAñadir = new JButton("Añadir");
        buttonAñadir.setBounds(270, 10, 100, 20);
        add(buttonAñadir);

        // Botón "Modificar"
        buttonModificar = new JButton("Modificar");
        buttonModificar.setBounds(270, 40, 100, 20);
        add(buttonModificar);

        // Etiqueta para la imagen
        labelImagen = new JLabel();
        labelImagen.setIcon(new ImageIcon("ingrepo.jpg")); // Reemplaza "ingrepo.jpg" por la ruta de tu imagen
        labelImagen.setBounds(10, 80, 400, 200);
        add(labelImagen);

        // Panel para los JCheckBox
        JPanel panelCheckBoxes = new JPanel();
        panelCheckBoxes.setLayout(new GridLayout(5, 4)); // 5 filas y 4 columnas
        panelCheckBoxes.setBounds(10, 300, 600, 200); // Ajusta el tamaño y la posición
        add(panelCheckBoxes);

        // Define los nombres específicos para los JCheckBox
        String[] nombresCheckBoxes = {
            "Sal", "..", "..", "..",
            "..", "..", "..", "..",
            "..", "..", ". ..", "..",
            "..", "..", "..", "..",
            "..", "..", "..", "."
        };

        // Inicializa los JCheckBox y los agrega al panel
        checkBoxes = new JCheckBox[nombresCheckBoxes.length]; // Usa el tamaño de nombresCheckBoxes
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(nombresCheckBoxes[i]);
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
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaz1());
    }
}
