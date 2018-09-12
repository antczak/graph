/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Marcin Antczak s11534
 * 
 * Format danych wejściowych (plik2.txt)
 * Jedna lina odpowiada jednemu wierzchołkowi
 * W każdej lini zapisane są etykiety wierzchołków (oddzielone przecinkami), z którymi wybrany wierzchołek jest połaczony
 * 0 oznacza, że wierzchołek na starcie nie jest połączony z żadnym innym (w kolejnych liniach inny wierzchołek może zostać do niego podłączony)
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        GraphReader oldReader = new GraphReader(new File("plik.txt"));
        
        //Poprzednie zadanie
        Node treeRoot = oldReader.readTree();
        //System.out.println("Drzewo");
        //System.out.println("Wysokość: "+TreeUtils.getHeight(treeRoot));
        //System.out.println("Największa etykieta: "+TreeUtils.findBiggestLabel(treeRoot));
        //System.out.println("Wierzchołek z największą ilością dzieci: "+TreeUtils.findLabelWithMostChild(treeRoot));
        
        //Nowe zadanie
        System.out.println("\n\nNowe zadanie");
        GraphReader reader = new GraphReader(new File("plik2.txt"));
        Node root = reader.readGraph();
        ArrayList<ArrayList<Node>> blocks = GraphUtils.findBlock(root);
        System.out.println("Znalezione bloki: ");
        for (ArrayList<Node> bList: blocks) {
            for (Node v: bList) {
                System.out.print(v.getLabel()+" ");
            }
            System.out.println();
        }
    }
    
}
