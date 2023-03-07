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

        RedBlackNode sibling = nodeToBeDeleted.getSibling();
        RedBlackNode left = nodeToBeDeleted.getLeft();
        RedBlackNode right = nodeToBeDeleted.getRight();
        RedBlackNode successor = (RedBlackNode) super.delete(nodeToBeDeleted);

        // If node to be deleted is red. No adjustment is required.
        if(nodeToBeDeleted.getColor() == Color.RED){ return true; }
        // Else if node has one child that is red. Recolor the child.
        else if(left == null && right != null && right.getColor() == Color.RED){
            right.setColor(nodeToBeDeleted.getColor());
        }
        else if(right == null && left != null && left.getColor() == Color.RED){
            left.setColor(nodeToBeDeleted.getColor());
        }
        // Else if the node to be deleted children are both red we just find the corrent replacement node and change it's color.
        // The replacement of a node to be deleted with correct node is happening in super.delete() method.
        else if(right != null && left != null && left.getColor() == Color.RED && right.getColor() == Color.RED){
            successor.setColor(nodeToBeDeleted.getColor());
        }
        // Else we have to readjust the tree. We will now only care about sibling and it's children to balance the tree.
        else{
            balanceDeletion(sibling);
        }


        return true;
    }

    private void balanceDeletion(RedBlackNode sibling){
        // a. If the node's sibling is red, rotate the parent of the deleted node so that the sibling becomes the parent's parent and recolor the new parent and its children as appropriate. This reduces the problem to the case where the sibling is black.
        if(sibling.getColor() == Color.RED){
            deletionHandlerRedSibling(sibling);
        }
        // b. If the node's sibling is black and has two black children, recolor the sibling to red and reapply the deletion algorithm to the parent node (i.e., start again from step 7).
        else if(sibling.getColor() == Color.BLACK && sibling.getLeft() == null || sibling.getLeft().getColor() == Color.BLACK && sibling.getRight() == null || sibling.getRight().getColor() == Color.BLACK){
            sibling.setColor(Color.RED);
            // Reapply the deletion algorithm to the parent node (i.e., start again from step 7)
            balanceDeletion(sibling.getParent());
        }
        // c. If the node's sibling is black and has at least one red child, rotate the parent node so that the sibling becomes the child of the node's grandparent and recolor the new parent and sibling as appropriate. Then, recolor the sibling's red child black to balance the tree.
        else if(sibling.getColor() == Color.BLACK && sibling.getLeft() != null && sibling.getLeft().getColor() == Color.RED || sibling.getRight() != null && sibling.getRight().getColor() == Color.RED){
            deletionHandlerRedChild(sibling);
        }

    }

    private void deletionHandlerRedSibling(RedBlackNode sibling){
        // Rotate parent of deleted node
        // So that sibling becomes the parent's parent

        if(sibling.isOnRight()){
            leftRightRotate(sibling);
        }
        else if(sibling.isOnLeft()){
            rightLeftRotate(sibling);
        }
    }

    private void deletionHandlerRedChild(RedBlackNode sibling){
        if(sibling.isOnLeft() && sibling.getParent() != null){
            rightRotate(sibling.getParent());
        }
        else if(sibling.isOnRight() && sibling.getParent() != null){
            leftRotate(sibling.getParent());
        }

        if(sibling.getLeft() != null && sibling.getLeft().getColor() == Color.RED){
            sibling.getLeft().setColor(Color.BLACK);
        }
        if(sibling.getRight()!= null && sibling.getRight().getColor() == Color.RED){
            sibling.getRight().setColor(Color.BLACK);
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
