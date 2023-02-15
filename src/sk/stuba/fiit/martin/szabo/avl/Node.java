package sk.stuba.fiit.martin.szabo.avl;

public class Node{

    //* Attributes
    private Node left = null;
    private Node right = null;
    private Node parent = null;
    private Integer height = 0;
    private Integer depth = 0;
    private Integer balance = 0;

    //* Constructors
    public Node(){
    }

    //* Utility methods
    public Integer calculateBalance(){
        Integer leftHeight = left != null ? left.getHeight() : 0;
        Integer rightHeight = right != null ? right.getHeight() : 0;
        this.balance = leftHeight - rightHeight;
        return this.balance;
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
}
