package sk.stuba.fiit.martin.szabo.main;


// TODO:: Check avl tree deletion

// TODO:: refactor placments in classes
// TODO:: create menus and file opening system

// TODO:: Add interface for Tree and implement it on AVL and RedBlack Tree

import sk.stuba.fiit.martin.szabo.avl.Node;
import sk.stuba.fiit.martin.szabo.avl.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        // Data1 is testing case for left tree: 3, 5, 0, 2, 1
        // Data2 is testing case for right tree 5, 1, 9, 7, 8
        // Data3 is large dataset #1
        // Data4 is large dataset #2
        // Data5 is dataset that creates bug

        //? Loading from file
        /*ArrayList<Integer> data = Main.dataFromFile("data/data5.txt", ",");
        Tree tree = Main.createTree(data);*/

        Tree tree = new Tree();

        tree.insert(158);
        tree.insert(131);
        tree.insert(424);
        tree.insert(172);
        tree.insert(117);
        tree.insert(408);
        tree.insert(444);
        tree.insert(234);

        System.out.println(tree);


        /*
        Tree treeRL = new Tree();
        Tree treeLR = new Tree();
        Tree treeL = new Tree();
        Tree treeR = new Tree();

        // Right left rotate
        treeRL.insert(new Node(1));
        treeRL.insert(new Node(3));
        treeRL.insert(new Node(2));

        // Left right rotate
        treeLR.insert(new Node(3));
        treeLR.insert(new Node(1));
        treeLR.insert(new Node(2));

        // Left rotate
        treeL.insert(new Node(1));
        treeL.insert(new Node(2));
        treeL.insert(new Node(3));

        // Right rotate
        treeR.insert(new Node(3));
        treeR.insert(new Node(2));
        treeR.insert(new Node(1));

        System.out.println("Right left rotate test tree:\n" + treeRL);
        System.out.println("Left right rotate test tree:\n" + treeLR);
        System.out.println("Left rotate test tree:\n" + treeL);
        System.out.println("Right rotate test tree:\n" + treeR);
        */
    }

    static ArrayList<Integer> dataFromFile(String path, String delimiter){
        File file = new File(path);

        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        ArrayList<Integer> data = new ArrayList<>();
        while(sc.hasNext()){
            String line = sc.next();
            if(delimiter != null){
                String[] split = line.split(delimiter);
                for(String input : split){
                    try{
                        data.add(Integer.parseInt(input));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                data.add(Integer.parseInt(line));
            }
        }
        return data;
    }
    static Tree createTree(ArrayList<Integer> data){
        Tree tree = new Tree(new Node(data.get(0)));
        for(int i=1; i<data.size(); i++){
            tree.insert(data.get(i));
        }
        return tree;
    }
}