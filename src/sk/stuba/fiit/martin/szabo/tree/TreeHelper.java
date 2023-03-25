package sk.stuba.fiit.martin.szabo.tree;

import sk.stuba.fiit.martin.szabo.tree.bst.BstTree;
import sk.stuba.fiit.martin.szabo.utils.TimeConverter;

import java.util.ArrayList;

import static java.lang.System.out;

public class TreeHelper{

    private TreeHelper(){}

    public static void treeExecute(BstTree tree, String type, Integer insertionValue, Boolean output, ArrayList<Integer> dataset, ArrayList<String> datasetValue, Boolean delete){

        if(tree == null) return;

        //* ----------------------------- Tree creation -----------------------------
        long startTime;
        long endTime;
        long durationNanoseconds = -1;

        if(dataset != null && datasetValue != null){
            startTime = System.nanoTime();

            for(int i = 0; i < dataset.size(); i++){

                tree.insert(dataset.get(i), datasetValue.get(i));

            }


            endTime = System.nanoTime();
            durationNanoseconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree insert -----------------------------
        long insertNanoSeconds = -1;

        if(insertionValue != null){
            startTime = System.nanoTime();

            tree.insert(insertionValue);

            endTime = System.nanoTime();
            insertNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree search -----------------------------
        long searchNanoSeconds = -1;

        if(insertionValue != null){
            startTime = System.nanoTime();



            int i = 0;
            if(dataset != null){
                for(Integer dataToSearch : dataset){
                    i++;
                    if(i % 1000 == 0) startTime = System.nanoTime();

                    tree.search(dataToSearch);

                    if(i % 1000 == 0){
                        endTime = System.nanoTime();
                        durationNanoseconds = (endTime - startTime);
                        out.println(durationNanoseconds);
                    }

                }
            }

            endTime = System.nanoTime();
            searchNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree output -----------------------------
        long outputNanoSeconds = -1;

        if(Boolean.TRUE.equals(output)){
            startTime = System.nanoTime();


            out.println(tree);

            endTime = System.nanoTime();
            outputNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Tree deletion -----------------------------
        long deletionNanoSeconds = -1;

        if(Boolean.TRUE.equals(delete)){

            startTime = System.nanoTime();

            int i = 0;
            if(dataset != null){
                /*for(int nodeToDelete : dataset){
                    i++;
                    if(i % 1000 == 0) startTime = System.nanoTime();

                    tree.delete(nodeToDelete);

                    if(i % 1000 == 0){
                        endTime = System.nanoTime();
                        durationNanoseconds = (endTime - startTime);
                        out.println(durationNanoseconds);
                    }
                }*/
            }
            if(insertionValue != null) tree.delete(insertionValue);

            endTime = System.nanoTime();
            deletionNanoSeconds = endTime - startTime;
        }
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Statistics  -----------------------------

        /*if(dataset != null && datasetValue != null){
            out.println(type + " creation took " + TimeConverter.nanoToSeconds(durationNanoseconds) + (
                    durationNanoseconds != 1 ? " seconds " : " second"
            ));
        }

        if(insertionValue != null){
            out.println(type + " insertion took " + TimeConverter.nanoToSeconds(insertNanoSeconds) + (
                    insertNanoSeconds != 1 ? " seconds " : " second"
            ));
        }

        if(insertionValue != null){
            out.println(type + " search took " + TimeConverter.nanoToSeconds(searchNanoSeconds) + (
                    searchNanoSeconds != 1 ? " seconds " : " second"
            ));
        }

        if(Boolean.TRUE.equals(output)){
            out.println(type + " output took " + TimeConverter.nanoToSeconds(outputNanoSeconds) + (
                    outputNanoSeconds != 1 ? " seconds " : " second"
            ));
        }

        if(Boolean.TRUE.equals(delete)){
            out.println(type + " deletion took " + TimeConverter.nanoToSeconds(deletionNanoSeconds) + (
                    deletionNanoSeconds != 1 ? " seconds " : " second"
            ));
        }

        out.println();*/
        //* --------------------------------------------------------------------------
    }



}
