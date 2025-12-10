# Proyecto-Berretacoin
Este proyecto fue realizado por Lila Fage, Santiago Garcia Nowak, Napoleon Saran Varela y Santos Basaldua.
Se realizo como entrega para la materia "Algoritmos y Estructuras de Datos II" en Julio del año 2025, para la Universidad de Buenos Aires.

El proyecto se enfoca en realizar buenas practicas para modelado de objetos, tomando las precauciones necesarias, al igual que justificar formalmente la eleccion de estructuras de datos y complejidad de cada función. Se priorizó la correcta elección de:
* **Estructuras:** (Ej: Listas Enlazadas para encadenar bloques, Heaps para ordenar transacciones, etc.).
* **Algoritmos:** (Ej: Algoritmos de ordenamiento para heap, Algortimos que eviten aliasing en una lista enlazada, etc.)

Aunque las ideas se acercan a la estructura de una blockchain, esta aplicación no utiliza ningun algoritmo de criptografía.


## Tabla de Contenidos
1. [Información preliminar](#información-preliminar)
2. [Especificación y tipos de Datos](#Especificación-y-tipos-de-Datos)
3. [Implementación en JAVA](#Implementación-en-JAVA)
4. [Licencia](#licencia)


## Información Preliminar:

Se propuso la creacion de la unica criptomoneda completamente libre de controles criptograficos: la **$Berretacoin**.
Nuestra criptomoneda tiene usuarios que identificamos con un numero entero positivo. Cada transaccion involucra a dos usuarios:
1. **Comprador**: Es quien adquiere un bien (o servicio) pagando con la criptomoneda
2. **Vendedor**: Es quien recibe el pago y entrega el bien (o servicio).
 
 Además, existen algunos objetos importantes para definir:
* **Transacción**: Cada una de estas tiene un **monto** asociado representado con un número entero positivo y un **identificador** dentro del bloque, representado con un entero no negativo. Vamos a representar este objeto como una tupla de enteros: (id transaccion, id comprador, id vendedor, monto). Las transacciones se agrupan en **bloques** simulando una blockchain.
* **Bloque**: Cada bloque contiene a lo sumo 50 transacciones cuyos identificadores están ordenados dentro del bloque. La primera transacción de cada bloque “crea” una nueva unidad de $Berretacoin hasta alcanzar el límite de emisión, que está fijado en 3000 unidades. Cada bloque tiene un identificador que es un entero no negativo. Los bloques se encadenan de forma consecutiva, siguiendo la idea de una Blockchain.
* **Transacción de creación**: A estas transacciones especiales las denominamos “transacciones de creación” porque son las que emiten una nueva unidad de $Berretacoin, y tienen la característica de que el comprador tiene identificador 0 y el vendedor es algún usuario arbitrario siempre distinto.


*Aclaración: a partir del bloque 3000 no hay más “transacciones de creación”*



## Especificación y tipos de Datos

Para la creación del sistema, primero decidimos centrarnos en la espicificación y elección de datos a utilizar. Creamos y especificamos un TAD que modela su funcionamiento, nuestra cripto tiene ** usuarios ** (que identificamos con un número entero positivo), **transacciones** (que involucran exactamente a dos usuarios) y **bloques** (que contienen a lo sumo 50 transacciones, cuyos identificadores están ordenados dentro del bloque). A su vez, cada bloque tiene un identificador que es un entero no negativo. Los bloques se encadenan de forma consecutiva de acuerdo al identificador.

Logramos especificar el TAD $Berretacoin con las siguientes operaciones:

* agregarBloque: dada una secuencia de transacciones agrega un nuevo bloque con esas transacciones a la cadena de bloques.
* maximosTenedores: devuelve una lista que contiene al o los usuarios que tienen la mayor cantidad de $Berretacoin.
* montoMedio: devuelve el monto promedio de todas las transacciones sin considerar las “transacciones de creación”.
* cotizacionAPesos: dada una lista de cotizaciones (números enteros positivos, que está en correspondencia 1 a 1 con la cantidad de bloques) que indica a cu´antos pesos equivale un $Berretacoin, se pide devolver otra lista de la misma longitud que indique el monto de pesos que se oper´o en el bloque correspondiente. Por ejemplo, si dentro del i-´esimo
bloque la suma de los montos de las transacciones es 1000 y la i-´esima cotizaci´on es 2 (1 $Berretacoin equivale a 2 pesos), hay que devolver 2000 en la posici´on i de la lista resultante.

Observaciones importantes:
En una transacción los ids del comprador y del vendedor son distintos.
No vamos a modelar ningún aspecto criptográfico (ni el Nonce, ni los hashes ni las claves).

Observar [especificación semi-formal](ruta/al/archivo.pdf) para la especificación detallada.


## Implementación en JAVA

### Introducción

Cuando especificamos el tipo de datos abstracto $Berretacoin, sin saberlo, estabamos siguiendo ordenes para diseñar una criptomoneda con fines maliciosos, bajo la falsa promesa de estar creando una “revolución financiera descentralizada”. Ahora, revelaremos parte de sus intenciones ocultas mientras implementamos este TAD utilizando estructuras de datos apropiadas.

Entre los requisitos específicos de los Berreteros -*quienes ingeniaron la moneda*-, destaca la función hackearTx, un método malicioso diseñado para extraer el valor máximo (MEV) de las transacciones, permitiendoles manipular la cadena de bloques y realizar estafas sistemáticas a los desprevenidos tenedores de la moneda. Este método es parte integral de su plan para realizar esquemas pump-and-dump donde artificialmente elevan el valor de la moneda para luego extraer las transacciones más valiosas, dejando a los inversores con pérdidas significativas.

Para cumplir con las consignas establecidas, debimos generar un codigo que cumpla las siguientes normativas:

**Aclaraciones**
* Los usuarios se identifican con números enteros positivos consecutivos.
* A diferencia de la especificación anterior, esta vez la cantidad de transacciones por bloque no está acotada.
* Al hackear una transacción, se debe restaurar el monto de la transacción al comprador y al vendedor.
* Las complejidades asociadas a cada operación deben coincidir, o ser menores.


### Operaciones implementadas:

Usaremos las siguientes variables para expresar las complejidades:
* **p**: cantidad total de usuarios
* **nb**: cantidad de transacciones en el bloque

  
1. **nuevoBerretacoin**(in n usuarios: Z): $Berretacoin O(P)
   Inicializa al sistema de criptomonedas con usuarios numerados de 1 a n usuarios.
2. **agregarBloque**(inout berretacoin: $Berretacoin, in transacciones: seq<Transaccion>) O(nb ∗ log P)
   Agrega un nuevo bloque con la secuencia de transacciones, que vienen ordenadas por su identificador, a la cadena de bloques.
3. **txMayorValorUltimoBloque**(in berretacoin: $Berretacoin): Transaccion O(1)
   Devuelve la transacción de mayor valor del último bloque (sin extraerla). En caso de empate, devuelve aquella de mayor id.
4. **txUltimoBloque**(in berretacoin: $Berretacoin): seq<Transaccion> O(nb)
   Devuelve una copia de la secuencia de transacciones del último bloque, ordenadas por su identificador.
5. **maximoTenedor**(in berretacoin: $Berretacoin): Z O(1)
   Devuelve al usuario que posee la mayor cantidad de $Berretacoin. En caso de empate, devuelve aquél de menor id.
6. **montoMedioUltimoBloque**(in berretacoin: $Berretacoin): Z O(1)
   Devuelve el monto promedio de todas las transacciones en el ´ultimo bloque de la cadena, sin considerar las ”transacciones de creaci´on”. En caso de que no haya transacciones, devuelve 0.
7. **hackearTx**(inout berretacoin: $Berretacoin): O(log nb + log P)
   Extrae del último bloque de la cadena la transacción de mayor monto. No importa si después de la extracción queda una transacción dentro del bloque donde el comprador no tiene fondos suficientes.

La ultima norma a cumplir fue superar los test provistos por los Berreteros. (ver archivo [BerretacoinTest.java](ruta/al/archivo.pdf)) Pero solo utilizando las estructuras de datos especificadas a continuación, implementadas por nosotros mismos:
* Arreglos
* Arreglos redimensionables
* Listas enlazadas
* ABB (Árbol Binario de Búsqueda)
* AVL (Árbol AVL)
* heap (Cola de prioridad)

Con excepción de algunas clases predefinidas en la biblioteca estándar de Java como: **ArrayList**, **String** y **StringBuffer**.

Para abarcar el sistema de forma mas sencilla, intentamos encapsular y modularizar los problemas lo mas posible. Por ejemplo, para modelar la blockchain utilizamos el tipo de dato lista enlazada, el cual especificamos e implementamos como una clase de TAD previo a la utilizacion como objeto blockchain.

### Toma de decisiones



