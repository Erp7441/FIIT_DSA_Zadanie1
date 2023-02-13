package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.structure.Node;

public class Main{
    public static void main(String[] args){
        Node root = new Node(1);
        root.setRight(new Node(2));
        root.setLeft(new Node(3));
        root.getRight().setLeft(new Node(4));
        root.getRight().setRight(new Node(5));
        root.printNodes();

    }
}