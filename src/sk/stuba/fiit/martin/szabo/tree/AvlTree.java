package sk.stuba.fiit.martin.szabo.tree;

public class AvlTree extends BinarySearchTree{

    public AvlTree(){
        this.setRoot(null);
    }
    public AvlTree(AvlNode root){
        this.setRoot(root);
    }

    @Override
    public AvlNode getRoot(){
        BinarySearchNode root = super.getRoot();
        if(root instanceof AvlNode){
            return ((AvlNode) root);
        }
        return null;
    }

    public AvlTree(BinarySearchNode root){
        super(root);
    }
}
