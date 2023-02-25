package sk.stuba.fiit.martin.szabo.avl;

import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

public class AvlTree extends BstTree{

    //* Constructors
    public AvlTree(){
    }
    public AvlTree(AvlNode root){
        this.setRoot(root);
        this.getRoot().setHeight(0);
    }

    //* Basic methods for binary tree
    public boolean insert(AvlNode node){

        super.insert(node);
        // Then we rebalance the tree.
        this.balance(node);
        return true;
    }

    @Override
    public boolean insert(Integer value){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(value);
        return insert(node);
    }

    public boolean delete(AvlNode node){

        BstNode childsParent = super.delete(node).getParent();
        if(childsParent instanceof AvlNode){
            this.balance((AvlNode) childsParent);
        }
        else{
            return false;
        }

        System.out.println("DEBUG: Delete method balanced");

        return true;
    }

    @Override
    public boolean delete(Integer value){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(value);
        return delete(node);
    }

    public void balance(AvlNode node){
        // Recalculating balance and height.
        AvlNode current = node;

        // We transverse up the tree.
        while(current != null){
            // Recalculating balance and height of each node we go through.
            current.calculateHeight();
            current.calculateBalance();

            // If current node balance is less than -1 then we have use either left or right-left rotation to rebalance the tree.
            if(current.getBalance() < -1){
                // If inserted key is bigger than current key, and we inserted the new node to the right subtree. We need to just rotate left.
                if(node.getKey() > current.getRight().getKey()){
                    leftRotate(current);
                }
                // Else we want to first do right rotation on the current node and then left rotation on the parent node of left subtree after rotation.
                else{
                    rightLeftRotate(current);
                }
            }
            // If current node balance is greater than 1 then we have use either right or left-right rotation to rebalance the tree.
            else if(current.getBalance() > 1){
                // If inserted key is smaller than current key, and we inserted the new node to the left subtree. We need to just rotate right.
                if(node.getKey() < current.getLeft().getKey()){
                    rightRotate(current);
                }
                // Else we want to first do left rotation on the current node and then right rotation on the parent node of right subtree after rotation.
                else{
                    leftRightRotate(current);
                }
            }
            current = (AvlNode) current.getParent();
            // Advance upwards to another node.
            /*if(current.getParent() instanceof AvlNode){
                current = (AvlNode) current.getParent();
            }
            else{
                System.err.println("Failed to cast at AVL balance method");
                return;
            }*/
        }
    }

}