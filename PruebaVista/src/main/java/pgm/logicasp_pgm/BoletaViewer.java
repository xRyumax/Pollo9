/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgm.logicasp_pgm;

import javax.swing.*;
import java.awt.*;

public class BoletaViewer extends JFrame {

    public BoletaViewer(String boletaTexto) {
        setTitle("Boleta de Pedido");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea taBoleta = new JTextArea();
        taBoleta.setText(boletaTexto);
        taBoleta.setEditable(false);
        add(new JScrollPane(taBoleta), BorderLayout.CENTER);
    }
}