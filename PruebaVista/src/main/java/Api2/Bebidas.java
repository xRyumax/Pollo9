package Api2;


import java.util.*;

public class Bebidas {

    private static final Map<String, List<Bebida>> BebidasClima = new HashMap<>();
    private static final double TemperaturaAlta = 22.0;
    private static final double UMBRAL = 0.5;

    static {
        BebidasClima.put("alto", Arrays.asList(
                new Bebida("Refresco", 3.5),
                new Bebida("Limonada", 2.5),
                new Bebida("Chicha", 2.5),
                new Bebida("Gaseosa", 2.5),
                new Bebida("Cerveza", 4.0),
                new Bebida("Mojito", 5.0),
                new Bebida("Sangria", 4.5),
                new Bebida("Limonada de panela",2.3),
                new Bebida("Té helado", 4.5),
                new Bebida("Gin Lemon", 3.4)                
        ));
        BebidasClima.put("bajo", Arrays.asList(
                new Bebida("Te caliente", 2.0),
                new Bebida("Cafe", 2.5),
                new Bebida("Chocolate caliente", 3.0),
                new Bebida("Vino caliente", 5.0),
                new Bebida("Grog", 4.5),
                new Bebida("Leche Dorada", 4.8),
                new Bebida("Golden Milk", 2.8),
                new Bebida("Leche de almendras con chocolate", 2.3),
                new Bebida("Capuchino de matcha con chocolate blanco", 2.4),
                new Bebida("Atole de elote", 3.5)
        ));
    }

    private ServicioClima servicioClima;
    private String climaActual;
    private double temperaturaActual;
    private Perceptron perceptron;

    public Bebidas() {
        servicioClima = new ServicioClima();
        perceptron = new Perceptron(1);
        entrenarPerceptron();
    }

    private void entrenarPerceptron() {
        try {
            List<Double> temperaturas = servicioClima.obtenerHistorialTemperaturas();

            // Imprimir el historial de temperaturas
            System.out.println("Historial de temperaturas:");
            for (Double temperatura : temperaturas) {
                System.out.println(temperatura);
            }

            int numDatos = temperaturas.size();
            double[][] datos = new double[numDatos][1];
            int[] etiquetas = new int[numDatos];

            for (int i = 0; i < numDatos; i++) {
                double temperatura = temperaturas.get(i);
                datos[i][0] = temperatura;
                etiquetas[i] = temperatura >= TemperaturaAlta ? 1 : 0;
            }

            perceptron.entrenar(datos, etiquetas, 1000, 0.1);
        } catch (Exception e) {
            System.out.println("No se pudo obtener el historial de temperaturas. Intenta de nuevo.");
        }
    }

    public void sugerirBebida() {
        try {
            temperaturaActual = servicioClima.obtenerTemperatura();
            climaActual = evaluarClima(temperaturaActual);
            mostrarTemperatura();
            mostrarSugerencia();
        } catch (Exception e) {
            System.out.println("No se pudo obtener la temperatura. Intenta de nuevo.");
        }
    }

    private String evaluarClima(double temperatura) {
        double input = temperatura;
        double resultado = perceptron.predecir(new double[]{input});
        return resultado >= UMBRAL ? "alto" : "bajo";
    }

    private void mostrarTemperatura() {
        System.out.println("Temperatura actual: " + temperaturaActual + "C");
    }

    private void mostrarSugerencia() {
        List<Bebida> sugerencias = BebidasClima.get(climaActual);
        System.out.println("Sugerencias de bebidas para clima " + climaActual + ":");
        for (Bebida bebida : sugerencias) {
            System.out.println("- " + bebida.getNombre() + ": $" + bebida.getPrecio());
        }
    }

    private static class Bebida {

        private final String nombre;
        private final double precio;

        public Bebida(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }

    private static class Perceptron {

        private double[] pesos;
        private int numInputs;
        private Random random;

        public Perceptron(int numInputs) {
            this.numInputs = numInputs;
            random = new Random();
            pesos = new double[numInputs + 1];
            inicializarPesos();
        }

        private void inicializarPesos() {
            for (int i = 0; i < pesos.length; i++) {
                pesos[i] = random.nextDouble() * 2 - 1;
            }
        }

        public void entrenar(double[][] datos, int[] etiquetas, int epochs, double tasaAprendizaje) {
            for (int epoch = 0; epoch < epochs; epoch++) {
                boolean errorEncontrado = false;
                for (int i = 0; i < datos.length; i++) {
                    double[] entrada = datos[i];
                    double prediccion = predecir(entrada);
                    double error = etiquetas[i] - prediccion;
                    if (Math.abs(error) > 0.01) {
                        ajustarPesos(entrada, error, tasaAprendizaje);
                        errorEncontrado = true;
                    }
                }
                if (!errorEncontrado) {
                    break;
                }
            }
        }

        public double predecir(double[] entrada) {
            double suma = pesos[0];
            for (int i = 0; i < entrada.length; i++) {
                suma += entrada[i] * pesos[i + 1];
            }
            return sigmoid(suma);
        }

        private void ajustarPesos(double[] entrada, double error, double tasaAprendizaje) {
            pesos[0] += tasaAprendizaje * error;
            for (int i = 0; i < entrada.length; i++) {
                pesos[i + 1] += tasaAprendizaje * error * entrada[i];
            }
        }

        private double sigmoid(double x) {
            return 1.0 / (1.0 + Math.exp(-x));
        }

    }

    public static void main(String[] args) {
        Bebidas app = new Bebidas();
        app.sugerirBebida();
    }
}