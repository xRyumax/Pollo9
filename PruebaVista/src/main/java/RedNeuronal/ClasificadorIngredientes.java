package RedNeuronal;

public class ClasificadorIngredientes {

    private Perceptron perceptron;

    public ClasificadorIngredientes() {
        // Inicializa el perceptrón con 5 entradas (una para cada característica) y una tasa de aprendizaje
        perceptron = new Perceptron(5, 0.1);

        // Datos de entrenamiento actualizados
        // Estos datos deben ser ajustados según la realidad de la clasificación de los platos
        double[][] entradas = {
            {1.0, 1.0, 1.0, 1.0, 1.0},  // Características ideales para "bueno"
            {0.7, 0.7, 0.7, 0.7, 0.7},  // Características buenas pero no ideales para "más o menos"
            {0.3, 0.3, 0.3, 0.3, 0.3},  // Características deficientes para "malo"
            {1.0, 0.3, 0.3, 0.3, 0.3},  // Ejemplo de combinación que podría ser "malo"
            {0.3, 1.0, 1.0, 0.3, 0.3}   // Ejemplo de combinación que podría ser "malo"
        };
        int[] salidas = {1, 2, 0, 0, 0}; // 1: bueno, 2: más o menos, 0: malo
        perceptron.entrenar(entradas, salidas, 1000);
    }

    public String clasificar(double[] entradas) {
        // Aquí implementa la lógica de tu red neuronal simple
        return clasificarRecursivo(entradas, 0);
    }

    private String clasificarRecursivo(double[] entradas, int index) {
        if (index >= entradas.length) {
            // Base case: clasificación final
            return clasificarResultado(entradas);
        }
        
        // Lógica recursiva
        double valor = entradas[index];
        
        // Normalización de entradas
        entradas[index] = valor > 0.5 ? 1.0 : 0.0; 
        
        return clasificarRecursivo(entradas, index + 1);
    }

    private String clasificarResultado(double[] entradas) {
        // Determina las características
        boolean salado = entradas[0] > 0.5;
        boolean picante = entradas[1] > 0.5;
        boolean agridulce = entradas[2] > 0.5;
        boolean crocante = entradas[3] > 0.5;
        boolean jugoso = entradas[4] > 0.5;

        // Evaluación de la calidad del plato
        double[] entradasEvaluacion = { entradas[0], entradas[1], entradas[2], entradas[3], entradas[4] };
        int clasificacion = perceptron.predecir(entradasEvaluacion);

        // Ejemplo de resultado basado en las características
        StringBuilder resultado = new StringBuilder();
        resultado.append("Salado: ").append(salado ? "Sí" : "No").append("\n");
        resultado.append("Picante: ").append(picante ? "Sí" : "No").append("\n");
        resultado.append("Agridulce: ").append(agridulce ? "Sí" : "No").append("\n");
        resultado.append("Crocante: ").append(crocante ? "Sí" : "No").append("\n");
        resultado.append("Jugoso: ").append(jugoso ? "Sí" : "No").append("\n");

        // Clasificación del plato
        String evaluacion;
        switch (clasificacion) {
            case 1:
                evaluacion = "Bueno";
                break;
            case 2:
                evaluacion = "Más o menos";
                break;
            case 0:
                evaluacion = "Malo";
                break;
            default:
                evaluacion = "Evaluación desconocida";
                break;
        }

        // Añadir la evaluación al resultado
        resultado.append("El plato está: ").append(evaluacion).append("\n");

        return resultado.toString();
    }
}









