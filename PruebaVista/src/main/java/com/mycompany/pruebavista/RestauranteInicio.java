package com.mycompany.pruebavista;

import Perceptron.PerceptronMenu;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import pgm.logicasp_pgm.PlatoGUI;

public class RestauranteInicio extends JFrame {

    private JFormattedTextField edadField;
    private int edadUsuario = -1;  // Variable para guardar la edad del usuario
    private String presupuestoUsuario = "";  // Variable para guardar el presupuesto del usuario
    public PerceptronMenu perceptronMenu;
    
    public String getPresupuestoUsuario() {
        return presupuestoUsuario;
    }

    public void setPresupuestoUsuario(String presupuestoUsuario) {
        this.presupuestoUsuario = presupuestoUsuario;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }
       
    public RestauranteInicio() {
        // Configuración de la ventana
        this.perceptronMenu = new PerceptronMenu();
        setTitle("Restaurante - Inicio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel con imagen de fondo
        FondoPanel fondoPanel = new FondoPanel();
        fondoPanel.setLayout(new GridBagLayout());  // Usar GridBagLayout para centrar el panel negro

        // Crear el panel negro que contendrá las preguntas
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridBagLayout());
        panelContenido.setBackground(new Color(150, 150, 150, 150));  
        panelContenido.setPreferredSize(new Dimension(500, 500));  // Tamaño del contenedor más pequeño

        GridBagConstraints c = new GridBagConstraints();

        // Título de la ventana
        JLabel titleLabel = new JLabel("Bienvenido a Flavors");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;  // Para que ocupe todo el ancho de los botones
        c.insets = new Insets(10, 10, 10, 10);
        panelContenido.add(titleLabel, c);

        // Pregunta sobre el género
        JLabel generoLabel = new JLabel("Seleccione su género:");
        generoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        generoLabel.setForeground(Color.WHITE);
        c.gridy = 1;
        c.gridwidth = 1;
        panelContenido.add(generoLabel, c);

        JButton hombreButton = new JButton("", new ImageIcon(getClass().getResource("/hombre.png")));
        JButton mujerButton = new JButton("", new ImageIcon(getClass().getResource("/mujer.png")));
        hombreButton.setBackground(Color.WHITE);  
        mujerButton.setBackground(Color.WHITE);   

        c.gridy = 2;
        c.gridx = 0;
        c.insets = new Insets(10, 5, 10, 5);
        panelContenido.add(hombreButton, c);

        c.gridx = 1;
        panelContenido.add(mujerButton, c);

        // Pregunta sobre la edad
        JLabel edadLabel = new JLabel("Ingrese su edad:");
        edadLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        edadLabel.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;  // Para que ocupe todo el ancho
        panelContenido.add(edadLabel, c);

        // Campo para ingresar la edad (más delgado)
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();  // Formato para solo aceptar números enteros
        edadField = new JFormattedTextField(numberFormat);
        edadField.setPreferredSize(new Dimension(100, 30));  // Más delgado
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridx = 0;
        panelContenido.add(edadField, c);

        // Botón para validar la edad
        JButton validarEdadButton = new JButton("Enviar");
        validarEdadButton.setBackground(Color.BLACK);  
        validarEdadButton.setForeground(Color.WHITE); 
      
        c.gridx = 1;
        panelContenido.add(validarEdadButton, c);

        // Acción del botón para validar la edad
        validarEdadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edadField.commitEdit();  // Confirmar el valor editado
                    int edad = ((Number) edadField.getValue()).intValue();
                    if (edad >= 0) {
                        edadUsuario = edad;  // Guardamos la edad para usarla posteriormente
                        JOptionPane.showMessageDialog(null, "Edad validada y guardada: " + edadUsuario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese una edad válida (mayor o igual a 0).");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido para la edad.");
                }
            }
        });

        // Sección para seleccionar el presupuesto
        JLabel presupuestoLabel = new JLabel("Seleccione su presupuesto:");
        presupuestoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        presupuestoLabel.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        panelContenido.add(presupuestoLabel, c);

        // Botones para el presupuesto: Bajo, Medio, Alto (con el mismo tamaño)
        JButton bajoButton = new JButton("Bajo");
        JButton medioButton = new JButton("Medio");
        JButton altoButton = new JButton("Alto");

        // Colores de fondo y texto
        bajoButton.setBackground(Color.BLACK);
        bajoButton.setForeground(Color.WHITE);
        medioButton.setBackground(Color.BLACK);
        medioButton.setForeground(Color.WHITE);
        altoButton.setBackground(Color.BLACK);
        altoButton.setForeground(Color.WHITE);

        // Asegurar que todos los botones tengan el mismo tamaño
        Dimension buttonSize = new Dimension(0, 40);
        bajoButton.setPreferredSize(buttonSize);
        medioButton.setPreferredSize(buttonSize);
        altoButton.setPreferredSize(buttonSize);

        // Configuración de layout para los botones
        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);  // Espacio uniforme entre los botones
        c.fill = GridBagConstraints.HORIZONTAL;  // Los botones llenan el espacio horizontalmente
        panelContenido.add(bajoButton, c);

        c.gridx = 1;
        panelContenido.add(medioButton, c);

        c.gridx = 2;
        panelContenido.add(altoButton, c);

        // Etiquetas descriptivas debajo de los botones
        JLabel bajoLabel = new JLabel("Menos de 40");
        bajoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bajoLabel.setForeground(Color.WHITE);
        c.gridy = 7;
        c.gridx = 0;
        panelContenido.add(bajoLabel, c);

        JLabel medioLabel = new JLabel("Entre 40 y 60");
        medioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        medioLabel.setForeground(Color.WHITE);
        c.gridx = 1;
        panelContenido.add(medioLabel, c);

        JLabel altoLabel = new JLabel("Más de 60");
        altoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        altoLabel.setForeground(Color.WHITE);
        c.gridx = 2;
        panelContenido.add(altoLabel, c);

        // Acciones para los botones de presupuesto
        bajoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPresupuestoUsuario("Bajo");
                perceptronMenu.setPresupuesto(presupuestoUsuario);
                JOptionPane.showMessageDialog(null, "Presupuesto seleccionado: Bajo");
            }
        });

        medioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPresupuestoUsuario("Medio");
                perceptronMenu.setPresupuesto(presupuestoUsuario);
                JOptionPane.showMessageDialog(null, "Presupuesto seleccionado: Medio");
            }
        });

        altoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPresupuestoUsuario("Alto");
                perceptronMenu.setPresupuesto(presupuestoUsuario);
                JOptionPane.showMessageDialog(null, "Presupuesto seleccionado: Alto");
            }
        });

        JButton irMenuButton = new JButton("Selecciona tus ingredientes");
        irMenuButton.setBackground(Color.BLACK);  
        irMenuButton.setForeground(Color.WHITE);  
 
        c.gridy = 8;
        c.gridx = 0;
        c.gridwidth = 1;
      
        panelContenido.add(irMenuButton, c);

        // Acción del botón de ir al Menú usando ActionListener
    irMenuButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (edadUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Por favor valide su edad antes de continuar.");
        } else if (presupuestoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione su presupuesto antes de continuar.");
        } else {
            // Ejecutar la clase Interfaz1 del paquete Interfaz
            new Interfaz.Interfaz1().setVisible(true);
            dispose(); // Cerrar la ventana actual
        }
       }
        }); 


        // Botón para ir a Sugerencias (mismo tamaño que el de ir al Menú)
        JButton irSugerenciaButton = new JButton("Ir a Sugerencia");
        irSugerenciaButton.setBackground(Color.BLACK); 
        irSugerenciaButton.setForeground(Color.WHITE);  

        
        c.gridx = 1;
        panelContenido.add(irSugerenciaButton, c);

        // Acción del botón de ir a Sugerencias usando ActionListener
        irSugerenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (edadUsuario == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor valide su edad antes de continuar.");
                } else if (presupuestoUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione su presupuesto antes de continuar.");
                } else {
                    new PlatoGUI().setVisible(true); // Ir a la vista de RestauranteSugerencia
                    dispose(); // Cerrar la ventana actual
                }
            }
        });

        // Centrar el panel negro en el fondo
        fondoPanel.add(panelContenido, new GridBagConstraints());

        // Añadir el panel con fondo a la ventana
        add(fondoPanel);
    }

    // Clase interna para establecer la imagen de fondo
    class FondoPanel extends JPanel {
        private Image imagen;

        public FondoPanel() {
            // Cargar la imagen de fondo
            imagen = new ImageIcon(getClass().getResource("/FondoInicio.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen de fondo escalada al tamaño del panel
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Clase interna para crear bordes redondeados
    class RoundBorder extends AbstractBorder {
        private int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(10, 10, 10, 10);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = 10;
            return insets;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestauranteInicio().setVisible(true);
            }
        });
    }
}