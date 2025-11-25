package org.example;

import java.util.concurrent.Semaphore;

/**
 * Clase Mesa:
 * Representa la mesa redonda donde se sientan los filósofos.
 * Contiene:
 *  - Un array de palillos (semaforos)
 *  - Un array de filósofos
 * La mesa se encarga únicamente de CREAR los recursos y los filósofos.
 * No ejecuta nada (el Main es quien los inicia).
 */
public class Mesa {

    private int numFilosofos;       // Número total de filósofos en la mesa
    private Semaphore[] palillos;   // Array de palillos (1 semáforo = 1 palillo)
    private Filosofo[] filosofos;   // Array que almacena a todos los filósofos

    /**
     * Constructor que recibe la cantidad de filósofos y sus nombres.
     * Aquí se inicializan los palillos y se crean los objetos Filosofo.
     */
    public Mesa(int numFilosofos, String[] nombres) {

        this.numFilosofos = numFilosofos;


        // Crear los palillos (semaforos)

        palillos = new Semaphore[numFilosofos];

        // Cada palillo es un semáforo con 1 permiso → solo 1 filósofo lo puede usar
        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Semaphore(1);
        }


        // Crear los filósofos

        filosofos = new Filosofo[numFilosofos];

        // Se asigna a cada filósofo:
        //  - un ID (posición en la mesa)
        //  - un nombre
        //  - el array de palillos (para que coja el izquierdo y derecho según su ID)
        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, nombres[i], palillos);
        }
    }

    /**
     * Devuelve el array completo de filósofos.
     * El main usará este método para iniciar los hilos.
     */
    public Filosofo[] getFilosofos() {
        return filosofos;
    }
}
