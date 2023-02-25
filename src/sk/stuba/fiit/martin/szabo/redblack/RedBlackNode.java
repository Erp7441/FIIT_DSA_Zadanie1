package sk.stuba.fiit.martin.szabo.redblack;

import sk.stuba.fiit.martin.szabo.bst.BstNode;

public class RedBlackNode extends BstNode{

    private Color color = Color.RED;

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

    public void setParent(RedBlackNode parent){
        super.setParent(parent);
    }
}
