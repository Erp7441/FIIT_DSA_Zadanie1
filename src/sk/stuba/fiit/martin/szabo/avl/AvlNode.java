package sk.stuba.fiit.martin.szabo.avl;

import sk.stuba.fiit.martin.szabo.bst.BstNode;

public class AvlNode extends BstNode{

    //* Attributes

    private Integer balance = 0;

    //* Constructors
    public AvlNode(Integer key){
        super(key);
    }

    //* Utility methods

    public Integer calculateBalance(){
        Integer leftHeight = this.getLeft() != null ? this.getLeft().calculateHeight() : 0;
        Integer rightHeight = this.getRight() != null ? this.getRight().calculateHeight() : 0;
        this.balance = leftHeight - rightHeight;
        return this.balance;
    }
    public Integer getBalance(){
        return balance;
    }
}