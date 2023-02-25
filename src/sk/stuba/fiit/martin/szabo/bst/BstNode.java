package sk.stuba.fiit.martin.szabo.bst;

public class BstNode{

    //* Attributes
    private Integer key;
    private BstNode left = null;
    private BstNode right = null;
    private BstNode parent = null;
    private Integer height = 0;
    //* Constructors
    public BstNode(Integer key){
        this.key = key;
    }

    //* Utility methods

    // TODO:: Refactor to avoid recursion. This is killing your performance
    public Integer calculateHeight(){
        int leftHeight = left != null ? left.calculateHeight() : 0;
        int rightHeight = right != null ? right.calculateHeight() : 0;
        this.setHeight((Math.max(leftHeight, rightHeight)) + 1);
        return this.getHeight();
    }

    // Gets minimum of tree
    public static BstNode minimum(BstNode root){
        BstNode current = root;
        while (current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    // Gets maximum of tree
    public static BstNode maximum(BstNode root){
        BstNode current = root;
        while (current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }

    // Gets the next node with minimal value in order of a tree
    public static BstNode getInOrderSuccessorOrPredeecesor(BstNode root){
        if(root.getRight() != null){
            // In order successor
            return BstNode.minimum(root.getRight());
        }
        else if(root.getLeft() != null){
            return BstNode.maximum(root.getLeft());
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

    public Integer getHeight(){
        return height;
    }

    public void setHeight(Integer height){
        this.height = height;
    }

    public Integer getKey(){
        return key;
    }

    public void setKey(Integer key){
        this.key = key;
    }
}