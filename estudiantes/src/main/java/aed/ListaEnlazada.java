package aed;
// Todas las complejidades no especificadas son O(1).

import java.util.ArrayList;

public class ListaEnlazada<T extends Comparable <T>> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    public class Nodo {
        private T valor;
        private Nodo sig;
        private Nodo ant;

        Nodo(T v) { this.valor = v; }

        public T valor(){
            return this.valor;
        }
        
        public Nodo sig(){
            return this.sig;
        }
    }
    
    public ListaEnlazada() {
       primero = null;
       ultimo = null;
    }

    // Utilizado para tests
    public ListaEnlazada<T> constructorPorCopiaListaEnlazada(ListaEnlazada<T> l){
        ListaEnlazada<T> res = new ListaEnlazada();
        int i = 0;

        while(i < longitud){
            res.agregarAtras(l.obtener(i));
            i = i + 1;
        }
        
        return res;
    }

    public class Handle implements Comparable<Handle>{
        private Nodo nodoAsociado;

        public Handle(Nodo nodo) {
            this.nodoAsociado = nodo;
        }

        public Nodo nodoAsociado(){
            return this.nodoAsociado;
        }

        public T nodoAsocValor(){
            return this.nodoAsociado.valor;
        }
        
        @Override
        public int compareTo(Handle otro) {
            return this.nodoAsociado.valor.compareTo(otro.nodoAsociado.valor);
        }
    }

    public int longitud() {
        return longitud;   
    }

    // Lo usamos en agregarBloque(), en Berretacoin.java para agregar transacciones a un bloque. Tiene complejidad O(1).
    public Handle agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.ant = ultimo;
        nuevo.sig = null;

        if (ultimo != null){
            ultimo.sig = nuevo;
        } else {
            primero = nuevo;
        }

        ultimo = nuevo;
        longitud ++;
        return new Handle(nuevo);
    }

    // Complejidad O(n) 
    // (en el peor caso, si queremos ver el anteultimo elemento tenemos que recorrer toda la lista)
    public T obtener(int i) {
        int indice = 0;
        Nodo actual = primero;

        while (indice < i) {
            actual = actual.sig;
            indice += 1;
        }   

        return actual.valor;
    }

    public void eliminarNodo(Handle handle){
        Nodo nodo = handle.nodoAsociado();

        if(nodo == primero){
            primero = nodo.sig;
        } else {
            //cualquier nodo que no sea primero ni ultimo entra en los dos if
            if(nodo.ant != null){
                nodo.ant.sig = nodo.sig;
            }
            if(nodo.sig != null){
                nodo.sig.ant = nodo.ant;
            }
        }
        nodo.sig = null;
        nodo.ant = null;
        longitud--;
    }
    
    // Complejidad O(n)
    public ArrayList<T> listToArrayList(){
        int l = this.longitud;
        int i = 0;
        ArrayList<T> res = new ArrayList<T>(l);
        ListaEnlazada<T>.Nodo actual = this.primero;

        while (i < l) {
            res.add(i, actual.valor);
            actual = actual.sig;
            i++;
        };

        return res;
    }

    public T primero(){
        if(this.primero == null){
            return null;
        }

        return this.primero.valor;
    }

    public T ultimo(){
        if(this.ultimo == null){
            return null;
        }

        return this.ultimo.valor;
    }

    public void modificarLongitud(int l){
        this.longitud = l;
    }

    // Utilizada para tests
    public int longitudTests() {
        Nodo actual = primero;
        int i = 0;
        while (actual != null) {
            i ++;
            actual = actual.sig;
        }
        return i;   
    }

}

