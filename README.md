# 📘 El Problema de la Cena de los Filósofos  
Proyecto en Java utilizando Semáforos

---

# 1. Portada

**Título del Proyecto:**  
📌 *El Problema de la Cena de los Filósofos*

**Asignatura:** Diseño de Interfaces Web – Tema 7-8  
**Alumno:** (tu nombre)  
**Fecha:** (fecha de entrega)

---

# 2. Introducción

El problema de la **Cena de los Filósofos** es un clásico en la programación concurrente. Describe cómo cinco filósofos, sentados alrededor de una mesa circular, alternan entre *pensar* y *comer*.  

Para comer, cada filósofo necesita dos palillos: el de su izquierda y el de su derecha. Los palillos son recursos compartidos, por lo que la sincronización es esencial para evitar condiciones de carrera, interbloqueos o inanición.

### 🎯 Objetivo de la implementación
Implementar en Java una solución basada en **semáforos** que garantice:

- Exclusión mutua en el uso de los palillos  
- Evitar interbloqueo  
- Evitar inanición  
- Mostrar claramente los estados de cada filósofo  

---

# 3. Análisis del Problema

## 🧩 Componentes del sistema

### ✔ Filósofos
Representados como **hilos independientes** (`Runnable`).  
Cada filósofo alterna entre los estados:
- Pensar  
- Tener hambre  
- Comer  

### ✔ Palillos
Cada palillo es un **recurso compartido** entre dos filósofos, modelado como un:
```java
Semaphore palillo = new Semaphore(1, true);
```
El permiso único garantiza **exclusión mutua**.

## ⚠️ Desafíos de concurrencia

- **Interbloqueo (deadlock):**  
  Todos los filósofos toman un palillo y esperan el otro sin liberarlo jamás.

- **Inanición (starvation):**  
  Un filósofo podría no conseguir nunca los palillos si otros ocupan constantemente la zona crítica.

- **Condiciones de carrera:**  
  Dos filósofos podrían intentar tomar simultáneamente el mismo palillo.

---

# 4. Diseño de la Solución

## 📐 Diagrama de clases (descripción)

- **Principal.java**
  - Crea los semáforos (palillos)
  - Crea el semáforo mayordomo
  - Inicia los hilos filósofos  

- **Filosofo.java** (implements Runnable)  
  - Atributos: id, nombre, palillos[], mayordomo  
  - Métodos: run(), pensar(), intentarComer(), comer()

## 🛑 Uso de Semáforos

### ✔ Palillos
Cada palillo es un semáforo que permite ser tomado por un único filósofo.

### ✔ Mayordomo
Para evitar interbloqueo se usa:
```java
Semaphore mayordomo = new Semaphore(4, true);
```
Donde solo **4 filósofos** pueden intentar comer simultáneamente (N−1).

### ✔ Orden fijo de adquisición
Cada filósofo toma los palillos en orden:

```java
primero = min(izq, der)
segundo = max(izq, der)
```

Esto rompe la espera circular → evita deadlock.

---

# 5. Implementación

## 🔄 Método run()
El filósofo alterna indefinidamente entre pensar e intentar comer:

```java
while (true) {
    pensar();
    intentarComer();
    comer();
}
```

## 🥢 Sincronización con semáforos

### ✔ Para comer:
1. Solicita permiso al mayordomo  
2. Intenta adquirir ambos palillos  
3. Entra en la zona crítica y come  
4. Libera palillos  
5. Libera el permiso del mayordomo  

## 📝 Comentarios clave sobre la implementación

- El mayordomo evita interbloqueos globales  
- El orden fijo evita la espera circular  
- Los semáforos en modo justo evitan inanición  
- Los mensajes impresos permiten observar el comportamiento del sistema

---

# 6. Prevención de Interbloqueo e Inanición

## 🚫 Evitar Interbloqueo
- Concediendo acceso solo a N−1 filósofos simultáneamente  
- Adquiriendo palillos siempre en el mismo orden  

## 🍽️ Evitar Inanición
- Uso de semáforos en modo **fair**  
- Los filósofos liberan rápidamente los palillos tras comer  
- Ningún filósofo se queda esperando permanentemente  

---

# 7. Resultados de la Ejecución

Durante la ejecución, el sistema muestra mensajes como:

```
Sócrates está pensando.
Platón está hambriento.
Platón está comiendo.
Nietzsche está hambriento.
Nietzsche está comiendo.
Nietzsche ha terminado de comer, palillos libres: 4, 0
```

### ✔ Análisis
- Ningún filósofo queda esperando indefinidamente  
- No se observan bloqueos globales  
- Todos alternan correctamente entre pensar/comer  

(En el documento final se pueden incluir capturas de pantalla reales.)

---

# 8. Conclusiones

## 📚 Lecciones Aprendidas
- Los semáforos permiten gestionar acceso exclusivo a recursos compartidos  
- La concurrencia puede producir deadlocks si no se diseña correctamente  
- El uso del patrón N−1 y adoptando un orden fijo evita interbloqueo  
- La equidad de los semáforos reduce la inanición  

## 🔧 Posibles Mejoras
- Añadir estadísticas del tiempo que cada filósofo pasa pensando o esperando  
- Implementar versiones alternativas del problema  
- Añadir interfaces gráficas para visualizar la cena  

---

✍ **Fin del documento – README.md**
