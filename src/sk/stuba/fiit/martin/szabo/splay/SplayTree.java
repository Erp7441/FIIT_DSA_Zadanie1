package sk.stuba.fiit.martin.szabo.splay;

import org.w3c.dom.Node;
import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

public class SplayTree extends BstTree{

    public void splay(BstNode node){
        while(node.getParent() != null){
            splayNode(node);
        }
    }

    private void splayNode(BstNode node){
        if(node.isOnLeft() && node.getParent().getParent() == null){
            rightRotate(node);
        }
        else if(node.isOnRight() && node.getParent().getParent() == null){
            leftRotate(node);
        }
        else if(node.isOnRight() && node.getParent().isOnLeft()){
            leftRightRotate(node);
        }
        else if(node.isOnLeft() && node.getParent().isOnRight()){
            rightLeftRotate(node);
        }
        else if(node.isOnLeft() && node.getParent().isOnLeft()){
            rightRotate(node.getParent());
            rightRotate(node);
        }
        else if(node.isOnRight() && node.getParent().isOnRight()){
            leftRotate(node.getParent());
            leftRotate(node);
        }
    }

    @Override
    public boolean insert(BstNode node){

        return false;
    }

    @Override
    public boolean insert(Integer value){
        BstNode node = new BstNode(value);
        return insert(node);
    }

}
