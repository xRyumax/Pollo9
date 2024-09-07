package Perceptron;

public class PerceptronMenu {
    private int idPerceptron;
    private String[] menuCalor;
    private String[] menuFrio;
    private double[] variable;
    private double[] peso;
    private String presupuesto;

    public PerceptronMenu() {
    }

    public PerceptronMenu(int idPerceptron, String[] menuCalor, String[] menuFrio, double[] variable, double[] peso) {
        this.idPerceptron = idPerceptron;
        this.menuCalor = menuCalor;
        this.menuFrio = menuFrio;
        this.variable = variable;
        this.peso = peso;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    
    
    public int getIdPerceptron() {
        return idPerceptron;
    }

    public void setIdPerceptron(int idPerceptron) {
        this.idPerceptron = idPerceptron;
    }

    public String[] getMenuCalor() {
        return menuCalor;
    }

    public void setMenuCalor(String[] menuCalor) {
        this.menuCalor = menuCalor;
    }

    public String[] getMenuFrio() {
        return menuFrio;
    }

    public void setMenuFrio(String[] menuFrio) {
        this.menuFrio = menuFrio;
    }

    public double[] getVariable() {
        return variable;
    }

    public void setVariable(double[] variable) {
        this.variable = variable;
    }

    public double[] getPeso() {
        return peso;
    }

    public void setPeso(double[] peso) {
        this.peso = peso;
    }

    // Método para calcular la salida del perceptrón
    public int calcularSalidaPerceptron() {
        double suma = 0;
        for (int i = 0; i < variable.length; i++) {
            suma += variable[i] * peso[i];
        }
        return suma >= 0.5 ? 1 : 0; // Umbral simple
    }

    public String[] obtenerMenuSegunClima(double temperatura) {
        if (temperatura >= 20) {
            return menuCalor;
        } else {
            return menuFrio;
        }
    }
}


