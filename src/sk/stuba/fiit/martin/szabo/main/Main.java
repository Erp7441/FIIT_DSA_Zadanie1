package sk.stuba.fiit.martin.szabo.main;


import sk.stuba.fiit.martin.szabo.hashtable.HashtableHelper;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;
import sk.stuba.fiit.martin.szabo.hashtable.separatechaining.HashtableSeparateChaining;
import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;
import sk.stuba.fiit.martin.szabo.utils.Dataset;

import java.util.ArrayList;

import static java.lang.System.out;


public class Main{
    public static void main(String[] args){
        //ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        //ArrayList<String> datasetString = Dataset.createStringDataset(null, dataset.size());

        //ArrayList<Integer> dataset = Dataset.createInOrderIntegerDataset(1, 1000000);
        //ArrayList<String> datasetString = Dataset.createInOrderStringDataset(1, 1000000);
        //ArrayList<Integer> dataset = Dataset.createInOrderIntegerDataset(1000000, 1);
        ArrayList<String> datasetString = Dataset.createInOrderStringDataset(1000000, 1);

        //trees(dataset, null, datasetString, false, true);
        hashtables(datasetString, "TEST_INSERT",false, true);
    }

    private static void trees(ArrayList<Integer> dataset, Integer insertionValue, ArrayList<String> datasetString, Boolean output, Boolean delete){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        //TreeHelper.treeExecute(avlTree, "Avl Tree", insertionValue, output, dataset, datasetString, delete);
        //TreeHelper.treeExecute(splayTree, "Splay Tree", insertionValue, output, dataset, datasetString, delete);
    }

    private static void hashtables(ArrayList<String> dataset, String insertionValue, Boolean output, Boolean delete){
        HashtableOpenAddressing openAddressing = new HashtableOpenAddressing();
        HashtableSeparateChaining separateChaining = new HashtableSeparateChaining();

        HashtableHelper.hashtableExecute(openAddressing, "Open Addressing Hashtable", insertionValue, output, delete, dataset);
        //HashtableHelper.hashtableExecute(separateChaining, "Separate Chaning Hashtable", insertionValue, output, delete, dataset);
    }


}