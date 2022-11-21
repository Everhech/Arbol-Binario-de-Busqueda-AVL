/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario.de.busqueda.avl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arbol.binario.de.busqueda.avl.NodoArbolAVL;

/**
 * Esta clase imprime por consola la estructura de un arbol binario
 *
 *
 * Adaptado de:
 * http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 *
 * Forma de usarlo: ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda(); ...
 * BTreePrinter.printNode(arbol.getRaiz());
 */
public class GraficadoraArbol {

    public static void printNode(NodoArbolAVL root) {
        int maxLevel = GraficadoraArbol.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<NodoArbolAVL> nodes, int level,
            int maxLevel) {
        if (nodes.isEmpty() || GraficadoraArbol.isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        GraficadoraArbol.printWhitespaces(firstSpaces);

        List<NodoArbolAVL> newNodes = new ArrayList<NodoArbolAVL>();
        for (NodoArbolAVL node : nodes) {
            if (node != null) {
                System.out.print(node.getDato());
                newNodes.add(node.getHijoIzquierdo());
                newNodes.add(node.getHijoDerecho());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            GraficadoraArbol.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                GraficadoraArbol.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    GraficadoraArbol.printWhitespaces(endgeLines + endgeLines + i
                            + 1);
                    continue;
                }

                if (nodes.get(j).getHijoIzquierdo() != null) {
                    System.out.print("/");
                } else {
                    GraficadoraArbol.printWhitespaces(1);
                }

                GraficadoraArbol.printWhitespaces(i + i - 1);

                if (nodes.get(j).getHijoDerecho() != null) {
                    System.out.print("\\");
                } else {
                    GraficadoraArbol.printWhitespaces(1);
                }

                GraficadoraArbol.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static int maxLevel(NodoArbolAVL node) {
        if (node == null) {
            return 0;
        }

        return Math.max(GraficadoraArbol.maxLevel(node.getHijoIzquierdo()),
                GraficadoraArbol.maxLevel(node.getHijoDerecho())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }

        return true;
    }

}
