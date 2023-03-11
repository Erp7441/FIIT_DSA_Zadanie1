package sk.stuba.fiit.martin.szabo.tree.bst;

import java.util.ArrayList;

public class BstTree{

    //* Attributes
    private BstNode root = null;

    //* Constructors
    public BstTree(){
    }
    public BstTree(BstNode root){
        this.root = root;
    }


    //* Basic methods for binary tree
    public boolean insert(BstNode node){

        // If node we are inserting is already present in the tree then we should return as no duplicates are allowed.
        BstNode found = this.search(node.getKey());
        if(found != null && (node == found ||  node.getKey().equals(found.getKey()))){
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

    public boolean insert(Integer key){
        // This overloaded method makes life a bit easier :)
        BstNode node = new BstNode(key);
        return insert(node);
    }

    public boolean insert(Integer key, String value){
        BstNode node = new BstNode(key, value);
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

    public BstNode search(Integer value, boolean returnLast){
        // We transverse the tree
        BstNode previous = null;
        BstNode currentNode = root;
        while(currentNode != null){
            // If key of node we are looking for is less than key of current node then we move left.
            if(value < currentNode.getKey()){
                // Move left
                previous = currentNode;
                currentNode = currentNode.getLeft();
            }
            // If key of node we are looking for is greater than key of current node then we move right.
            else if(value > currentNode.getKey()){
                // Move right
                previous = currentNode;
                currentNode = currentNode.getRight();
            }
            // Else we found our node.
            else{
                return currentNode;
            }
        }
        if(returnLast){
            return previous;
        }
        return null;
    }

    public BstNode delete(BstNode node){

        // Find node that needs to be deleted
        BstNode nodeToBeDeleted = this.search(node.getKey());
        if(nodeToBeDeleted == null) {
            return null;
        }

        BstNode child = null;
        BstNode childsParent = null;

        // If node to be deleted has two children we need to find in order predecessor
        if (nodeToBeDeleted.getLeft() != null && nodeToBeDeleted.getRight() != null) {
            BstNode predecessor = BstNode.getInOrderPredeecesor(nodeToBeDeleted);
            BstNode successor = BstNode.getInOrderSuccessor(nodeToBeDeleted);
            if(successor != null && predecessor != null){
                if(predecessor.getKey() < successor.getKey()){
                    child = predecessor;
                }
                else{
                    child = successor;
                }
            }
            else if(successor == null){
                child = predecessor;
            }
            else if(predecessor == null){
                child = successor;
            }
        }
        // If node to be deleted has one child use that child as replacement
        else if(nodeToBeDeleted.getLeft() != null || nodeToBeDeleted.getRight() != null){
            child = nodeToBeDeleted.getLeft() != null ? nodeToBeDeleted.getLeft() : nodeToBeDeleted.getRight();
        }

        // If we have child that will be replacing the node to be deleted
        if(child != null){
            childsParent = child.getParent();

            // If node to be deleted has children where child does not. Move them to the replacement node.
            if(nodeToBeDeleted.getLeft() != null){
                if(child.isOnRight()){
                    child.getParent().setRight(child.getLeft());
                }
                else if(child.isOnLeft()){
                    child.getParent().setLeft(child.getLeft());
                }
                child.setLeft(nodeToBeDeleted.getLeft());
            }
            if(nodeToBeDeleted.getRight() != null){
                if(child.isOnRight()){
                    child.getParent().setRight(child.getRight());
                }
                else if(child.isOnLeft()){
                    child.getParent().setLeft(child.getRight());
                }
                child.setRight(nodeToBeDeleted.getRight());
            }
        }

        if(nodeToBeDeleted == this.getRoot()){
            //* Root case
            this.setRoot(child);
        }
        // Replacing node to be deleted with replacment node
        else if(nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted){
            nodeToBeDeleted.getParent().setLeft(child);
        }
        else{
            nodeToBeDeleted.getParent().setRight(child);
        }

        // We should either return the child we replaced nodeTobeDeleted with or if it is parent we want to return the subtree we took it from
        return (child != null && child.getParent() != null) ? child : childsParent;
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
        if(right == null) return;

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


    }

    public void rightRotate(BstNode node){
        // LL rotation

        BstNode left = node.getLeft(); // Get left child of node we want to rotate
        if(left == null) return;

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

    }

    public void leftRightRotate(BstNode node){
        // LR rotation

        // First we get left child of node
        BstNode left = node.getLeft();
        if(left == null) return;

        // Then that left child's right child
        BstNode leftRight = left.getRight();
        if(leftRight == null) return;

        // First we rotate the left child to the left. This will "straighten" the left subtree.
        leftRotate(left);

        // Since during the left rotation the left-right child's parent changed. We want to get the new parent tree with right rotation.
        rightRotate(leftRight.getParent());

    }

    public void rightLeftRotate(BstNode node){
        // RL rotation

        // First we get right child of node
        BstNode right = node.getRight();
        if(right == null) return;

        // Then that right child's left child
        BstNode rightLeft = right.getLeft();
        if(rightLeft == null) return;

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