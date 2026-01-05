# 🪙 Berretacoin - Implementación Optimizada de Blockchain

Este proyecto consiste en el desarrollo del backend de una criptomoneda, realizado para la materia **Algoritmos y Estructuras de Datos II (UBA)** en Julio de 2025. El foco principal es la **eficiencia algorítmica** y la gestión rigurosa de estructuras de datos.

**Autores:** Lila Fage, Santiago Garcia Nowak, Napoleon Saran Varela y Santos Basaldúa.

![Java](https://img.shields.io/badge/Language-Java-orange)
![Complexity](https://img.shields.io/badge/Focus-Algorithmic%20Complexity-blue)
![Data Structures](https://img.shields.io/badge/Structures-Custom%20Implementation-green)

---

## 🚀 Desafío Técnico
A diferencia de una aplicación estándar, nuestro mayor desafío fue la **implementación manual de todas las estructuras de datos** (sin recurrir a las *Java Collections*). Esto nos permitió optimizar el procesamiento de transacciones bajo restricciones de complejidad temporal estrictas.

### Competencias Clave
* **Estructuras de Datos Custom:** Implementamos desde cero **Árboles AVL, Colas de Prioridad (Heaps) y Listas Enlazadas**.
* **Análisis de Algoritmos:** Cada método fue diseñado para garantizar costos de complejidad específicos ($O(1)$, $O(\log n)$).
* **Gestión de Memoria:** Control exhaustivo de punteros para evitar problemas de *aliasing* al manipular bloques y usuarios.

---

## 📝 Especificación y Funcionamiento

Berretacoin modela una red donde los usuarios (IDs enteros) realizan transacciones agrupadas en bloques. Aunque la estructura emula una blockchain, esta aplicación **no utiliza algoritmos criptográficos**.

### Conceptos Principales:
* **Transacción:** Tupla de enteros `(id, comprador, vendedor, monto)`.
* **Bloque:** Contiene hasta 50 transacciones ordenadas.
* **Transacción de creación:** Emite nuevas unidades de $Berretacoin (hasta 3000 unidades).

### Operaciones e Implementación:
| Operación | Complejidad | Descripción |
| :--- | :--- | :--- |
| `nuevoBerretacoin` | $O(P)$ | Inicializa el sistema con $P$ usuarios. |
| `agregarBloque` | $O(nb \cdot \log P)$ | Agrega un bloque con $nb$ transacciones. |
| `txMayorValorUltimoBloque` | $O(1)$ | Retorna la transacción más valiosa del último bloque. |
| `maximoTenedor` | $O(1)$ | Retorna el usuario con mayor saldo (vía Heap). |
| `hackearTx` | $O(\log nb + \log P)$ | Extrae la transacción de mayor monto del último bloque. |

---

## 🛠️ Toma de Decisiones y Arquitectura

Para cumplir con las complejidades exigidas, modularizamos el sistema bajo el principio de *Divide and Conquer*:

1.  **Blockchain:** Implementada como una **Lista Enlazada** de bloques para permitir un encadenamiento eficiente.
2.  **Gestión de Usuarios (Clase `Users`):** * Utilizamos un **Heap (Cola de Prioridad)** para almacenar objetos `User`. Esto permite que la consulta del `maximoTenedor` sea $O(1)$.
    * Implementamos un **Array de Handles** (referencias directas al Heap). Cada índice del array corresponde al ID de un usuario, permitiendo localizar y actualizar saldos en el Heap de forma práctica y eficiente.
3.  **Encapsulamiento:** Definimos subclases para modelar la relación entre el balance y el ID, asegurando que los atributos privados solo se modifiquen mediante métodos validados.

---

## 📂 Documentación Adicional
* 📄 [Especificación Semi-formal (PDF)](especificacionTAD-Berretacoin/especificacion-Berretacoin.pdf)
* 🧪 [Suite de Tests Unitarios](Code/src/test/java/aed/BerretacoinTests.java)

---
*Este proyecto fue desarrollado con fines académicos para la Universidad de Buenos Aires.*
