package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import aed.Heap.Handle;

import org.junit.jupiter.api.BeforeEach;


public class HeapHandleTest {
    private ListaEnlazada listaH;
    private Heap heap;
    private Usuarios.User[] listaUsers;

    @Test
    public void listaDeHandles(){
        Heap<Usuarios.User> heapUsuarios = new Heap<Usuarios.User>(5);
        listaH = new ListaEnlazada();
        listaUsers = new Usuarios.User[] {
            new Usuarios.User(0, 0),
            new Usuarios.User(1, 50), 
            new Usuarios.User(2, 12),
            new Usuarios.User(3, 19),
            new Usuarios.User(4, 19) 
        };
        
        for(int i = 0; i < listaUsers.length; i++){
            Heap<Usuarios.User>.Handle punt = heapUsuarios.AgregarConPuntero(listaUsers[i]);
            listaH.agregarAtras(punt.nodo.valor);
        }
        assertEquals(heapUsuarios.obtLongitud(), 5);

        assertEquals(listaH.obtener(1), heapUsuarios.obtMax());
    }

}
