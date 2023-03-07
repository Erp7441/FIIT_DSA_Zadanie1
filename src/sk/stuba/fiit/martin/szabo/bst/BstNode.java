package sk.stuba.fiit.martin.szabo.bst;

import sk.stuba.fiit.martin.szabo.avl.AvlNode;

import java.util.ArrayList;

public class BstNode{

    //* Attributes
    private Integer key = null;
    private String value = null;
    private BstNode left = null;
    private BstNode right = null;
    private BstNode parent = null;

    //* Constructors

    public BstNode(){}
    public BstNode(Integer key){
        this.key = key;
    }
    public BstNode(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    //* Utility methods



    public static BstNode minimum(BstNode root){

        if(root == null){ return null; }

        BstNode current = root;

        // We go through all the left nodes of subtree.
        while (current.getLeft() != null){
            // If left node has smaller key than right node then we want to move left
            if(current.getKey() > current.getLeft().getKey()){
                current = current.getLeft();
            }
            // Else we found the minimal node
            else{
                break;
            }
        }
        return current;
    }

    public static BstNode maximum(BstNode root){

        if(root == null){ return null; }

        // We go through all the right nodes of subtree.
        BstNode current = root;
        while (current.getRight() != null){
            // If right node has bigger key than current node then we want to move right
            if(current.getKey() < current.getRight().getKey()){
                current = current.getRight();
            }
            // Else we found the maximum node
            else{
                break;
            }
        }
        return current;
    }

    public static BstNode getInOrderPredeecesor(BstNode root){
        if(root.getLeft() != null){
            // Gets max node value from left subtree
            return BstNode.maximum(root.getLeft());
        }
        return null;
    }

    public static BstNode getInOrderSuccessor(BstNode root){
        if(root.getRight() != null){
            // Gets min node value from right subtree
            return BstNode.minimum(root.getRight());
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.key).append(": ");
        if(this.left != null){
            sb.append(this.left.getKey()).append(", ");
        }
        else{
            sb.append("x, ");
        }
        if(this.right != null){
            sb.append(this.right.getKey());
        }
        else{
            sb.append("x");
        }
        return sb.toString();
    }

    public static BstNode getDeepestNode(BstNode root){
        ArrayList<BstNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(root); // Gets current level nodes

        while(true){

            ArrayList<BstNode> nextLevelNodes = new ArrayList<>();

            for(BstNode node : currentLevelNodes){ // For each node on current level
                if(node != null && node.getLeft() != null){ // And append it to the list of next level nodes.
                    nextLevelNodes.add(node.getLeft());
                }
                if(node != null && node.getRight() != null){
                    nextLevelNodes.add(node.getRight());
                }
            }
            if(nextLevelNodes.isEmpty()){ break; }
            currentLevelNodes = nextLevelNodes;
        }
        return currentLevelNodes.get(0);
    }

    //* Getters and setters
    public BstNode getLeft(){
        return left;
    }

    public void setLeft(BstNode left){
        this.left = left;
        if(left != null){
            left.setParent(this);
        }
    }

    public BstNode getRight(){
        return right;
    }

    public void setRight(BstNode right){
        this.right = right;
        if(right != null){
            right.setParent(this);
        }
    }

    public BstNode getParent(){
        return parent;
    }

    public void setParent(BstNode parent){
        this.parent = parent;
    }

    public Integer getKey(){
        return key;
    }

    public void setKey(Integer key){
        this.key = key;
    }

    public boolean isOnLeft(){
        if(this.getParent() == null){
            return true;
        }
        return this.getParent().getLeft() == this;
    }

    public boolean isOnRight(){
        if(this.getParent() == null){
            return true;
        }
        return this.getParent().getRight() == this;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}