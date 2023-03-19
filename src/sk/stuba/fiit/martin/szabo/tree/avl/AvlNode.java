package sk.stuba.fiit.martin.szabo.tree.avl;

import sk.stuba.fiit.martin.szabo.tree.bst.BstNode;

/**
 * AvlNode represents a node of an AVL Tree, which is a self-balancing binary search tree. It extends the BstNode
 * class and adds attributes to keep track of the node's height and balance factor.
 */
public class AvlNode extends BstNode{

    //* Attributes

    private Integer height = 0;
    private Integer balance = 0;

    //* Constructors

    /**
     * Constructs a new AvlNode with the given key and a null value.
     * @param key the key of the new node.
     */
    public AvlNode(Integer key){
        super(key, null);
    }

    /**
     * Constructs a new AvlNode with the given key and value.
     * @param key the key of the new node.
     * @param value the value of the new node.
     */
    public AvlNode(Integer key, String value){
        super(key, value);
    }

    //* Utility methods

    /**
     * Calculates the balance factor of the AVL node by subtracting the height of its right subtree from the height of
     * its left subtree. If the node is a leaf, sets its balance factor to 0. If the node has only a right child, sets
     * its balance factor to the negative height of the right child. If the node has only a left child, sets its balance
     * factor to the height of the left child. If the node has both left and right children, sets its balance factor to
     * the difference between the height of the left and right children.
     *
     * @return the calculated balance factor of the node
     */
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

    /**
     * Calculates and sets the height of this AVL node. If both the left and right children are null, the height
     * is set to 1. If the left child is null, the height is set to the height of the right child plus 1. If the
     * right child is null, the height is set to the height of the left child plus 1. Otherwise, the height is set
     * to the maximum height between the left and right children plus 1.
     *
     * @return the calculated height of this AVL node
     */
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