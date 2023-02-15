package sk.stuba.fiit.martin.szabo.tree;
import java.util.ArrayList;

import static java.lang.System.err;


public class AvlTree{

    private AvlNode root;

    public AvlTree(){
        this.root = null;
    }
    public AvlTree(AvlNode root){
        this.root = root;
    }

    public AvlNode getRoot(){
        return root;
    }
    public void setRoot(AvlNode root){
        this.root = root;
    }

    /**
     * Prints all nodes in the tree. Level by level.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<AvlNode> currentLevelAvlNodes = new ArrayList<>();
        currentLevelAvlNodes.add(this.getRoot()); // Gets current level nodes
        while(!currentLevelAvlNodes.isEmpty()){
            ArrayList<AvlNode> nextLevelAvlNodes = new ArrayList<>();
            for(AvlNode binarySearchNode : currentLevelAvlNodes){ // For each node on current level
                sb.append(binarySearchNode).append("\n"); // Append it to the final output
                if(binarySearchNode != null && binarySearchNode.getLeftChild() != null){ // And append it to the list of next level nodes.
                    nextLevelAvlNodes.add(binarySearchNode.getLeftChild());
                }
                if(binarySearchNode != null && binarySearchNode.getRightChild() != null){
                    nextLevelAvlNodes.add(binarySearchNode.getRightChild());
                }
            }
            currentLevelAvlNodes = nextLevelAvlNodes;
        }
        return sb.toString();
    }

    public AvlNode search(Integer value){
        return null;
    }
    public void insert(AvlNode node){

        AvlNode splitNode = root;

        // Check if tree is empty
        if(splitNode == null){
            this.setRoot(node);
            return;
        }

        // If left and right is not empty
        while(splitNode.getLeftChild() != null && splitNode.getRightChild() != null){

            // Move to the left?
            if(node.getValue() < splitNode.getValue()){
                splitNode = splitNode.getLeftChild();
            }
            // Or to the right?
            else if(node.getValue() > splitNode.getValue()){
                splitNode = splitNode.getRightChild();
            }
            // Error state
            else{
                throw new IllegalStateException("Invalid tree");
            }
        }

        // Eventually we will get to the bottom of the tree
        // That's when we want to decide where to insert the new node
        if(node.getValue() < splitNode.getValue()){
            splitNode.setLeftChild(node);
            splitNode.getLeftChild().setParent(splitNode);
        }
        else{
            splitNode.setRightChild(node);
            splitNode.getRightChild().setParent(splitNode);
        }

        //TODO:: Rebalance tree
    }
    public void delete(Integer value){

        // TODO:: Reimplement this


    }

    public void leftRotation(AvlNode n1, AvlNode n2){
        // Taking left child of n2 and assinging as right child of n1
        if(n2.getLeftChild() != null){
            n2.getLeftChild().setParent(n1);
            n1.setRightChild(n2.getLeftChild());
        }

        // Check if we need to modify root of the tree
        if(n1 == this.getRoot()){
            this.setRoot(n2);
        }


        // Now that n2 left child is empty we need to move n1 there
        n2.setParent(n1.getParent()); // We "copy" old top node parent and assing it to the new top node
        n1.setParent(n2); // We set old top node parent to the new top node
        n2.setLeftChild(n1); // Same thing as above from new top node perspective
    }

    public void rightRotation(AvlNode n1, AvlNode n2){
        if(n1.getRightChild() != null){
            n1.getRightChild().setParent(n2);
            n2.setLeftChild(n1.getRightChild());
        }

        // Check if we need to modify root of the tree
        if(n2 == this.getRoot()){
            this.setRoot(n1);
        }

        // Now that n1 right child is empty we need to move n2 there
        n1.setParent(n2.getParent()); // We "copy" old top node parent and assing it to the new top node
        n2.setParent(n1); // We set old top node parent to the new top node
        n1.setRightChild(n2); // Same thing as above from new top node perspective
    }

    public void rotate(AvlNode n1, AvlNode n2){

        // Do a left rotation
        if(n1.getRightChild() == n2 || n2.getRightChild() == n1){
            this.leftRotation(n1, n2);
        }

        // Do a right rotation
        else if(n1.getLeftChild() == n2 || n2.getLeftChild() == n1){
            this.rightRotation(n1, n2);
        }

    }

}
