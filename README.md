# proyecto-Berretacoin
Este proyecto fue realizado por Lila Fage, Santiago Garcia Nowak, Napoleon Saran Varela y Santos Basaldua.
Se realizo como entrega para la materia "Algoritmos y Estructuras de Datos II" en Julio del año 2025, para la Universidad de Buenos Aires.

Contexto e introduccion al sistema de $Berretacoin:
Se propuso la creacion de la unica cripto completamente libre de controles criptograficos: la $Berretacoin.
Nuestra cripto tiene usuarios que identificamos con un numero entero positivo. Cada transaccion involucra a dos usuarios:
1) un comprador que es quien adquiere el bien (o servicio) pagando con la cripto, y 2) un vendedor que es quien recibe el
pago. Adem´as, una transacci´on tiene un monto asociado que tambi´en es un n´umero entero positivo y un identificador dentro
del bloque (un entero no negativo). Es decir que una transacci´on es una tupla de enteros: id transaccion, id comprador,
id vendedor, monto.
Las transacciones se agrupan en bloques como en Blockchain. Cada bloque contiene a lo sumo 50 transacciones cuyos
identificadores est´an ordenados dentro del bloque. La primera transacci´on de cada bloque “crea” una nueva unidad de
$Berretacoin hasta alcanzar el l´ımite de emisi´on, que est´a fijado en 3000 unidades. A estas transacciones especiales las
denominamos “transacciones de creaci´on”, y tienen la caracter´ıstica de que el comprador tiene identificador 0 y el vendedor
es alg´un usuario arbitrario siempre distinto. Aclaraci´on: a partir del bloque 3000 no hay m´as “transacciones de creaci´on”.
A su vez, cada bloque tiene un identificador que es un entero no negativo. Los bloques se encadenan de forma consecutiva
de acuerdo al identificador.
