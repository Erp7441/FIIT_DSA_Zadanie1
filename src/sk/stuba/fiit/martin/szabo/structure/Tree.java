package sk.stuba.fiit.martin.szabo.structure;

import sk.stuba.fiit.martin.szabo.utils.Position;

import java.util.ArrayList;

import static java.lang.System.*;

public class Tree{

    private Node root;

    public Tree(Node root){
        this.root = root;
    }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }

    /**
     * Prints all nodes in the tree.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<Node> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(this.getRoot());
        while(!currentLevelNodes.isEmpty()){
            ArrayList<Node> nextLevelNodes = new ArrayList<>();
            for(Node node : currentLevelNodes){
                sb.append(node).append("\n");
                nextLevelNodes.addAll(node.getAdjects());
            }
            currentLevelNodes = nextLevelNodes;
        }
        return sb.toString();
    }

    /**
     * Searched for a node in the tree based on its value
     *
     * @param value - value of node we are looking for
     * @return - node reference we found
     */
    public Node search(Object value){
        ArrayList<Node> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(this.getRoot());
        while(!currentLevelNodes.isEmpty()){
            ArrayList<Node> nextLevelNodes = new ArrayList<>();
            for(Node currentNode : currentLevelNodes){
                if(currentNode.getValue().equals(value)){
                    return currentNode;
                }
                nextLevelNodes.addAll(currentNode.getAdjects());
            }
            currentLevelNodes = nextLevelNodes;
        }
        return null;
    }

    /**
     * Inserts a new node at the specified position in the tree.
     *
     * @param value - the value to be inserted into the tree
     * @param parent - value of node that is above the inserted node
     * @param position - position where the new node should be inserted
     */
    public void insert(Object value, int parent, Position position){
        Node parentNode = this.search(parent);
        if(parentNode == null){
            err.println("Parent node with value "+ value.toString() + " not found");
            return;
        }
        parentNode.addAdjecent(value, position);
    }

    public void insert(Object value, int parent){
        // Find the parent node based on its value
        Node parentNode = this.search(parent);
        if(parentNode == null){
            err.println("Parent node with value "+ value.toString() +" not found");
            return;
        }
        // Add adjacent to that node
        parentNode.addAdjecent(value);
    }

    public void delete(Object value){
        Node deletedNode = this.search(value);
        if(deletedNode == null){
            err.println("Node with value "+ value.toString() +" not found");
            return;
        }

        // Finds if "deleteNode" is on the left or right side of the parent
        if(deletedNode.getTop().getLeft() == deletedNode){
            deletedNode.getTop().setLeft(null);
        }
        else if(deletedNode.getTop().getRight() == deletedNode){
            deletedNode.getTop().setRight(null);
        }
    }
}
