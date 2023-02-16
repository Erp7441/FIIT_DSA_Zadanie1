package sk.stuba.fiit.martin.szabo.avl;

import java.util.ArrayList;

public class Tree{

    //* Attributes
    private Integer height = 0;
    private Node root = null;

    //* Constructors
    public Tree(){
    }
    public Tree(Node root){
        this.root = root;
        this.root.setHeight(0);
        this.height = 0;
    }


    //* Basic methods for binary tree
    public void insert(Node node){
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

        if(root == null){
            this.root = node;
            this.root.setHeight(0);
            this.height = 0;
            return;
        }

        Node currentRoot = this.root;
        while(currentRoot.getChildCount() == 0 || currentRoot.getChildCount() == 1){
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
                return;
            }
        }


        // TODO:: Rebalance the whole treek,

        /*this.root.calculateBalance();
        this.root.calculateHeight(); //? Should I do this?
        if(this.root.getBalance() < -1 || this.root.getBalance() > 1){
            this.balance(this.root);
        }*/
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
            if(currentNode.equals(node) || currentNode.getKey().equals(node.getKey())){ break; }
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
    public void delete(Node node){
        /*
            1. Start
            2. Use search to locate node to be deleted

            3. If @noteToBeDeleted has two children. Set child as inorder successor of @noteToBeDeleted and replace @noteToBeDeleted with @child
            4. Else if @noteToBeDeleted has one child. Replace it with the @child
            5. Else @noteToBeDeleted is a leaf. Then remove it

            6. Update balance factor of whole tree
            7. Balance the whole tree
        */

        Node nodeToBeDeleted = this.search(node);
        if(nodeToBeDeleted == null) { return; }

        Integer childCount = nodeToBeDeleted.getChildCount();
        Node child = null;

        if (childCount == 2){
            child = Node.getInOrderSuccessor(nodeToBeDeleted);
        }
        else if(childCount == 1){
            child = nodeToBeDeleted.getLeft() != null ? nodeToBeDeleted.getLeft() : nodeToBeDeleted.getRight();
        }

        if(child != null && (childCount == 1 || childCount == 2)){
            child.setParent(nodeToBeDeleted.getParent());
        }

        if(nodeToBeDeleted.getParent().getLeft() == nodeToBeDeleted){
            nodeToBeDeleted.getParent().setLeft(child);
        }
        else{
            nodeToBeDeleted.getParent().setRight(child);
        }
        nodeToBeDeleted.setParent(null);

        this.root.calculateBalance();
        // TODO:: Balance the whole tree
    }

    //* AVL methods
    public void leftRotate(Node n1, Node n2){
        /*
            1. Start
            2. If @n2 has left child. Assign @n1 as new parent (Utility method)

            3. If @n1 parent is NULL then assign @n2 as root
            4. Else if @n1 is the left child of a parent. Set new parent left child as @n2
            5. Else Assign @n2 as right child of parent

            6. Make @n2 parent of @n1
            7. End
        */

        if(n1 == null || n2 == null) { return; }

        // TODO:: Improve inline comments

        // Taking left child of n2 and assigning as right child of n1
        if(n2.getLeft() != null){
            n2.getLeft().setParent(n1);
            n1.setRight(n2.getLeft());
        }

        // Check if we need to modify root of the tests
        if(n1 == this.getRoot()){
            this.setRoot(n2);
        }


        // Now that n2 left child is empty we need to move n1 there
        n2.setParent(n1.getParent()); // We "copy" old top node parent and assigning it to the new top node
        n1.setParent(n2); // We set old top node parent to the new top node
        n2.setLeft(n1); // Same thing as above from new top node perspective
    }
    public void rightRotate(Node n1, Node n2){
        /*
            1. Start
            2. If @n1 has right child. Assign @n2 as new parent (Utility method)

            3. If @n2 parent is NULL then assign @n1 as root
            4. Else if @n2 is the right child of a parent. Set new parent right child as @n1
            5. Else Assign @n1 as left child of parent

            6. Make @n1 parent of @n2
            7. End
        */

        if(n1 == null || n2 == null) { return; }

        // TODO:: Improve inline comments

        if(n1.getRight() != null){
            n1.getRight().setParent(n2);
            n2.setLeft(n1.getRight());
        }

        // Check if we need to modify root of the tests
        if(n2 == this.getRoot()){
            this.setRoot(n1);
        }

        // Now that n1 right child is empty we need to move n2 there
        n1.setParent(n2.getParent()); // We "copy" old top node parent and assigning it to the new top node
        n2.setParent(n1); // We set old top node parent to the new top node
        n1.setRight(n2); // Same thing as above from new top node perspective
    }
    public void leftAndRightRotate(Node n1, Node n2, Node n3){
        leftRotate(n1, n2);
        rightRotate(n2, n3);
    }
    public void rightAndLeftRotate(Node n1, Node n2, Node n3){
        rightRotate(n1, n2);
        leftRotate(n2, n3);
    }

    //? Is special one needed for insertion and deletion ?//
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

        node.calculateBalance();
        while(node.getBalance() < -1 || node.getBalance() > 1){
            if(node.getBalance() > 1){
                if(node.getKey() < node.getLeft().getKey()){
                    rightRotate(node, node.getLeft());
                }
                else{
                    leftAndRightRotate(node, node.getRight(), node.getParent());
                }
            }
            else if(node.getBalance() < -1){
                if(node.getKey() > node.getRight().getKey()){
                    leftRotate(node, node.getRight());
                }
                else{
                    rightAndLeftRotate(node, node.getRight(), node.getParent());
                }
            }
        }
    }

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
        return height;
    }

    public void setHeight(Integer height){
        this.height = height;
    }

    public Node getRoot(){
        return root;
    }

    public void setRoot(Node root){
        this.root = root;
    }
}
