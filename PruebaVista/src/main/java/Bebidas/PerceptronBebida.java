package Bebidas;

public class PerceptronBebida {

    private double[] pesos;
    private double umbralFrio;
    private double umbralModerado;
    private double umbralCaliente;
    private double tasaAprendizaje = 0.1; // Tasa de aprendizaje para ajustar los pesos
    private double bias;

    public PerceptronBebida() {
        // Inicializar los pesos para cada entrada: temperatura y hora del día
        this.pesos = new double[]{0.5, 0.3}; // Ejemplo: temperatura tiene más peso que la hora del día
        this.umbralFrio = 25.0;  // Mayor umbral para bebidas frías
        this.umbralModerado = 18.0; 
        this.umbralCaliente = 10.0;
        this.bias = -1.0;
    }

    /**
     * Predice el estado de la bebida basado en la temperatura.
     * @param temperatura Temperatura actual en grados Celsius.
     * @return Estado sugerido de la bebida.
     */
    public String predecirEstado(double temperatura) {
        if (temperatura >= umbralFrio) {
            return "Helada";
        } else if (temperatura >= umbralModerado) {
            return "Al tiempo";
        } else {
            return "Caliente";  // Se sugiere bebidas calientes para temperaturas menores
        }
    }

    /**
     * Sugiere el tipo de bebida basado en la temperatura.
     * @param temperatura Temperatura actual en grados Celsius.
     * @return Tipo de bebida sugerido.
     */
    public String sugerirTipo(double temperatura) {
        if (temperatura > umbralFrio) {
            return "Gaseosa";
        } else if (temperatura > umbralModerado) {
            return "Refrescos";
        } else {
            return "Infusiones";  // Se sugieren bebidas calientes para bajas temperaturas
        }
    }

    /**
     * Ajusta los pesos del perceptrón basado en la retroalimentación.
     * @param inputs Las entradas (ej. temperatura, hora del día).
     * @param salidaEsperada La salida esperada (1 para frío, 0 para caliente).
     */
    public void ajustarPesos(double[] inputs, int salidaEsperada) {
        int salida = calcularSalida(inputs);
        int error = salidaEsperada - salida;
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] += tasaAprendizaje * error * inputs[i];
        }
        bias += tasaAprendizaje * error;
    }

    /**
     * Calcula la salida del perceptrón para las entradas proporcionadas.
     * @param inputs Las entradas (ej. temperatura, hora del día).
     * @return 1 para bebidas frías o 0 para calientes.
     */
    private int calcularSalida(double[] inputs) {
        double suma = 0.0;
        for (int i = 0; i < pesos.length; i++) {
            suma += pesos[i] * inputs[i];
        }
        suma += bias;
        return (suma >= 0) ? 1 : 0;
    }
}
