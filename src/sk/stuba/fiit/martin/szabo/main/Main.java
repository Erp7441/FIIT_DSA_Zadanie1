package sk.stuba.fiit.martin.szabo.main;

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system
// TODO:: Add interface for AvlTree and implement it on AVL and RedBlack AvlTree
// TODO:: !!! Remove redundant calculations !!!
// TODO:: Remove debug outputs

// TODO:: ADDDDDDD STRINGGGGGGGSSSSSSS

import sk.stuba.fiit.martin.szabo.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.bst.BstNode;
import sk.stuba.fiit.martin.szabo.bst.BstTree;
import sk.stuba.fiit.martin.szabo.splay.SplayTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){
        avlTreeExecute(1000000000, 1000000);
        splayTreeExecute(1000000000, 1000000);
    }

    static void splayTreeExecute(Integer max, Integer count){

        //* ----------------------------- Splay Tree creation -----------------------------
        long startTime = System.nanoTime();
        SplayTree splayTree = new SplayTree();

        //? Loading from file
        //String path = "data/data9.txt";
        //Main.generateRandomDatasetToFile(path, 0, 10000000, 1000000);
        //Main.createTreeFromFile(splayTree, path, ",");

        //? Generating random dataset
        ArrayList <Integer> randomDataset = Main.generateRandomDatasetToTree(splayTree, 0, max, count);

        //* Fancy output

        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Splay insert -----------------------------
        startTime = System.nanoTime();

        splayTree.insert(605022365);

        endTime = System.nanoTime();
        long insertNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Splay search -----------------------------
        startTime = System.nanoTime();

        splayTree.search(605022365);

        endTime = System.nanoTime();
        long searchNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Splay output -----------------------------
        startTime = System.nanoTime();

        //out.println(splayTree);

        endTime = System.nanoTime();
        long outputNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Splay deletion -----------------------------
        startTime = System.nanoTime();

        for(int nodeToDelete : randomDataset){
            splayTree.delete(nodeToDelete);
        }

        //splayTree.delete(522);

        endTime = System.nanoTime();
        long deletionNanoSeconds = endTime - startTime;
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Staistics  -----------------------------
        out.println("Splay Tree creation took " + durationNanoseconds + (
                durationNanoseconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("Splay Tree insertion took " + insertNanoSeconds + (
                insertNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("Splay Tree search took " + searchNanoSeconds + (
                searchNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("Splay Tree output took " + outputNanoSeconds + (
                outputNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println("Splay Tree deletion took " + deletionNanoSeconds + (
                deletionNanoSeconds != 1 ? " nano seconds " : " nano second"
        ));
        out.println();
        //* --------------------------------------------------------------------------
    }

    static void avlTreeExecute(Integer max, Integer count){

        //* ----------------------------- AvlTree creation -----------------------------
        long startTime = System.nanoTime();
        AvlTree avlTree = new AvlTree();

            //? Loading from file
            //String path = "data/data8.txt";
            //Main.generateRandomDatasetToFile(path, 0, 100000, 10000);
            //Main.createTreeFromFile(avlTree, path, ",");

            //? Generating random dataset
            ArrayList <Integer> randomDataset = Main.generateRandomDatasetToTree(avlTree, 0, max, count);

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

            //out.println(avlTree);

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
        out.println();
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