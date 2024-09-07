package com.polloabrasa.frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientPanel extends JPanel {
    private MainFrame mainFrame;
    private List<JButton> ingredientButtons = new ArrayList<>();
    private double[] ingredientValues;

    public IngredientPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Aplicar FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 2));

        // Inicializar los valores de los ingredientes
        ingredientValues = new double[14]; // Asegurarse de que el tamaño sea 14

        // Lista de ingredientes (14)
        String[] ingredients = {
            "Ajo", "Pimienta", "Sal", "Comino", "Orégano", 
            "Sillao", "Vinagre", "Limón", "Ají Panca", "Cerveza",
            "Aceite de Oliva", "Romero", "Paprika", "Mostaza"
        };

        for (int i = 0; i < ingredients.length; i++) {
            JButton ingredientButton = new JButton(ingredients[i]);
            int index = i;
            ingredientButton.addActionListener(e -> {
                ingredientValues[index] = ingredientValues[index] == 1.0 ? 0.0 : 1.0;
                ingredientButton.setBackground(ingredientValues[index] == 1.0 ? Color.GREEN : null);
            });
            ingredientButtons.add(ingredientButton);
            buttonsPanel.add(ingredientButton);
        }

        add(buttonsPanel, BorderLayout.CENTER);

        JButton resultButton = new JButton("Ver Resultado");
        resultButton.addActionListener(e -> {
            JFrame resultFrame = new JFrame("Resultado");
            resultFrame.setSize(500, 400);
            resultFrame.setLocationRelativeTo(mainFrame);
            resultFrame.add(new ResultPanel(mainFrame, ingredientValues));
            resultFrame.setVisible(true);
        });

        add(resultButton, BorderLayout.SOUTH);
    }
}





