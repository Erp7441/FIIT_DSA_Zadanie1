package sk.stuba.fiit.martin.szabo.avl;

public class Tree{

    //* Attributes
    private Integer height = 0;
    private Node root = null;

    //* Constructors
    public Tree(){
    }
    public Tree(Node root){
        this.root = root;
    }


    //* Basic methods for binary tree
    public void insert(Node node){
        /*
            1. Start
            2. Loop throught tree nodes until you find a leaf
            3. If @node.value < @currentRoot.value then move left
            4. Else if @node.value > @currentRoot.value then move right
            5. Else you found a leaf


            4. Compare @node.value to @leaf.value
            5. If @node.value < @leaf.value make @node left child of @leaf
            6. Else make @node right child of @leaf

            7. Update balance factor of whole tree
            8. Rebalance the whole tree
        */
    }
    public void search(Node node){}
    public void delete(Node node){
        /*
            1. Start
            2. Use search to locate node to be deleted

            3. If @noteToBeDeleted has two children. Find inorder successor of @noteToBeDeleted and replace it with the successor
            4. Else if @noteToBeDeleted has one child. Replace it with the child
            5. Else @noteToBeDeleted is a leaf. Then remove it

            6. Update balance factor of whole tree
            7. Rebalance the whole tree
        */
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
    public void rebalance(Node node){
        /*
            1. Start
            2. If balance factor is > 1 it means the heigh of the left subtree is greater than the right subtree
            3. Use right rotation or left right rotation to fix the balance factor
                3.1 If @node.value < @node.left.value then do right rotation
                3.2 Else do left right rotation

            4. If balance factor is < -1 it means the height of the right subtree is greater than left subtree
            5. Use left rotation or right left rotation to fix the balance factor
                5.1 If @node.value > @node.right.value then do left rotation
                5.2 Else do right left rotation
            6. End
        */
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
