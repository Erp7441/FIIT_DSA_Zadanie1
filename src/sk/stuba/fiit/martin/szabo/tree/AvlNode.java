package sk.stuba.fiit.martin.szabo.tree;
import java.util.ArrayList;

public class AvlNode{
    private Integer balancingFactor;
    private Integer value;
    private AvlNode leftChild = null;
    private AvlNode rightChild = null;
    private AvlNode parent = null;

    public AvlNode(Integer value){
        this.value = value;
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

    public AvlNode getInorderSuccessor(){

        // TODO:: Reimplement

        // If right tree exits look for minimal value of the tree
        if(this.getRightChild() != null){
            return this.getRightChild().minimum();
        }

        AvlNode parent = this.getParent();
        AvlNode current = this;
        // While we are the right child of a parent
        while(current != null && current == parent.getRightChild()){
            current = parent;
            parent = parent.getParent(); // Move up a parent
        }
        return parent; // If we are the left child. We return parent as the inorder successor
    }

    public AvlNode minimum(){
        // TODO:: Reimplement this
        AvlNode current = this;
        while (current.getLeftChild() != null){
            current = current.getLeftChild();
        }
        return current;
    }

    public Integer height(){
        // TODO:: Reimplement this
        Integer height = 0;
        ArrayList<AvlNode> currentLevelAvlNodes = new ArrayList<>();
        currentLevelAvlNodes.add(this);
        while(!currentLevelAvlNodes.isEmpty()){
            ArrayList<AvlNode> nextLevelAvlNodes = new ArrayList<>();

            for(AvlNode avlNode : currentLevelAvlNodes){
                if(avlNode.getLeftChild() != null){ //
                    nextLevelAvlNodes.add(avlNode.getLeftChild());
                }
                if(avlNode.getRightChild() != null){
                    nextLevelAvlNodes.add(avlNode.getRightChild());
                }
            }

            if(!nextLevelAvlNodes.isEmpty()){
                currentLevelAvlNodes = nextLevelAvlNodes;
                height++;
            }
            else{
                break;
            }
        }
        return height;
    }

    public Integer depth(){
        // TODO:: Reimplement this
        Integer depth = 0;
        AvlNode current = this;
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
        if(!(obj instanceof AvlNode)){
            return false;
        }
        AvlNode avlNode = (AvlNode) obj;
        if(this.value == null){
            if(avlNode.value!= null){
                return false;
            }
        }
        else if(!this.value.equals(avlNode.value)){
            return false;
        }
        if(this.leftChild == null){
            if(avlNode.leftChild != null){
                return false;
            }
        }
        else if(!this.leftChild.equals(avlNode.leftChild)){
            return false;
        }
        if(this.rightChild == null){
            if(avlNode.rightChild != null){
                return false;
            }
        }
        else if(!this.rightChild.equals(avlNode.rightChild)){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

    public void calculateBalancingFactor(){
        Integer left = this.getLeftChild() != null ? this.getLeftChild().height() : 0;
        Integer right = this.getRightChild() != null ? this.getRightChild().height() : 0; //TODO:: Here it fails
        this.balancingFactor = left - right;
    }
    public Integer getBalancingFactor(){
        return balancingFactor;
    }
    public AvlNode getLeftChild(){
        return this.leftChild;
    }
    public AvlNode getRightChild(){
        return this.rightChild;
    }
    public Integer getValue(){
        return this.value;
    }
    public AvlNode getParent(){
        return this.parent;
    }
    public void setParent(AvlNode avlNode){
        this.parent = avlNode;
    }
    public void setLeftChild(AvlNode avlNode){
        this.leftChild = avlNode;
    }
    public void setRightChild(AvlNode avlNode){
        this.rightChild = avlNode;
    }

}

