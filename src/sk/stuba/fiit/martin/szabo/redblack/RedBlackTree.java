package sk.stuba.fiit.martin.szabo.redblack;

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

                    // If node we inserted is on the left
                    if(parent.getLeft() == current){
                        // We do right left rotation

                        // First we straighten the node order
                        this.rightRotate(parent);

                        // Then we move grandparent to the left, child at the top and parent to the right
                        this.leftRotate(grandParent);

                        // We recolor the nodes
                        current.setColor(Color.BLACK); // The node we just inserted is at top so we color it black
                        grandParent.setColor(Color.RED); // We moved grandparent to one of the sides so we color it red
                    }
                    else if(parent.getRight() == current){
                        // We do left right rotation

                        // First we straighten the node order
                        this.leftRotate(parent);

                        // Then we move grandparent to the right, child at the top and parent to the left
                        this.rightRotate(grandParent);

                        // We recolor the nodes
                        current.setColor(Color.BLACK); // The node we just inserted is at top so we color it black
                        grandParent.setColor(Color.RED); // We moved grandparent to one of the sides so we color it red
                    }
                }
                // Node inserted is outer grand child
                else{
                    // We already have straight line of nodes connected to the grand parent
                    // We just need to rotate them in proper direction to form a triangle

                    // If node we inserted is on the left
                    if(parent.getLeft() == current){
                        // We rotate to the oposite direction
                        this.rightRotate(grandParent);

                        // Recolor the nodes
                        parent.setColor(Color.BLACK); // Parent is now the top node so color it black
                        grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red
                    }
                    // If node we inserted is on the right
                    else if(parent.getRight() == current){
                        // We rotate to the oposite direction
                        this.leftRotate(grandParent);

                        // Recolor the nodes
                        parent.setColor(Color.BLACK); // Parent is now the top node so color it black
                        grandParent.setColor(Color.RED); // Grandparent is on one of the sides so color it red
                    }
                }
            }

            // We move up one grandparent node
            current = parent.getParent();
            parent = parent.getGrandParent();

            // Root is always black // TODO:: Needed when you check if parent is not root?
            ((RedBlackNode) this.getRoot()).setColor(Color.BLACK);
        }
    }

    private static boolean checkChilds(RedBlackNode root){
        if(root.getLeft() != null && root.getLeft().getColor() == root.getColor()){
            return false;
        }
        else if(root.getLeft() != null && root.getRight().getColor() == root.getColor()){
            return false;
        }
        return true;
    }


    public void balanceDeletion(RedBlackNode node){
        // TODO:: Implement this
    }
}
