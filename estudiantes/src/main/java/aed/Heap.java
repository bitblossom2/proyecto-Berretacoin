package aed;
import java.util.ArrayList;
/*
 * Decidimos crear una clase heap general para utilizarla en ListaEnlazada (lista de transacciones) y en Usuarios
 * (para almacenar saldos de Beretacoin de cada user). Modelamos la estructura del heap con un arrayList de estructura base.
 * 
 * Ademas, creamos una subclase Handle la cual tiene de atributo un nodo (al cual hace referencia)
 * y 4 metodos utiles para la implementacion y uso del Heap. Dentro de esta tambien se encuentran los
 * metodos: Handle(), obtValor(), obtIndice(), setValor() explicados debajo.
 
 * Definimos los siguientes metodos: Heap(), constructorPorCopiaHeap(), obtPadre(), obtDer(), obtIzq(), actualizarHandlesDespuesDeSwap(),
 * OrdenarArriba(), OrdenarAbajo(), AgregarConPuntero(), Agregar(), Heapify(), ObtLongitud(), ObtMax() y actualizarClave().
 * 
 * Todas las complejidades no especificadas son O(1).
 */
 
 import aed.ListaEnlazada.Handle;
 import aed.ListaEnlazada.Nodo;
 import aed.Usuarios.User;


public class Heap <T extends Comparable<T>>{
    //Definimos los atributos del heap: heap (de tipo ArrayList) y su longitud.
    private ArrayList<Nodo> heap;
    private int longitud;

    public class Handle{
        public Nodo nodo;

        // asigna los valores recibidos como parametros a sus atributos, obtValor() que retorna
        public Handle(Nodo nodo) {
            this.nodo = nodo;
            nodo.handle = this;
        }

        // retorna el valor del nodo
        public T obtValor() {
            return nodo.valor;
        }

        // retorna el indice del nodo
        public int obtIndice() {
            return nodo.indice;
        }

        // redefine el valor del nodo
        public void setValor(T valor) {
            nodo.valor = valor;
        }
    }

    public class Nodo {
        int indice;
        T valor;
        Handle handle;
        Nodo (T v, int indice) {
            this.valor = v;
            this.indice = indice;
            this.handle = null;

        }
    }

    // Inicializa la estructura 
    public Heap(int capacidad){
        heap = new ArrayList<Nodo>(capacidad);
        longitud = 0;
    }

    // Constructor por copia, utilizado para tests
    public Heap<T> constructorPorCopiaHeap(Heap<T> heap){
        Heap<T> res = new Heap(heap.obtLongitud());
        int i = 0;

        while (i < heap.obtLongitud()){
            res.Agregar(heap.obtPos(i));
            i = i + 1;
        }

        return res;
    }

    // Devolvemos la posicion de cada familar del nodo actual, 
    // nos basamos en el algoritmo propuesto en clase.
    private int obtPadre(int i){
        return (i - 1) / 2;
    }

    private int obtDer(int i){
        return 2*i + 2;
    }

    private int obtIzq(int i){
        return 2*i + 1;
    }
 
    // Actualiza correctamente los handles cuando reordenamos el heap
    // Complejidad O(1)
    private void actualizarHandlesDespuesDeSwap(int i, int j) {
        // Asegura que el handle de cada nodo apunte al nodo correcto después del swap
        if (heap.get(i).handle != null) {
            heap.get(i).handle.nodo = heap.get(i);
        }
        if (heap.get(j).handle != null) {
            heap.get(j).handle.nodo = heap.get(j);
        }
    }

    // ordena el heap desde arriba hacia abajo.
    // Complejidad O(log n) con n la cantidad de nodos del heap.
    public void OrdenarArriba(int iActual) {
        
        // Verificamos que iActual (la posicion del nodo a ordenar) sea valida (porque hacemos recursion), ademas queremos que
        // solo se ordene si el valor del padre es menor al valor del hijo
        while (iActual > 0 && heap.get(obtPadre(iActual)).valor.compareTo(heap.get(iActual).valor) < 0) {
            // Intercambiamos los nodos:
            //almacenamos el valor del padre
            Nodo temp = heap.get(obtPadre(iActual));

            //seteamos en la posicion del padre, el valor del nodo a ordenar
            heap.set(obtPadre(iActual), heap.get(iActual));

            //seteamos en la posicion del nodo a ordenar, su padre (porq su padre es menor)
            heap.set(iActual, temp);

            // Actualizamos los indices si es necesario.
            heap.get(iActual).indice = iActual;
            heap.get(obtPadre(iActual)).indice = obtPadre(iActual);

            actualizarHandlesDespuesDeSwap(iActual, obtPadre(iActual));
            // Subimos al padre
            iActual = obtPadre(iActual);
        }
    }

