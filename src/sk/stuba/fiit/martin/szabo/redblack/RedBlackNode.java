package sk.stuba.fiit.martin.szabo.redblack;

import sk.stuba.fiit.martin.szabo.bst.BstNode;

public class RedBlackNode extends BstNode{

    private Color color = Color.RED;

    // ANSI color constants
    private static final String RED_COLOR = "\u001B[31m";
    private static final String BLACK_COLOR = "\u001B[37m";
    private static final String RESET_COLOR = "\u001B[0m";

    public RedBlackNode(){}

    public RedBlackNode(Integer key){
        super(key);
    }

    public RedBlackNode(Integer key, Color color){
        super(key);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public RedBlackNode getLeft(){
        return (RedBlackNode) super.getLeft();
    }

    public void setLeft(RedBlackNode left){
        super.setLeft(left);
    }

    @Override
    public RedBlackNode getRight(){
        return (RedBlackNode) super.getRight();
    }

    public void setRight(RedBlackNode right){
        super.setRight(right);
    }

    @Override
    public RedBlackNode getParent(){
        return (RedBlackNode) super.getParent();
    }

    public RedBlackNode getGrandParent(){
        if(super.getParent() != null){
            return (RedBlackNode) super.getParent().getParent();
        }
        return null;
    }

    public RedBlackNode getUncle(){
        return this.getParent().getSibling();
    }

    public RedBlackNode getRightNephew(){
        return this.getSibling().getRight();
    }

    public RedBlackNode getLeftNephew(){
        return this.getSibling().getLeft();
    }

    public RedBlackNode getSibling(){
        if(super.getParent() == null) { return null; }

        if(super.getParent().getRight() == this){
            return (RedBlackNode) super.getParent().getLeft();
        }
        else if(super.getParent().getLeft() == this){
            return (RedBlackNode) super.getParent().getRight();
        }
        return null;
    }

    public void setParent(RedBlackNode parent){
        super.setParent(parent);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(this.getColor() == Color.BLACK){
            sb.append(BLACK_COLOR);
        }
        else{
            sb.append(RED_COLOR);
        }


        sb.append(this.getKey()).append(RESET_COLOR).append(": ");

        if(this.getLeft() != null){
            if(this.getLeft().getColor() == Color.BLACK){
                sb.append(BLACK_COLOR);
            }
            else{
                sb.append(RED_COLOR);
            }
            sb.append(this.getLeft().getKey()).append(RESET_COLOR).append(", ");
        }
        else{
            sb.append(BLACK_COLOR).append("x").append(RESET_COLOR).append(", ");
        }
        if(this.getRight() != null){
            if(this.getRight().getColor() == Color.BLACK){
                sb.append(BLACK_COLOR);
            }
            else{
                sb.append(RED_COLOR);
            }
            sb.append(this.getRight().getKey()).append(RESET_COLOR);
        }
        else{
            sb.append(BLACK_COLOR).append("x").append(RESET_COLOR);
        }

        return sb.toString();
    }

}
