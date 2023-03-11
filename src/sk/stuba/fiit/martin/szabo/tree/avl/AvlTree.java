package sk.stuba.fiit.martin.szabo.tree.avl;

import sk.stuba.fiit.martin.szabo.tree.bst.BstNode;
import sk.stuba.fiit.martin.szabo.tree.bst.BstTree;

public class AvlTree extends BstTree{

    //* Constructors
    public AvlTree(){
    }
    public AvlTree(AvlNode root){
        this.setRoot(root);
        if(this.getRoot() != null){
            ((AvlNode) this.getRoot()).setHeight(1);
        }
    }

    public boolean insert(AvlNode node){

        // Do classic BST insertion
        super.insert(node);

        // Then we rebalance the tree.
        this.balanceInsertion(node);
        return true;
    }

    @Override
    public boolean insert(Integer key){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(key);
        return insert(node);
    }

    @Override
    public boolean insert(Integer key, String value){
        // This overloaded method makes life a bit easier :)
        AvlNode node = new AvlNode(key, value);
        return insert(node);
    }

    public boolean delete(AvlNode node){
        // Default BST deletion
        AvlNode replacement = (AvlNode) super.delete(node);

        // Rebalancing subtree where root is the replacement node
        if(replacement != null){
            this.balanceDeletionSubTree(replacement);
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
        // TODO:: Get rid of deepest node and recalculate from balance deletion from root downwards.
        this.balanceDeletion((AvlNode) BstNode.getDeepestNode(root));
    }

    @Override
    public void leftRotate(BstNode node){
        AvlNode right = (AvlNode) node.getRight();
        super.leftRotate(node);
        // And we recalculate the height's
        ((AvlNode)node).calculateHeight();
        right.calculateHeight();
    }

    @Override
    public void rightRotate(BstNode node){
        AvlNode left = (AvlNode) node.getLeft();
        super.rightRotate(node);
        // And we recalculate the height's
        ((AvlNode)node).calculateHeight();
        left.calculateHeight();
    }

    public Integer getHeight(){
        return ((AvlNode) this.getRoot()).getHeight();
    }

    public void setHeight(Integer height){
        ((AvlNode) this.getRoot()).setHeight(height);
    }

    @Override
    public void setRoot(BstNode root){
        if(this.getRoot() == null){
            ((AvlNode) root).setHeight(1);
        }
        super.setRoot(root);
    }
}