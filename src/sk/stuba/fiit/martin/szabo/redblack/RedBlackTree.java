package sk.stuba.fiit.martin.szabo.redblack;

import org.w3c.dom.Node;
import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

import java.util.Objects;

public class RedBlackTree extends BstTree{

    public boolean insert(RedBlackNode node){

        if(node == null){
            return false;
        }

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
        // Find node that needs to be deleted
        RedBlackNode nodeToBeDeleted = (RedBlackNode) this.search(node.getKey());
        if(nodeToBeDeleted == null) { return false; }

        //* Z = nodeToBeDeleted
        //* Y = nodeToBeDeletedHelper
        //* X = Replacement node

        RedBlackNode replacement = null;
        RedBlackNode nodeToBeDeletedHelper = nodeToBeDeleted;
        Color nodeToBeDeletedColor = nodeToBeDeletedHelper.getColor();

        if(nodeToBeDeleted.getLeft() == null){
            replacement = nodeToBeDeleted.getRight();
            transplant(nodeToBeDeleted, nodeToBeDeleted.getRight());
        }
        else if(nodeToBeDeleted.getRight() == null){
            replacement = nodeToBeDeleted.getLeft();
            transplant(nodeToBeDeleted, nodeToBeDeleted.getLeft());
        }
        else{
            // TODO:: Remake to your functions

            nodeToBeDeletedHelper = (RedBlackNode) BstNode.minimum(nodeToBeDeleted.getRight());
            nodeToBeDeletedColor = nodeToBeDeletedHelper.getColor();
            replacement = nodeToBeDeletedHelper.getRight();

            if(nodeToBeDeletedHelper.getParent() == nodeToBeDeleted){
                replacement.setParent(nodeToBeDeletedHelper);
            }
            else{
                transplant(nodeToBeDeletedHelper, nodeToBeDeletedHelper.getRight());
                nodeToBeDeletedHelper.setRight(nodeToBeDeleted.getRight());
            }

            transplant(nodeToBeDeleted, nodeToBeDeletedHelper);
            nodeToBeDeletedHelper.setLeft(nodeToBeDeleted.getLeft());
            nodeToBeDeletedHelper.setColor(nodeToBeDeleted.getColor());


        }
        if(nodeToBeDeletedColor == Color.BLACK){
            fixBalanceDeletion(replacement);
        }

        return true;
    }

