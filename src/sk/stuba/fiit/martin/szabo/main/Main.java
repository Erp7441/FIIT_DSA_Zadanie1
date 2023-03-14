package sk.stuba.fiit.martin.szabo.main;


// TODO:: refactor placements in classes
// TODO:: !!! Remove redundant calculations !!!


import sk.stuba.fiit.martin.szabo.hashtable.HashtableHelper;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;
import sk.stuba.fiit.martin.szabo.hashtable.separatechaining.HashtableSeparateChaining;
import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;
import sk.stuba.fiit.martin.szabo.utils.Dataset;

import java.util.ArrayList;

import static java.lang.System.*;


public class Main{
    public static void main(String[] args){
        //ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        ArrayList<String> datasetString = Dataset.createStringDataset(null, 1000000);

        //trees(dataset, 605022365, datasetString, false);

        /*
            Open Addressing Hashtable creation took 177.813201904297 seconds
            Open Addressing Hashtable insertion took 0.000154300 seconds
            Open Addressing Hashtable search took 0.000024600 seconds
            Open Addressing Hashtable output took 0.145627290 seconds
            Open Addressing Hashtable deletion took 82.18177032471 seconds
        */
        hashtables(datasetString, "TEST_INSERT",false, true);
    }

    // TODO:: add if statements to tree handler to match hashtable handler
    private static void trees(ArrayList<Integer> dataset, Integer insertionValue, ArrayList<String> datasetString, Boolean output, Boolean delete){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        TreeHelper.treeExecute(avlTree, "Avl Tree", insertionValue, output, dataset, datasetString, delete);
        TreeHelper.treeExecute(splayTree, "Splay Tree", insertionValue, output, dataset, datasetString, delete);
    }

    private static void hashtables(ArrayList<String> dataset, String insertionValue, Boolean output, Boolean delete){
        HashtableOpenAddressing openAddressing = new HashtableOpenAddressing();
        HashtableSeparateChaining separateChaining = new HashtableSeparateChaining();

        out.println("DEBUG: openAddressing:");
        HashtableHelper.hashtableExecute(openAddressing, "Open Addressing Hashtable", insertionValue, output, delete, dataset);

        out.println("DEBUG: separateChaining:");
        HashtableHelper.hashtableExecute(separateChaining, "Separate Chaning Hashtable", insertionValue, output, delete, dataset);
    }
}