    // ordena el heap desde abajo hacia arriba.
    // Complejidad O(log n) con n la cantidad de nodos del heap.
    public void ordenarAbajo(int i){
        int hijoIzq = obtIzq(i);
        int hijoDer = obtDer(i);
        int mayor = i;
        

        if (hijoIzq < longitud && heap.get(hijoIzq).valor.compareTo(heap.get(mayor).valor) >= 0) {
            mayor = hijoIzq;
        }
        if (hijoDer < longitud && heap.get(hijoDer).valor.compareTo(heap.get(mayor).valor) >= 0) {
            mayor = hijoDer;
        }
        if (mayor != i) {
            Nodo temp = heap.get(i);
            heap.set(i, heap.get(mayor));
            heap.set(mayor, temp);

            heap.get(i).indice = i;
            heap.get(mayor).indice = mayor;

            actualizarHandlesDespuesDeSwap(i, mayor);

            ordenarAbajo(mayor); // Continúa bajando
        }       
    }

    /*
    La idea para facilitar el uso del handle fue que esta funcion realice mas de un objetivo:
 *  primero, que agrege el elem al heap al final del array, luego que lo ordene en el lugar correspondiente,
 *  y por ultimo que retorne/devuelva el puntero/handle. Se penso para usar cuando agregamos el elem a la 
 *  estructura donde se va a usar el handle directamente.
 *  Tiene complejidad O(log n) con n la cantidad de nodos del heap.
    */
    public Handle AgregarConPuntero(T elem) {
        // Agregamos el elemento
        Nodo nuevo = new Nodo(elem, longitud);
        heap.add(longitud,nuevo);

        // Asignamos el nuevo handle
        Handle handle = new Handle(nuevo);

        // Ordenamos el heap, en complejidad O(log n)
        OrdenarArriba(longitud);
        longitud++;

        return handle;
    }

    // Agrega el elem al heap y lo reordena.
    // Complejidad O(log n) por la llamada al metodo OrdenarArriba.
    public void Agregar(T elem) {
        Nodo nuevo = new Nodo(elem, longitud);
        heap.add(longitud,nuevo);

        OrdenarArriba(longitud);
        longitud++;
    }

    /*
     Implementacion de heapify
     Tiene complejidad O(n). Agregar elementos al heap (primer while) tiene complejidad O(n)
     Dentro del segundo while se baja desde el ultimo nodo padre hasta la raiz y se ejecuta ordenarAbajo(j).
     El bucle corre desde el último padre (j = obtPadre(longitud - 1) = n/2 - 1) hasta la raíz (j = 0). Se ejecuta O(n) veces
     pero el costo de cada llamada a ordenarAbajo(j) depende de la altura del subarbol. El nodo mas profundo solo necesita pocos
     pasos, mientras que la raíz puede necesitar O(log n) pasos.
     la suma total para todos los nodos padres es O(n).
    */
    public void heapify(ArrayList<T> lista){
        int i = 0;

        // Agrega los elementos de lista al heap de manera comun en O(n)
        while (i < lista.size()) {
            heap.add(new Nodo(lista.get(i), i));
            i++;            
        }
        
        longitud = lista.size();
        int j = obtPadre(longitud - 1);
        
        // se ordena el heap en O(n log n)
        while(j >= 0) {
            ordenarAbajo(j);
            j--;
        }
    }

    // Elimina el maximo del heap
    // complejidad O(log n) gracias a la llamada del metodo OrdenarAbajo().
    public void eliminarMax() {
        if (longitud == 0) return;

        // El elem que queremos eliminar
        Nodo eliminado = heap.get(0);

        if (longitud > 1) {
            // Movemos el ultimo a la raiz.
            heap.set(0, heap.get(longitud - 1));

            // Actualizamos el indice y handle de la nueva raiz.
            heap.get(0).indice = 0;
            if (heap.get(0).handle != null) {
                heap.get(0).handle.nodo = heap.get(0);
            }
        }

        heap.remove(longitud - 1);
        longitud--;

        if (eliminado.handle != null) {
            eliminado.handle.nodo = null;
        }

        if (longitud > 0) {
            ordenarAbajo(0);
        }
    }
    
    public int obtLongitud() {
        return longitud;
    }

    public T obtMax(){
        return heap.get(0).valor;
    }

    public T obtPos(int n){
        return heap.get(n).valor;
    }

    // Modifica el valor del nodo el cual apunta el handle recibido como parametro, por el valor tipo T recibido como parametro.
    // complejidad O(log n) gracias a que llamamos a ordenarArriba/ordenarAbajo
    public void actualizarClave(Handle handle, T nuevoValor) {
        Nodo nodoAnterior = handle.nodo;
        int cmp = nuevoValor.compareTo(nodoAnterior.valor);

        if (cmp > 0) {
            handle.setValor(nuevoValor);
            OrdenarArriba(handle.nodo.indice);
        } else if (cmp < 0) {
            handle.setValor(nuevoValor);
            ordenarAbajo(handle.nodo.indice);
        } else {
            // Si el valor es igual, solo actualiza si es necesario
            handle.setValor(nuevoValor);
        }
    }
}