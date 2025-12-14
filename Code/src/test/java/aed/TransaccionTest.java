package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
 * Casos cubiertos:
 *      - testCompareTo() Verifica que el metodo compareTo() de la clase Transaccion este bien definido.
 *      - testEquals() Verifica que el metodo equals() de la clase Transaccion este bien definido.
 *      - testAtributos() Verifica que los atributos se asignen de manera correcta, y que los metodos obtener funcionen.
 */

public class TransaccionTest {

    @Test
    void testCompareTo() {
        Transaccion t1 = new Transaccion(1, 10, 20, 500);
        Transaccion t2 = new Transaccion(2, 11, 21, 700);
        Transaccion t3 = new Transaccion(3, 12, 22, 500);
        Transaccion t4 = new Transaccion(1, 10, 20, 500); // igual que t1

        assertTrue(t1.compareTo(t2) < 0, "t1 debería ser menor que t2 (por monto)");
        assertTrue(t2.compareTo(t1) > 0, "t2 debería ser mayor que t1 (por monto)");
        assertTrue(t1.compareTo(t3) < 0, "t1 debería ser menor que t3 (mismo monto, menor id)");
        assertTrue(t3.compareTo(t1) > 0, "t3 debería ser mayor que t1 (mismo monto, mayor id)");
        assertEquals(0, t1.compareTo(t4), "t1 debería ser igual a t4");
    }

    @Test
    void testEquals() {
        Transaccion t1 = new Transaccion(1, 10, 20, 500);
        Transaccion t2 = new Transaccion(1, 10, 20, 500);
        Transaccion t3 = new Transaccion(1, 10, 20, 501); // distinto monto
        Transaccion t4 = new Transaccion(2, 10, 20, 500); // distinto id

        assertEquals(t1, t2, "t1 debería ser igual a t2");
        assertNotEquals(t1, t3, "t1 no debería ser igual a t3 (distinto monto)");
        assertNotEquals(t1, t4, "t1 no debería ser igual a t4 (distinto id)");
        assertNotEquals(t1, null, "t1 no debería ser igual a null");
        assertNotEquals(t1, "otro tipo", "t1 no debería ser igual a objeto de otra clase");
    }

    @Test
    void testAtributos() {
        Transaccion t = new Transaccion(42, 7, 8, 900);

        assertEquals(42, t.id(), "id incorrecto");
        assertEquals(7, t.id_comprador(), "id_comprador incorrecto");
        assertEquals(8, t.id_vendedor(), "id_vendedor incorrecto");
        assertEquals(900, t.monto(), "monto incorrecto");
    }
}
