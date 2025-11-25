package org.example;

import java.util.concurrent.Semaphore;

/**
 * Clase Filosofo que implementa Runnable para ejecutarse como un hilo.
 * Representa a un filósofo sentado en una mesa redonda con dos palillos:
 * uno a su izquierda y otro a su derecha.
 */
public class Filosofo implements Runnable {

    private int id;                 // Identificador único del filósofo
    private String nombre;          // Nombre del filósofo (solo para mostrar en pantalla)
    private Semaphore[] palillos;   // Array de semáforos que representan los palillos
    private int numFilosofos;       // Número total de filósofos en la mesa

    /**
     * Constructor para inicializar el filósofo con su ID, nombre y acceso a los palillos.
     */
    public Filosofo(int id, String nombre, Semaphore[] palillos) {
        this.id = id;
        this.nombre = nombre;
        this.palillos = palillos;
        this.numFilosofos = palillos.length;
    }

    /**
     * Método principal del hilo: ciclo infinito pensar → coger palillos → comer → soltar palillos.
     */
    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                cogerPalillos();
                comer();
                soltarPalillos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simula el tiempo en el que el filósofo está pensando.
     */
    private void pensar() throws InterruptedException {
        System.out.println(nombre + " está pensando.");
        Thread.sleep((int) (Math.random() * 2000) + 500); // Pausa aleatoria
    }

    /**
     * Intenta coger los dos palillos necesarios para comer.
     * Se usa una estrategia para evitar interbloqueo:
     *  - Filósofos pares cogen primero el palillo izquierdo.
     *  - Filósofos impares cogen primero el palillo derecho.
     */
    private void cogerPalillos() throws InterruptedException {
        System.out.println(nombre + " está hambriento.");

        int palilloIzq = id;                           // Palillo de la izquierda
        int palilloDer = (id + 1) % numFilosofos;      // Palillo de la derecha

        // Control de orden para evitar deadlocks
        if (id % 2 == 0) {
            // Primero palillo izquierdo
            palillos[palilloIzq].acquire();
            System.out.println(nombre + " ha cogido el palillo izquierdo (" + palilloIzq + ").");

            // Luego palillo derecho
            palillos[palilloDer].acquire();
            System.out.println(nombre + " ha cogido el palillo derecho (" + palilloDer + ").");
        } else {
            // Primero palillo derecho
            palillos[palilloDer].acquire();
            System.out.println(nombre + " ha cogido el palillo derecho (" + palilloDer + ").");

            // Luego palillo izquierdo
            palillos[palilloIzq].acquire();
            System.out.println(nombre + " ha cogido el palillo izquierdo (" + palilloIzq + ").");
        }

        System.out.println(nombre + " ahora tiene los dos palillos ("
                + palilloIzq + ", " + palilloDer + ") y puede comer.");
    }

    /**
     * Simula que el filósofo está comiendo.
     */
    private void comer() throws InterruptedException {
        int palilloIzq = id;
        int palilloDer = (id + 1) % numFilosofos;

        System.out.println(nombre + " está comiendo con los palillos "
                + palilloIzq + " y " + palilloDer + ".");
        Thread.sleep((int) (Math.random() * 2000) + 500); // Tiempo aleatorio de comida
    }

    /**
     * Suelta ambos palillos para que otros filósofos puedan usarlos.
     */
    private void soltarPalillos() {
        int palilloIzq = id;
        int palilloDer = (id + 1) % numFilosofos;

        // Liberar semáforos
        palillos[palilloIzq].release();
        palillos[palilloDer].release();

        System.out.println(nombre + " ha terminado de comer y ha soltado los palillos "
                + palilloIzq + " y " + palilloDer + ".\n");
    }
}
