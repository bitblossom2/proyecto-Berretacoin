package aed;
/* 
 * Los usuarios se identifican con numeros enteros positivos consecutivos.(letra P)
 * La cantidad de transacciones por bloque no esta acotada.(letras nb)
 * 
 * Al hackear una transaccion, se debe restaurar el monto de la transaccion al comprador y al vendedor.
*/
public class Berretacoin {
    private ListaEnlazada<Bloque> blockchain;
    private Usuarios saldo;
    public int cantMonedas;

    // Complejidad O(P), por la complejidad de inicializarUsuarios() (ver archivo Usuarios.java)
    public Berretacoin(int n_usuarios){ 
        blockchain = new ListaEnlazada<Bloque>();
        saldo = new Usuarios();
        saldo.inicializarUsuarios(n_usuarios);
    }

    // Complejidad O(nb), solo pesan las complejidades del primer while y de la llamada al metodo
    // agregarTransaccion() de Bloque.java
    public void agregarBloque(Transaccion[] transacciones){ 
        int id;

        if(blockchain.ultimo() == null){
            id = 0;
        } else {
            id = blockchain.ultimo().id_bloque() + 1;
        }

        Bloque nuevo_bloque = new Bloque(id, transacciones.length);
        int indice = 0;
        int monto = 0;

        // este bucle tiene complejidad O(nb)
        while (indice < transacciones.length) {
            monto += transacciones[indice].monto();
            
            if (transacciones[indice].id_comprador() == 0) {
                saldo.sumarSaldo(transacciones[indice].id_vendedor(), transacciones[indice].monto());
                indice ++;
            } else {
                saldo.sumarSaldo(transacciones[indice].id_vendedor(), transacciones[indice].monto());
                saldo.restarSaldo(transacciones[indice].id_comprador(), transacciones[indice].monto());
                indice++;
            }
        }
        
        // agregarTransaccion tiene complejidad O(nb)
        nuevo_bloque.agregarTransaccion(transacciones);
        nuevo_bloque.modificarMonto(monto);
        blockchain.agregarAtras(nuevo_bloque);
        cantMonedas += monto;
    }

    
    // Este metodo tiene complejidad O(1) porque usando un handle accedemos a la mayor transaccion
    // guardada como raiz en el heap y esa operacion es O(1).(No se elimina)
    public Transaccion txMayorValorUltimoBloque(){
        return blockchain.ultimo().obtenerMaxTx();
    }

    // Este metodo tiene complejidad O(nb) ya que llamamos a constructorPorCopia() de la clase
    // Bloque.java (ver justificacion de complejidad en su archivo)
    public Transaccion[] txUltimoBloque(){
        Bloque ult_bloque = blockchain.ultimo();
        return ult_bloque.constructorPorCopia();
    }

    // Este metodo tiene complejidad O(1) porque usando un handle accedemos a la mayor transaccion,
    // guardada como raiz en el heap y esa operacion es O(1).(No se elimina)
    public int maximoTenedor(){
        if(blockchain.ultimo() == null){
            return 1;
        }
        return saldo.obtMaxUsuario();
    }

    //Este metodo tiene complejidad O(1) ya que guardamos como atributo la suma de todas las transacciones
    // y a su vez guardamos la longitud en cada bloque para que el programa ejecute una sola operacion.
    public int montoMedioUltimoBloque(){
        int sumaMontos = blockchain.ultimo().sumaMontos();
        int cantTransacciones = blockchain.ultimo().longitud();
        Bloque ult_bloque = blockchain.ultimo();

        if(ult_bloque.vacio()){
            return 0;
        }

        if(ult_bloque.esDeCreacion()){
            cantTransacciones --; 
            sumaMontos --;
        }

        if(cantTransacciones == 0){
            return 0;
        }

        return sumaMontos / cantTransacciones;
    }

    /*
     Este metodo tiene complejidad O(log(nb)+ log(P)) ya que se elimina la transaccion que es la raiz de un heap,
     luego el ordenarlo tiene complejidad O(log(nb))(metodo eliminarMax() de Bloque.java) 
     y cuando se actualiza el saldo de las dos personas afectadas, estas se ordenan provocando complejidad O(log(P))
     (metodos restarSaldo/sumarSaldo de usuarios.java).
     */
    public void hackearTx(){
        Transaccion tx =  blockchain.ultimo().obtenerMaxTx();
        int montoActual = blockchain.ultimo().sumaMontos();

        if(tx.id_comprador() == 0){
            saldo.restarSaldo(tx.id_vendedor(), tx.monto());
        } else {
            saldo.sumarSaldo(tx.id_comprador(), tx.monto());
            saldo.restarSaldo(tx.id_vendedor(), tx.monto());    
        }

        blockchain.ultimo().modificarMonto(montoActual - tx.monto());
        blockchain.ultimo().eliminarMaxTx();
    }
}
