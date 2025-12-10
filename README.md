# Proyecto-Berretacoin
Este proyecto fue realizado por Lila Fage, Santiago Garcia Nowak, Napoleon Saran Varela y Santos Basaldua.
Se realizo como entrega para la materia "Algoritmos y Estructuras de Datos II" en Julio del año 2025, para la Universidad de Buenos Aires.


## Tabla de Contenidos
1. [Información preliminar](#información-preliminar)
2. [Especificación y tipos de Datos](#Especificación-y-tipos-de-Datos)
3. [Contribuciones](#contribuciones)
4. [Licencia](#licencia)

##Información Preliminar:

Se propuso la creacion de la unica criptomoneda completamente libre de controles criptograficos: la **$Berretacoin**.
Nuestra criptomoneda tiene usuarios que identificamos con un numero entero positivo. Cada transaccion involucra a dos usuarios:
1. **Comprador**: Es quien adquiere un bien (o servicio) pagando con la criptomoneda
2. **Vendedor**: Es quien recibe el pago y entrega el bien (o servicio).
 
 Además, existen algunos objetos importantes para definir:
* **Transacción**: Cada una de estas tiene un **monto** asociado representado con un número entero positivo y un **identificador** dentro del bloque, representado con un entero no negativo. Vamos a representar este objeto como una tupla de enteros: (id transaccion, id comprador, id vendedor, monto). Las transacciones se agrupan en **bloques** simulando una blockchain.
* **Bloque**: Cada bloque contiene a lo sumo 50 transacciones cuyos identificadores están ordenados dentro del bloque. La primera transacción de cada bloque “crea” una nueva unidad de $Berretacoin hasta alcanzar el límite de emisión, que está fijado en 3000 unidades. Cada bloque tiene un identificador que es un entero no negativo.  de acuerdo al identificador. Los bloques se encadenan de forma consecutiva, siguiendo la idea de una Blockchain.
* **Transacción de creación**: A estas transacciones especiales las denominamos “transacciones de creación” porque son las que emiten una nueva unidad de $Berretacoin, y tienen la característica de que el comprador tiene identificador 0 y el vendedor es algún usuario arbitrario siempre distinto.

*Aclaración: a partir del bloque 3000 no hay m´as “transacciones de creación”*


##Especificación y tipos de Datos

Se pide especificar el TAD $Berretacoin con las siguientes operaciones:
agregarBloque: dada una secuencia de transacciones agrega un nuevo bloque con esas transacciones a la cadena de
bloques.
maximosTenedores: devuelve una lista que contiene al o los usuarios que tienen la mayor cantidad de $Berretacoin.
montoMedio: devuelve el monto promedio de todas las transacciones sin considerar las “transacciones de creaci´on”.
cotizacionAPesos: dada una lista de cotizaciones (n´umeros enteros positivos, que est´a en correspondencia 1 a 1 con
la cantidad de bloques) que indica a cu´antos pesos equivale un $Berretacoin, se pide devolver otra lista de la misma
longitud que indique el monto de pesos que se oper´o en el bloque correspondiente. Por ejemplo, si dentro del i-´esimo
bloque la suma de los montos de las transacciones es 1000 y la i-´esima cotizaci´on es 2 (1 $Berretacoin equivale a 2
pesos), hay que devolver 2000 en la posici´on i de la lista resultante.
Observaciones importantes:
El TAD debe estar completo, hay que agregar todo lo que corresponda (ej. la operaci´on de creaci´on).
En todo momento hay que garantizar que nadie gaste m´as de lo que tiene.
En una transacci´on los ids del comprador y del vendedor son distintos.
No hay que modelar ning´un aspecto criptogr´afico (ni el Nonce, ni los hashes ni las claves).



