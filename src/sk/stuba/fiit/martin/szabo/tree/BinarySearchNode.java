package sk.stuba.fiit.martin.szabo.tree;

import java.util.ArrayList;

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
        if(binarySearchNode != null){
            binarySearchNode.setParent(this);
        }
        this.leftChild = binarySearchNode;
    }
    public void setRightChild(BinarySearchNode binarySearchNode){
        if(binarySearchNode != null){
            binarySearchNode.setParent(this);
        }
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
            sb.append("(null), ");
        }
        if(this.rightChild != null){
            sb.append(this.rightChild.getValue());
        }
        else{
            sb.append("(null)");
        }
        return sb.toString();
    }

    /**
     * Gets adjecent node of this node
     * @return adjecent node of this node
     */
    public ArrayList<BinarySearchNode> getAdjects(){
        ArrayList<BinarySearchNode> adjects = new ArrayList<>();
        if(this.leftChild != null){
            adjects.add(this.leftChild);
        }
        if(this.rightChild != null){
            adjects.add(this.rightChild);
        }
        return adjects;
    }

    /**
     * Adds adjecent node to this node
     * @param value value of new adjecent node
     */
    public void addAdjecent(Object value){
        // TODO add balancing logic
    }

    public BinarySearchNode getInorderSuccessor(){
        BinarySearchNode current = this;
        BinarySearchNode successor = null;

        // If right tree exits look for minimal value of the tree


        return successor;
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
