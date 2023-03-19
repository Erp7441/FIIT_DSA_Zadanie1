package sk.stuba.fiit.martin.szabo.tree.bst;

import java.util.ArrayList;

/**
 * BstNode represents a node of a binary search tree. It has an integer key value, a string value, left and right child
 * nodes, and a parent node. The class provides methods to find the minimum and maximum nodes of a subtree, the in-order
 * predecessor and successor of a node, and the deepest node of the tree.
 */
public class BstNode{

    //* Attributes
    private Integer key = null;
    private String value = null;
    private BstNode left = null;
    private BstNode right = null;
    private BstNode parent = null;

    //* Constructors

    /**
     * Creates a new Binary Search Tree Node with null values for its attributes.
     */
    public BstNode(){}

    /**
     * Creates a new Binary Search Tree Node with the specified key and null value for its attributes.
     * @param key the key value of the node
     */
    public BstNode(Integer key){
        this.key = key;
    }

    /**
     * Creates a new BstNode with the specified key and value.
     * @param key the key value of the node
     * @param value the value of the node
     */
    public BstNode(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    //* Utility methods


    /**
     * Returns the node with the minimum key value in the subtree rooted at the given node. If the given root node is null,
     * null is returned. This method traverses the subtree by moving down the left side of the tree, since the smallest
     * value is guaranteed to be in the leftmost node. If the left child of a node is smaller than the node itself, we
     * continue moving down the left subtree; otherwise, we have found the node with the minimum key value and return it.
     *
     * @param root the root of the subtree to search for the minimum node
     * @return the node with the minimum key value in the subtree, or null if the subtree is empty
     */
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

    /**
     * Returns the node with the maximum key value in the subtree rooted at the given node. If the given root node is
     * null, null is returned. This method traverses the subtree by moving down the right side of the tree, since the
     * largest value is guaranteed to be in the rightmost node. If the right child of a node is greater than the node
     * itself, we continue moving down the right subtree; otherwise, we have found the node with the maximum key value
     * and return it.
     *
     * @param root the root node of the binary search tree to search for the maximum key value.
     * @return the node with the maximum key value in the given binary search tree, or null if the tree is empty.
     */
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

    /**
     * This method returns the in-order predecessor node of the given node in a binary search tree. An in-order
     * predecessor is the node with the greatest key value smaller than the key value of the given node. If the
     * given node has a left subtree, the in-order predecessor will be the rightmost node in that subtree.
     * Otherwise, there is no in-order predecessor for the given node.
     *
     * @param root the node for which the in-order predecessor is required
     * @return the in-order predecessor of the given node or null if it doesn't have one
     */
    public static BstNode getInOrderPredecessor(BstNode root){
        if(root.getLeft() != null){
            // Gets max node value from left subtree
            return BstNode.maximum(root.getLeft());
        }
        return null;
    }

    /**
     * Returns the in-order successor of the given node. If the right subtree of the given node is not
     * null, then the in-order successor is the minimum node of the right subtree.
     *
     * @param root the root node of the binary search tree to search for in-order successor
     * @return the in-order successor of the given node, or null if the right subtree is null
     */
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
        sb.append(", ").append(this.getValue());
        return sb.toString();
    }

    /**
     * Returns the deepest node in a binary search tree rooted at the specified root. If there are multiple
     * deepest nodes, returns the one that appears first in an in-order traversal.
     *
     * @param root The root of the binary search tree.
     * @return The deepest node in the binary search tree. Returns null if the root is null.
     */
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