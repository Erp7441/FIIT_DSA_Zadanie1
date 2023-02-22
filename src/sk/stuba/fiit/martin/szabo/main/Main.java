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

        //? Loading from file
        /*String path = "data/data7.txt";
        Main.generateRandomDatasetToFile(path, 0, 100000, 30000);
        Tree tree = Main.createTreeFromFile(path, null);*/

        Tree tree = new Tree();

        long startTime = System.nanoTime();
        Main.generateRandomDatasetToTree(tree, 0, 1000000000, 20000);
        long endTime = System.nanoTime();
        long durationMilliseconds = (endTime - startTime) / 1000000;
        long durationSeconds = durationMilliseconds / 1000;

        out.println("Tree:\n" + tree);

        out.println("Tree created in " + durationSeconds + (
            durationSeconds > 1 ? " seconds (" : " second ("
        ) + durationMilliseconds + (
            durationMilliseconds > 1 ? " milliseconds)." : " millisecond)."
        ));

    }

    static Tree createTreeFromFile(String path, String delimiter) throws FileNotFoundException{
        Tree tree = new Tree();
        File file = new File(path);

        try(Scanner sc = new Scanner(file)){
            while(sc.hasNext()){
                String line = sc.next();
                insertLine(tree, line, delimiter);
            }
        }
        catch(FileNotFoundException e){
            throw new FileNotFoundException();
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

    static void generateRandomDatasetToFile(String path, Integer min, Integer max, Integer count) throws IOException{
        File file = new File(path);

        try{
            if(!file.exists() && !file.createNewFile()){
                throw new IOException("Error creating file " + path);
            }
        }
        catch(IOException e){
            throw new IOException("Error creating file " + path);
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
            throw new IOException("Error generating dataset");
        }

    }

    static void generateRandomDatasetToTree(Tree tree, Integer min, Integer max, Integer count){
            for(int i=0; i<count; i++){
                tree.insert((int) Math.floor((Math.random() * (max - min + 1)) + min));
            }
    }

}