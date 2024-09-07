package pgm.logicasp_pgm;

import java.util.HashMap;
import java.util.Map;

public class PerceptronBebidas {

    private Map<String, Double> umbrales;

    public PerceptronBebidas() {
        // Inicializa los umbrales para cada bebida (temperatura en °C)
        umbrales = new HashMap<>();
        umbrales.put("Coca Cola", 20.0);   // Umbral de temperatura para Coca Cola
        umbrales.put("Inka Kola", 25.0);   // Umbral de temperatura para Inka Kola
        umbrales.put("Fanta", 30.0);       // Umbral de temperatura para Fanta
        umbrales.put("Cusqueña", 15.0);    // Umbral de temperatura para Cusqueña
        umbrales.put("Arequipeña", 20.0);  // Umbral de temperatura para Arequipeña
        umbrales.put("Pilsen", 25.0);      // Umbral de temperatura para Pilsen
    }

    public String sugerirBebida(double temperatura) {
        return sugerirBebidaRecursivo(temperatura, umbrales.keySet().iterator().next());
    }

    private String sugerirBebidaRecursivo(double temperatura, String bebidaActual) {
        if (umbrales.isEmpty()) {
            return "No hay bebida sugerida";
        }

        double umbral = umbrales.get(bebidaActual);

        if (temperatura <= umbral) {
            return bebidaActual;
        }

        // Elimina la bebida actual y pasa a la siguiente
        umbrales.remove(bebidaActual);
        if (umbrales.isEmpty()) {
            return "No hay bebida sugerida";
        }

        // Toma la siguiente bebida en el iterador
        return sugerirBebidaRecursivo(temperatura, umbrales.keySet().iterator().next());
    }
}

