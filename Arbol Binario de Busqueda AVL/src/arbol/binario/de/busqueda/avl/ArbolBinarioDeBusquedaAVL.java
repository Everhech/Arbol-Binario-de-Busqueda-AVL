/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario.de.busqueda.avl;

import javax.swing.JOptionPane;

/**
 *
 * @author Edwin O. Restrepo
 */
public class ArbolBinarioDeBusquedaAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolAVL arbolAVL = new ArbolAVL();
        do {
            switch (menu()) {
                case 1: //Insertando nodos
                    // 10, 5, 13, 1, 6, 17 -> 16
                    int dato = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el valor del nodo", "Insertar nodo al arbol AVL", JOptionPane.INFORMATION_MESSAGE));
                    arbolAVL.insertarNodo(dato);
                    GraficadoraArbol.printNode(arbolAVL.getRaiz());
                    break;
                case 2: //Recorrido preorden
                    if (arbolAVL.getRaiz() != null) {
                        arbolAVL.preOrdenRecursivo(arbolAVL.getRaiz());
                        System.out.println("\n\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Arbol vacío", "Error - CUIDADO", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3: //Recorrido inorden
                    if (arbolAVL.getRaiz() != null) {
                        arbolAVL.inOrdenRecursivo(arbolAVL.getRaiz());
                        System.out.println("\n\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Arbol vacío", "Error - CUIDADO", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 4: //Recorrido posorden
                    if (arbolAVL.getRaiz() != null) {
                        arbolAVL.posOrdenRecursivo(arbolAVL.getRaiz());
                        System.out.println("\n\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Arbol vacío", "Error - CUIDADO", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 5: //Método eliminación
                    if (arbolAVL.getRaiz() != null) {
                        dato = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el valor del nodo a eliminar", "Eliminar nodo al arbol AVL", JOptionPane.INFORMATION_MESSAGE));
                        if (arbolAVL.buscarNodoAVL(dato, arbolAVL.getRaiz()) != null) {
                            arbolAVL.setRaiz(arbolAVL.eliminarNodo(dato, arbolAVL.getRaiz()));
                            GraficadoraArbol.printNode(arbolAVL.getRaiz());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nodo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Arbol vacío", "Error - CUIDADO", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 6: //Busqueda de nodos
                    if (arbolAVL.getRaiz() != null) {
                        dato = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el valor del nodo a buscar", "Buscar nodo en el arbol AVL", JOptionPane.INFORMATION_MESSAGE));
                        if (arbolAVL.buscarNodoAVL(dato, arbolAVL.getRaiz()) != null) {
                            System.out.println(arbolAVL.buscarNodoAVL(dato, arbolAVL.getRaiz()).getDato() + "\n\n");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nodo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Arbol vacío", "Error - CUIDADO", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 7:
                    System.exit(0);
                    return;
                default:
                    break;
            }
        } while (true);
    }

    public static int menu() {
        int option = Integer.parseInt(JOptionPane.showInputDialog(null, "Opciones: "
                + "\n1.Insertar nodo"
                + "\n2.Recorrido PreOrden"
                + "\n3.Recorrido InOrden"
                + "\n4.Recorrido PosOrden"
                + "\n5.Eliminar nodo"
                + "\n6.Buscar nodo"
                + "\n7.Salir"
                + "\nOpcion", "Arbol Binario AVL", JOptionPane.QUESTION_MESSAGE));
        return option;
    }
}
