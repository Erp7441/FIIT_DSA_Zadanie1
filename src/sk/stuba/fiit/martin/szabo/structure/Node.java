package sk.stuba.fiit.martin.szabo.structure;

public class Node{
    private Object value = null;
    private Node left = null;
    private Node right = null;

    public Node(){}
    public Node(Object value){
        this.value = value;
    }
    public Node(Node left, Node right, Object value){
        this.left = left;
        this.right = right;
        this.value = value;
    }
    public Node(Node node, boolean left, Object value){
        if(left){
            this.left = node;
        }
        else{
            this.right = node;
        }
        this.value = value;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public Object getValue(){
        return this.value;
    }
    public void setLeft(Node node){
        this.left = node;
    }
    public void setRight(Node node){
        this.right = node;
    }
    public void setValue(Object value){
        this.value = value;
    }
    public void printNodes(){
        System.out.println(this.getValue());
        if(this.left != null){
            this.left.printNodes();
        }
        if(this.right != null){
            this.right.printNodes();
        }
    }
}
