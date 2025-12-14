package aed;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;

import org.junit.jupiter.api.Test;

//import aed.Usuarios.User;
//import org.junit.jupiter.api.BeforeEach;

/*
 * Casos cubiertos:
 *      - Crear heap vacio con capacidad de n elementos dentro.
 *      - Agregar un elemento (tanto en el heap usuarios como en transacciones).
 *      - Agregar varios elementos (tanto en usuarios como en transacciones).
 *      - Llenar heap y luego vaciarlo (tanto en usuarios como en transacciones). Con estos test tambien confirmamos que
 *        los metodos ordenarArriba() y ordenarAbajo() funcionan.
 *          
 */

public class HeapTests {
    private Heap<Integer> heapInt;
    private Integer[] listaEnteros;


    @Test 
    public void pruebaActualizarClave(){

    }

    private Heap<Usuarios.User> heapUsuarios;
    private Heap<Transaccion> heapTx;
    private Transaccion[] listaTx;
    private Usuarios.User[] listaUsers;


    @Test
    public void heapVacio(){
        heapUsuarios = new Heap<>(0);
        assertEquals(heapUsuarios.obtLongitud(), 0);

        heapUsuarios = new Heap<>(1);
        assertEquals(heapUsuarios.obtLongitud(), 0);

        heapUsuarios = new Heap<>(8);
        assertEquals(heapUsuarios.obtLongitud(), 0);

        heapUsuarios = new Heap<>(1000);
        assertEquals(heapUsuarios.obtLongitud(), 0);
    }

    @Test
    public void unElemento(){
        heapUsuarios = new Heap<Usuarios.User>(100);
        Usuarios.User usuario = new Usuarios.User(1, 100);

        heapTx = new Heap<Transaccion>(100);
        Transaccion tx = new Transaccion(0,0,1,1);

        heapUsuarios.Agregar(usuario);
        assertEquals(usuario, heapUsuarios.obtMax()); 

        heapTx.Agregar(tx);
        assertEquals(heapTx.obtMax(), tx);

        assertEquals(heapTx.obtLongitud(), 1);
        assertEquals(heapUsuarios.obtLongitud(), 1);

    }
    
 
    @Test
    public void variosUsuarios(){
        heapUsuarios = new Heap<Usuarios.User>(100);

        listaUsers = new Usuarios.User[] {
            new Usuarios.User(0, 0),
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 12),
            new Usuarios.User(3, 19),
            new Usuarios.User(4, 15) 
        };

        for(int i = 0; i < listaUsers.length; i++){
            heapUsuarios.Agregar(listaUsers[i]);
        }
        assertEquals(heapUsuarios.obtLongitud(), 5);


        assertEquals(listaUsers[1], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[3], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[4], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[2], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[0], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(heapUsuarios.obtLongitud(), 0);
        
    }

    @Test
    public void variasTransacciones(){
        heapTx = new Heap<Transaccion>(100);

        listaTx = new Transaccion[] {
            new Transaccion(0, 0, 2, 20), // 2 -> $1
            new Transaccion(1, 2, 3, 10), // 3 -> $1
            new Transaccion(2, 3, 4, 40),
            new Transaccion(3, 5, 4, 50) // 4 -> $1
        };

        for(int i = 0; i < listaTx.length; i++){
            heapTx.Agregar(listaTx[i]);
        }
        assertEquals(heapTx.obtLongitud(), 4);

        assertEquals(heapTx.obtMax(), listaTx[3]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[2]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[0]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[1]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtLongitud(), 0);

    }

    @Test
    public void heapUsuariosLleno(){
        heapUsuarios = new Heap<Usuarios.User>(8);

        listaUsers = new Usuarios.User[] {
            new Usuarios.User(0, 0),
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 12),
            new Usuarios.User(3, 19),
            new Usuarios.User(4, 15),
            new Usuarios.User(5, 40), 
            new Usuarios.User(6, 102),
            new Usuarios.User(7, 38)
        };

        for(int i = 0; i < listaUsers.length; i++){
            heapUsuarios.Agregar(listaUsers[i]);
        }
        assertEquals(heapUsuarios.obtLongitud(), 8);


        assertEquals(listaUsers[6], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[1], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[5], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[7], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[3], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[4], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[2], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[0], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(heapUsuarios.obtLongitud(), 0);
        
    }

    @Test
    public void heapTxLleno(){
        heapTx = new Heap<Transaccion>(6);

        listaTx = new Transaccion[] {
            new Transaccion(0, 0, 2, 20), // 2 -> $1
            new Transaccion(1, 2, 3, 10), // 3 -> $1
            new Transaccion(2, 3, 4, 40), // 4 -> $1
            new Transaccion(3, 5, 4, 50), // 4 -> $1
            new Transaccion(4, 3, 1, 60), // 1 -> $1
            new Transaccion(5, 1, 5, 30)  // 5 -> $1

        };

        for(int i = 0; i < listaTx.length; i++){
            heapTx.Agregar(listaTx[i]);
        }
        assertEquals(heapTx.obtLongitud(), 6);

        assertEquals(heapTx.obtMax(), listaTx[4]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[3]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[2]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[5]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[0]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtMax(), listaTx[1]);
        heapTx.eliminarMax();
        assertEquals(heapTx.obtLongitud(), 0);
        
    }
    @Test
    public void testOrdenarArriba(){
        heapInt = new Heap<Integer>(10);
        Heap<Integer>.Handle[] handle = new Heap.Handle[10];

        for(int i = 0; i < 10; i++){
            handle[i] = heapInt.AgregarConPuntero(i*5);

        }

        heapInt.actualizarClave(handle[0],300 );
        heapInt.actualizarClave(handle[2],500 );
        heapInt.actualizarClave(handle[1],500 );
        heapInt.actualizarClave(handle[0],100 );
    }
/*
    ESTO CREO Q ES TEST DE USUARIO DIRECTAMENTE Vemos que pasa cuando dos usuarios tienen mismo monto (el maximo esta repetido), 
    deberia devolver el handle del usuario con mayor id

    @Test
    public void maximoMontoRepetido(){
        
        heapUsuarios = new Heap<Usuarios.User>(100);

        listaUsers = new Usuarios.User[] {
            new Usuarios.User(0, 0),
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 50),
            new Usuarios.User(3, 19),
            new Usuarios.User(4, 19) 
        };

        for(int i = 0; i < listaUsers.length; i++){
            heapUsuarios.Agregar(listaUsers[i]);
        }

        assertEquals(listaUsers[1], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[2], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[4], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();


    }

*/
    
}

