# 🪙 Berretacoin - Implementación Optimizada de Blockchain

Este proyecto consiste en el desarrollo del backend de una criptomoneda, realizado para la materia **Algoritmos y Estructuras de Datos II (UBA)** en Julio de 2025.

**Autores:** Lila Fage, Santiago Garcia Nowak, Napoleon Saran Varela y Santos Basaldúa.

---

## 📌 Introducción y Propósito
En este proyecto desarrollamos el backend de una criptomoneda, poniendo el foco rigurosamente en la eficiencia y en cumplir restricciones de complejidad temporal estrictas.

A diferencia de una aplicación estándar, nuestro mayor desafío fue la **implementación manual de todas las estructuras de datos** (sin recurrir a las Java Collections). Esto nos permitió entender a fondo cómo optimizar el procesamiento de transacciones.

### Nuestras Competencias Clave
* **Lenguaje:** Java con principios sólidos de Diseño Orientado a Objetos.
* **Estructuras de Datos Custom:** Implementamos desde cero **Árboles AVL, Colas de Prioridad (Heaps) y Listas Enlazadas**.
* **Análisis de Algoritmos:** Optimizamos cada método para garantizar costos de Complejidad específicos ($O(1)$, $O(\log n)$).
* **Testing:** Pruebas unitarias exhaustivas para validar la integridad y cubrir casos borde.

---

## 📋 Información Preliminar

Se propuso la creación de la única criptomoneda completamente libre de controles criptográficos: la **$Berretacoin**.

### Definiciones del Dominio:
* **Usuarios:** Identificados con un número entero positivo. Hay dos tipos en cada transaccion: Un comprador (quien realiza una compra de un bien o servicio y paga con Berretacoin) y un Vendedor (quien recibe el pago y entrega el bien o servicio).
* **Transacción:** Tupla de enteros `(id transaccion, id comprador, id vendedor, monto)`.
* **Bloque:** Contiene a lo sumo 50 transacciones ordenadas por ID. Los bloques se encadenan consecutivamente.
* **Transacción de Creación:** Emite una nueva unidad de $Berretacoin (comprador ID 0). Límite de emisión: 3000 unidades.

> **Aclaración:** A partir del bloque 3000 no hay más “transacciones de creación”. Esta aplicación no utiliza algoritmos de criptografía reales.

---

## ⚙️ Especificación y Tipos de Datos

Logramos especificar e implementar el TAD $Berretacoin con las siguientes operaciones principales:

* **agregarBloque:** Dada una secuencia de transacciones, agrega un nuevo bloque a la cadena.
* **maximosTenedores:** Devuelve los usuarios con mayor cantidad de $Berretacoin.
* **montoMedio:** Promedio de transacciones (excluyendo creación).
* **cotizacionAPesos:** Conversión de montos basada en una lista de cotizaciones por bloque.

📄 Ver [especificación semi-formal detallada](especificacionTAD-Berretacoin/especificacion-Berretacoin.pdf).

---

## 💻 Implementación en JAVA

### El Contexto de los "Berreteros"
Durante el desarrollo, se reveló que la moneda fue diseñada con fines maliciosos bajo la falsa promesa de una “revolución financiera”. Entre los requisitos destaca la función `hackearTx`, un método diseñado para extraer el valor máximo (MEV) y permitir esquemas *pump-and-dump*.

### Requerimientos de Complejidad
Utilizamos las siguientes variables:
* **P:** cantidad total de usuarios.
* **nb:** cantidad de transacciones en el bloque.

| Operación | Complejidad Requerida |
| :--- | :--- |
| `nuevoBerretacoin(n)` | $O(P)$ |
| `agregarBloque(transacciones)` | $O(nb * \log P)$ |
| `txMayorValorUltimoBloque()` | $O(1)$ |
| `txUltimoBloque()` | $O(nb)$ |
| `maximoTenedor()` | $O(1)$ |
| `montoMedioUltimoBloque()` | $O(1)$ |
| `hackearTx()` | $O(\log nb + \log P)$ |

El ultimo requerimiento se baso en el cumplimiento de los test del sistema. (ver archivo [BerretacoinTests.java](Code/src/test/java/aed/BerretacoinTests.java))

---

## 🛠️ Toma de Decisiones Técnicas

Para no superar los límites de complejidad, decidimos modularizar el sistema utilizando:

1.  **Blockchain:** Una **Lista Enlazada** de bloques.
2.  **Gestión de Usuarios (Clase `Users`):**
    * Cada usuario (`User`) mantiene su ID y monto actualizado.
    * Utilizamos un **Heap (Max-Heap)** para almacenar los usuarios, permitiendo obtener al máximo tenedor en $O(1)$.
    * **Array de Handles:** Un arreglo donde cada índice corresponde al ID del usuario y contiene una referencia directa a su posición en el Heap. Esto permite actualizar saldos y re-ordenar el Heap en $O(\log P)$ tras cada transacción.
3. **Transacciones**
   * Cada Transacción tiene 4 atributos: su id propio, para poder identificarla; El id del comprador y el id del vendedor de la transacción, junto con el monto de esta como ultimo atributo.
   * Decidimos que dos transacciones sean comparables, siguiendo el criterio de comparacion mediante los montos de estas (una transferencia X es mayor que una transferencia Y si y solo si el atributo monto de X es mayor que el de Y).
4. **Bloque**
5.  **Estructuras propias:** Solo se utilizaron estructuras implementadas por nosotros:
    * Arreglos redimensionables, Listas enlazadas, AVL y Heaps.


---

## 📂 Archivos del Proyecto
* **Código Fuente:** Implementación de estructuras y lógica de negocio.
* **Tests:** [BerretacoinTests.java](Code/src/test/java/aed/BerretacoinTests.java) (Validación de lógica y performance).
