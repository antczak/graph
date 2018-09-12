/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marcin Antczak s11534
 */
public class Node {
    private int label;
    private ArrayList<Node> nodes;
    private ArrayList<Node> secondaryNodes;
    private HashMap<String, Object> optional;
    public Node(int label)
    {
        this.setLabel(label);
        this.setNodes(new ArrayList<>());
        this.setSecondaryNodes(new ArrayList<>());
        optional = new HashMap<>();
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void addNode(Node node) {
        if (!this.getNodes().contains(node) && node != null)
            this.getNodes().add(node);
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Node> getSecondaryNodes() {
        return secondaryNodes;
    }

    public void setSecondaryNodes(ArrayList<Node> secondaryNodes) {
        this.secondaryNodes = secondaryNodes;
    }
    
    public void addSecondaryNode(Node node) {
        if (!this.getSecondaryNodes().contains(node) && node != null)
            this.getSecondaryNodes().add(node);
    }

    public HashMap<String, Object> getOptional() {
        return optional;
    }

    public void setOptional(HashMap<String, Object> optional) {
        this.optional = optional;
    }
    
    public void setParam(String name, Object value) {
        optional.put(name, value);
    }
    
    public Object getParam(String name) {
        if (optional.containsKey(name))
            return optional.get(name);
        return null;
    }
    
    public void secondaryToMain() {
        if (!this.getSecondaryNodes().isEmpty()) {
            this.getNodes().clear();
            this.getNodes().addAll(this.getSecondaryNodes());
            this.getSecondaryNodes().clear();
        }
    }
    
}
