package com.polloabrasa.frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import com.polloabrasa.backend.PolloAbrasa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPanel extends JPanel {
    private MainFrame mainFrame;

    public ResultPanel(MainFrame mainFrame, double[] ingredients) {
        this.mainFrame = mainFrame;

        // Aplicar FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Obtener la descripción del pollo a la brasa según los ingredientes
        PolloAbrasa pollo = new PolloAbrasa(ingredients);
        String descripcion = pollo.getDescripcion();

        // Crear el JLabel con el resultado
        JLabel resultLabel = new JLabel(descripcion);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.CENTER);

        // Botón para regresar al inicio
        JButton backButton = new JButton("Regresar al Inicio");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Añadir resultado al historial
                String resultadoConNombre = mainFrame.getUserName() + ": " + descripcion;
                mainFrame.addResultadoAlHistorial(resultadoConNombre);

                // Regresar al panel de inicio con el historial actualizado
                mainFrame.showStartWithHistory();
            }
        });

        // Añadir el botón en la parte inferior
        add(backButton, BorderLayout.SOUTH);
    }
}



