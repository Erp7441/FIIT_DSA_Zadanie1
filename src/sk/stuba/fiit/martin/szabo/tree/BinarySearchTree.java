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
     * Prints all nodes in the tree.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<BinarySearchNode> currentLevelBinarySearchNodes = new ArrayList<>();
        currentLevelBinarySearchNodes.add(this.getRoot());
        while(!currentLevelBinarySearchNodes.isEmpty()){
            ArrayList<BinarySearchNode> nextLevelBinarySearchNodes = new ArrayList<>();
            for(BinarySearchNode binarySearchNode : currentLevelBinarySearchNodes){
                sb.append(binarySearchNode).append("\n");
                nextLevelBinarySearchNodes.addAll(binarySearchNode.getAdjects());
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
    // TODO Auto decide value position and Auto adjust tree after value insertion.
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
        }
        else{
            splitNode.setRightChild(new BinarySearchNode(value));
        }
    }

    public void delete(Integer value){
        BinarySearchNode deleteNode = this.search(value);
        BinarySearchNode parent = deleteNode.getParent();

        // Case 1 leaf
        if(deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null){
            if(parent.getLeftChild() == deleteNode){
                parent.setLeftChild(null);
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

            if(deleteNode.getLeftChild() == null){
                child = deleteNode.getRightChild();
            }
            else{
                child = deleteNode.getLeftChild();
            }

            if(parent.getLeftChild() == deleteNode){
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
            child = deleteNode.getInorderSuccessor();

            if(parent.getLeftChild() == deleteNode){
                parent.setLeftChild(child);
            }
            else{
                parent.setRightChild(child);
            }
        }
    }

    public Boolean isBalanced(){
        ArrayList<BinarySearchNode> currentLevelBinarySearchNodes = new ArrayList<>();
        currentLevelBinarySearchNodes.add(this.getRoot());

        while(!currentLevelBinarySearchNodes.isEmpty()){
            ArrayList<BinarySearchNode> nextLevelBinarySearchNodes = new ArrayList<>();

            for(BinarySearchNode currentBinarySearchNode : currentLevelBinarySearchNodes){
                if(
                    (currentBinarySearchNode.getValue() - currentBinarySearchNode.getParent().getValue()) != 1 &&
                    (currentBinarySearchNode.getValue() - currentBinarySearchNode.getParent().getValue()) != 0
                ){
                    return false;
                }
                nextLevelBinarySearchNodes.addAll(currentBinarySearchNode.getAdjects());
            }

            currentLevelBinarySearchNodes = nextLevelBinarySearchNodes;
        }
        return true;
    }
}
