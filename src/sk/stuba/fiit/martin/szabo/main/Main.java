package sk.stuba.fiit.martin.szabo.main;


// TODO:: refactor placements in classes
// TODO:: !!! Remove redundant calculations !!!


import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;

import sk.stuba.fiit.martin.szabo.utils.Dataset;

import java.util.ArrayList;


public class Main{
    public static void main(String[] args){
        ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        ArrayList<String> datasetString = Dataset.createStringDataset(null, dataset.size());

        //trees(dataset, datasetString, false);
        hashtables(dataset, datasetString, false);
    }

    private static void trees(ArrayList<Integer> dataset, ArrayList<String> datasetString, Boolean output){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        TreeHelper.treeExecute(avlTree, "Avl Tree", 605022365, output, dataset, datasetString);
        TreeHelper.treeExecute(splayTree, "Splay Tree", 605022365, output, dataset, datasetString);
    }

    private static void hashtables(ArrayList<Integer> dataset, ArrayList<String> datasetString, Boolean output){

    }
}