package sk.stuba.fiit.martin.szabo.avl;

import java.util.ArrayList;

public class Node{

    //* Attributes
    private Integer key;
    private Node left = null;
    private Node right = null;
    private Node parent = null;
    private Integer height = 0;
    private Integer depth = 0;
    private Integer balance = 0;

    //* Constructors
    public Node(Integer key){
        this.key = key;
    }

    //* Utility methods

    public Integer calculateBalance(){
        Integer leftHeight = left != null ? left.getHeight() : 0;
        Integer rightHeight = right != null ? right.getHeight() : 0;
        this.balance = leftHeight - rightHeight;
        return this.balance;
    }

    public Integer calculateHeight(){
        Integer height = -1;

        ArrayList<Node> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(this); // Gets current level nodes

        while(!currentLevelNodes.isEmpty()){

            ArrayList<Node> nextLevelNodes = new ArrayList<>();
            for(Node node : currentLevelNodes){ // For each node on current level
                if(node != null && node.getLeft() != null){ // And append it to the list of next level nodes.
                    nextLevelNodes.add(node.getLeft());
                }
                if(node != null && node.getRight() != null){
                    nextLevelNodes.add(node.getRight());
                }
            }
            currentLevelNodes = nextLevelNodes;
            height++;
        }
        this.setHeight(height);
        return height;
    }
    public Integer calculateDepth(){
        Integer depth = 0;
        Node current = this;
        while(current.getParent() != null){
            current = current.getParent();
            depth++;
        }
        this.setDepth(depth);
        return depth;
    }

    public Integer getChildCount(){
        if (this.getLeft() == null && this.getRight() == null){
            return 0;
        }
        else if(this.getLeft() != null && this.getRight() != null){
            return 2;
        }
        return 1;
    }

    // Gets minimum of tree
    public static Node minimum(Node root){
        Node current = root;
        while (current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    // Gets maximum of tree
    public static Node maximum(Node root){
        Node current = root;
        while (current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }

    // Gets the next node with minimal value in order of a tree
    public static Node getInOrderSuccessor(Node root){
        // If right tests exits look for minimal value of the tests
        if(root.getRight() != null){
            return Node.minimum(root.getRight());
        }

        Node parent = root.getParent();
        Node current = root;
        // While we are the right child of a parent
        while(current != null && current == parent.getRight()){
            current = parent;
            parent = parent.getParent(); // Move up a parent
        }
        return parent; // If we are the left child. We return parent as the inorder successor
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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Node)) return false;

        Node node = (Node) o;

        if(!getKey().equals(node.getKey())) return false;
        if(!getLeft().equals(node.getLeft())) return false;
        if(!getRight().equals(node.getRight())) return false;
        if(!getParent().equals(node.getParent())) return false;
        if(!getHeight().equals(node.getHeight())) return false;
        if(!getDepth().equals(node.getDepth())) return false;
        return getBalance().equals(node.getBalance());
    }

    @Override
    public int hashCode(){
        int result = getKey().hashCode();
        result = 31 * result + getLeft().hashCode();
        result = 31 * result + getRight().hashCode();
        result = 31 * result + getParent().hashCode();
        result = 31 * result + getHeight().hashCode();
        result = 31 * result + getDepth().hashCode();
        result = 31 * result + getBalance().hashCode();
        return result;
    }

    //* Getters and setters
    public Node getLeft(){
        return left;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public Node getRight(){
        return right;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Integer getHeight(){
        return height;
    }

    public void setHeight(Integer height){
        this.height = height;
    }

    public Integer getDepth(){
        return depth;
    }

    public void setDepth(Integer depth){
        this.depth = depth;
    }

    public Integer getBalance(){
        return balance;
    }

    public Integer getKey(){
        return key;
    }

    public void setKey(Integer key){
        this.key = key;
    }
}