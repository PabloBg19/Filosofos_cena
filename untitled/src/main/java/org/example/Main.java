package org.example;

/**
 * Clase Main: punto de entrada del programa.
 * Aquí se crea la mesa, los filósofos y se inician los hilos.
 */
public class Main {

    public static void main(String[] args) {

        // Lista de nombres para identificar a cada filósofo
        String[] nombres = {
                "Sócrates",
                "Platón",
                "Aristóteles",
                "Descartes",
                "Nietzsche"
        };

        // Crear una mesa con tantos filósofos como nombres haya
        // La mesa se encarga de crear los filósofos y sus palillos
        Mesa mesa = new Mesa(nombres.length, nombres);

        // Iniciar los hilos de los filósofos
        for (Filosofo f : mesa.getFilosofos()) {
            new Thread(f).start();   // Cada filósofo corre en su propio hilo
        }

        // Mensaje informativo
        System.out.println("\n== Mesa iniciada. Los filósofos están pensando ==\n");
    }
}
