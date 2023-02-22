package sk.stuba.fiit.martin.szabo.main;


// TODO:: Check avl tree deletion

// TODO:: refactor placements in classes
// TODO:: create menus and file opening system

// TODO:: Add interface for Tree and implement it on AVL and RedBlack Tree

// TODO:: Remove redundant calculations

import sk.stuba.fiit.martin.szabo.avl.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

public class Main{
    public static void main(String[] args){

        //* Tree creation
        long startTime = System.nanoTime();

        //? Loading from file
        String path = "data/data8.txt";
        //Main.generateRandomDatasetToFile(path, 0, 100000, 30000);
        Tree tree = Main.createTreeFromFile(path, null);


        //Tree tree = new Tree();
        //Main.generateRandomDatasetToTree(tree, 0, 20, 6000);

        long endTime = System.nanoTime();
        long durationMilliseconds = (endTime - startTime) / 1000000;
        long durationSeconds = durationMilliseconds / 1000;



        //* Tree output
        startTime = System.nanoTime();

        out.println("Tree:\n" + tree);

        endTime = System.nanoTime();
        long outputMiliseconds = (endTime - startTime) / 1000000;
        long outputSeconds = outputMiliseconds / 1000;

        out.println("Tree created in " + durationSeconds + (
            durationSeconds != 1 ? " seconds (" : " second ("
        ) + durationMilliseconds + (
            durationMilliseconds != 1 ? " milliseconds)." : " millisecond)."
        ));

        out.println("Tree output took " + outputSeconds + (
                outputSeconds != 1 ? " seconds (" : " second ("
        ) + outputMiliseconds + (
                outputMiliseconds != 1 ? " milliseconds)." : " millisecond)."
        ));

        out.println("\nDEBUG: Deleting node");
        tree.delete(15);
        out.println("\n" + tree);
    }

    static Tree createTreeFromFile(String path, String delimiter){
        Tree tree = new Tree();
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

        return tree;
    }

    private static void insertLine(Tree tree, String line, String delimiter){
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

    static void generateRandomDatasetToTree(Tree tree, Integer min, Integer max, Integer count){
            for(int i=0; i<count; i++){
                tree.insert((int) Math.floor((Math.random() * (max - min + 1)) + min));
            }
    }

}