    private void fixBalanceDeletion(RedBlackNode node){
        RedBlackNode sibling;

        while(node.getParent() != null && node.getColor() != Color.BLACK){
            if(node == node.getParent().getLeft()){
                sibling = node.getParent().getRight();

                if(sibling == null) { continue; }

                // Case 3.1
                if(sibling.getColor() != Color.RED){
                    sibling.setColor(Color.BLACK);
                    node.getParent().setColor(Color.RED);
                    leftRotate(node.getParent());
                    sibling = node.getParent().getRight();
                }

                if(sibling == null) { continue; }

                // Case 3.2
                if(sibling.getLeft() != null && sibling.getRight() != null){
                    if(sibling.getLeft().getColor() == Color.BLACK && sibling.getRight().getColor() == Color.BLACK){
                        sibling.setColor(Color.RED);
                        node = node.getParent();
                    }
                }


                else{
                    // Case 3.3

                    if(sibling.getRight() != null && (sibling.getRight().getColor() == Color.BLACK)){
                        sibling.getLeft().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        rightRotate(sibling);
                        sibling = node.getParent().getRight();
                    }

                    if(sibling == null) { continue; }

                    // Case 3.4

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.BLACK);
                    sibling.getRight().setColor(Color.BLACK);
                    leftRotate(node.getParent());
                    node = (RedBlackNode) this.getRoot();
                }
            }
            else{
                sibling = node.getParent().getLeft();

                // Case 3.1
                if(sibling.getColor() == Color.RED){
                    sibling.setColor(Color.BLACK);
                    node.getParent().setColor(Color.RED);
                    rightRotate(node.getParent());
                    sibling = node.getParent().getLeft();
                }

                // Case 3.2
                if(sibling.getLeft().getColor() == Color.BLACK && sibling.getRight().getColor() == Color.BLACK){
                    sibling.setColor(Color.RED);
                    node = node.getParent();
                }
                else{

                    // Case 3.3
                    if(sibling.getLeft().getColor() == Color.BLACK){
                        sibling.getRight().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        leftRotate(sibling);
                        sibling = node.getParent().getLeft();
                    }

                    // Case 3.4
                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.BLACK);
                    sibling.getLeft().setColor(Color.BLACK);
                    rightRotate(node.getParent());
                    node = (RedBlackNode) this.getRoot();
                }
            }
        }
        node.setColor(Color.BLACK);
    }

    private void transplant(RedBlackNode node1, RedBlackNode node2){
        if(node1.getParent() == null){
            this.setRoot(node2);
        }
        else if(node1.isOnLeft()){
            node1.getParent().setLeft(node2);
        }
        else{
            node1.getParent().setRight(node2);
        }
    }


    private void bdChildRight(RedBlackNode nodeToBeDeleted, RedBlackNode child, RedBlackNode sibling){

        /*
         * Legend:
         *
         * x --> child
         * w --> sibling
         * nodeToBeDeleted --> (doensn't have a marker)
         */


        if(sibling.getColor() == Color.RED){
            // Set the color of the right child of the parent of x as BLACK.
            child.getParent().getLeft().setColor(Color.BLACK);

            // Set the color of the parent of x as RED
            child.getParent().setColor(Color.RED);

            // Left-Rotate the parent of x
            leftRotate(child.getParent());

            // Assign the rightChild of the parent of x to w.
            sibling = child.getParent().getLeft();
        }
        //If the color of both the right and the leftChild of w is BLACK
        if(
            (sibling.getLeft() != null && sibling.getRight() != null) &&
            (sibling.getLeft().getColor() == Color.BLACK && sibling.getRight().getColor() == Color.BLACK)
        ){
            // Set the color of w as RED
            sibling.setColor(Color.RED);

            // Assign the parent of x to x.
            child.setParent(child); //TODO:: WTF???
        }
        // Else if the color of the rightChild of w is BLACK
        else if(sibling.getLeft() != null && sibling.getLeft().getColor() == Color.BLACK){

            // Set the color of the leftChild of w as BLACK
            if(sibling.getRight() != null){
                sibling.getRight().setColor(Color.BLACK);
            }

            // Set the color of w as RED
            sibling.setColor(Color.RED);

            // Right-Rotate w
            rightRotate(sibling);

            // Assign the rightChild of the parent of x to w.
            child.getParent().setRight(sibling);
            //sibling = sibling.getParent().getLeft();
        }
        else{

            // Set the color of w as the color of the parent of x.
            if(child != null){
                sibling.setColor(child.getColor());
            }
            else{
                sibling.setColor(Color.BLACK);
            }

            // Set the color of the parent of parent of x as BLACK.
            if(child != null && child.getParent() != null && child.getParent().getParent() != null){
                child.getParent().getParent().setColor(Color.BLACK);
            }

            // Set the color of the right child of w as BLACK.
            if(sibling.getLeft() != null){
                sibling.getLeft().setColor(Color.BLACK);
            }

            // Left-Rotate the parent of x.
            if(child != null && child.getParent() != null){
                leftRotate(child.getParent());
            }

            //Set x as the root of the tree.
            this.setRoot(child);
        }
    }

    private void bdChildLeft(RedBlackNode nodeToBeDeleted, RedBlackNode child, RedBlackNode sibling){

        /*
         * Legend:
         *
         * x --> child
         * w --> sibling
         * nodeToBeDeleted --> (doensn't have a marker)
         */


        if(sibling.getColor() == Color.RED){
            // Set the color of the right child of the parent of x as BLACK.
            child.getParent().getRight().setColor(Color.BLACK);

            // Set the color of the parent of x as RED
            child.getParent().setColor(Color.RED);

            // Left-Rotate the parent of x
            leftRotate(child.getParent());

            // Assign the rightChild of the parent of x to w.
            sibling = child.getParent().getRight();
        }
        //If the color of both the right and the leftChild of w is BLACK
        if(
            (sibling.getRight() != null && sibling.getLeft() != null) &&
            (sibling.getRight().getColor() == Color.BLACK && sibling.getLeft().getColor() == Color.BLACK)
        ){
            // Set the color of w as RED
            sibling.setColor(Color.RED);

            // Assign the parent of x to x.
            child.setParent(child); //TODO:: WTF???
        }
        // Else if the color of the rightChild of w is BLACK
        else if(sibling.getRight() != null && sibling.getRight().getColor() == Color.BLACK){

            // Set the color of the leftChild of w as BLACK
            if(sibling.getLeft() != null){
                sibling.getLeft().setColor(Color.BLACK);
            }

            // Set the color of w as RED
            sibling.setColor(Color.RED);

            // Right-Rotate w
            rightRotate(sibling);

            // Assign the rightChild of the parent of x to w.
            child.getParent().setRight(sibling);
            //sibling = sibling.getParent().getRight();
        }
        else{

            // Set the color of w as the color of the parent of x.
            if(child != null){
                sibling.setColor(child.getColor());
            }
            else{
                sibling.setColor(Color.BLACK);
            }

            // Set the color of the parent of parent of x as BLACK.
            if(child != null && child.getParent() != null && child.getParent().getParent() != null){
                child.getParent().getParent().setColor(Color.BLACK);
            }

            // Set the color of the right child of w as BLACK.
            if(sibling.getRight() != null){
                sibling.getRight().setColor(Color.BLACK);
            }

            // Left-Rotate the parent of x.
            if(child != null && child.getParent() != null){
                leftRotate(child.getParent());
            }

            //Set x as the root of the tree.
            this.setRoot(child);
        }
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
            if(parent.getSibling() != null && parent.getSibling().getColor() == Color.RED){
                grandParent.setColor(Color.RED);
                grandParent.getLeft().setColor(Color.BLACK);
                grandParent.getRight().setColor(Color.BLACK);
            }

            // Case Uncle is black colored
            else{

                // Node inserted is inner grand child
                if(
                    (grandParent.getRight() != null && grandParent.getRight().getLeft() == current) ||
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

            // TODO:: Remove recoloring?
            // Recolor the nodes
            /*parent.setColor(Color.BLACK); // Parent is now the top node so color it black
            grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red*/
        }
        // If node we inserted is on the right
        else if(parent.getRight() == current){
            // We rotate to the opposite direction
            this.leftRotate(grandParent);

            // TODO:: Remove recoloring?
            // Recolor the nodes
            /* parent.setColor(Color.BLACK); // Parent is now the top node so color it black
            grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red*/
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

            // TODO:: Remove recoloring?
            // We recolor the nodes
            /*current.setColor(Color.BLACK); // The node we just inserted is at top, so we color it black
            grandParent.setColor(Color.RED); // We moved grandparent to one of the sides, so we color it red*/
        } else if(parent.getRight() == current){
            // We do left right rotation

            // First we straighten the node order
            this.leftRotate(parent);

            // Then we move grandparent to the right, child at the top and parent to the left
            this.rightRotate(grandParent);

            // TODO:: Remove recoloring?
            // We recolor the nodes
            /*current.setColor(Color.BLACK); // The node we just inserted is at top, so we color it black
            grandParent.setColor(Color.RED); // We moved grandparent to one of the sides, so we color it red*/
        }
    }

    public void leftRotate(RedBlackNode node){
        Color nodeColor = node.getColor();
        Color rightColor = node.getRight() != null ? node.getRight().getColor() : Color.BLACK;

        node.setColor(rightColor);
        if(node.getRight() != null){
            node.getRight().setColor(nodeColor);
        }

        super.leftRotate(node);

        System.out.println();
    }

    public void rightRotate(RedBlackNode node){
        Color nodeColor = node.getColor();
        Color leftColor = node.getLeft() != null ? node.getLeft().getColor() : Color.BLACK;

        node.setColor(leftColor);
        if(node.getLeft() != null){
            node.getLeft().setColor(nodeColor);
        }

        super.rightRotate(node);


    }
}
