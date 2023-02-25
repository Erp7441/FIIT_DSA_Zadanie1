package sk.stuba.fiit.martin.szabo.main;

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system
// TODO:: Add interface for AvlTree and implement it on AVL and RedBlack AvlTree
// TODO:: !!! Remove redundant calculations !!!
// TODO:: Remove debug outputs

import sk.stuba.fiit.martin.szabo.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){
        avlTreeExecute();
    }

    static void avlTreeExecute(){

        //* ----------------------------- AvlTree creation -----------------------------
        long startTime = System.nanoTime();

            //? Loading from file
            //String path = "data/data8.txt";
            //Main.generateRandomDatasetToFile(path, 0, 100000, 10000);
            //AvlTree avlTree = (AvlTree) Main.createTreeFromFile(path, ",");

            //? Generating random dataset
            AvlTree avlTree = new AvlTree();
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

    static BstTree createTreeFromFile(String path, String delimiter){
        AvlTree avlTree = new AvlTree();
        File file = new File(path);

        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                String line = sc.next();
                insertLine(avlTree, line, delimiter);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return avlTree;
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