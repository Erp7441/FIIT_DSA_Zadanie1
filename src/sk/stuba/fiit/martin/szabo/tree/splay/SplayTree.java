package sk.stuba.fiit.martin.szabo.tree.splay;

import sk.stuba.fiit.martin.szabo.tree.bst.BstNode;
import sk.stuba.fiit.martin.szabo.tree.bst.BstTree;

import java.util.Objects;

/**
 * A SplayTree is a type of Binary Search Tree where the last accessed node is moved to the root. It extends BstTree.
 * The class has methods to insert and splay nodes in the tree. The splaying algorithm works by repeatedly
 * performing rotations until the specified node becomes the root. There are three possible splaying cases:
 *
 * <ul>
 * <li>rotateOnce: when the specified node has a parent and a grandparent, and they form a zig-zig pattern</li>
 * <li>rotateTwiceSameSide: when the specified node has a parent and a grandparent, and they form a zig-zag pattern on the same side</li>
 * <li>rotateTwiceDiffSide: when the specified node has a parent and a grandparent, and they form a zig-zag pattern on opposite sides</li>
 * </ul>
 *
 * If none of these cases occur during the loop, the method exits and returns the current root of the tree. The class
 * has a constructor that takes a root node and a default constructor. It overrides the insert method of BstTree and
 * has a splay method that splays the specified node to the root of the Splay Tree by performing the necessary rotations.
 */
public class SplayTree extends BstTree{

    /**
     * Creates a new Splay tree with null values for its attributes.
     */
    public SplayTree(){
    }

    /**
     * Creates a new Splay tree with a root node
     * @param root root of the Splay Tree
     */
    public SplayTree(BstNode root){
        this.setRoot(root);
    }

    /**
     * Splays the specified node to the root of the Splay Tree by performing the necessary rotations. If the
     * specified node is already the root, no rotations are performed. The splaying algorithm works by repeatedly
     * performing rotations until the specified node becomes the root.
     * <br>
     * There are three possible splaying cases:
     * <br>
     *
     * <ul>
     * <li>rotateOnce: when the specified node has a parent and a grandparent, and they form a zig-zig pattern</li>
     * <li>rotateTwiceSameSide: when the specified node has a parent and a grandparent, and they form a zig-zag pattern on the same side</li>
     * <li>rotateTwiceDiffSide: when the specified node has a parent and a grandparent, and they form a zig-zag pattern on opposite sides</li>
     * <li>If none of these cases occur during the loop, the method exits and returns the current root of the tree</li>
     * </ul>
     *
     * @param node the node to be splayed to the root
     * @return the new root after splaying
     */
    public BstNode splay(BstNode node){
        if(node == null) return null;

        while(node != this.getRoot()){
            // If no splay case happens then we just exit out of loop because the node we are looking for is a root.
            if(
                !rotateOnce(node) &&
                !rotateTwiceSameSide(node) &&
                !rotateTwiceDiffSide(node)
            ){ break; }
        }
        return this.getRoot();
    }

