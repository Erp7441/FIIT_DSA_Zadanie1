package sk.stuba.fiit.martin.szabo.main;

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system
// TODO:: Add interface for AvlTree and implement it on AVL and RedBlack AvlTree
// TODO:: !!! Remove redundant calculations !!!
// TODO:: Remove debug outputs

// TODO:: ADDDDDDD STRINGGGGGGGSSSSSSS

import sk.stuba.fiit.martin.szabo.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.bst.BstTree;
import sk.stuba.fiit.martin.szabo.splay.SplayTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        ArrayList<Integer> dataset = Main.createDataset("data/data11.txt", 0, 1000000000, 1000000, true);

        treeExecute(avlTree, "Avl Tree", 605022365, false, dataset);
        treeExecute(splayTree, "Splay Tree", 605022365, false, dataset);
    }

    static void treeExecute(BstTree tree, String type, Integer insertionValue, Boolean output, ArrayList<Integer> dataset){

        //* ----------------------------- Tree creation -----------------------------
        long startTime = System.nanoTime();

        for(Integer i: dataset){
            tree.insert(i);
        }

        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree insert -----------------------------
        startTime = System.nanoTime();

        tree.insert(insertionValue);

        endTime = System.nanoTime();
        long insertNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree search -----------------------------
        startTime = System.nanoTime();

        tree.search(insertionValue);

        endTime = System.nanoTime();
        long searchNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree output -----------------------------
        startTime = System.nanoTime();

        if(Boolean.TRUE.equals(output)){
            out.println(tree);
        }

        endTime = System.nanoTime();
        long outputNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree deletion -----------------------------
        startTime = System.nanoTime();

        for(int nodeToDelete : dataset){
            tree.delete(nodeToDelete);
        }
        tree.delete(insertionValue);

        endTime = System.nanoTime();
        long deletionNanoSeconds = endTime - startTime;
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Statistics  -----------------------------
        out.println(type + " creation took " + Main.nanoToSeconds(durationNanoseconds) + (
                durationNanoseconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " insertion took " + Main.nanoToSeconds(insertNanoSeconds) + (
                insertNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " search took " + Main.nanoToSeconds(searchNanoSeconds) + (
                searchNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " output took " + Main.nanoToSeconds(outputNanoSeconds) + (
                outputNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " deletion took " + Main.nanoToSeconds(deletionNanoSeconds) + (
                deletionNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println();
        //* --------------------------------------------------------------------------
    }

    private static void generateRandomDatasetToFile(String path, Integer min, Integer max, Integer count){
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

    private static ArrayList<Integer> generateRandomDataset(Integer min, Integer max, Integer count){
        ArrayList<Integer> randomDataset = new ArrayList<>();
        for(int i=0; i<count; i++){
            Integer number = (int) Math.floor((Math.random() * (max - min + 1)) + min);
            randomDataset.add(number);
        }
        return randomDataset;
    }

    private static String nanoToSeconds(Long nanoseconds){
        Long decimal = Main.getNumberLength(nanoseconds);
        String specifier = "%." + ((decimal < 9L) ? 9L : decimal) + "f";
        return String.format(specifier, (nanoseconds.floatValue() / 1000000000));
    }

    private static Long getNumberLength(Long number){
        Long count = 0L;
        while(number != 0){
            count++;
            number = number / 10;
        }
        return count;
    }

    private static ArrayList<Integer> createDataset(String pathToFile, Integer min, Integer max, Integer count, Boolean overwrite){
        if(pathToFile != null){
            File file = new File(pathToFile);
            if(!file.exists() || Boolean.TRUE.equals(overwrite)){
                Main.generateRandomDatasetToFile(pathToFile, min, max, count);
            }
            return Main.fileToArray(pathToFile);
        }
        else{
            //? Generating random dataset
            return Main.generateRandomDataset(min, max, count);
        }
    }

    private static ArrayList<Integer> fileToArray(String pathToFile){
        File file = new File(pathToFile);
        Scanner sc;

        try{
            sc = new Scanner(file);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        ArrayList<Integer> dataset = new ArrayList<>();
        while(sc.hasNext()){
            dataset.add(Integer.valueOf(sc.next()));
        }

        return dataset;
    }

}