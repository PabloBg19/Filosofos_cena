# 🧠🍝 <span style="color:#4CAF50;">El Problema de la Cena de los Filósofos</span>

Proyecto de Programación Multihilo en <span style="color:#2196F3;">Java</span>  
Uso de <code>Semaphore</code> para sincronización entre hilos.

---

## <span style="color:#FF9800;">1. Descripción General</span>

### 🎯 <span style="color:#E91E63;">Objetivo del ejercicio</span>

- Implementar el problema clásico de la **Cena de los Filósofos** utilizando <code>Semaphore</code>.
- Garantizar:
  - 🔒 <span style="color:#9C27B0;">Exclusión mutua</span> sobre los palillos.
  - 🚫 <span style="color:#F44336;">Prevención de deadlock</span>.
  - 🍽️ <span style="color:#795548;">Prevención de inanición</span>.
- Mostrar mensajes por consola indicando las acciones de los filósofos.

---

## <span style="color:#3F51B5;">2. Estructura del Proyecto</span>

```
org.example
├── Filosofo.java   // Lógica del hilo-filósofo
├── Mesa.java       // Monta los palillos y crea a los filósofos
└── Main.java       // Inicia el programa
```

---

## <span style="color:#009688;">3. Clases del Proyecto</span>

### 🔹 <span style="color:#8BC34A;">Filosofo.java</span>
Controla el comportamiento del filósofo como hilo independiente.

---

### 🔹 <span style="color:#CDDC39;">Mesa.java</span>
Crea:
- Array de <code>Semaphore</code> → palillos  
- Array de <code>Filosofo</code> → hilos

---

### 🔹 <span style="color:#FF5722;">Main.java</span>
Punto de entrada. Inicia todos los hilos.

---

## <span style="color:#9C27B0;">4. Estrategia Contra el Deadlock</span>

Para evitar el interbloqueo clásico:

- 👨‍🔬 Filósofos <span style="color:#4CAF50;">pares</span>:  
  Cogen primero el palillo **izquierdo**.
- 👨‍🏫 Filósofos <span style="color:#F44336;">impares</span>:  
  Cogen primero el palillo **derecho**.

Esto rompe la simetría y evita que todos bloqueen a todos.

---

## <span style="color:#00BCD4;">5. Capturas de Pantalla</span>

### 📸 Ejecución del programa
```
[ESPACIO PARA CAPTURA 1]
```

### 📸 Filósofos cogiendo palillos
```
[ESPACIO PARA CAPTURA 2]
```

### 📸 Filósofos comiendo
```
[ESPACIO PARA CAPTURA 3]
```

---

## <span style="color:#FF9800;">6. Cómo Ejecutar</span>

Compila:
```
javac org/example/*.java
```

Ejecuta:
```
java org.example.Main
```

---

## <span style="color:#4CAF50;">7. Mejoras Futuras</span>

- Interfaz gráfica en JavaFX 🎨  
- Límite de comidas por filósofo 🍽️  
- Estadísticas de rendimiento 📊

---

## <span style="color:#E91E63;">8. Autor</span>

Proyecto educativo basado en el problema clásico de sincronización:  
<strong><span style="color:#3F51B5;">La Cena de los Filósofos</span></strong>.
