package sk.stuba.fiit.martin.szabo.tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchNode{
    private Integer value;
    private BinarySearchNode leftChild = null;
    private BinarySearchNode rightChild = null;
    private BinarySearchNode parent = null;

    public BinarySearchNode(Integer value){
        this.value = value;
    }

    public BinarySearchNode getLeftChild(){
        return this.leftChild;
    }
    public BinarySearchNode getRightChild(){
        return this.rightChild;
    }
    public Integer getValue(){
        return this.value;
    }
    public BinarySearchNode getParent(){
        return this.parent;
    }

    public void setParent(BinarySearchNode binarySearchNode){
        this.parent = binarySearchNode;
    }
    public void setLeftChild(BinarySearchNode binarySearchNode){
        this.leftChild = binarySearchNode;
    }
    public void setRightChild(BinarySearchNode binarySearchNode){
        this.rightChild = binarySearchNode;
    }

    /**
     * Prints all nodes in the tree in format: <br><br>
     *
     * [CurrentNode]: [LeftNode], [RightNode]
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.value).append(": ");
        if(this.leftChild != null){
            sb.append(this.leftChild.getValue()).append(", ");
        }
        else{
            sb.append("x, ");
        }
        if(this.rightChild != null){
            sb.append(this.rightChild.getValue());
        }
        else{
            sb.append("x");
        }
        return sb.toString();
    }

    public BinarySearchNode getInorderSuccessor(){
        // If right tree exits look for minimal value of the tree
        if(this.getRightChild() != null){
            return this.getRightChild().minimum();
        }

        BinarySearchNode parent = this.getParent();
        BinarySearchNode current = this;
        // While we are the right child of a parent
        while(current != null && current == parent.getRightChild()){
            current = parent;
            parent = parent.getParent(); // Move up a parent
        }
        return parent; // If we are the left child. We return parent as the inorder successor
    }

    public BinarySearchNode minimum(){
        BinarySearchNode current = this;
        while (current.getLeftChild() != null){
            current = current.getLeftChild();
        }
        return current;
    }

    public Integer height(){
        Integer height = 0;
        ArrayList<BinarySearchNode> currentLevelBinarySearchNodes = new ArrayList<>();
        currentLevelBinarySearchNodes.add(this);
        while(!currentLevelBinarySearchNodes.isEmpty()){
            ArrayList<BinarySearchNode> nextLevelBinarySearchNodes = new ArrayList<>();

            for(BinarySearchNode binarySearchNode : currentLevelBinarySearchNodes){
                if(binarySearchNode.getLeftChild() != null){ //
                    nextLevelBinarySearchNodes.add(binarySearchNode.getLeftChild());
                }
                if(binarySearchNode.getRightChild() != null){
                    nextLevelBinarySearchNodes.add(binarySearchNode.getRightChild());
                }
            }

            if(!nextLevelBinarySearchNodes.isEmpty()){
                currentLevelBinarySearchNodes = nextLevelBinarySearchNodes;
                height++;
            }
            else{
                break;
            }
        }
        return height;
    }

    public Integer depth(){
        Integer depth = 0;
        BinarySearchNode current = this;
        while(current.getParent() != null){
            current = current.getParent();
            depth++;
        }
        return depth;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(!(obj instanceof BinarySearchNode)){
            return false;
        }
        BinarySearchNode binarySearchNode = (BinarySearchNode) obj;
        if(this.value == null){
            if(binarySearchNode.value!= null){
                return false;
            }
        }
        else if(!this.value.equals(binarySearchNode.value)){
            return false;
        }
        if(this.leftChild == null){
            if(binarySearchNode.leftChild != null){
                return false;
            }
        }
        else if(!this.leftChild.equals(binarySearchNode.leftChild)){
            return false;
        }
        if(this.rightChild == null){
            if(binarySearchNode.rightChild != null){
                return false;
            }
        }
        else if(!this.rightChild.equals(binarySearchNode.rightChild)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

}
