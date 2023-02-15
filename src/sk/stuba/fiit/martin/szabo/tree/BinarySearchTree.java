package sk.stuba.fiit.martin.szabo.tree;

import sk.stuba.fiit.martin.szabo.tree.BinarySearchNode;

import java.util.ArrayList;

import static java.lang.System.*;

public class BinarySearchTree{

    private BinarySearchNode root;

    public BinarySearchTree(){
        this.root = null;
    }
    public BinarySearchTree(BinarySearchNode root){
        this.root = root;
    }

    public BinarySearchNode getRoot(){
        return root;
    }

    public void setRoot(BinarySearchNode root){
        this.root = root;
    }

    /**
     * Prints all nodes in the tree. Level by level.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<BinarySearchNode> currentLevelBinarySearchNodes = new ArrayList<>();
        currentLevelBinarySearchNodes.add(this.getRoot()); // Gets current level nodes
        while(!currentLevelBinarySearchNodes.isEmpty()){
            ArrayList<BinarySearchNode> nextLevelBinarySearchNodes = new ArrayList<>();
            for(BinarySearchNode binarySearchNode : currentLevelBinarySearchNodes){ // For each node on current level
                sb.append(binarySearchNode).append("\n"); // Append it to the final output
                if(binarySearchNode != null && binarySearchNode.getLeftChild() != null){ // And append it to the list of next level nodes.
                    nextLevelBinarySearchNodes.add(binarySearchNode.getLeftChild());
                }
                if(binarySearchNode != null && binarySearchNode.getRightChild() != null){
                    nextLevelBinarySearchNodes.add(binarySearchNode.getRightChild());
                }
            }
            currentLevelBinarySearchNodes = nextLevelBinarySearchNodes;
        }
        return sb.toString();
    }

    /**
     * Searched for a node in the tree based on its value
     *
     * @param value - value of node we are looking for
     * @return - node reference we found
     */
    public BinarySearchNode search(Integer value){

        BinarySearchNode splitNode = root;

        // Check if tree is empty
        if(splitNode == null){
            return null;
        }

        // If left and right is not empty
        while(splitNode.getLeftChild() != null && splitNode.getRightChild() != null){

            // Move to the left?
            if(value < splitNode.getValue()){
                splitNode = splitNode.getLeftChild();
            }
            // Or to the right?
            else if(value > splitNode.getValue()){
                splitNode = splitNode.getRightChild();
            }
            // Or I am currently on a node that I am looking for?
            else{
                return splitNode;
            }
        }

        return splitNode;
    }

    /**
     * Inserts a new node at the specified position in the tree.
     *
     * @param value - the value to be inserted into the tree
     */
    public void insert(Integer value){

        BinarySearchNode splitNode = root;

        // Check if tree is empty
        if(splitNode == null){
            this.setRoot(new BinarySearchNode(value));
            return;
        }

        // If left and right is not empty
        while(splitNode.getLeftChild() != null && splitNode.getRightChild() != null){

            // Move to the left?
            if(value < splitNode.getValue()){
                splitNode = splitNode.getLeftChild();
            }
            // Or to the right?
            else if(value > splitNode.getValue()){
                splitNode = splitNode.getRightChild();
            }
            // Error state
            else{
                throw new IllegalStateException("Invalid tree");
            }
        }

        // Eventually we will get to the bottom of the tree
        // That's when we want to decide where to insert the new node
        if(value < splitNode.getValue()){
            splitNode.setLeftChild(new BinarySearchNode(value));
            splitNode.getLeftChild().setParent(splitNode);
        }
        else{
            splitNode.setRightChild(new BinarySearchNode(value));
            splitNode.getRightChild().setParent(splitNode);
        }
    }

    public void insert(BinarySearchNode node){
        Integer value = node.getValue();
        BinarySearchNode splitNode = root; // TODO:: Resolve duplicate code warking

        // Check if tree is empty
        if(splitNode == null){
            this.setRoot(node);
            return;
        }

        // If left and right is not empty
        while(splitNode.getLeftChild() != null && splitNode.getRightChild() != null){

            // Move to the left?
            if(value < splitNode.getValue()){
                splitNode = splitNode.getLeftChild();
            }
            // Or to the right?
            else if(value > splitNode.getValue()){
                splitNode = splitNode.getRightChild();
            }
            // Error state
            else{
                throw new IllegalStateException("Invalid tree");
            }
        }

        // Eventually we will get to the bottom of the tree
        // That's when we want to decide where to insert the new node
        if(value < splitNode.getValue()){
            splitNode.setLeftChild(node);
            splitNode.getLeftChild().setParent(splitNode);
        }
        else{
            splitNode.setRightChild(node);
            splitNode.getRightChild().setParent(splitNode);
        }

        // TODO:: Move node
    }

    public void delete(Integer value){
        BinarySearchNode deleteNode = this.search(value);
        BinarySearchNode parent = deleteNode.getParent();

        // We are deleteing a leaf
        if(deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null){
            if(parent.getLeftChild() == deleteNode){
                parent.setLeftChild(null); // Just removing leaf from tree
            }
            else{
                parent.setRightChild(null);
            }
        }

        // Case 2 Node with one child
        else if(
            deleteNode.getLeftChild() == null && deleteNode.getRightChild()!= null ||
            deleteNode.getRightChild() != null && deleteNode.getLeftChild() == null
        ){
            BinarySearchNode child;

            // Looking which child is empty
            if(deleteNode.getLeftChild() == null){
                // Getting the child that is not empty
                child = deleteNode.getRightChild();
            }
            else{
                child = deleteNode.getLeftChild();
            }

            if(parent.getLeftChild() == deleteNode){
                // Setting the child as a parent's child
                parent.setLeftChild(child);
            }
            else{
                parent.setRightChild(child);
            }
        }

        // Case 3 Node with two children
        else if(
            deleteNode.getLeftChild()!= null && deleteNode.getRightChild()!= null
        ){
            BinarySearchNode child;

            // Getting the smallest child that is bigger than the node to be deleted
            child = deleteNode.getInorderSuccessor();

            // Removing the child from parent side
            if(child.getParent().getLeftChild() == child){
                child.getParent().setLeftChild(null);
            }
            else if(child.getParent().getRightChild() == child){
                child.getParent().setRightChild(null);
            }
            // Removing the parent from child side
            child.setParent(null);

            // Now that the child node is completely removed and doesn't have a parent or left nor right children
            // Figuring out the placement of the node to be deleted and replacing it with the child node
            if(parent.getLeftChild() == deleteNode){
                parent.setLeftChild(child);
            }
            else{
                parent.setRightChild(child);
            }

            // Setting up connections from node to be deleted to the child
            child.setLeftChild(deleteNode.getLeftChild());
            child.setRightChild(deleteNode.getRightChild());
            child.setParent(parent);

        }
    }

    public Boolean isBalanced(){

        return false; // TOOD Implement this
    }
}
