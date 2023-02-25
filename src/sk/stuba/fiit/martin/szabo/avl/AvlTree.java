package sk.stuba.fiit.martin.szabo.avl;

import sk.stuba.fiit.martin.szabo.bst.BstTree;

import java.util.ArrayList;

import static java.lang.System.*;

public class AvlTree extends BstTree{

    //* Constructors
    public AvlTree(){
    }
    public AvlTree(AvlNode root){
        this.setRoot(root);
        this.getRoot().setHeight(0);
    }

    public boolean insert(AvlNode node){

        super.insert(node);
        // Then we rebalance the tree.
        this.balanceInsertion(node);
        return true;
    }

    @Override
    public boolean insert(Integer value){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(value);
        return insert(node);
    }

    public boolean delete(AvlNode node){
        AvlNode replacement = (AvlNode) super.delete(node);
        if(replacement != null){
            this.balanceDeletionSubTree(replacement);
            //out.println("DEBUG: Delete method balanced");
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer value){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(value);
        return delete(node);
    }

    public void balanceInsertion(AvlNode node){
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

            // Advance upwards to another node.
            current = (AvlNode) current.getParent();
        }
    }

    public void balanceDeletion(AvlNode node){
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
                if(((AvlNode) current.getRight()).calculateBalance() <= 0){
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
                if(((AvlNode) current.getLeft()).calculateBalance() >= 0){
                    rightRotate(current);
                }
                // Else we want to first do left rotation on the current node and then right rotation on the parent node of right subtree after rotation.
                else{
                    leftRightRotate(current);
                }
            }

            // Advance upwards to another node.
            current = (AvlNode) current.getParent();
        }
    }

    public void balanceDeletionSubTree(AvlNode root){
        ArrayList<AvlNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(root); // Gets current level nodes

        while(!currentLevelNodes.isEmpty()){

            ArrayList<AvlNode> nextLevelNodes = new ArrayList<>();

            for(AvlNode node : currentLevelNodes){ // For each node on current level

                this.balanceDeletion(node);

                if(node != null && node.getLeft() != null){ // And append it to the list of next level nodes.
                    nextLevelNodes.add((AvlNode) node.getLeft());
                }
                if(node != null && node.getRight() != null){
                    nextLevelNodes.add((AvlNode) node.getRight());
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
    }
}