package aed;

import aed.ListaEnlazada;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ListaEnlazadaTest {

    @Test
    void testAgregarAtras(){
        Transaccion t1 = new Transaccion(1, 10, 20, 500);
        Transaccion t2 = new Transaccion(2, 11, 21, 700);
        Transaccion t3 = new Transaccion(3, 12, 22, 500);
        Transaccion t4 = new Transaccion(1, 10, 20, 500);

        ListaEnlazada<Transaccion> lista=new ListaEnlazada<>();
        lista.agregarAtras(t1);
        lista.agregarAtras(t2);
        lista.agregarAtras(t3);
        lista.agregarAtras(t4);

        assertEquals(t1,lista.obtener(0));
        assertEquals(t2,lista.obtener(1));
        assertEquals(t3,lista.obtener(2));
        assertEquals(t4,lista.obtener(3));
        assertNotEquals(t1,lista.obtener(2));
        assertNotEquals(t2,lista.obtener(3));
    }

    @Test
    void testEliminarNodo() {
        ListaEnlazada<Transaccion> lista = new ListaEnlazada<>();

        // Agrego elementos y guardo los Handles
        ListaEnlazada<Transaccion>.Handle h1 = lista.agregarAtras(new Transaccion(1, 10, 20, 500));
        ListaEnlazada<Transaccion>.Handle h2 = lista.agregarAtras(new Transaccion(2, 11, 21, 700));
        ListaEnlazada<Transaccion>.Handle h3 = lista.agregarAtras(new Transaccion(3, 12, 22, 600));

        // Antes de eliminar, verificamos la longitud
        assertEquals(3, lista.longitudTests(), "La lista debería tener 3 elementos");

        // Eliminamos el nodo usando su Handle
        lista.eliminarNodo(h2);

        // La longitud debe disminuir
        assertEquals(2, lista.longitudTests(), "La lista debería tener 2 elementos después de la eliminación");

        // Verificamos que los nodos restantes siguen accesibles
        assertEquals(500, lista.obtener(0).monto(), "El primer elemento debería seguir siendo 500");
        assertEquals(600, lista.obtener(1).monto(), "El segundo elemento debería seguir siendo 600");

    }

}