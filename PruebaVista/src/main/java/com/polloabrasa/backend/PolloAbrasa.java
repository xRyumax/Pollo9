package com.polloabrasa.backend;

public class PolloAbrasa {
    private String crocantez;
    private String jugosidad;
    private String sabor;
    private String salinidad;
    private String acidez;

    public PolloAbrasa(double[] ingredients) {
        this.crocantez = calcularCrocantez(ingredients);
        this.jugosidad = calcularJugosidad(ingredients);
        this.sabor = calcularSabor(ingredients);
        this.salinidad = calcularSalinidad(ingredients);
        this.acidez = calcularAcidez(ingredients);
    }

    private String calcularCrocantez(double[] ingredients) {
        // Ejemplo de lógica basada en ingredientes
        if (ingredients[0] > 0.5 || ingredients[12] > 0.5) { // Ajo o Paprika
            return "Muy Crocante";
        } else if (ingredients[1] > 0.5) { // Pimienta
            return "Crocante";
        }
        return "Poco Crocante";
    }

    private String calcularJugosidad(double[] ingredients) {
        if (ingredients[4] > 0.5 || ingredients[9] > 0.5) { // Orégano o Cerveza
            return "Muy Jugoso";
        } else if (ingredients[10] > 0.5) { // Aceite de Oliva
            return "Jugoso";
        }
        return "Seco";
    }

    private String calcularSabor(double[] ingredients) {
        StringBuilder saborBuilder = new StringBuilder();
        if (ingredients[0] > 0.5) saborBuilder.append("Ajo ");
        if (ingredients[1] > 0.5) saborBuilder.append("Pimienta ");
        if (ingredients[3] > 0.5) saborBuilder.append("Comino ");
        if (ingredients[4] > 0.5) saborBuilder.append("Orégano ");
        if (ingredients[6] > 0.5) saborBuilder.append("Vinagre ");
        if (ingredients[7] > 0.5) saborBuilder.append("Limón ");
        if (ingredients[8] > 0.5) saborBuilder.append("Ají Panca ");
        if (ingredients[9] > 0.5) saborBuilder.append("Cerveza ");
        if (ingredients[10] > 0.5) saborBuilder.append("Aceite de Oliva ");
        if (ingredients[11] > 0.5) saborBuilder.append("Romero ");
        if (ingredients[12] > 0.5) saborBuilder.append("Paprika ");
        if (ingredients[13] > 0.5) saborBuilder.append("Mostaza ");
        
        String sabor = saborBuilder.toString().trim();
        return sabor.isEmpty() ? "Sabor Balanceado" : "Sabor " + sabor;
    }

    private String calcularSalinidad(double[] ingredients) {
        if (ingredients[2] > 0.7) { // Sal
            return "Salado";
        } else if (ingredients[2] > 0.3) {
            return "Medianamente Salado";
        }
        return "Sabor Balanceado";
    }

    private String calcularAcidez(double[] ingredients) {
        if (ingredients[6] > 0.6 || ingredients[7] > 0.5) { // Vinagre o Limón
            return "Ácido";
        }
        return "Poco Ácido";
    }

    public String getDescripcion() {
        return String.format(
            "<html>Pollo a la brasa con <b>%s</b>, <b>%s</b>, <b>%s</b>, <b>%s</b> y <b>%s</b>.<br/>",
            crocantez, jugosidad, sabor, salinidad, acidez
        );
    }
}
