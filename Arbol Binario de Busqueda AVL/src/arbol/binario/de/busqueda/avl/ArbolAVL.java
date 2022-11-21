package arbol.binario.de.busqueda.avl;

public class ArbolAVL {

    private NodoArbolAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    //Método para buscar un nodo
    public NodoArbolAVL buscarNodoAVL(int dato, NodoArbolAVL raiz) {
        if (raiz == null) {
            return null;
        } else if (raiz.getDato() == dato) {
            return raiz;
        } else if (raiz.getDato() < dato) {
            return buscarNodoAVL(dato, raiz.getHijoDerecho());
        } else {
            return buscarNodoAVL(dato, raiz.getHijoIzquierdo());
        }
    }

    //Método para obtener factor de equilibrio
    public int obtenerFactorEquilibrio(NodoArbolAVL raiz) {
        if (raiz == null) {
            return -1;
        } else {
            return raiz.getFactorEquilibrio();
        }
    }

    //Método rotacióm simple hacia la izquierda
    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL raiz) {
        NodoArbolAVL aux = raiz.getHijoIzquierdo();
        raiz.setHijoIzquierdo(aux.getHijoDerecho());
        aux.setHijoDerecho(raiz);
        raiz.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(raiz.getHijoIzquierdo()), obtenerFactorEquilibrio(raiz.getHijoDerecho())) + 1); 
        aux.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(raiz.getHijoIzquierdo()), obtenerFactorEquilibrio(raiz.getHijoDerecho())) + 1);
        return aux;
    }

    //Método rotación simple hacia la derecha
    public NodoArbolAVL rotacionDerecha(NodoArbolAVL raiz) {
        NodoArbolAVL aux = raiz.getHijoDerecho();
        raiz.setHijoDerecho(aux.getHijoIzquierdo());
        aux.setHijoIzquierdo(raiz);
        raiz.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(raiz.getHijoIzquierdo()), obtenerFactorEquilibrio(raiz.getHijoDerecho())) + 1); 
        aux.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(raiz.getHijoIzquierdo()), obtenerFactorEquilibrio(raiz.getHijoDerecho())) + 1);
        return aux;
    }

    //Método de rotación doble derecha - izquierda
    public NodoArbolAVL rotacionDerechaIzquierda(NodoArbolAVL raiz) {
        NodoArbolAVL aux;
        raiz.setHijoIzquierdo(rotacionDerecha(raiz.getHijoIzquierdo()));
        aux = rotacionIzquierda(raiz);
        return aux;
    }

    //Método de rotación doble izquierda - derecha
    public NodoArbolAVL rotacionIzquierdaDerecha(NodoArbolAVL raiz) {
        NodoArbolAVL aux;
        raiz.setHijoDerecho(rotacionIzquierda(raiz.getHijoDerecho()));
        aux = rotacionDerecha(raiz);
        return aux;
    }

    //Método para insertar nodos AVL
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevoNodo, NodoArbolAVL subArbol) {
        NodoArbolAVL nuevoPadre = subArbol;
        if (nuevoNodo.getDato() < subArbol.getDato()) {
            if (subArbol.getHijoIzquierdo() == null) {
                subArbol.setHijoIzquierdo(nuevoNodo);
            } else {
                subArbol.setHijoIzquierdo(insertarAVL(nuevoNodo, subArbol.getHijoIzquierdo()));
                if (obtenerBalance(subArbol) >= 2 || obtenerBalance(subArbol) <= -2) {
                    if (nuevoNodo.getDato() < subArbol.getHijoIzquierdo().getDato()) {
                        nuevoPadre = rotacionIzquierda(subArbol);
                    } else {
                        nuevoPadre = rotacionDerechaIzquierda(subArbol);
                    }
                }
            }
        } else if (nuevoNodo.getDato() > subArbol.getDato()) {
            if (subArbol.getHijoDerecho() == null) {
                subArbol.setHijoDerecho(nuevoNodo);
            } else {
                subArbol.setHijoDerecho(insertarAVL(nuevoNodo, subArbol.getHijoDerecho()));
                if (obtenerBalance(subArbol) >= 2 || obtenerBalance(subArbol) <= -2) {
                    if (nuevoNodo.getDato() > subArbol.getHijoDerecho().getDato()) {
                        nuevoPadre = rotacionDerecha(subArbol);
                    } else {
                        nuevoPadre = rotacionIzquierdaDerecha(subArbol);
                    }
                }
            }
        }
        //actualizar altura
        if ((subArbol.getHijoIzquierdo() == null) && (subArbol.getHijoDerecho() != null)) {
            subArbol.setFactorEquilibrio(subArbol.getHijoDerecho().getFactorEquilibrio() + 1);
        } else if ((subArbol.getHijoDerecho() == null) && (subArbol.getHijoIzquierdo() != null)) {
            subArbol.setFactorEquilibrio(subArbol.getHijoIzquierdo().getFactorEquilibrio() + 1);
        } else {
            subArbol.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()), obtenerFactorEquilibrio(subArbol.getHijoDerecho())) + 1); 
        }
        return nuevoPadre;
    }

    //Método para insertar el valor al nodo
    public void insertarNodo(int dato) {
        NodoArbolAVL nuevo = new NodoArbolAVL(dato);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    //Método para eliminar un nodo en el árbol AVL
    public NodoArbolAVL eliminarNodo(int dato, NodoArbolAVL raiz) {
        if (dato < raiz.getDato()) {
            raiz.setHijoIzquierdo(eliminarNodo(dato, raiz.getHijoIzquierdo()));
        } else if (dato > raiz.getDato()) {
            raiz.setHijoDerecho(eliminarNodo(dato, raiz.getHijoDerecho()));
        } else {
            if ((raiz.getHijoIzquierdo() == null) || (raiz.getHijoDerecho() == null)) {
                NodoArbolAVL aux = null;
                if (aux == raiz.getHijoIzquierdo()) {
                    aux = raiz.getHijoDerecho();
                } else {
                    aux = raiz.getHijoIzquierdo();
                }

                if (aux == null) {
                    aux = raiz;
                    raiz = null;
                } else {
                    raiz = aux;
                }
            } else {
                NodoArbolAVL aux = obtenerMinimo(raiz.getHijoDerecho());
                raiz.setDato(aux.getDato());
                raiz.setHijoDerecho(eliminarNodo(aux.getDato(), raiz.getHijoDerecho()));
            }
        }
        if (raiz == null) {
            return raiz;
        }
        raiz.setFactorEquilibrio(Math.max(obtenerFactorEquilibrio(raiz.getHijoIzquierdo()), obtenerFactorEquilibrio(raiz.getHijoDerecho())) + 1);
        
        int balance = obtenerBalance(raiz);
        if(balance >= 1 && obtenerBalance(raiz.getHijoIzquierdo()) >= 0){
            return rotacionIzquierda(raiz);
        }
        if(balance >= 1 && obtenerBalance(raiz.getHijoIzquierdo()) < 0){
            return rotacionDerechaIzquierda(raiz);
        }
        if(balance <= -1 && obtenerBalance(raiz.getHijoDerecho()) <= 0){
            return rotacionDerecha(raiz);
        }
        if(balance <= -1 && obtenerBalance(raiz.getHijoDerecho()) > 0){
            return rotacionIzquierdaDerecha(raiz);
        }
        return raiz;
    }

    //Método para obtener el valor minimo de un nodo
    public NodoArbolAVL obtenerMinimo(NodoArbolAVL subArbol) {
        NodoArbolAVL aux = subArbol;
        while (aux.getHijoIzquierdo() != null) {
            aux = aux.getHijoIzquierdo();
        }
        return aux;
    }
    
    //Método para balancear o equilibrar un nodo
    public int obtenerBalance(NodoArbolAVL subArbol){
        if(subArbol == null){
            return 0;
        }
        return obtenerFactorEquilibrio(subArbol.getHijoIzquierdo()) - obtenerFactorEquilibrio(subArbol.getHijoDerecho());
    }

    //Método de recorrido inOrden
    public void inOrdenRecursivo(NodoArbolAVL raiz) {
        if (raiz != null) {
            inOrdenRecursivo(raiz.getHijoIzquierdo());
            System.out.print(raiz.getDato() + " ");
            inOrdenRecursivo(raiz.getHijoDerecho());
        }
    }

    //Método de recorrido PreOrden
    public void preOrdenRecursivo(NodoArbolAVL raiz) {
        if (raiz != null) {
            System.out.print(raiz.getDato() + " ");
            preOrdenRecursivo(raiz.getHijoIzquierdo());
            preOrdenRecursivo(raiz.getHijoDerecho());
        }
    }

    //Método de recorrido PosOrden
    public void posOrdenRecursivo(NodoArbolAVL raiz) {
        if (raiz != null) {
            posOrdenRecursivo(raiz.getHijoIzquierdo());
            posOrdenRecursivo(raiz.getHijoDerecho());
            System.out.print(raiz.getDato()+  " ");
        }
    }

    //Devuelve la raiz
    public NodoArbolAVL getRaiz() {
        return raiz;
    }
    //Cambia la raiz
    public void setRaiz(NodoArbolAVL nuevo){
        raiz = nuevo;
    }
}
