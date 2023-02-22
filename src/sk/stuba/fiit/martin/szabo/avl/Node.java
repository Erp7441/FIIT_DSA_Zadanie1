package sk.stuba.fiit.martin.szabo.avl;

public class Node{

    //* Attributes
    private Integer key;
    private Node left = null;
    private Node right = null;
    private Node parent = null;
    private Integer height = 0;
    private Integer balance = 0;

    //* Constructors
    public Node(Integer key){
        this.key = key;
    }

    //* Utility methods

    public Integer calculateBalance(){
        Integer leftHeight = left != null ? left.calculateHeight() : 0;
        Integer rightHeight = right != null ? right.calculateHeight() : 0;
        this.balance = leftHeight - rightHeight;
        return this.balance;
    }

    // TODO:: Refactor to avoid recursion. This is killing your performance
    public Integer calculateHeight(){
        int leftHeight = left != null ? left.calculateHeight() : 0;
        int rightHeight = right != null ? right.calculateHeight() : 0;
        this.setHeight((Math.max(leftHeight, rightHeight)) + 1);
        return this.getHeight();
    }

    // Gets minimum of tree
    public static Node minimum(Node root){
        Node current = root;
        while (current.getLeft() != null){
            current = current.getLeft();
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
        while(current == parent.getRight()){
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

    //* Getters and setters
    public Node getLeft(){
        return left;
    }

    public void setLeft(Node left){
        this.left = left;
        if(left != null){
            left.setParent(this);
        }
    }

    public Node getRight(){
        return right;
    }

    public void setRight(Node right){
        this.right = right;
        if(right != null){
            right.setParent(this);
        }
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