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
Para la creación del sistema, primero decidimos centrarnos en la espicificación y elección de datos a utilizar. Creamos y especificamos un TAD que modela su funcionamiento, nuestra cripto tiene **usuarios**, **transacciones** (que involucran exactamente a dos usuarios) y **bloques** (que contienen a lo sumo 50 transacciones, cuyos identificadores están ordenados dentro del bloque). A su vez, cada bloque tiene un identificador que es un entero no negativo. Los bloques se encadenan de forma consecutiva de acuerdo al identificador.

Logramos especificar e implementar el TAD $Berretacoin con las siguientes operaciones principales:

* **agregarBloque:** Dada una secuencia de transacciones, agrega un nuevo bloque a la cadena.
* **maximosTenedores:** Devuelve los usuarios con mayor cantidad de $Berretacoin.
* **montoMedio:** Promedio de transacciones (excluyendo creación).
* **cotizacionAPesos:** Conversión de montos basada en una lista de cotizaciones por bloque.

📄 Ver [especificación semi-formal detallada](especificacionTAD-Berretacoin/especificacion-Berretacoin.pdf).

---

## 💻 Implementación en JAVA

Cuando especificamos el tipo de datos abstracto $Berretacoin, sin saberlo, estabamos siguiendo ordenes para diseñar una criptomoneda con fines maliciosos, bajo la falsa promesa de estar creando una “revolución financiera descentralizada”. Ahora, revelaremos parte de sus intenciones ocultas mientras implementamos este TAD utilizando estructuras de datos apropiadas.

Entre los requisitos específicos de los Berreteros -quienes ingeniaron la moneda-, destaca la función hackearTx, un método malicioso diseñado para extraer el valor máximo (MEV) de las transacciones, permitiendoles manipular la cadena de bloques y realizar estafas sistemáticas a los desprevenidos tenedores de la moneda. Este método es parte integral de su plan para realizar esquemas pump-and-dump donde artificialmente elevan el valor de la moneda para luego extraer las transacciones más valiosas, dejando a los inversores con pérdidas significativas.

Para cumplir con las consignas establecidas, debimos generar un codigo que cumpla las siguientes normativas:

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


### Aclaraciones

- Los usuarios se identifican con números enteros positivos consecutivos.
- A diferencia de la especificación anterior, esta vez la cantidad de transacciones por bloque no está acotada.
- Al hackear una transacción, se debe restaurar el monto de la transacción al comprador y al vendedor.
- Las complejidades asociadas a cada operación deben coincidir, o ser menores.
- Para abarcar el sistema de forma mas sencilla, intentamos encapsular y modularizar los problemas lo mas posible. Por ejemplo, para modelar la blockchain utilizamos el tipo de dato lista enlazada, el cual especificamos e implementamos como una clase de TAD previo a la utilizacion como objeto blockchain.
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
