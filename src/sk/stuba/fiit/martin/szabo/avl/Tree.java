package sk.stuba.fiit.martin.szabo.avl;

import java.util.ArrayList;

public class Tree{

    //* Attributes
    private Node root = null;

    //* Constructors
    public Tree(){
    }
    public Tree(Node root){
        this.root = root;
        this.root.setHeight(0);
    }


    //* Basic methods for binary tree
    public boolean insert(Node node){

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

        Node currentRoot = this.root;

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

        // Then we rebalance the tree.
        this.balance(node);
        return true;
    }

    public boolean insert(Integer value){
        // This overloaded method makes life a bit easier :)
        Node node = new Node(value);
        return insert(node);
    }

    public Node search(Node node){
        // We transverse the tree
        Node currentNode = root;
        while(currentNode != null){
            // If key of node we are looking for is less than key of current node then we move left.
            if(node.getKey() < currentNode.getKey()){
                // Move left
                currentNode = currentNode.getLeft();
            }
            // If key of node we are looking for is greater than key of current node then we move right.
            else if(node.getKey() > currentNode.getKey()){
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
    public Node search(Integer value){
        // This overloaded method makes life a bit easier :)
        Node node = new Node(value);
        return search(node);
    }
    public boolean delete(Node node){

        // TODO:: finish this method
        /*
            1. Start
            2. Use search to locate node to be deleted

            3. If @noteToBeDeleted has two children. Set child as inorder successor of @noteToBeDeleted and replace @noteToBeDeleted with @child
            4. Else if @noteToBeDeleted has one child. Replace it with the @child
            5. Else @noteToBeDeleted is a leaf. Then remove it

            6. Update balance factor of whole tree
            7. Balance the whole tree
        */

        Node nodeToBeDeleted = this.search(node.getKey());
        if(nodeToBeDeleted == null) { return false; }

        Integer childCount = nodeToBeDeleted.getChildCount();
        Node child = null;

        if (childCount == 2){
            child = Node.getInOrderSuccessor(nodeToBeDeleted);
        }
        else if(childCount == 1){
            child = nodeToBeDeleted.getLeft() != null ? nodeToBeDeleted.getLeft() : nodeToBeDeleted.getRight();
        }

        if(child != null){
            child.setParent(nodeToBeDeleted.getParent());
        }

        if(nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted){
            nodeToBeDeleted.getParent().setLeft(child);
        }
        else{
            nodeToBeDeleted.getParent().setRight(child);
        }
        nodeToBeDeleted.setParent(null);

        // TODO:: Rebalance

        return true;
    }
    public boolean delete(Integer value){
        // This overloaded method makes life a bit easier :)
        Node node = new Node(value);
        return delete(node);
    }

    //* AVL methods
    public void leftRotate(Node node){
        // RR rotation

        Node right = node.getRight(); // Get right child of node we want to rotate
        Node rightLeft = right.getLeft(); // Get left child of the right child

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

        // And we recalculate the height's and balance's
        node.calculateHeight();
        right.calculateHeight();
        node.calculateBalance();
        right.calculateBalance();

    }

    public void rightRotate(Node node){
        // LL rotation

        Node left = node.getLeft(); // Get left child of node we want to rotate
        Node leftRight = left.getRight(); // Get right child of the left child

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

        // And we recalculate the height's and balance's
        node.calculateHeight();
        left.calculateHeight();
        node.calculateBalance();
        left.calculateBalance();
    }

    public void leftRightRotate(Node node){
        // LR rotation

        // First we get left child of node
        Node left = node.getLeft();

        // Then that left child's right child
        Node leftRight = left.getRight();

        // First we rotate the left child to the left. This will "straighten" the left subtree.
        leftRotate(left);

        // Since during the left rotation the left-right child's parent changed. We want to get the new parent and rebalance the tree with right rotation.
        rightRotate(leftRight.getParent());

    }

    public void rightLeftRotate(Node node){
        // RL rotation

        // First we get right child of node
        Node right = node.getRight();

        // Then that right child's left child
        Node rightLeft = right.getLeft();

        // First we rotate the right child to the right. This will "straighten" the right subtree.
        rightRotate(right);

        // Since during the right rotation the right-left child's parent changed. We want to get the new parent and rebalance the tree with left rotation.
        leftRotate(rightLeft.getParent());

    }

    public void balance(Node node){
        // Recalculating balance and height.
        Node current = node;

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
            current = current.getParent();
        }
    }

    //* Utility methods
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        ArrayList<Node> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(this.getRoot()); // Gets current level nodes

        while(!currentLevelNodes.isEmpty()){

            ArrayList<Node> nextLevelNodes = new ArrayList<>();

            for(Node node : currentLevelNodes){ // For each node on current level

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

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
        if(root != null){
            root.setParent(null);
        }
    }

}