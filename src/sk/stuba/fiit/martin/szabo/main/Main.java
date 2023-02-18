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
        Node value1 = new Node(1);
        Node value0 = new Node(0);
        Node value2 = new Node(2);
        Node value5 = new Node(5);

        Tree avl = new Tree(root);
        avl.insert(value5);
        avl.insert(value0);
        avl.insert(value2);
        avl.insert(value1);
        avl.rightRotate(value2);
        avl.leftRotate(value0);

        out.println(avl);
    }
}