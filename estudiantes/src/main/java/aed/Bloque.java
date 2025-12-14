package aed;

import java.util.ArrayList;

/*
 *     Dentro de esta clase nos encargamos de almacenar la informacion de los bloques, es decir, los atributos: 
 *  - id_bloque;
 *  - heap (que es el heap de transacciones); 
 *  - lista (que es una lista enlazada de transacciones, donde reutilizamos el codigo de uno de los ultimos 
 *    talleres, con algunas modificaciones por supuesto); 
 *  - longitud (cantidad de transacciones del bloque, identificada como nb cuando nos referimos a complejidad);
 *  - sumaMontos (util para construir el promedio de la transaccion en Berretacoin).
 * 
 *     Definimos los siguientes metodos: id_bloque(), heap(), lista(), longitud() y sumaMontos().
 * Al mismo tiempo, como el unico atributo que nos interesaba modificar era la suma de los montos (sumaMontos), 
 * creamos un metodo especifico para este: modificarMontos() el cual recibe la nueva suma de estos.
 * 
 *     Tambien creamos otros metodos: Bloque(), agregarTransaccion(), eliminarMaxTx(), obtenerMaxTx() y constructorPorCopia().
 *
 * Ademas, sobre-escribimos el metodo compareTo para que compare dos obj de clase Bloque mediante sus id's.
 * Todas las complejidades no especificadas son O(1).
*/

public class Bloque implements Comparable<Bloque>{
    private int id_bloque;
    private Heap<ListaEnlazada<Transaccion>.Handle> heap;
    private ListaEnlazada<Transaccion> lista;
    private int longitud; 
    private int sumaMontos;

    public Bloque(int idb, int l){
        id_bloque = idb;
        heap = new Heap<ListaEnlazada<Transaccion>.Handle>(l);
        lista = new ListaEnlazada<>();
        longitud = l;
        sumaMontos = 0;
    }

    /* 
     Agrega la transaccion pasada como parametro a la lista enlazada y al handle del heap.
     Complejidad O(nb) con nb la cantidad de transacciones(elementos del heap) que resulta de llamar al metodo Agregar() de la clase Heap.
     Lo utilizamos en agregarBloque en Berretacoin.java
    */
    public void agregarTransaccion(Transaccion[] transaccion){
        ArrayList<ListaEnlazada<Transaccion>.Handle> arr = new ArrayList<ListaEnlazada<Transaccion>.Handle>(transaccion.length);
        int i = 0;

        // el while tiene complejidad O(nb) con nb la cantidad de transacciones
        while (i < transaccion.length) {
            arr.add(i, lista.agregarAtras(transaccion[i]));
            i ++;
        }
        
        // tiene complejidad O(nb)
        this.heap.heapify(arr); 
    }

    // Elimina la mayor transaccion de la lista y el heap, ademas actualiza la longitud de este.
    // Complejidad O(log n) que resulta de llamar al metodo eliminarMax() de heap.
    public void eliminarMaxTx(){
        lista.eliminarNodo(heap.obtMax());
        heap.eliminarMax();
        this.longitud --;
    }

    // Retorna la mayor transaccion del heap.
    // Complejidad O(1)
    public Transaccion obtenerMaxTx(){
        return heap.obtMax().nodoAsociado().valor();
    }

    @Override
    public int compareTo(Bloque otro){
        return this.id_bloque - otro.id_bloque;
    }

    // Intuitivamente, es el constructor por copia de la clase Bloque, el cual se basa en el parametro
    // recibido, de tipo Lista Enlazada.
    // Complejidad O(nb) siendo nb la cantidad de transacciones de la lista.
    public Transaccion[] constructorPorCopia(){
        ArrayList<Transaccion> lista = this.lista.listToArrayList();
        Transaccion[] arreglo = lista.toArray(new Transaccion[lista.size()]);
        return arreglo;
    }

    public int id_bloque(){
        return this.id_bloque;
    }

    // Lo utilizamos para tests
    public Heap<ListaEnlazada<Transaccion>.Handle> heap(){
        return heap.constructorPorCopiaHeap(heap);
    }

    // Lo utilizamos para tests
    public ListaEnlazada<Transaccion> lista(){ //para tests
        return lista.constructorPorCopiaListaEnlazada(lista);
    }

    public boolean vacio(){
        return lista.primero() == null;
        
    } 

    public boolean esDeCreacion(){
        return lista.primero().TransaccionDeCreacion();
    }

    public int longitud(){
        return this.longitud;
    }

    public int sumaMontos(){
        return this.sumaMontos;
    }

    public void modificarMonto(int m){
        this.sumaMontos = m;
    }
}
