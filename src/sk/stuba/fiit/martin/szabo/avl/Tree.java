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
        /*
            1. Start
            2. Loop through tree nodes until you find a leaf
            3. If @node.key < @currentRoot.key then move left
            4. Else if @node.key > @currentRoot.key then move right
            5. Else you found a leaf


            4. Compare @node.key to @leaf.key
            5. If @node.key < @leaf.key make @node left child of @leaf
            6. Else make @node right child of @leaf

            7. Update balance factor of whole tree
            8. Balance the whole tree
        */

        if(node == this.search(node.getKey())){
            return false;
        }

        if(root == null){
            this.root = node;
            this.root.setHeight(0);
            return false;
        }

        Node currentRoot = this.root;
        while(currentRoot != null){
            if(node.getKey() < currentRoot.getKey()){

                // If we have to move left but there is no left child
                // We found leaf. Yay!
                if(currentRoot.getLeft() == null){
                    currentRoot.setLeft(node);
                    node.setParent(currentRoot);
                    break;
                }
                currentRoot = currentRoot.getLeft();
            }
            else if(node.getKey() > currentRoot.getKey()){

                // If we have to move right but there is no right child
                // We found leaf. Yay!
                if(currentRoot.getRight() == null){
                    currentRoot.setRight(node);
                    node.setParent(currentRoot);
                    break;
                }
                currentRoot = currentRoot.getRight();
            }
            else{
                // Can't have duplicate keys
                return false;
            }
        }

        this.calculateTreeHeight(node);
        node.calculateDepth();
        this.balance(node);
        return true;
    }
    public boolean insert(Integer value){
        Node node = new Node(value);
        return insert(node);
    }
    public Node search(Node node){
        /*
            1. Start
            2. Set @currentNode to @root
            3. Loop through nodes of the tree until @currentNode is NULL
            4. If @currentNode equals @node then break loop
            5. If @node.key < @currentNode.key move left
            6. Else move right
            7. Return @currentNode
        */

        Node currentNode = root;
        while(currentNode != null){
            try{
                if(currentNode.equals(node) || currentNode.getKey().equals(node.getKey())){ break; }
            }
            catch(Exception ignored){}

            if(node.getKey() < currentNode.getKey()){
                // Move left
                currentNode = currentNode.getLeft();
            }
            else{
                // Move right
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }
    public Node search(Integer value){
        Node currentNode = root;
        while(currentNode != null){
            try{
                if(currentNode.getKey().equals(value)){ break; }
            }
            catch(Exception ignored){}

            if(value < currentNode.getKey()){
                // Move left
                currentNode = currentNode.getLeft();
            }
            else{
                // Move right
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }
    public boolean delete(Node node){
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

        this.calculateTreeHeight(node);
        this.calculateTreeDepth(node);
        this.balance(node);
        return true;
    }
    public boolean delete(Integer value){
        Node node = new Node(value);
        return delete(node);
    }

    //* AVL methods
    public void leftRotate(Node node){
        /*
            1. Start
            2. If @n2 has left child. Assign @n1 as new parent (Utility method)

            3. If @n2 parent is NULL then assign @n1 as root
            4. Else if @n2 is the left child of a parent. Set new parent's left child as @n1
            5. Else Assign @n1 as right child of parent

            6. Make @n2 parent of @n1
            7. End
        */

        if(node == null) { return; }

        if(node.getRight() != null && node == this.root){
            this.root = node.getRight();
        }

        try{
            Node right = node.getRight();
            Node temp = right.getLeft();

            right.setLeft(node);
            node.setRight(temp);

            temp = node.getParent();
            right.setParent(temp);
            node.setParent(right);
            if(temp != null){
                temp.setLeft(right);
            }

            node.calculateHeight();
            right.calculateHeight();

            node.calculateDepth();
            right.calculateDepth();

            temp = right.getParent();
            while(temp != null){
                temp.calculateHeight();
                temp = temp.getParent();
            }
        }
        catch (Exception ignored){}
    }
    public void rightRotate(Node node){
        /*
            1. Start
            2. If @n1 has right child. Assign @n2 as new parent (Utility method)

            3. If @n2 parent is NULL then assign @n1 as root
            4. Else if @n2 is the right child of a parent. Set new parent right child as @n1
            5. Else Assign @n1 as left child of parent

            6. Make @n1 parent of @n2
            7. End
        */

        if(node == null) { return; }

        if(node.getLeft() != null && node == this.root){
            this.root = node.getLeft();
        }

        try{
            Node left = node.getLeft();
            Node temp = left.getRight();

            left.setRight(node);
            node.setLeft(temp);

            temp = node.getParent();
            left.setParent(temp);
            node.setParent(left);
            if(temp != null){
                temp.setRight(left);
            }

            node.calculateHeight();
            left.calculateHeight();

            temp = left.getParent();
            while(temp != null){
                temp.calculateHeight();
                temp = temp.getParent();
            }
        }
        catch (Exception ignored){}
    }

    public void balance(Node node){
        /*
            1. Start
            2. If balance factor is > 1 it means the height of the left subtree is greater than the right subtree
            3. Use right rotation or left right rotation to fix the balance factor
                3.1 If @node.key < @node.left.key then do right rotation
                3.2 Else do left right rotation

            4. If balance factor is < -1 it means the height of the right subtree is greater than left subtree
            5. Use left rotation or right left rotation to fix the balance factor
                5.1 If @node.key > @node.right.key then do left rotation
                5.2 Else do right left rotation
            6. End
        */

        Node current = node.getParent();
        while (current != null) {
            current.calculateBalance();

            if(current.getBalance() > 1){
                if(node.getKey() < current.getLeft().getKey()){
                    // Do right rotation
                    rightRotate(node.getParent());
                }
                else{
                    // Do right left rotation
                    rightRotate(node.getParent());
                    leftRotate(node.getParent());

                }
            }
            else if(current.getBalance() < -1){
                if(node.getKey() > current.getRight().getKey()){
                    // Do left rotation
                    leftRotate(node.getParent());
                }
                else{
                    // Do left right rotation
                    leftRotate(node.getParent());
                    rightRotate(node.getParent());
                }
            }
            current = current.getParent();
        }
    }

    public void calculateTreeHeight(Node node){
        Node current = node.getParent();
        while(current != null){
            current.calculateHeight();
            current = current.getParent();
        }
    }
    public void calculateTreeDepth(Node node){
        Node current = node.getParent();
        while(current != null){
            current.calculateDepth();
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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Tree)) return false;

        Tree tree = (Tree) o;

        return getRoot().equals(tree.getRoot());
    }

    @Override
    public int hashCode(){
        return getRoot().hashCode();
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
    }

}
