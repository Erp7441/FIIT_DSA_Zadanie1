package sk.stuba.fiit.martin.szabo.utils;

import sk.stuba.fiit.martin.szabo.tests.BinarySearchTree;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser{
    private ArrayList<Integer> dataset;

    public Parser(){
        this.dataset = null;
    }
    public Parser(ArrayList<Integer> dataset){
        this.dataset = dataset;
    }

    public void getInput(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your dataset [enter \"done\" to end dataset]: ");
        String input;
        while(true){
            input = in.nextLine();

            if(input.equals("")){
                dataset.add(null);
                continue;
            } else if(input.equals("done")){ break; }

            try{
                dataset.add(Integer.parseInt(input));
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input");
            }
        }
    }

    public BinarySearchTree createTree(){
        /*BinarySearchNode root = new BinarySearchNode(dataset.get(0));
        BinarySearchTree binaryTree = new BinarySearchTree(root);

        // TODO auto balance tests in BinarySearchTree class
        for(int i = 1; i < dataset.size(); i++){
            binaryTree.insert(dataset.get(i));
        }
        return binaryTree;*/
        return null;
    }

    public ArrayList<Integer> getDataset(){
        return this.dataset;
    }
    public void setDataset(ArrayList<Integer> input){
        this.dataset = input;
    }

}