    /**
     * Inserts a node into the Splay Tree. If the node being inserted is already present in the tree, it returns
     * false and does not modify the tree. If the node being inserted is the root, it is simply added as the head
     * of the tree. Otherwise, it loops through the tree nodes until it finds a leaf. If the value of the node to
     * be inserted is lesser than the current node, it moves to the left. If the value of the node to be inserted
     * is greater than the current node, it moves to the right. If there is no left or right child when the correct
     * position is found, the current node is splayed to become the root. The new node is then inserted as the
     * appropriate child of the current root. If there is already a node with the same key in the tree, it is splayed
     * to become the root and false is returned.
     *
     * @param node node to be inserted into the tree
     * @return true if the insertion was successful or false if the insertion was unsuccessful
     */
    @Override
    public boolean insert(BstNode node){
        // If node we are inserting is already present in the tree then we should return as no duplicates are allowed.
        // If node is a duplicate value we don't want to modify the tree.
        // We use super here because we need search without modification to tree.

        BstNode found = super.search(node.getKey());
        if(found != null && (node == found ||  node.getKey().equals(found.getKey()))){
            splay(super.search(node.getKey()));
            return false;
        }

        // If node we are inserting is root then we should just insert it as head of the tree and return.
        if(this.getRoot() == null){
            this.setRoot(node);
            return false;
        }

        BstNode currentRoot = this.getRoot();

        // Loop through tree nodes until you find a leaf.
        while(currentRoot != null){
            // If value of node to be inserted is lesser than current one. We move left.
            if(node.getKey() < currentRoot.getKey()){
                // If we have to move left but there is no left child.
                // Now we push current to root position. After that we push it to the right side of the tree and set our new root.
                if(currentRoot.getLeft() == null){
                    // We splay current node until it is root
                    splay(currentRoot);

                    // Since current node is bigger than new node. We place current node as right child of new node.
                    node.setRight(currentRoot);

                    // If current has a left child we set it as left child of new node since we...
                    // ...want to just push root to right and insert our new node
                    node.setLeft(currentRoot.getLeft());
                    currentRoot.setLeft(null);

                    // Finally we set the root
                    this.setRoot(node);
                    break; // We can end the loop here.
                }
                currentRoot = currentRoot.getLeft();
            }
            // If value of node to be inserted is greater than current one. We move right.
            else if(node.getKey() > currentRoot.getKey()){

                // If we have to move right but there is no right child.
                // Now we push current to root position. After that we push it to the left side of the tree and set our new root.
                if(currentRoot.getRight() == null){
                    // We splay current node until it is root
                    splay(currentRoot);

                    // Since current node is smaller than new node. We place current node as left child of new node.
                    node.setLeft(currentRoot);

                    // If current has a right child we set it as right child of new node since we...
                    // ...want to just push root to right and insert our new node
                    node.setRight(currentRoot.getRight());
                    currentRoot.setRight(null);

                    // Finally we set the root
                    this.setRoot(node);
                    break; // We can end the loop here.
                }
                currentRoot = currentRoot.getRight();
            }
            // We should not get here but just in case we somehow do. We just jump out immediately.
            else{
                // Can't have duplicate keys.
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the specified node from the splay tree, if it exists in the tree. If the node is not found
     * or the tree is empty, null is returned. This method works by first searching for the node to be deleted
     * using the search method. If the node is found, it is removed from the tree and the tree is splayed at the
     * parent node of the deleted node to restore the tree's balance. If the deleted node has no right subtree, its
     * left subtree is promoted as the new tree. Otherwise, the lowest value node from the right subtree is splayed
     * and its left subtree is reassigned to be the left subtree of the deleted node. Finally, the root of the right
     * subtree is set as the new root of the splay tree.
     *
     * @param node the node to be deleted from the splay tree.
     * @return the new root after the deletion, or null if the node was not found or the tree is empty.
     * @see BstNode#minimum(BstNode)
     */
    @Override
    public BstNode delete(BstNode node){
        if(this.getRoot() == null) return null;

        // Find node that needs to be deleted
        BstNode nodeToBeDeleted = this.search(node.getKey());
        if(!Objects.equals(nodeToBeDeleted.getKey(), this.getRoot().getKey())) return null;

        SplayTree rightSubtree = new SplayTree(nodeToBeDeleted.getRight());

        // If we have no right subtree set left subtree as new tree
        if(rightSubtree.getRoot() == null){
            this.setRoot(nodeToBeDeleted.getLeft());
        }
        else{
            // Else splay lowest value from right subtree
            rightSubtree.splay(BstNode.minimum(rightSubtree.getRoot()));
            // Reassign left subtree of node to be deleted as left subtree of right subtree (which will form the new tree)
            rightSubtree.getRoot().setLeft(nodeToBeDeleted.getLeft());
            // Assign root of right subtree as root of this tree effectively finalizing the deletion
            this.setRoot(rightSubtree.getRoot());
        }

        return this.getRoot();
    }

    /**
     * Searches for a node with the given integer value in the splay tree. If the node is found, it is splayed
     * to the root of the tree. If the node is not found, the last accessed node on the search path is splayed
     * to the root of the tree.
     *
     * @param value the integer value to search for
     * @return the splayed node with the given value, or the last accessed node if the value is not found in the tree.
     */
    @Override
    public BstNode search(Integer value){
        BstNode node = super.search(value);
        if(node != null){
            splay(node);
        }
        else{
            node = super.search(value, true);
            splay(node);
        }
        return node;
    }

    //* Splay helper methods

    /**
     * This method performs a rotation on the specified node, when it has a parent and a grandparent,
     * and they form a zig-zig pattern. This is done by rotating the parent node in the opposite direction
     * of the child node, which results in the child node taking the place of the grandparent node.
     * <br>
     * A zig-zig pattern occurs when a node is the right child of a left child or the left child of a right child.
     *
     * @param node the node to rotate
     * @return true if a rotation was performed, false otherwise
     */
    public boolean rotateOnce(BstNode node){
        // Right case
        if(node != null && node.getParent() != null && node.getParent().getParent() == null && node.isOnLeft()){
            rightRotate(node.getParent());
            return true;
        }
        // Left case
        else if(node != null && node.getParent() != null && node.getParent().getParent() == null && node.isOnRight()){
            leftRotate(node.getParent());
            return true;
        }
        return false;
    }

    /**
     * This method performs a double rotation on the same side when the specified node, its parent, and grandparent
     * form a zig-zag pattern on the same side of the tree. A zig-zag pattern occurs when the grandparent, parent, and
     * node are not on a straight line. In the left-left case, the node is rotated to the right twice, and in the
     * right-right case, the node is rotated to the left twice. This method returns true if the rotation was performed
     * successfully, and false otherwise.
     *
     * @param node the node to rotate
     * @return true if a rotation was performed, false otherwise
     */
    private boolean rotateTwiceSameSide(BstNode node){
        // Left-Left case
        if(node != null && node.getParent() != null && node.isOnLeft() && node.getParent().isOnLeft()){
            rightRotate(node.getParent().getParent());
            rightRotate(node.getParent());
            return true;
        }
        // Right-Right case
        else if(node != null && node.getParent() != null && node.isOnRight() && node.getParent().isOnRight()){
            leftRotate(node.getParent().getParent());
            leftRotate(node.getParent());
            return true;
        }
        return false;
    }

    /**
     * This method performs a double rotation when the specified node has a parent and a grandparent, and they
     * form a zig-zag pattern on opposite sides. A zig-zag pattern means that the parent and grandparent nodes
     * are not on the same side of the node. For example, if the node is a right child and its parent is a left
     * child, it forms a zig-zag pattern. This method performs the appropriate rotation's to fix the pattern and
     * balance the tree. If the pattern cannot be fixed, the method returns false.
     *
     * @param node the node to rotate
     * @return true if a rotation was performed, false otherwise
     */
    private boolean rotateTwiceDiffSide(BstNode node){
        // Left-right case
        if(node != null && node.getParent() != null && node.isOnRight() && node.getParent().isOnLeft()){
            leftRightRotate(node.getParent().getParent());
            return true;
        }
        // Right-left case
        else if(node != null && node.getParent() != null && node.isOnLeft() && node.getParent().isOnRight()){
            rightLeftRotate(node.getParent().getParent());
            return true;
        }
        return false;

    }
}
