package sk.stuba.fiit.martin.szabo.tree;

public class AvlNode extends BinarySearchNode{
    private Integer balancingFactor;

    public AvlNode(Integer value){
        super(value);
    }
    public void calculateBalancingFactor(){
        this.balancingFactor = this.getLeftChild().height() - this.getRightChild().height();
    }

    public Integer getBalancingFactor(){
        return balancingFactor;
    }
}
