package sk.stuba.fiit.martin.szabo.splay;

import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

import java.util.Objects;

public class SplayTree extends BstTree{

    public SplayTree(){
    }
    public SplayTree(BstNode root){
        this.setRoot(root);
    }

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
            this.getRoot().setHeight(0);
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
            // Assign root of right subtree as root of this tree effectively finalaizing the deletion
            this.setRoot(rightSubtree.getRoot());
        }

        return this.getRoot();
    }

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
