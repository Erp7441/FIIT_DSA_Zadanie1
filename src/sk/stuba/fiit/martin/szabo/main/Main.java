package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.avl.Tree;
import sk.stuba.fiit.martin.szabo.utils.Parser;

import java.io.IOException;

import static java.lang.System.out;

// TODO:: Check avl tree deletion

// TODO:: refactor placments in classes
// TODO:: create menus and file opening system

public class Main{
    public static void main(String[] args){

        // Test case for left tree: 3, 5, 0, 2, 1
        // Test case for right tree 5, 1, 9, 7, 8

        Parser parser = new Parser();


        parser.getInputFromFile("data.txt", " ");
        out.println("\n" + parser);
        Tree avl = parser.createTree();
        out.println("\nTree:\n" + avl);
    }
}