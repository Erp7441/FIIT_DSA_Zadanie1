package sk.stuba.fiit.martin.szabo.tree;

import sk.stuba.fiit.martin.szabo.tree.bst.BstTree;
import sk.stuba.fiit.martin.szabo.utils.TimeConverter;

import java.util.ArrayList;

import static java.lang.System.out;

public class TreeHelper{

    private TreeHelper(){}

    public static void treeExecute(BstTree tree, String type, Integer insertionValue, Boolean output, ArrayList<Integer> dataset, ArrayList<String> datasetValue){

        //* ----------------------------- Tree creation -----------------------------
        long startTime = System.nanoTime();

        for(int i = 0; i < dataset.size(); i++){
            tree.insert(dataset.get(i), datasetValue.get(i));
        }

        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree insert -----------------------------
        startTime = System.nanoTime();

        tree.insert(insertionValue);

        endTime = System.nanoTime();
        long insertNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree search -----------------------------
        startTime = System.nanoTime();

        tree.search(insertionValue);

        endTime = System.nanoTime();
        long searchNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree output -----------------------------
        startTime = System.nanoTime();

        if(Boolean.TRUE.equals(output)){
            out.println(tree);
        }

        endTime = System.nanoTime();
        long outputNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree deletion -----------------------------
        startTime = System.nanoTime();

        for(int nodeToDelete : dataset){
            tree.delete(nodeToDelete);
        }
        tree.delete(insertionValue);

        endTime = System.nanoTime();
        long deletionNanoSeconds = endTime - startTime;
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Statistics  -----------------------------
        out.println(type + " creation took " + TimeConverter.nanoToSeconds(durationNanoseconds) + (
                durationNanoseconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " insertion took " + TimeConverter.nanoToSeconds(insertNanoSeconds) + (
                insertNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " search took " + TimeConverter.nanoToSeconds(searchNanoSeconds) + (
                searchNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " output took " + TimeConverter.nanoToSeconds(outputNanoSeconds) + (
                outputNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println(type + " deletion took " + TimeConverter.nanoToSeconds(deletionNanoSeconds) + (
                deletionNanoSeconds != 1 ? " seconds " : " second"
        ));
        out.println();
        //* --------------------------------------------------------------------------
    }



}
