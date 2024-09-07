package pgm.logicasp_pgm;


import java.util.*;
import java.util.concurrent.*;

public class Plato {
    static final Map<String, List<String>> REQUISITOS = new HashMap<>();
    static final Map<String, String> RECETAS = new HashMap<>();
    static final Map<String, Integer> PRECIOS_BASE = new HashMap<>();

    static {
        // Recetas actualizadas para tipos de preparación
        REQUISITOS.put("parrilla", Arrays.asList("Panquita", "Vinagre Blanco Sibarita", "Ajo en polvo", "Orégano Molido", "Pimienta Sibarita", "Comino Sibarita", "Aceite Vegetal", "Sal"));
        REQUISITOS.put("brasa", Arrays.asList("Sal", "Comino", "Romero en Polvo", "Canela China", "Pimienta", "Ajo picado", "Cerveza Rubia", "Leche", "Mostaza", "Limón", "Aceite Girasol", "Ají amarillo", "Orégano Seco", "Hojas de Huacatay", "Leche Evaporada", "Aceite Vegetal"));
        REQUISITOS.put("horno", Arrays.asList("Mantequilla", "Ajo picado", "Ají Panca", "Mostaza", "Salsa de Soya", "Orégano Seco", "Romero Seco", "Comino"));
        REQUISITOS.put("broaster", Arrays.asList("Comino en polvo", "Ajo en polvo", "Ají en polvo", "Vinagre de vino", "Harina de trigo", "Maicena", "Aceite"));

        RECETAS.put("parrilla", "Receta para Pollo a la Parrilla: Mezcla todos los ingredientes y marina el pollo. Precalienta la parrilla y cocina el pollo por 10-15 minutos.");
        RECETAS.put("brasa", "Receta para Pollo a la Brasa: Mezcla los ingredientes y marina el pollo por al menos 2 horas. Cocina en un asador durante 45-60 minutos.");
        RECETAS.put("horno", "Receta para Pollo al Horno: Mezcla los ingredientes y cubre el pollo. Hornea a 180°C por 30-40 minutos.");
        RECETAS.put("broaster", "Receta para Pollo Broaster: Marina el pollo con los ingredientes y rebózalo en harina. Fríe en aceite caliente hasta dorar.");

        // Precios base para cada tipo de preparación
        PRECIOS_BASE.put("parrilla", 20);
        PRECIOS_BASE.put("brasa", 25);
        PRECIOS_BASE.put("horno", 18);
        PRECIOS_BASE.put("broaster", 22);
    }

    private Perceptron perceptron;
    private String tipoPreparacion;
    private String sabor;
    private String contextura;
    private double cantidad;
    private int numeroPollos;
    private Scanner scanner;

    public Plato() {
        scanner = new Scanner(System.in);
        perceptron = new Perceptron();
    }

    public Plato(Perceptron perceptron, String tipoPreparacion, String sabor, String contextura, double cantidad, int numeroPollos, Scanner scanner) {
        this.perceptron = perceptron;
        this.tipoPreparacion = tipoPreparacion;
        this.sabor = sabor;
        this.contextura = contextura;
        this.cantidad = cantidad;
        this.numeroPollos = numeroPollos;
        this.scanner = scanner;
    }

    public Perceptron getPerceptron() {
        return perceptron;
    }

    public void setPerceptron(Perceptron perceptron) {
        this.perceptron = perceptron;
    }

    public String getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(String tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getContextura() {
        return contextura;
    }

    public void setContextura(String contextura) {
        this.contextura = contextura;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumeroPollos() {
        return numeroPollos;
    }

    public void setNumeroPollos(int numeroPollos) {
        this.numeroPollos = numeroPollos;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void prepararPlato() {
        elegirPreparacion();
        elegirSabor();
        elegirContextura();
        elegirCantidad();
        elegirNumeroDePollos();

        int tiempoCoccion = perceptron.calcularTiempoCoccion(tipoPreparacion, sabor, contextura, cantidad, numeroPollos);
        cocinarPollo(tiempoCoccion);
        mostrarIngredientes();
        mostrarPrecio();
    }

    private void elegirPreparacion() {
        String[] preparaciones = {"parrilla", "brasa", "horno", "broaster"};
        for (int i = 0; i < preparaciones.length; i++) {
            System.out.println((i + 1) + ". " + preparaciones[i]);
        }
        int eleccion = leerEntero();
        tipoPreparacion = (eleccion > 0 && eleccion <= preparaciones.length) ? preparaciones[eleccion - 1] : "parrilla";
    }

    private void elegirSabor() {
        String[] sabores = {"agridulce", "picante", "dulce"};
        for (int i = 0; i < sabores.length; i++) {
            System.out.println((i + 1) + ". " + sabores[i]);
        }
        int eleccion = leerEntero();
        sabor = (eleccion > 0 && eleccion <= sabores.length) ? sabores[eleccion - 1] : "agridulce";
    }

    private void elegirContextura() {
        String[] contexturas = {"jugoso", "seco", "medio"};
        for (int i = 0; i < contexturas.length; i++) {
            System.out.println((i + 1) + ". " + contexturas[i]);
        }
        int eleccion = leerEntero();
        contextura = (eleccion > 0 && eleccion <= contexturas.length) ? contexturas[eleccion - 1] : "jugoso";
    }

    private void elegirCantidad() {
        String[] cantidades = {"1/8", "1/4", "1/2", "1"};
        double[] valores = {0.125, 0.25, 0.5, 1.0};
        for (int i = 0; i < cantidades.length; i++) {
            System.out.println((i + 1) + ". " + cantidades[i]);
        }
        int eleccion = leerEntero();
        cantidad = (eleccion > 0 && eleccion <= valores.length) ? valores[eleccion - 1] : 1.0;
        System.out.println("Cantidad seleccionada: " + cantidades[eleccion - 1]);
    }

    private void elegirNumeroDePollos() {
        numeroPollos = leerEntero();
        numeroPollos = numeroPollos > 0 ? numeroPollos : 1; // Asegura que al menos sea 1
    }

    private void mostrarIngredientes() {
        List<String> ingredientes = REQUISITOS.get(tipoPreparacion);
        System.out.println("Ingredientes necesarios:");
        for (String ingrediente : ingredientes) {
            System.out.println("- " + ingrediente);
        }
    }

    private void mostrarPrecio() {
        int precioBase = PRECIOS_BASE.getOrDefault(tipoPreparacion, 20);
        double precioFinal = precioBase * cantidad * numeroPollos;
        System.out.println("Precio estimado: S/." + precioFinal);
    }

    private void cocinarPollo(int tiempoCoccion) {
        System.out.println("Cocinando el pollo...");
        try {
            TimeUnit.SECONDS.sleep(tiempoCoccion); // Simulación del tiempo en segundos
            System.out.println("El pollo está listo.");
        } catch (InterruptedException e) {
            System.out.println("La cocción fue interrumpida.");
        }
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Método para calcular el precio del plato
    public double calcularPrecio() {
        int precioBase = PRECIOS_BASE.getOrDefault(tipoPreparacion, 20);
        return precioBase * cantidad * numeroPollos;
    }
}