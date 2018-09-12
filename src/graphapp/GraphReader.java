/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Marcin Antczak s11534
 */
public class GraphReader {
    private File file;
    public GraphReader(File file){
       this.setFile(file);
    }
    
    @Deprecated
    public Node readTree() throws FileNotFoundException{
        Node root = new Node(1);
        Scanner sc = new Scanner(this.getFile()).useDelimiter(",");
        int i = 2;
        while(sc.hasNext())
        {
            String next = sc.next();
            int n = Integer.parseInt(next);
            Node node = new Node(i);
            if (n != 0)
            {
                Node parent = TreeUtils.find(root, n);
                if (parent != null)
                    parent.addNode(node);
            }
            i++;
        }
        return root;
    }
    
    public Node readGraph() throws FileNotFoundException{
        HashMap<Integer, Node> allNodes = new HashMap<>();
        Scanner sc = new Scanner(this.getFile()).useDelimiter("\n");
        int label = 1;
        Node root = null;
        while(sc.hasNext()){
            Node node = new Node(label);
            if (label == 1)
                root = node;
            allNodes.put(label, node);
            String[] connected = sc.next().split(",");
            for(String value: connected){
                int parent = Integer.parseInt(value);
                if (parent != 0 && parent != node.getLabel()){
                    Node n = allNodes.get(parent);
                    n.addNode(node);
                    node.addNode(n);
                }
            }
            label++;
            
        }
        return root;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
}
