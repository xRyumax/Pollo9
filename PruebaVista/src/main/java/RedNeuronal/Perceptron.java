package RedNeuronal;

public class Perceptron {
    private double[] pesos;
    private double tasaAprendizaje;

    public Perceptron(int numEntradas, double tasaAprendizaje) {
        this.pesos = new double[numEntradas + 1]; // +1 para el sesgo
        this.tasaAprendizaje = tasaAprendizaje;
        // Inicializa los pesos con valores aleatorios
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = Math.random() * 2 - 1; // Valores entre -1 y 1
        }
    }

    public int predecir(double[] entradas) {
        double suma = pesos[0]; // Sesgo
        for (int i = 0; i < entradas.length; i++) {
            suma += entradas[i] * pesos[i + 1];
        }
        return (suma > 0) ? 1 : 0; // Activación
    }

    public void entrenar(double[][] entradas, int[] salidas, int epocas) {
        for (int epoch = 0; epoch < epocas; epoch++) {
            for (int i = 0; i < entradas.length; i++) {
                int prediccion = predecir(entradas[i]);
                int error = salidas[i] - prediccion;
                // Actualización de pesos
                pesos[0] += tasaAprendizaje * error; // Sesgo
                for (int j = 0; j < entradas[i].length; j++) {
                    pesos[j + 1] += tasaAprendizaje * error * entradas[i][j];
                }
            }
        }
    }
}
