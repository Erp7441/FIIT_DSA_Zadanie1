package sk.stuba.fiit.martin.szabo.main;


// TODO:: Check avl tree deletion

// TODO:: refactor placments in classes
// TODO:: create menus and file opening system

// TODO:: Add interface for Tree and implement it on AVL and RedBlack Tree

import sk.stuba.fiit.martin.szabo.avl.Node;
import sk.stuba.fiit.martin.szabo.avl.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

        String path = "data/data3.txt";
        //Main.generateRandomDataset(path, 0, 1000000, 30000);
        Tree tree = Main.createTreeFromFile(path, ",");


        System.out.println("Tree:\n" + tree);


        //? Gets stuck on vlaue 913466
        //? Right child of that value is 935946
        //? Parent is 913466



























































































        /*Tree tree = new Tree();
        tree.insert(50);
        tree.insert(209);
        tree.insert(210); //! This did not get rotated on insertions of 464
        tree.insert(464);
        tree.insert(506); //! Issue, this ended up in left subtree instead of right
        /*tree.insert(994);
        /*tree.insert(1100);
        /*tree.insert(1104);
        /*tree.insert(1160);
        /*tree.insert(1282);
        /*tree.insert(1358);
        /*tree.insert(1459);
        /*tree.insert(1726);
        /*tree.insert(1999);
        /*tree.insert(2044);
        /*tree.insert(2287);
        /*tree.insert(2309);
        /*tree.insert(2437);
        /*tree.insert(2735);
        /*tree.insert(3072);
        /*tree.insert(3111);
        /*tree.insert(3308);
        /*tree.insert(3545);
        /*tree.insert(3608);
        /*tree.insert(3740);
        /*tree.insert(3788);
        /*tree.insert(3793);
        /*tree.insert(3985);
        /*tree.insert(4346);
        /*tree.insert(4387);
        /*tree.insert(4476);
        /*tree.insert(4954);
        /*tree.insert(5007);
        /*tree.insert(5119);
        /*tree.insert(5193);
        /*tree.insert(5247);
        /*tree.insert(5351);
        /*tree.insert(5782);
        /*tree.insert(6329);
        /*tree.insert(6410);
        /*tree.insert(6486);
        /*tree.insert(6536);
        /*tree.insert(6578);
        /*tree.insert(6794);
        /*tree.insert(6865);
        /*tree.insert(6979);
        /*tree.insert(7089);
        /*tree.insert(7209);
        /*tree.insert(7378);
        /*tree.insert(7391);
        /*tree.insert(7552);
        /*tree.insert(7883);
        /*tree.insert(8160);
        /*tree.insert(8307);
        /*tree.insert(8755);
        /*tree.insert(8857);
        /*tree.insert(8927);
        /*tree.insert(9073);
        /*tree.insert(9138);
        /*tree.insert(9180);*/

















































        /*Tree tree = new Tree();

        tree.insert(214);
        tree.insert(475);
        tree.insert(296);
        tree.insert(428);
        tree.insert(329);
        tree.insert(286);
        tree.insert(352);
        tree.insert(456);
        tree.insert(115);
        tree.insert(497);
        tree.insert(166);
        tree.insert(465);
        tree.insert(113);
        tree.insert(291);*/




































        /*Tree treeRL = new Tree();
        Tree treeLR = new Tree();
        Tree treeL = new Tree();
        Tree treeR = new Tree();

        // Right left rotate
        treeRL.insert(new Node(1));
        treeRL.insert(new Node(3));
        treeRL.insert(new Node(2));

        /*treeRL.insert(new Node(4));
        treeRL.insert(new Node(5));

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
        System.out.println("Right rotate test tree:\n" + treeR);*/

    }

    static Tree createTreeFromFile(String path, String delimiter){
        Tree tree = new Tree();
        File file = new File(path);

        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        while(sc.hasNext()){
            String line = sc.next();
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

        return tree;
    }

    static void generateRandomDataset(String path, Integer min, Integer max, Integer count){
        File file = new File(path);

        try{
            if(!file.exists()){
                file.createNewFile();
            }
        }
        catch(IOException e){
            throw new RuntimeException("Error creating file " + path);
        }

        try{
            FileWriter write = new FileWriter(path);
            for(int i=0; i<count; i++){
                int number = (int) Math.floor((Math.random() * (max - min + 1)) + min);
                write.append(String.valueOf(number));
                if(i != count-1){
                    write.append("\n");
                }
            }
            write.close();
        }
        catch(IOException e){
            throw new RuntimeException("Error generating dataset");
        }

    }
}