package aed;
import aed.Heap.Handle;

/* 
 * En esta clase llenamos el heap de usuarios y a su vez vamos a almacenar los handles del heap en un Array.
 * La justificacion de almacenar los handles en esta estructura es que cada indice hace referencia a un id de usuario, osea
 * el array de handles se ordena mediante el id, luego a la hora de actualizar los saldos tenemos acceso facil
 * al lugar del heap donde se encuentra ese usuario.
 * 
 * (Por el enunciado, sabemos que los id son enteros positivos y consecutivos)
 * La prioridad del heap es el usuario con maximo monto y menor id.
 * 
 * Definimos tambien una subclase User con atributos ID y monto. Sobre-escribimos el metodo compareTo para que sea
 * valido el tipo de dato del heap (compara segun montos, con la condicion de que si el monto es igual devuelve que
 * el monto del id menor es el mayor monto).
 * 
 * Definimos 5 metodos: inicializarUsuarios(), obtSaldo(), obtMaxUsuario(), sumarSaldo() y restarSaldo().
 * 
 * Todas las complejidades no especificadas son O(1).
*/

public class Usuarios{
    public static class User implements Comparable<User>{

        private int id;
        private int monto;

        public int monto(){
            return monto;
        }
        public int id(){
            return id;
        }

        User(int i, int m){
            this.id = i;
            this.monto = m;
        }
        
        @Override
        public int compareTo(User otro){
            if(otro == null){
                return -1;
            } else if (Integer.compare(this.monto,otro.monto) != 0) {
                return this.monto - otro.monto;
            } else {
                return otro.id - this.id;
            }
        }
        
        @Override
        public boolean equals(Object otro){
            if (otro == null){
                return false;
            }
            if (this == otro){
                return true;
            }
            if (otro.getClass() == getClass()){
                int idUser = this.id;
                int montoUser = this.monto;

                int idOtro = ((User) otro).id;
                int montoOtro = ((User) otro).monto;

                return idOtro == idUser && montoOtro == montoUser;
            }
            return false;
        }   
    }

    private Heap<User> heapUsuarios;
    private Heap<User>.Handle[] arrayUsuarios;

    /*
     inicializa y llena el heap y el array. Decidimos que el heap se va a llenar predefiniendo
     los saldos de todos los usuarios en 0.
     Este metodo tiene complejidad O(p) siendo p la cantidad de usuarios, porque se hace una iteracion por cada uno.
     Se utiliza al inicializar Berretacoin.
    */
    public void inicializarUsuarios(int n) {
        heapUsuarios = new Heap(n+1);
        arrayUsuarios = new Handle[n+1];
        for (int i = 1; i <= n; i++) {
            User usuario = new User(i, 0);
            arrayUsuarios[i] = heapUsuarios.AgregarConPuntero(usuario);
        }
    } 
    
    public int obtSaldo(int id){
        return arrayUsuarios[id].nodo.valor.monto;
    }

    public int obtMaxUsuario(){
        return heapUsuarios.obtMax().id();
    }

    /*
     Se utilizan para actualizar los saldos-montos de los usuarios (tipo de dato User).
     Estos ultimos dos metodos tienen complejidad O(log n) gracias a que utilizan el metodo
     actualizarClave, que luego de actualizarla debe reordenar el heap.
     Son utilizados en agregarBloque() en Berretacoin.java
    */
    public void sumarSaldo(int id, int m) {
        User anterior = arrayUsuarios[id].nodo.valor;
        User actualizado = new User(anterior.id(), anterior.monto() + m);
        heapUsuarios.actualizarClave(arrayUsuarios[id], actualizado);
    }

    public void restarSaldo(int id, int m) {
        User anterior = arrayUsuarios[id].nodo.valor;
        User actualizado = new User(anterior.id(), anterior.monto() - m);
        heapUsuarios.actualizarClave(arrayUsuarios[id], actualizado);
    }

    // Utilizado para tests.
    public Heap<User> obtHeap(){
        return heapUsuarios.constructorPorCopiaHeap(heapUsuarios);
    }
}
