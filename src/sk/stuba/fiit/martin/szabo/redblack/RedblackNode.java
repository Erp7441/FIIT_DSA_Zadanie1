package sk.stuba.fiit.martin.szabo.redblack;

import sk.stuba.fiit.martin.szabo.bst.BstNode;

public class RedblackNode extends BstNode{

    private Color color = null;

    public RedblackNode(){}

    public RedblackNode(Integer key, Color color){
        super(key);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
