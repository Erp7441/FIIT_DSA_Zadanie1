package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.tree.BinarySearchNode;
import sk.stuba.fiit.martin.szabo.tree.BinarySearchTree;

// TODO refactor placments in classes

public class Main{
    public static void main(String[] args){

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(8);
        bst.insert(10);
        bst.insert(3);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(14);
        bst.insert(7);

        bst.delete(3);
        System.out.println(bst.toString());



        //System.out.println(bst.search(8));

        /*ArrayList<Integer> dataset = new ArrayList<>(5);
        dataset.add(1);
        dataset.add(2);
        dataset.add(3);
        dataset.add(4);
        dataset.add(5);

        Parser parser = new Parser(dataset);

        BinarySearchTree binaryTree = parser.createTree();

        out.println(binaryTree);
        */

        /*
        BinarySearchTree binaryTree = new BinarySearchTree(new BinarySearchNode(1));

        binaryTree.insert(3, 1, Position.LEFT);
        binaryTree.insert(2, 1, Position.RIGHT);
        binaryTree.insert(4, 3, Position.LEFT);
        binaryTree.insert(5, 3, Position.RIGHT);

        binaryTree.insert(6, 5, Position.RIGHT);

        binaryTree.delete(5);
        out.println(binaryTree);
        //out.println(binaryTree.search(5));
        */
    }
}