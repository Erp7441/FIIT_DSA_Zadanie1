package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.avl.Tree;
import sk.stuba.fiit.martin.szabo.avl.Node;

//import sk.stuba.fiit.martin.szabo.tests.AvlNode;
//import sk.stuba.fiit.martin.szabo.tests.AvlTree;

import static java.lang.System.out;

// TODO refactor placments in classes

public class Main{
    public static void main(String[] args){

        Node root = new Node(3);
        Node lChild1 = new Node(1);
        Node lChild2 = new Node(2);
        Node rChild1 = new Node(4);
        Node rChild2 = new Node(5);
        Node rChild3 = new Node(6);

        Tree avl = new Tree(root);
        avl.insert(lChild1);
        avl.insert(lChild2);
        avl.insert(rChild1);
        avl.insert(rChild2);
        avl.insert(rChild3);

        out.println(avl);
    }
}