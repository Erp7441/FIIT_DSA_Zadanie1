package sk.stuba.fiit.martin.szabo.main;


// TODO:: refactor placements in classes
// TODO:: !!! Remove redundant calculations !!!


import sk.stuba.fiit.martin.szabo.hashtable.HashtableHelper;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;
import sk.stuba.fiit.martin.szabo.hashtable.separatechaining.HashtableSeparateChaining;
import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;

import java.util.ArrayList;

import static java.lang.System.*;


public class Main{
    public static void main(String[] args){
        //ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        //ArrayList<String> datasetString = Dataset.createStringDataset(null, dataset.size());

        //trees(dataset, datasetString, false);
        hashtables(null, null, false);
    }

    private static void trees(ArrayList<Integer> dataset, ArrayList<String> datasetString, Boolean output){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        TreeHelper.treeExecute(avlTree, "Avl Tree", 605022365, output, dataset, datasetString);
        TreeHelper.treeExecute(splayTree, "Splay Tree", 605022365, output, dataset, datasetString);
    }

    private static void hashtables(ArrayList<Integer> dataset, ArrayList<String> datasetString, Boolean output){
        HashtableOpenAddressing openAddressing = new HashtableOpenAddressing(7);
        HashtableSeparateChaining separateChaining = new HashtableSeparateChaining(7);

        out.println("DEBUG: openAddressing:");
        HashtableHelper.hashtableExecute(openAddressing);

        out.println("DEBUG: separateChaining:");
        HashtableHelper.hashtableExecute(separateChaining);
    }
}