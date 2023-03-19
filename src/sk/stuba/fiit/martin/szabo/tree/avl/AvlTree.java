package sk.stuba.fiit.martin.szabo.tree.avl;

import sk.stuba.fiit.martin.szabo.tree.bst.BstNode;
import sk.stuba.fiit.martin.szabo.tree.bst.BstTree;

public class AvlTree extends BstTree{

    //* Constructors

    /**
     * Creates a new Avl Tree with null values for its attributes.
     */
    public AvlTree(){
    }

    /**
     * Creates a new Avl Tree with a root node
     * @param root root of the Splay Tree
     */
    public AvlTree(AvlNode root){
        this.setRoot(root);
        if(this.getRoot() != null){
            ((AvlNode) this.getRoot()).setHeight(1);
        }
    }

    /**
     * Inserts a new node into the AVL tree.
     *
     * @param node the AVL node to be inserted.
     * @return true if the node was successfully inserted, false otherwise.
     *
     * @see BstTree#insert(BstNode)
     * @see #balanceInsertion(AvlNode)
     */
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

    /**
     * Deletes a node from the AVL tree.
     *
     * @param node the node to be deleted
     * @return true if the node was deleted, false otherwise
     *
     * @see BstTree#delete(BstNode)
     * @see #balanceDeletionSubTree(AvlNode)
     */
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

    /**
     * Rebalances the AVL tree after an insertion. Recalculates the balance and height of each node as it traverses up
     * the tree starting from the inserted node. If a node becomes unbalanced after the insertion, it is rotated using
     * one of the four rotation methods (left rotation, right rotation, left-right rotation or right-left rotation) in
     * order to maintain the balance of the tree.
     *
     * @param node the newly inserted node
     *
     * @see AvlNode#calculateHeight()
     * @see AvlNode#calculateBalance()
     * @see #leftRotate(BstNode)
     * @see #rightRotate(BstNode)
     * @see #leftRightRotate(BstNode)
     * @see #rightLeftRotate(BstNode)
     */
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

    /**
     * Rebalances the AVL tree after a node is deleted. Recalculates the height and balance of each node from the
     * deleted node up to the root and performs rotations if necessary to restore the AVL property. Uses either
     * left, right, left-right or right-left rotation depending on the balance factor of the current node. If a
     * left-right or right-left rotation is used, the method first performs a right or left rotation respectively
     * on the current node and then a left or right rotation on the parent node of the left or right subtree after
     * rotation.
     *
     * @param node the node that was deleted
     *
     * @see AvlNode#calculateHeight()
     * @see AvlNode#calculateBalance()
     * @see AvlTree#leftRotate(BstNode)
     * @see AvlTree#rightRotate(BstNode)
     * @see AvlTree#leftRightRotate(BstNode)
     * @see AvlTree#rightLeftRotate(BstNode)
     */
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

    /**
     * Rebalances the AVL tree after a deletion operation starting from the root node of the subtree where the node
     * is deleted. This method finds the deepest node in the subtree, then calls the {@link #balanceDeletion(AvlNode)}
     * method to rebalance the tree starting from that node.
     *
     * @param root the root node of the subtree where the node is deleted
     *
     * @see #balanceDeletion(AvlNode)
     */
    public void balanceDeletionSubTree(AvlNode root){
        this.balanceDeletion((AvlNode) BstNode.getDeepestNode(root));
    }

    /**
     * Performs a left rotation on the given AVL node. This method overrides the left rotation method of the
     * parent class and additionally recalculates the heights of the rotated node and its right child.
     *
     * @param node the node to be left-rotated
     *
     * @see BstTree#leftRotate(BstNode)
     * @see AvlNode#calculateHeight()
     */
    @Override
    public void leftRotate(BstNode node){
        AvlNode right = (AvlNode) node.getRight();
        super.leftRotate(node);
        // And we recalculate the height's
        ((AvlNode)node).calculateHeight();
        right.calculateHeight();
    }

    /**
     * Performs a right rotation on the given AVL node. This method overrides the right rotation method of the
     * parent class and additionally recalculates the heights of the rotated node and its left child.
     *
     * @param node the node to be right-rotated
     *
     * @see BstTree#rightRotate(BstNode)
     * @see AvlNode#calculateHeight()
     */
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