/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapp;

import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Marcin Antczak s11534
 */
public class GraphUtils {
    private static ArrayList<Node> visited = new ArrayList<>();
    private static ArrayList<Node> all = new ArrayList<>();
    private static Node copyRoot;
    private static Node copyCurrent;
    private static int n;
    
    public static void dfs(Node root) {
        visited.clear();
        n = 0;
        visit(root);
        for(Node n: visited)
        {
            System.out.println("Dla wierzchołka "+n.getLabel()+" low = "+n.getParam("low")+", dfnum = "+n.getParam("dfnum"));
        }
    }
    
    private static void visit(Node node) {
        visited.add(node);
        node.setParam("dfnum", n);
        node.setParam("low", n);
        n++;
        for(Node child: node.getNodes()) {
            if (!visited.contains(child)) {
                node.addSecondaryNode(child);
                visit(child);
                node.setParam("low", min((int) node.getParam("low"), (int) child.getParam("low")));
            }
            else {
                node.setParam("low", min((int) node.getParam("low"), (int) child.getParam("dfnum")));             
            }
        }
    }

    public static ArrayList<Node> cutVertices(Node root){
        ArrayList<Node> kList = new ArrayList<>();
        dfs(root);
        if (root.getSecondaryNodes().size() >= 1)
            kList.add(root);
        visited.remove(visited.iterator().next());
        for(Node v: visited) {
            for(Node child: v.getSecondaryNodes()) {
                if ((int)child.getParam("low") >= (int)v.getParam("dfnum")) {
                    if (!kList.contains(v))
                        kList.add(v);
                }
            }
        }
        
        Collections.sort(kList, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return -((int)o1.getParam("dfnum") - (int)o2.getParam("dfnum"));
            }
        });
        for (Node n: kList)
        {
            //System.out.println(n.getLabel());
        }
        return kList;
    } 
      
    public static ArrayList<ArrayList<Node>> findBlock(Node root){
        int i = 0;
        ArrayList<Node> kList = cutVertices(root);
        ArrayList<ArrayList<Node>> bList = new ArrayList<>();
        for (int n = 0; n < kList.size(); n++) {
            Node v = kList.get(n);
            //System.out.println("Secondary nodes dla "+v.getLabel()+": ");
            for (int k = 0; k < v.getSecondaryNodes().size(); k++) {
                Node child = v.getSecondaryNodes().get(k);
                //System.out.println(child.getLabel());
                if ((int)child.getParam("low") >= (int)v.getParam("dfnum")) {
                    i++;
                    Node newRoot = child;
                    ArrayList<Node> b = new ArrayList<>(getAllSecondary(newRoot));
                    b.add(v);
                    bList.add(b);
                    v.getSecondaryNodes().remove(child);
                }
            }
        }
        return bList;
    }
    
    private static ArrayList<Node> getAllSecondary(Node root) {
        all.clear();
        getSecondary(root);
        return all;
    }
    
    private static void getSecondary(Node node) {
        all.add(node);
        for (Node child: node.getSecondaryNodes()) {
            if (!all.contains(child))
                getSecondary(child);
        }
        node.getSecondaryNodes().clear();
    }
    
    public static Node copyGraph(Node root) {
        copy(null, null, root);
        return copyRoot;
    }
    
    private static void copy(Node parent, Node secondaryParent, Node node) {
        copyCurrent = new Node(node.getLabel());
        copyCurrent.setOptional(new HashMap<>(node.getOptional()));
        if (copyRoot == null)
            copyRoot = copyCurrent;
        if (parent != null)
            parent.addNode(copyCurrent);
        if (secondaryParent != null)
            secondaryParent.addSecondaryNode(copyCurrent);
        for (Node primary: node.getNodes()) {
            copy(copyCurrent, null, primary);
        }
        for (Node secondary: node.getSecondaryNodes()) {
            copy(null, copyCurrent, secondary);
        }
    }
}
