# 🧠🍝 La Cena de los Filósofos — Proyecto de Concurrencia en Java

---

# 2. Introducción

## 📌 Descripción breve del problema

La **Cena de los Filósofos** es un problema clásico de la computación concurrente propuesto por Edsger Dijkstra.  
En él, cinco filósofos se sientan alrededor de una mesa circular. Cada uno alterna entre **pensar** y **comer**, pero para comer necesita dos palillos (recursos compartidos): el que está a su izquierda y el que está a su derecha.

Este problema es fundamental para estudiar:
- 🔒 **Exclusión mutua**  
- 🔄 **Sincronización entre procesos/hilos**  
- 🚫 **Interbloqueo (deadlock)**  
- 🍽️ **Inanición (starvation)**  
- 🧠 **Diseño de algoritmos concurrentes seguros**

---

## 🎯 Objetivo de la implementación

El objetivo principal es resolver este problema aplicando **semáforos en Java**, garantizando:
- Que solo un filósofo use un palillo a la vez.  
- Evitar que los filósofos se queden bloqueados permanentemente (deadlock).  
- Evitar que alguno nunca pueda comer (starvation).  

---

# 3. Análisis del Problema

## 🔧 Descripción de los componentes

### 👨‍🏫 Filósofos
- Se representan como **hilos** independientes (`Thread`).
- Ciclo continuo: *pensar → intentar comer → comer → soltar palillos → pensar*.

### 🍴 Palillos
- Son **recursos compartidos** entre dos filósofos.
- Cada palillo se implementa como un `Semaphore(1)`.

---

## ⚠️ Desafíos de concurrencia

### 🔁 Interbloqueo (deadlock)
Ocurre cuando:
- Todos los filósofos cogen un palillo.
- Y esperan eternamente a que el otro se libere.

### 🍽️ Inanición (starvation)
Un filósofo podría:
- No conseguir nunca ambos palillos debido a los demás.

Ambos problemas deben evitarse con un diseño cuidadoso.

---

# 4. Diseño de la Solución

## 🧩 Diagrama de clases



### 📐 Estructura general

- **Main**  
  Inicia la mesa y los hilos.

- **Mesa**  
  Crea palillos y filósofos.

- **Filosofo**  
  Implementa la lógica de sincronización usando semáforos.

---

## 🚦 Explicación de los Semáforos

Cada palillo es un:

```java
Semaphore palillo = new Semaphore(1);
```

Esto garantiza:
- Solo un filósofo puede usarlo.
- Si está ocupado, el filósofo espera.

Los filósofos adquieren sus palillos según su posición en la mesa.

---

# 5. Implementación

## 🧠 Método `run` del filósofo

El ciclo del hilo:

```java
public void run() {
    while (true) {
        pensar();
        cogerPalillos();
        comer();
        soltarPalillos();
    }
}
```

### 🔹 Estados:
- Pensando → no usa recursos.
- Hambriento → intenta adquirir 2 semáforos.
- Comiendo → posee 2 semáforos.
- Suelta palillos → libera recursos.

---

## 🛠️ Sincronización con Semáforos

Para evitar deadlock:
- Filósofos **pares** cogen primero el palillo izquierdo.
- Filósofos **impares** cogen primero el palillo derecho.

Esto rompe la simetría del problema clásico.

---

## 💬 Comentarios sobre la implementación

- Se usan `Thread.sleep()` para simular tiempo real.
- Se imprime cada acción para poder depurar visualmente.
- La estrategia del **orden alternado** garantiza que nunca se bloqueen entre sí de forma circular.

---

# 6. Prevención de Interbloqueo e Inanición

### 🛑 Prevención del interbloqueo
- La clave es el **orden de adquisición**:
  - Par: izquierda → derecha  
  - Impar: derecha → izquierda  

Esto elimina la posibilidad de un ciclo de espera circular.

### 🍽️ Prevención de inanición
- Los semáforos FIFO de Java gestionan correctamente las colas.
- Cada filósofo eventualmente logra adquirir ambos palillos.
- Además, al comer y soltar rápidamente, no se monopolizan recursos.

---

# 7. Resultados de la Ejecución

### 📸 Salida del programa *(GIF recomendado)*

![gif](gif.gif)


### 📊 Análisis de la salida

La salida muestra:
- Filósofos alternando entre pensar y comer.
- Aquellos que comparten palillo **no comen simultáneamente**.
- No se observa bloqueo total del sistema.
- No hay un filósofo que quede hambriento de forma indefinida.

Esto confirma que la solución funciona correctamente.

---

# 8. Conclusiones

## 📝 Lecciones Aprendidas

Durante este proyecto se aprendió:

- Cómo modelar hilos en Java mediante `Runnable`.
- Cómo usar `Semaphore` para controlar recursos compartidos.
- La importancia del orden al adquirir recursos para evitar deadlock.
- Cómo analizar un sistema concurrente basado en interacción circular.
- Buenas prácticas en programación concurrente.

---


✨ *Este README acompaña la implementación del ejercicio “La Cena de los Filósofos”.*  
✨ *Diseñado para ser claro, visual y completo para una entrega académica.*
