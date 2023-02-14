package sk.stuba.fiit.martin.szabo.utils;

import sk.stuba.fiit.martin.szabo.structure.Node;
import sk.stuba.fiit.martin.szabo.structure.Tree;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser{
    private ArrayList<Object> dataset;

    public Parser(){
        this.dataset = null;
    }
    public Parser(ArrayList<Object> dataset){
        this.dataset = dataset;
    }

    public void getInput(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your dataset [enter \"done\" to end dataset]: ");
        String input;
        do{
            input = in.nextLine();
            if(input.equals("")){
                dataset.add(null);
                continue;
            }
            dataset.add(input);

        } while(!input.equals("done"));
    }

    public Tree createTree(){
        Node root = new Node(dataset.get(0));
        Tree tree = new Tree(root);

        // TODO auto balance tree in Tree class
        for(int i = 1; i < dataset.size(); i++){
            tree.insert(dataset.get(i), i, Position.LEFT);
        }
        return tree;
    }

    public ArrayList<Object> getDataset(){
        return this.dataset;
    }
    public void setDataset(ArrayList<Object> input){
        this.dataset = input;
    }

}
