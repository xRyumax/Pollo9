package Interfaz;

import java.util.HashMap;
import java.util.Map;

public class PerceptronIngredientes {
    // Simula el perceptrón con entradas contextura y sabor, salida ingredientes
    private Map<String, String[]> ingredientesMap;

    public PerceptronIngredientes() {
        ingredientesMap = new HashMap<>();

        // Ingredientes por contextura y sabor
        ingredientesMap.put("Crocante_Jugoso", new String[]{
            "Pollo", "Sal", "Pimienta", "Cebolla", "Limón", 
            "Aceite (para lograr la textura crocante)", "Vinagre (para balancear jugosidad)"
        });

        ingredientesMap.put("Crocante_Agridulce", new String[]{
            "Pollo", "Sal", "Salsa de soya", "Vinagre", "Pimienta roja (ligeramente dulce)", 
            "Limón", "Aceite (para el crocante)"
        });

        ingredientesMap.put("Crocante_Picante", new String[]{
            "Pollo", "Pimienta negra", "Pimienta roja", "Pasta de ají", "Ajo", 
            "Aceite (para la textura crocante)", "Tomillo"
        });

        ingredientesMap.put("Tierno_Jugoso", new String[]{
            "Pollo", "Sal", "Cebolla", "Aceite", "Limón", 
            "Orégano", "Ajo en polvo"
        });

        ingredientesMap.put("Tierno_Agridulce", new String[]{
            "Pollo", "Vinagre", "Salsa de soya", "Cebolla", "Sal", 
            "Limón", "Romero (para darle suavidad y aroma)"
        });

        ingredientesMap.put("Tierno_Picante", new String[]{
            "Pollo", "Pimienta roja", "Pasta de ají", "Ajo", "Cebolla", 
            "Aceite", "Comino"
        });

        ingredientesMap.put("Seco_Jugoso", new String[]{
            "Pollo", "Sal", "Ajo en polvo", "Limón", "Cebolla", 
            "Pimienta negra", "Tomillo (para darle la sensación de sequedad en contraste)"
        });

        ingredientesMap.put("Seco_Agridulce", new String[]{
            "Pollo", "Salsa de soya", "Vinagre", "Ajo en polvo", "Laurel", 
            "Limón", "Pimienta negra"
        });

        ingredientesMap.put("Seco_Picante", new String[]{
            "Pollo", "Pasta de ají", "Ajo en polvo", "Comino", 
            "Pimienta negra", "Cilantro", "Sal"
        });
    }

    // Simula la decisión de ingredientes basada en contextura y sabor
    public String[] calcularIngredientes(String contextura, String sabor) {
        String clave = contextura + "_" + sabor;
        return ingredientesMap.getOrDefault(clave, new String[]{"Ingredientes no encontrados"});
    }
}
