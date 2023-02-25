package sk.stuba.fiit.martin.szabo.main;


// TODO:: Check avl tree deletion

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system

// TODO:: Add interface for AvlTree and implement it on AVL and RedBlack AvlTree

// TODO:: !!! Remove redundant calculations !!!


import sk.stuba.fiit.martin.szabo.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.bst.BstTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){

        //* AvlTree creation
        long startTime = System.nanoTime();

        //? Loading from file
        String path = "data/data8.txt";
        //Main.generateRandomDatasetToFile(path, 0, 100000, 30000);
        AvlTree avlTree = (AvlTree) Main.createTreeFromFile(path, null);


        //? Generating random dataset
        //AvlTree avlTree = new AvlTree();
        //Main.generateRandomDatasetToTree(avlTree, 0, 30000, 10000);

        long endTime = System.nanoTime();
        long durationMilliseconds = (endTime - startTime) / 1000000;
        long durationSeconds = durationMilliseconds / 1000;

        out.println("AvlTree created in " + durationSeconds + (
                durationSeconds != 1 ? " seconds (" : " second ("
        ) + durationMilliseconds + (
                durationMilliseconds != 1 ? " milliseconds)." : " millisecond)."
        ));

        //* AvlTree output
        /*startTime = System.nanoTime();

        out.println("AvlTree:\n" + avlTree);

        endTime = System.nanoTime();
        long outputMiliseconds = (endTime - startTime) / 1000000;
        long outputSeconds = outputMiliseconds / 1000;

        out.println("AvlTree output took " + outputSeconds + (
                outputSeconds != 1 ? " seconds (" : " second ("
        ) + outputMiliseconds + (
                outputMiliseconds != 1 ? " milliseconds)." : " millisecond)."
        ));*/

        //* AvlTree insert
        /*startTime = System.nanoTime();

        avlTree.insert(605022365);

        endTime = System.nanoTime();
        long insertNanoseconds = (endTime - startTime);

        out.println("AvlTree insertion took " + insertNanoseconds + (
                insertNanoseconds != 1 ? " nano seconds (" : " nano second ("
        ));*/

        //* AvlTree search
        /*startTime = System.nanoTime();

        avlTree.search(605022365);

        endTime = System.nanoTime();
        long nanoSeconds = (endTime - startTime);

        out.println("AvlTree search took " + nanoSeconds + (
                nanoSeconds != 1 ? " nano seconds (" : " nano second ("
        ));*/

        int nodeToDelete = 15;
        out.println("\nDEBUG: Deleting node " + nodeToDelete);
        avlTree.delete(nodeToDelete);
        out.println("\n" + avlTree);
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

    static void generateRandomDatasetToTree(BstTree tree, Integer min, Integer max, Integer count){
            for(int i=0; i<count; i++){
                tree.insert((int) Math.floor((Math.random() * (max - min + 1)) + min));
            }
    }

}