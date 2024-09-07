package com.polloabrasa.frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private String userName;
    private List<String> historialResultados;

    public MainFrame() {
        // Aplicar FlatDarkLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        historialResultados = new ArrayList<>();
        setTitle("Pollo a la Brasa Neural Network");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Mostrar el panel de inicio
        showStartPanel();

        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }

    private void showStartPanel() {
        StartPanel startPanel = new StartPanel(this);
        add(startPanel, BorderLayout.CENTER);
    }

    public void showIngredientPanel() {
        getContentPane().removeAll(); // Eliminar el panel de inicio
        add(new IngredientPanel(this), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void showStartWithHistory() {
        getContentPane().removeAll(); // Eliminar el panel actual
        add(new StartPanel(this), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setUserName(String userName) {
        this.userName = userName;
        setTitle("Bienvenido " + userName + " - Pollo a la Brasa Neural Network");
    }

    public String getUserName() {
        return userName;
    }

    public void addResultadoAlHistorial(String resultado) {
        historialResultados.add(0, resultado); // Añadir al inicio
        if (historialResultados.size() > 10) { // Limitar a 10 resultados
            historialResultados.remove(historialResultados.size() - 1);
        }
    }

    public List<String> getHistorialResultados() {
        return historialResultados;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}



