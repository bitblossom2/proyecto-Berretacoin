package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
 * OBSERVACION: El usuario 0 es reservado para las transacciones de creacion, por lo que no
 * va a ser contemplado en los tests de actualizar saldo normal.
 * 
 * Casos cubiertos:
 *      - actualizarSaldo() Inicializa el objeto de clase Usuarios() y verifica que se
 *        pueda modificar solo un elemento.
 *      - actualizarSaldos() Inicializa el objeto de clase Usuarios() y verifica que se
 *        puedan modificar varios elementos.
 *      - maximoHeap() Verifica que el maximo del Heap de usuarios es el correcto, comparando con montos.
 *      - maximoCuandoCero() Verifica que el maximo sea correcto cuando se inicializa el objeto.
 *      - maximoMontoRepetido() Compara que el User del maximo del heap sea el correcto, comparando
 *        con la lista de Users.
 *      - maximoMontoRepetido2() Verifica que el maximo del heap y el elemento del heap coincidan.
 * 
 */
 
public class UsuariosTests {
    Usuarios prueba = new Usuarios();
    public Heap<Usuarios.User> heapUsuarios;
    public Usuarios.User[] listaUsers;
    Usuarios.User userMax;

    @Test
    public void actualizarSaldo(){
        prueba.inicializarUsuarios(10);

        prueba.sumarSaldo(1,4);
        assertEquals(prueba.obtSaldo(1), 4);
        prueba.restarSaldo(1,4);
        assertEquals(prueba.obtSaldo(1), 0);

    }

    @Test
    public void actualizarSaldos(){
        prueba.inicializarUsuarios(5);

        // Modificamos los saldos con valores crecientes.
        for(int i = 1; i < 5; i++){
            int m = i*5;
            prueba.sumarSaldo(i,m);
        }

        assertEquals(prueba.obtSaldo(1), 1*5);
        assertEquals(prueba.obtSaldo(2), 2*5);
        assertEquals(prueba.obtSaldo(3), 3*5);
        assertEquals(prueba.obtSaldo(4), 4*5);

        // Restauramos los saldos a 0.
        for(int i = 1; i < 5; i++){
            int m = i*5;
            prueba.restarSaldo(i,m);
        }

        assertEquals(prueba.obtSaldo(1), 0);
        assertEquals(prueba.obtSaldo(2), 0);
        assertEquals(prueba.obtSaldo(3), 0);
        assertEquals(prueba.obtSaldo(4), 0);
    }

    @Test
    public void maximoHeap(){
        heapUsuarios = new Heap<Usuarios.User>(100);

        listaUsers = new Usuarios.User[] {
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 0),
            new Usuarios.User(3, 20),
            new Usuarios.User(4, 54),
            new Usuarios.User(5, 10)
        };

        for(int i = 0; i < listaUsers.length; i++){
            heapUsuarios.Agregar(listaUsers[i]);
        }

        assertEquals(listaUsers[3], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[0], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[2], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[4], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[1], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();

    }

    @Test
    public void maximoCuandoCero(){
        prueba.inicializarUsuarios(10);
        heapUsuarios = prueba.obtHeap();

        userMax = heapUsuarios.obtMax();
        assertEquals(1,userMax.id());

    }

    @Test
    public void maximoMontoRepetido(){ 
        heapUsuarios = new Heap<Usuarios.User>(100);

        listaUsers = new Usuarios.User[] {
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 0),
            new Usuarios.User(3, 20),
            new Usuarios.User(4, 50),
            new Usuarios.User(5, 10),
            new Usuarios.User(6, 19),
            new Usuarios.User(7, 19) 
        };

        for(int i = 0; i < listaUsers.length; i++){
            heapUsuarios.Agregar(listaUsers[i]);
        }

        assertEquals(listaUsers[0], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[3], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[2], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[5], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[6], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[4], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();
        assertEquals(listaUsers[1], heapUsuarios.obtMax());
        heapUsuarios.eliminarMax();

    }

    @Test
    public void maxMontoRepetido2(){
        prueba.inicializarUsuarios(10);

        for(int i = 1; i < 10; i++){
            prueba.sumarSaldo(i, i*6);
        }

        heapUsuarios = prueba.obtHeap();
        userMax = heapUsuarios.obtMax();

        assertEquals(prueba.obtSaldo(9), userMax.monto());
        assertEquals(9, userMax.id());

        prueba.restarSaldo(9,9*6);
        heapUsuarios = prueba.obtHeap();
        userMax = heapUsuarios.obtMax();

        assertEquals(prueba.obtSaldo(8), userMax.monto());
        assertEquals(8, userMax.id());

        prueba.restarSaldo(8,8*6);
        heapUsuarios = prueba.obtHeap();
        userMax = heapUsuarios.obtMax();

        assertEquals(prueba.obtSaldo(7), userMax.monto());
        assertEquals(7, userMax.id());

    }
}