package sk.stuba.fiit.martin.szabo.bst;

import java.util.ArrayList;

import static java.lang.System.*;

public class BstTree{

    //* Attributes
    private BstNode root = null;

    //* Constructors
    public BstTree(){
    }
    public BstTree(BstNode root){
        this.root = root;
        this.root.setHeight(0);
    }


    //* Basic methods for binary tree
    public boolean insert(BstNode node){

        // If node we are inserting is already present in the tree then we should return as no duplicates are allowed.
        if(node == this.search(node.getKey())){
            return false;
        }

        // If node we are inserting is root then we should just insert it as head of the tree and return.
        if(root == null){
            this.root = node;
            this.root.setHeight(0);
            return false;
        }

        BstNode currentRoot = this.root;

        // Loop through tree nodes until you find a leaf.
        while(currentRoot != null){
            // If value of node to be inserted is lesser than current one. We move left.
            if(node.getKey() < currentRoot.getKey()){
                // If we have to move left but there is no left child.
                // We found leaf. Yay!
                if(currentRoot.getLeft() == null){
                    currentRoot.setLeft(node); // We insert the node on the left of current.
                    break; // We can end the loop here.
                }
                currentRoot = currentRoot.getLeft();
            }
            // If value of node to be inserted is greater than current one. We move right.
            else if(node.getKey() > currentRoot.getKey()){

                // If we have to move right but there is no right child.
                // We found leaf. Yay!
                if(currentRoot.getRight() == null){
                    currentRoot.setRight(node); // We insert the node on the right of current.
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

    public boolean insert(Integer value){
        // This overloaded method makes life a bit easier :)
        BstNode node = new BstNode(value);
        return insert(node);
    }

    public BstNode search(Integer value){
        // We transverse the tree
        BstNode currentNode = root;
        while(currentNode != null){
            // If key of node we are looking for is less than key of current node then we move left.
            if(value < currentNode.getKey()){
                // Move left
                currentNode = currentNode.getLeft();
            }
            // If key of node we are looking for is greater than key of current node then we move right.
            else if(value > currentNode.getKey()){
                // Move right
                currentNode = currentNode.getRight();
            }
            // Else we found our node.
            else{
                return currentNode;
            }
        }
        return null;
    }

    public BstNode delete(BstNode node){

        out.println("DEBUG: Delete method called");

        // TODO:: Delete
        if(node.getKey() == 329){
            out.println();
        }

        BstNode nodeToBeDeleted = this.search(node.getKey());
        if(nodeToBeDeleted == null) {
            out.println("DEBUG: Node not found");
            return null;
        }

        BstNode child = null;
        BstNode childsParent;

        if (nodeToBeDeleted.getLeft() != null && nodeToBeDeleted.getRight() != null) {
            if(nodeToBeDeleted.getParent() != null) {
                if(nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted){
                    child = BstNode.getInOrderSuccessor(nodeToBeDeleted);
                }
                else{
                    child = BstNode.getInOrderPredeecesor(nodeToBeDeleted);
                }
            }
            else{
                //* Root case
                child = BstNode.getInOrderPredeecesor(nodeToBeDeleted);
            }
        }
        else if(nodeToBeDeleted.getLeft() != null || nodeToBeDeleted.getRight() != null){
            child = nodeToBeDeleted.getLeft() != null ? nodeToBeDeleted.getLeft() : nodeToBeDeleted.getRight();
        }

        if(child != null){
            childsParent = child.getParent();

            // Ripping away "child" from its parent
            if(childsParent != null && childsParent.getLeft() == child){
                childsParent.setLeft(null);
            }
            else if(childsParent != null && childsParent.getRight() == child){
                childsParent.setRight(null);
            }

            if(nodeToBeDeleted.getLeft() != null && child.getLeft() == null){
                child.setLeft(nodeToBeDeleted.getLeft());
            }
            if(nodeToBeDeleted.getRight() != null && child.getRight() == null){
                child.setRight(nodeToBeDeleted.getRight());
            }
        }

        if(nodeToBeDeleted == this.getRoot()){
            //* Root case
            this.setRoot(child);
        }
        else if(nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted){
            nodeToBeDeleted.getParent().setLeft(child);
        }
        else{
            nodeToBeDeleted.getParent().setRight(child);
        }

        out.println("DEBUG: Delete method finished");

        //? We should either return the child we replaced nodeTobeDeleted with or if we just yet out the nodeToBeDeleted
        //? then we need parent node of the nodeToBeDeleted
        return child != null ? child : nodeToBeDeleted.getParent();
    }

    public boolean delete(Integer value){
        // This overloaded method makes life a bit easier :)
        BstNode node = new BstNode(value);
        return delete(node) != null;
    }

    //* BST methods
    public void leftRotate(BstNode node){
        // RR rotation

        BstNode right = node.getRight(); // Get right child of node we want to rotate
        BstNode rightLeft = right.getLeft(); // Get left child of the right child

        // If current node is root. We just set the right child as root
        if(node == this.getRoot()){
            this.setRoot(right);
        }
        // Else we want to tell parent of our node that his right child is changing to node's right child.
        else if(node.getParent().getLeft() == node){
            node.getParent().setLeft(right);
        }
        else if(node.getParent().getRight() == node){
            node.getParent().setRight(right);
        }

        // And move the right's left child as the right child of rotated node. I had to draw this step in MS Paint :)
        node.setRight(rightLeft);

        // Now we rotate the node to the left
        right.setLeft(node);

        // And we recalculate the height's
        node.calculateHeight();
        right.calculateHeight();
    }

    public void rightRotate(BstNode node){
        // LL rotation

        BstNode left = node.getLeft(); // Get left child of node we want to rotate
        BstNode leftRight = left.getRight(); // Get right child of the left child

        // If current node is root. We just set the right child as root
        if(node == this.getRoot()){
            this.setRoot(left);
        }
        // Else we want to tell parent of our node that his right child is changing to node's right child.
        else if(node.getParent().getRight() == node){
            node.getParent().setRight(left);
        }
        else if(node.getParent().getLeft() == node){
            node.getParent().setLeft(left);
        }

        // And move the left's right child as the left child of rotated node. I had to draw this step in MS Paint :)
        node.setLeft(leftRight);

        // Now we rotate the node to the right
        left.setRight(node);

        // And we recalculate the height's
        node.calculateHeight();
        left.calculateHeight();
    }

    public void leftRightRotate(BstNode node){
        // LR rotation

        // First we get left child of node
        BstNode left = node.getLeft();

        // Then that left child's right child
        BstNode leftRight = left.getRight();

        // First we rotate the left child to the left. This will "straighten" the left subtree.
        leftRotate(left);

        // Since during the left rotation the left-right child's parent changed. We want to get the new parent tree with right rotation.
        rightRotate(leftRight.getParent());

    }

    public void rightLeftRotate(BstNode node){
        // RL rotation

        // First we get right child of node
        BstNode right = node.getRight();

        // Then that right child's left child
        BstNode rightLeft = right.getLeft();

        // First we rotate the right child to the right. This will "straighten" the right subtree.
        rightRotate(right);

        // Since during the right rotation the right-left child's parent changed. We want to get the new parent and the tree with left rotation.
        leftRotate(rightLeft.getParent());

    }

    //* Utility methods
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        ArrayList<BstNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(this.getRoot()); // Gets current level nodes

        while(!currentLevelNodes.isEmpty()){

            ArrayList<BstNode> nextLevelNodes = new ArrayList<>();

            for(BstNode node : currentLevelNodes){ // For each node on current level

                sb.append(node).append("\n"); // Append it to the final output

                if(node != null && node.getLeft() != null){ // And append it to the list of next level nodes.
                    nextLevelNodes.add(node.getLeft());
                }
                if(node != null && node.getRight() != null){
                    nextLevelNodes.add(node.getRight());
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
        return sb.toString();
    }

    //* Getters and setters
    public Integer getHeight(){
        return this.getRoot().getHeight();
    }

    public void setHeight(Integer height){
        this.getRoot().setHeight(height);
    }

    public BstNode getRoot(){
        return root;
    }

    public void setRoot(BstNode root){
        this.root = root;
        if(root != null){
            root.setParent(null);
        }
    }

}