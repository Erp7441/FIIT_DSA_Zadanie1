package sk.stuba.fiit.martin.szabo.main;

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system
// TODO:: Add interface for AvlTree and implement it on AVL and RedBlack AvlTree
// TODO:: !!! Remove redundant calculations !!!
// TODO:: Remove debug outputs

import sk.stuba.fiit.martin.szabo.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.bst.BstTree;
import sk.stuba.fiit.martin.szabo.redblack.RedBlackTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){
        //avlTreeExecute();
        redBlackTreeExecute();
    }

    static void redBlackTreeExecute(){

        RedBlackTree redBlackTree = new RedBlackTree();

            //? Loading from file
            //String path = "data/data8.txt";
            //Main.generateRandomDatasetToFile(path, 0, 100000, 10000);
            //Main.createTreeFromFile(redBlackTree, path, null);

            //? Generating random dataset
            //ArrayList <Integer> randomDataset = Main.generateRandomDatasetToTree(redBlackTree, 0, 10, 5);

        redBlackTree.insert(50);
        redBlackTree.insert(209);
        redBlackTree.insert(210);
        redBlackTree.insert(464);
        redBlackTree.insert(506);
        redBlackTree.insert(994);
        redBlackTree.insert(1100);
        redBlackTree.insert(1104); //? Here it now works

        // Untested
        /*redBlackTree.insert(1160);
        /*redBlackTree.insert(1282);
        /*redBlackTree.insert(1358);
        /*redBlackTree.insert(1459);
        /*redBlackTree.insert(1726);
        /*redBlackTree.insert(1999);
        /*redBlackTree.insert(2044);
        /*redBlackTree.insert(2287);
        /*redBlackTree.insert(2309);
        /*redBlackTree.insert(2437);
        /*redBlackTree.insert(2735);
        /*redBlackTree.insert(3072);
        /*redBlackTree.insert(3111);
        /*redBlackTree.insert(3308);
        /*redBlackTree.insert(3545);
        /*redBlackTree.insert(3608);
        /*redBlackTree.insert(3740);
        /*redBlackTree.insert(3788);
        /*redBlackTree.insert(3793);
        /*redBlackTree.insert(3985);
        /*redBlackTree.insert(4346);
        /*redBlackTree.insert(4387);
        /*redBlackTree.insert(4476);
        /*redBlackTree.insert(4954);
        /*redBlackTree.insert(5007);
        /*redBlackTree.insert(5119);
        /*redBlackTree.insert(5193);
        /*redBlackTree.insert(5247);
        /*redBlackTree.insert(5351);
        /*redBlackTree.insert(5782);
        /*redBlackTree.insert(6329);
        /*redBlackTree.insert(6410);
        /*redBlackTree.insert(6486);
        /*redBlackTree.insert(6536);
        /*redBlackTree.insert(6578);
        /*redBlackTree.insert(6794);
        /*redBlackTree.insert(6865);
        /*redBlackTree.insert(6979);
        /*redBlackTree.insert(7089);
        /*redBlackTree.insert(7209);
        /*redBlackTree.insert(7378);
        /*redBlackTree.insert(7391);
        /*redBlackTree.insert(7552);
        /*redBlackTree.insert(7883);
        /*redBlackTree.insert(8160);
        /*redBlackTree.insert(8307);
        /*redBlackTree.insert(8755);
        /*redBlackTree.insert(8857);
        /*redBlackTree.insert(8927);
        /*redBlackTree.insert(9073);
        /*redBlackTree.insert(9138);
        /*redBlackTree.insert(9180);*/

        out.println(redBlackTree);
    }

    static void avlTreeExecute(){

        //* ----------------------------- AvlTree creation -----------------------------
        long startTime = System.nanoTime();
        AvlTree avlTree = new AvlTree();

            //? Loading from file
            //String path = "data/data8.txt";
            //Main.generateRandomDatasetToFile(path, 0, 100000, 10000);
            //Main.createTreeFromFile(avlTree, path, ",");

            //? Generating random dataset
            ArrayList <Integer> randomDataset = Main.generateRandomDatasetToTree(avlTree, 0, 1000000, 10000);

        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- AvlTree insert -----------------------------
        startTime = System.nanoTime();

            avlTree.insert(605022365);

        endTime = System.nanoTime();
        long insertNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- AvlTree search -----------------------------
        startTime = System.nanoTime();

            avlTree.search(605022365);

        endTime = System.nanoTime();
        long searchNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- AvlTree output -----------------------------
        startTime = System.nanoTime();

            out.println(avlTree);

        endTime = System.nanoTime();
        long outputNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- AvlTree deletion -----------------------------
        startTime = System.nanoTime();

            for(int nodeToDelete : randomDataset){
                avlTree.delete(nodeToDelete);
            }

        endTime = System.nanoTime();
        long deletionNanoSeconds = endTime - startTime;
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Staistics  -----------------------------
        out.println("AvlTree creation took " + durationNanoseconds + (
                durationNanoseconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("AvlTree insertion took " + insertNanoSeconds + (
                insertNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("AvlTree search took " + searchNanoSeconds + (
                searchNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("AvlTree output took " + outputNanoSeconds + (
                outputNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("AvlTree deletion took " + deletionNanoSeconds + (
                deletionNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        //* --------------------------------------------------------------------------
    }

    static void createTreeFromFile(BstTree tree, String path, String delimiter){
        File file = new File(path);

        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                String line = sc.next();
                insertLine(tree, line, delimiter);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void insertLine(BstTree tree, String line, String delimiter){
        if(delimiter != null){
            String[] split = line.split(delimiter);
            for(String input : split){
                try{
                    tree.insert(Integer.parseInt(input));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            tree.insert(Integer.parseInt(line));
        }
    }

    static void generateRandomDatasetToFile(String path, Integer min, Integer max, Integer count){
        File file = new File(path);

        try{
            if(!file.exists() && !file.createNewFile()){
                err.println("Error creating file " + path);
            }
        }
        catch(IOException e){
            err.println("Error creating file " + path);
            e.printStackTrace();
        }

        try(FileWriter write = new FileWriter(path)){
            for(int i=0; i<count; i++){
                int number = (int) Math.floor((Math.random() * (max - min + 1)) + min);
                write.append(String.valueOf(number));
                if(i != count-1){
                    write.append("\n");
                }
            }
        }
        catch(IOException e){
            err.println("Error generating dataset");
            e.printStackTrace();
        }

    }

    static ArrayList<Integer> generateRandomDatasetToTree(BstTree tree, Integer min, Integer max, Integer count){
        ArrayList<Integer> randomDataset = new ArrayList<>();
        for(int i=0; i<count; i++){
            Integer number = (int) Math.floor((Math.random() * (max - min + 1)) + min);
            randomDataset.add(number);
            tree.insert(number);
        }
        return randomDataset;
    }

}