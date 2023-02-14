package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.structure.Node;
import sk.stuba.fiit.martin.szabo.structure.Tree;
import sk.stuba.fiit.martin.szabo.utils.Position;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){
        Node root = new Node(1);
        Tree tree = new Tree(root);

        tree.insert(3, 1, Position.LEFT);
        tree.insert(2, 1, Position.RIGHT);
        tree.insert(4, 3, Position.LEFT);
        tree.insert(5, 3, Position.RIGHT);

        tree.insert(6, 5, Position.RIGHT);

        out.println(tree);
        //out.println(tree.search(5));
    }
}