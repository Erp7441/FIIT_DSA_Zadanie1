package sk.stuba.fiit.martin.szabo.tree.avl;

import sk.stuba.fiit.martin.szabo.tree.bst.BstNode;

public class AvlNode extends BstNode{

    //* Attributes

    private Integer height = 0;
    private Integer balance = 0;

    //* Constructors
    public AvlNode(Integer key){
        super(key, null);
    }
    public AvlNode(Integer key, String value){
        super(key, value);
    }

    //* Utility methods

    public Integer calculateBalance(){
        if (this.getLeft() == null && this.getRight() == null) {
            this.setBalance(0); // height of leaf node is 0
        }
        else if (this.getLeft() == null) {
            this.setBalance(-((AvlNode) this.getRight()).getHeight());
        }
        else if (this.getRight() == null) {
            this.setBalance(((AvlNode) this.getLeft()).getHeight());
        }
        else {
            this.setBalance(((AvlNode) this.getLeft()).getHeight()-((AvlNode) this.getRight()).getHeight());
        }
        return this.getBalance();
    }

    public Integer calculateHeight(){
        if (this.getLeft() == null && this.getRight() == null) {
            this.setHeight(1);
        }
        else if (this.getLeft() == null) {
            this.setHeight(((AvlNode) this.getRight()).getHeight() + 1);
        }
        else if (this.getRight() == null) {
            this.setHeight(((AvlNode) this.getLeft()).getHeight() + 1);
        }
        else {
            this.setHeight(Math.max(((AvlNode) this.getLeft()).getHeight(), ((AvlNode) this.getRight()).getHeight()) + 1);
        }
        return this.getHeight();
    }
    public Integer getBalance(){
        return balance;
    }

    public Integer getHeight(){
        return height;
    }

    public void setHeight(Integer height){
        this.height = height;
    }

    public void setBalance(Integer balance){
        this.balance = balance;
    }
}