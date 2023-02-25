package sk.stuba.fiit.martin.szabo.redblack;

import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

public class RedBlackTree extends BstTree{

    public boolean insert(RedBlackNode node){

        if(node == null){ return false;}

        // If tree doesn't exist
        if(this.getRoot() == null){
            // Set node color to black and set it as root
            node.setColor(Color.BLACK);
            this.setRoot(node);
            return true;
        }

        // Call standard BST delete
        super.insert(node);

        // Balance red black tree after deletion
        balanceInsertion(node);

        return true;
    }

    @Override
    public boolean insert(Integer value){
        RedBlackNode node = new RedBlackNode(value);
        return this.insert(node);
    }


    public boolean delete(RedBlackNode node){
        // Call standard BST delete
        super.delete(node);

        // Balance red black tree after deletion
        balanceDeletion(node);

        return true;
    }

    public void balanceInsertion(RedBlackNode node){
        // TODO:: Implement balance algorithm for red black trees

        RedBlackNode parent = node.getParent();
        while(parent.getColor() == Color.RED){

            RedBlackNode grandParent = parent.getParent();
            if(grandParent.getLeft() == parent){
                // Case 1
                if(grandParent.getRight().getColor() == Color.RED){
                    grandParent.setColor(Color.RED);
                    grandParent.getLeft().setColor(Color.BLACK);
                    grandParent.getRight().setColor(Color.BLACK);

                    // TODO:: Assign grandParent to node
                }
                // Case 2
                else if(parent.getRight() == node){
                    // TODO:: Assing parent to node
                    this.leftRotate(node);
                }
                // Case 3
                else{
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    this.rightRotate(grandParent);
                }
            }
            else{
                // Case 1
                if(grandParent.getLeft().getColor() == Color.RED){
                    grandParent.setColor(Color.RED);
                    grandParent.getLeft().setColor(Color.BLACK);
                    grandParent.getRight().setColor(Color.BLACK);

                    // TODO:: Assign grandParent to node
                }
                // Case 2
                else if(parent.getLeft() == node){
                    // TODO:: Assing parent to node
                    this.rightRotate(node);
                }
                // Case 3
                else{
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    this.leftRotate(grandParent);
                }
            }
            ((RedBlackNode) this.getRoot()).setColor(Color.BLACK);
        }
    }

    public void balanceDeletion(RedBlackNode node){
        // TODO:: Implement this
    }
}
