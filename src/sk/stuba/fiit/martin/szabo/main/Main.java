package sk.stuba.fiit.martin.szabo.main;

import sk.stuba.fiit.martin.szabo.tree.AvlNode;
import sk.stuba.fiit.martin.szabo.tree.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.BinarySearchTree;

import static java.lang.System.out;

// TODO refactor placments in classes

public class Main{
    public static void main(String[] args){

        /*BinarySearchTree bst = new BinarySearchTree();
        bst.insert(8);
        bst.insert(10);
        bst.insert(3);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(14);
        bst.insert(7);

        //bst.delete(3);
        //System.out.println(bst.toString());
         */
        AvlNode root = new AvlNode(33);
        AvlNode node1 = new AvlNode(9);
        AvlNode node2 = new AvlNode(53);
        AvlNode node3 = new AvlNode(8);
        AvlNode node4 = new AvlNode(21);
        AvlNode node5 = new AvlNode(11);
        AvlNode node6 = new AvlNode(53);
        AvlNode node7 = new AvlNode(61);

        AvlTree avl = new AvlTree(root);
        avl.insert(node1);
        avl.insert(node2);
        avl.insert(node3);
        avl.insert(node4);
        avl.insert(node5);
        avl.insert(node6);
        avl.insert(node7);
        /*
        avl.insert(33);
        avl.insert(21);
        avl.insert(53);
        avl.insert(9);
        avl.insert(8);
        avl.insert(61);
        avl.insert(11);
        */

        node2.calculateBalancingFactor();
        out.println(node2.getBalancingFactor()); // FIXME:: Null exception



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