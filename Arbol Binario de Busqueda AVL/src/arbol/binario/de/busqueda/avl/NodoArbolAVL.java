/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario.de.busqueda.avl;

/**
 *
 * @author Edwin O. Restrepo
 */
public class NodoArbolAVL {
    private int dato, FactorEquilibrio;
    private NodoArbolAVL hijoIzquierdo, hijoDerecho;

    public NodoArbolAVL(int dato) {
        this.dato = dato;
        this.FactorEquilibrio = 0;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    /**
     * @return the dato
     */
    public int getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    /**
     * @return the FactorEquilibrio
     */
    public int getFactorEquilibrio() {
        return FactorEquilibrio;
    }

    /**
     * @param FactorEquilibrio the FactorEquilibrio to set
     */
    public void setFactorEquilibrio(int FactorEquilibrio) {
        this.FactorEquilibrio = FactorEquilibrio;
    }

    /**
     * @return the hijoIzquierdo
     */
    public NodoArbolAVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * @param hijoIzquierdo the hijoIzquierdo to set
     */
    public void setHijoIzquierdo(NodoArbolAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    /**
     * @return the hijoDerecho
     */
    public NodoArbolAVL getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * @param hijoDerecho the hijoDerecho to set
     */
    public void setHijoDerecho(NodoArbolAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
}
