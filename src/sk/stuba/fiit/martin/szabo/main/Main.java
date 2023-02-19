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

        ArrayList<Integer> data = Main.dataFromFile("data/data1.txt", ",");

        Tree tree = Main.createTree(data);
        System.out.println(tree);
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