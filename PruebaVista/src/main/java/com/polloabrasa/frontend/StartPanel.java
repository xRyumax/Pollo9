package com.polloabrasa.frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mycompany.pruebavista.RestauranteInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartPanel extends JPanel {
    private JTextField nameField;
    private MainFrame mainFrame;

    public StartPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Aplicar FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Etiqueta de bienvenida
        JLabel welcomeLabel = new JLabel("Bienvenido a Pollo a la Brasa Neural Network", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel para el campo de entrada del nombre
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));

        nameField = new JTextField();
        inputPanel.add(new JLabel("Ingrese su nombre:"));
        inputPanel.add(nameField);

        add(inputPanel, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FlowLayout para posicionar a la derecha

        // Botón de inicio
        JButton startButton = new JButton("Iniciar");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText().trim();
                if (!userName.isEmpty()) {
                    mainFrame.setUserName(userName);
                    mainFrame.showIngredientPanel();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Por favor, ingrese su nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(startButton); // Agregar botón de inicio al panel de botones

        // Botón 'Atrás'
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción al hacer clic en 'Atrás'
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(StartPanel.this);
                frame.dispose(); // Cerrar la ventana actual
                RestauranteInicio inicio = new RestauranteInicio(); // Crear instancia de RestauranteInicio
                inicio.setVisible(true); // Mostrar la ventana de RestauranteInicio
            }
        });
        buttonPanel.add(btnAtras); // Agregar botón 'Atrás' al panel de botones

        add(buttonPanel, BorderLayout.SOUTH); // Añadir el panel de botones al sur (parte inferior)

        // Panel para el historial de resultados
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Historial de Resultados"));

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        add(historyPanel, BorderLayout.EAST);

        // Actualizar el historial al cargar el panel
        updateHistory(historyArea);
    }

    private void updateHistory(JTextArea historyArea) {
        List<String> historial = mainFrame.getHistorialResultados();
        StringBuilder sb = new StringBuilder();
        for (String resultado : historial) {
            sb.append(resultado).append("\n\n");
        }
        historyArea.setText(sb.toString());
    }
}





