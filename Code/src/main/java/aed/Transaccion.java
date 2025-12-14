package aed;

import aed.ListaEnlazada.Handle;
/*
  * En este archivo definimos la clase Transaccion, la cual almacena cuatro atributos fundamentales,
  * Id (de la transaccion); id_comprador: quien efectua la compra y paga con Berretacoin;
  * id_vendedor: quien recibe Berretacoin luego de vender su producto; el monto, es decir, la cantidad de
  * Berretacoin que recibe el comprador.
  *
  * OBS: Todos los metodos en esta clase tienen complejidad O(1).
  * Ademas, definimos los siguiente metodos:
  *    - Transaccion() que asigna los valores recibidos como parametros a los atributos del objeto.
  *    - monto(), id_comprador(), id_vendedor() que retornan dichos valores de la transaccion,
  *      para evitar la modificacion de los atributos.
  *    - Sobre-escribimos los metodos compareTo y equals para poder utilizarlos con las prioridades exigidas.
*/

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    /* 
     * La restriccion del compareTo respeta los siguientes casos: 
     * CASO A: si res es mayor a 0 (res>0) entonces la transaccion es mayor que la recibida como parametro.
     * CASO B: si res es menor a 0 (res<0) entonces la transaccion recibida como parametro es mayor.
     * CASO C: si res=0 es que son iguales.
    */
    @Override
    public int compareTo(Transaccion otro) {
        int res=0;
        if(otro==null){
            throw new UnsupportedOperationException("No hay elemnto para comparar");
        }
        else{
            if(this.monto-otro.monto>0 || this.monto-otro.monto<0){
                res = this.monto-otro.monto;
            }
            else if(this.monto-otro.monto==0){
                res = this.id-otro.id;
            }
        }
        return res;
    }

    /* 
     * Este metodo resulta mas intuitivo que el anterior, definimos dos elementos iguales si son exactamente el 
     * mismo objeto, o si todos sus atributos son iguales
    */
    @Override
    public boolean equals(Object otro){
        if(otro==null){
            return false;
        }
        if(otro==this){
            return true;
        }
        if(otro.getClass()== getClass()){
            int idtx = this.id;
            int idc = this.id_comprador;
            int idv = this.id_vendedor;
            int monto = this.monto;

            int idtxO = ((Transaccion) otro).id;
            int idcO = ((Transaccion) otro).id_comprador;
            int idvO = ((Transaccion) otro).id_vendedor;
            int montoO = ((Transaccion) otro).monto;

           return idtx==idtxO && idc==idcO && idv==idvO && monto==montoO;
        }
        return false;
    }

    public boolean TransaccionDeCreacion(){
        return this.id_comprador ==0;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int id() {
        return id;
    }
}
