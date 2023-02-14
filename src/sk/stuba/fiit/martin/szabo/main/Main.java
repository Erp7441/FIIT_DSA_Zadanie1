package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.structure.Node;
import sk.stuba.fiit.martin.szabo.structure.Tree;
import sk.stuba.fiit.martin.szabo.utils.Parser;
import sk.stuba.fiit.martin.szabo.utils.Position;

import java.util.ArrayList;

import static java.lang.System.*;

// TODO refactor placments in classes

public class Main{
    public static void main(String[] args){

        ArrayList<Object> dataset = new ArrayList<>(5);
        dataset.add(1);
        dataset.add(2);
        dataset.add(3);
        dataset.add(4);
        dataset.add(5);

        Parser parser = new Parser(dataset);

        Tree tree = parser.createTree();

        out.println(tree);

        /*
        Tree tree = new Tree(new Node(1));

        tree.insert(3, 1, Position.LEFT);
        tree.insert(2, 1, Position.RIGHT);
        tree.insert(4, 3, Position.LEFT);
        tree.insert(5, 3, Position.RIGHT);

        tree.insert(6, 5, Position.RIGHT);

        tree.delete(5);
        out.println(tree);
        //out.println(tree.search(5));
        */
    }
}