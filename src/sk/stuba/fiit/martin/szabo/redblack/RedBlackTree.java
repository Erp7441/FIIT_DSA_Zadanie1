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

    @Override
    public boolean delete(Integer value){
        RedBlackNode node = new RedBlackNode(value);
        return this.delete(node);
    }

    public boolean delete(RedBlackNode node){

        // Call standard BST delete
        RedBlackNode nodeToBeDeleted = (RedBlackNode) this.search(node.getKey());
        RedBlackNode nodeToBeDeletedSibling = nodeToBeDeleted.getSibling();


        Boolean isOnLeft = nodeToBeDeleted.isOnLeft();
        super.delete(nodeToBeDeleted);

        // Balance red black tree after deletion
        if(nodeToBeDeleted.getColor() == Color.BLACK){
            balanceDeletion(nodeToBeDeleted, nodeToBeDeletedSibling, isOnLeft);
        }

        return true;
    }

    public void balanceInsertion(RedBlackNode node){

        RedBlackNode current = node;
        RedBlackNode parent = current.getParent();
        while(parent != null && parent.getColor() == Color.RED){

            RedBlackNode grandParent = parent.getParent();

            if(parent == this.getRoot()){
                break;
            }

            // Case Uncle is red colored
            if(parent.getSibling() != null && parent.getSibling().getColor() == Color.RED ){
                grandParent.setColor(Color.RED);
                grandParent.getLeft().setColor(Color.BLACK);
                grandParent.getRight().setColor(Color.BLACK);
            }

            // Case Uncle is black colored
            else{

                // Node inserted is inner grand child
                if(
                    (grandParent.getRight() != null && grandParent.getRight().getLeft() ==  current) ||
                    (grandParent.getLeft() != null && grandParent.getLeft().getRight() == current)
                ){

                    insertInnerGrandChild(current, parent, grandParent);
                }
                // Node inserted is outer grand child
                else{
                    insertOuterGrandChild(current, parent, grandParent);
                }
            }

            // We move up one grandparent node
            current = parent.getParent();
            parent = parent.getGrandParent();

            // Root is always black // TODO:: Needed when you check if parent is not root?
            ((RedBlackNode) this.getRoot()).setColor(Color.BLACK);
        }
    }

    private void insertOuterGrandChild(RedBlackNode current, RedBlackNode parent, RedBlackNode grandParent){
        // We already have straight line of nodes connected to the grandparent
        // We just need to rotate them in proper direction to form a triangle

        // If node we inserted is on the left
        if(parent.getLeft() == current){
            // We rotate to the opposite direction
            this.rightRotate(grandParent);

            // Recolor the nodes
            parent.setColor(Color.BLACK); // Parent is now the top node so color it black
            grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red
        }
        // If node we inserted is on the right
        else if(parent.getRight() == current){
            // We rotate to the opposite direction
            this.leftRotate(grandParent);

            // Recolor the nodes
            parent.setColor(Color.BLACK); // Parent is now the top node so color it black
            grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red
        }
    }

    private void insertInnerGrandChild(RedBlackNode current, RedBlackNode parent, RedBlackNode grandParent){
        // If node we inserted is on the left
        if(parent.getLeft() == current){
            // We do right left rotation

            // First we straighten the node order
            this.rightRotate(parent);

            // Then we move grandparent to the left, child at the top and parent to the right
            this.leftRotate(grandParent);

            // We recolor the nodes
            current.setColor(Color.BLACK); // The node we just inserted is at top, so we color it black
            grandParent.setColor(Color.RED); // We moved grandparent to one of the sides, so we color it red
        }
        else if(parent.getRight() == current){
            // We do left right rotation

            // First we straighten the node order
            this.leftRotate(parent);

            // Then we move grandparent to the right, child at the top and parent to the left
            this.rightRotate(grandParent);

            // We recolor the nodes
            current.setColor(Color.BLACK); // The node we just inserted is at top, so we color it black
            grandParent.setColor(Color.RED); // We moved grandparent to one of the sides, so we color it red
        }
    }

    public void balanceDeletion(RedBlackNode node, RedBlackNode sibling, Boolean isOnLeft){

        if(node == null) { return; }

        // Root case
        if(node.getParent() == null){
            if(node.getLeft() == null && node.getRight() == null){
                this.setRoot(null);
            }
            else if(node.getLeft() == null){
                this.setRoot(node.getRight());
                node.getRight().setColor(Color.BLACK);
            }
            else if(node.getRight() == null){
                this.setRoot(node.getLeft());
                node.getLeft().setColor(Color.BLACK);
            }
            else{
                this.setRoot(BstNode.getInOrderPredeecesor(node));
                ((RedBlackNode) this.getRoot()).setColor(Color.BLACK);
            }
            return;
        }

        RedBlackNode current = node;

        while(current != null){

            // Case sibling is red
            if(sibling != null && sibling.getColor() == Color.RED){
                this.bdSiblingRed(current, sibling, isOnLeft);
            }
            // Case sibling is black
            else if(sibling != null && sibling.getColor() == Color.BLACK){
                this.bdSiblingBlack(current, sibling, isOnLeft);
            }

            current = current.getGrandParent();
            ((RedBlackNode) this.getRoot()).setColor(Color.BLACK);
        }
    }

    private void bdSiblingBlack(RedBlackNode current, RedBlackNode sibling, Boolean isOnLeft){
        // Sibling has 2 black children
        if(
            (sibling.getLeft() == null || sibling.getLeft().getColor() == Color.BLACK) &&
            (sibling.getRight() == null || sibling.getRight().getColor() == Color.BLACK)
        ){
            bdSibling2BlackChildren(current, sibling);
        }
        // Sibling outer child is black
        else if((current.getRightNephew().getColor() == Color.BLACK || current.getRightNephew() == null)){
            bdSiblingOuterChildBlack(current, sibling, isOnLeft);
        }
        // Sibling outer child is red
        else if(current.getRightNephew().getColor() == Color.RED){
            bdSiblingOuterChildRed(current, sibling, isOnLeft);
        }
    }

    private void bdSiblingRed(RedBlackNode current, RedBlackNode sibling, Boolean isOnLeft){
        sibling.setColor(Color.BLACK);
        current.getParent().setColor(Color.RED);

        if(Boolean.TRUE.equals(isOnLeft)){
            leftRotate(current.getParent());
        }
        else{
            rightRotate(current.getParent());
        }
        if(sibling != null){
            current.getParent().setColor(Color.BLACK);
            sibling.setColor(Color.RED);
        }
    }

    private void  bdSibling2BlackChildren(RedBlackNode current, RedBlackNode sibling){
        // Parent is black we just color sibling red


        // However if parent is red we want to recolor him to black
        if(current.getParent() != null && current.getParent().getColor() == Color.RED){
            current.getParent().setColor(Color.BLACK);
            sibling.setColor(Color.RED);
        }

        if(current.getUncle() != null && current.getUncle().getColor() == Color.BLACK){
            current.getUncle().setColor(Color.RED);
        }
        if(current.getGrandParent() != null && current.getGrandParent().getColor() == Color.RED){
            current.getGrandParent().setColor(Color.BLACK);
        }
    }

    private void bdSiblingOuterChildBlack(RedBlackNode current, RedBlackNode sibling, Boolean isOnLeft){

        // Recoloring sibling to red
        sibling.setColor(Color.RED);

        // TODO:: Sussy
        current.getParent().setColor(Color.BLACK);

        // Sibling outer child is right
        if(current.getParent().getRight() == current.getSibling()){
            // Recoloring inner nephew to black
            current.getLeftNephew().setColor(Color.BLACK);
            bdHandleRotations(current, sibling, isOnLeft);

        }
        // Sibling outer child is left
        else if(current.getParent().getLeft() == current.getSibling()){
            current.getRightNephew().setColor(Color.BLACK);
            bdHandleRotations(current, sibling, isOnLeft);
        }
    }

    private void bdHandleRotations(RedBlackNode current, RedBlackNode sibling, Boolean isOnLeft){
        if(Boolean.TRUE.equals(isOnLeft)){
            rightRotate(sibling);

            sibling.setColor(current.getParent().getColor());
            // TODO:: Sussy
            current.getRightNephew().setColor(Color.BLACK);

            leftRotate(current.getParent());
        }
        else{
            leftRotate(sibling);

            sibling.setColor(current.getParent().getColor());
            current.getLeftNephew().setColor(Color.BLACK);

            rightRotate(current.getParent());
        }
    }

    private void bdSiblingOuterChildRed(RedBlackNode current, RedBlackNode sibling, Boolean isOnLeft){
        sibling.setColor(current.getParent().getColor());
        current.getParent().setColor(Color.BLACK);

        // Sibling outer child is right
        if(current.getParent().getRight() == sibling){
            current.getRightNephew().setColor(Color.BLACK);
        }
        // Sibling outer child is left
        else if(current.getParent().getLeft() == sibling){
            current.getLeftNephew().setColor(Color.BLACK);
        }

        if(Boolean.TRUE.equals(isOnLeft)){
            leftRotate(current.getParent());
        }
        else{
            rightRotate(current.getParent());
        }
    }
}
