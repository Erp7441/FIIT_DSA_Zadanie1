package sk.stuba.fiit.martin.szabo.tree;

public class AvlNode extends BinarySearchNode{
    private Integer balancingFactor;

    public AvlNode(Integer value){
        super(value);
    }
    public void calculateBalancingFactor(){
        Integer left = this.getLeftChild() != null ? this.getLeftChild().height() : 0;
        Integer right = this.getRightChild() != null ? this.getRightChild().height() : 0; //TODO:: Here it fails
        this.balancingFactor = left - right;
    }

    public Integer getBalancingFactor(){
        return balancingFactor;
    }
}
