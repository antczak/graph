/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapp;

/**
 *
 * @author Marcin Antczak s11534
 */
public class TreeUtils {
    public static int findBiggestLabel(Node root){
        if (root.getNodes().isEmpty())
            return root.getLabel();
        int max = 0;
        for(Node node: root.getNodes()){
            int label = findBiggestLabel(node);
            if (label > max)
                max = label;
        }
        return max;
    }

    public static int findLabelWithMostChild(Node root){
        int maxlabel = root.getLabel();
        int max = root.getNodes().size();
        for(Node node: root.getNodes()){
            int childnum = findLabelWithMostChild(node);
            if (childnum > max){
                max = childnum;
                maxlabel = node.getLabel();
            }
        }
        return maxlabel;
    }

    public static void printTree(Node root){
        System.out.println(root.getLabel());
        for(Node node: root.getNodes()){
            printTree(node);
        }
    }

    public static int getHeight(Node root){
        if (root.getNodes().isEmpty())
            return 0;
        int max = 0;
        for(Node node: root.getNodes()){
            int height = getHeight(node);
            if (height > max)
                max = height;
        }
        return max + 1;       
    }
    
    public static Node find(Node root, int label){
        if (root.getLabel() == label)
            return root;
        for(Node node: root.getNodes()){
            Node result = find(node, label);
            if(result != null)
                return result;
        } 
        return null;
    }
}
