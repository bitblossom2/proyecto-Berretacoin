package aed;

import aed.Bloque;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BloqueTest {
    
    @Test
    void testCrearHeapdeHandles(){
        Transaccion[] lista_transacciones = new Transaccion[4];
        
        Transaccion t1 = new Transaccion(1, 10, 20, 500);
        Transaccion t2 = new Transaccion(2, 11, 21, 700);
        Transaccion t3 = new Transaccion(3, 12, 22, 500);
        Transaccion t4 = new Transaccion(1, 10, 20, 500);

        lista_transacciones[0] = t1;
        lista_transacciones[1] = t2;
        lista_transacciones[2] = t3;
        lista_transacciones[3] = t4;

        System.out.println(t1);

        Bloque bloque = new Bloque(0, 4);
        bloque.agregarTransaccion(lista_transacciones);

        assertEquals(t1, bloque.lista().obtener(0));
        assertEquals(lista_transacciones.length, bloque.lista().longitudTests());
        assertEquals(t2, bloque.obtenerMaxTx());

        bloque.eliminarMaxTx();
        
        assertEquals(t3, bloque.obtenerMaxTx());
        assertEquals(3, bloque.heap().obtLongitud());
        assertEquals(3, bloque.lista().longitudTests());

    }


}

