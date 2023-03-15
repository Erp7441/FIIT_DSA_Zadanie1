package sk.stuba.fiit.martin.szabo.main;


// TODO:: refactor placements in classes
// TODO:: !!! Remove redundant calculations !!!


import sk.stuba.fiit.martin.szabo.hashtable.HashtableHelper;
import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;
import sk.stuba.fiit.martin.szabo.hashtable.separatechaining.HashtableSeparateChaining;
import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;
import sk.stuba.fiit.martin.szabo.utils.Dataset;
import sk.stuba.fiit.martin.szabo.utils.TimeConverter;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.System.*;


public class Main{
    public static void main(String[] args){
        //ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        ArrayList<String> datasetString = Dataset.createStringDataset(null, 1000000);

        //trees(dataset, 605022365, datasetString, false);

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
        miskoAverage(openAddressing);

        out.println("DEBUG: separateChaining:");
        HashtableHelper.hashtableExecute(separateChaining, "Separate Chaning Hashtable", insertionValue, output, delete, dataset);

        out.println();
    }

    private static void miskoAverage(Hashtable hashtable){
        Long max = 0L;

        for(Long value : hashtable.time){
            if(value > max){
                max = value;
            }
        }

        if(hashtable.time.size() != 0){
            Long average = max / hashtable.time.size() ;

            out.println("DEBUG: Misko Average - " + TimeConverter.nanoToSeconds(average) + "\n");
        }


    }



    // TODO:: Remove these helper functions
    private static void checkOpenAddressingTable(HashtableOpenAddressing openAddressing){
        out.println("DEBUG: Pre-Open Addressing Hashtable elements " + openAddressing.getElements() + "\n");
        for(Object value : openAddressing.getTable()){
            if(value != null && !value.equals(Hashtable.DELETED_VALUE)){
                openAddressing.delete(value);
                out.println("DEBUG: Deleted value " + value);
            }
        }
        out.println("\n\nDEBUG: Post-Open Addressing Hashtable elements " + openAddressing.getElements() + "\n");
    }

    private static void checkSeparateTable(HashtableSeparateChaining separateChaining){
        out.println("DEBUG: Pre-Separate Chaning Hashtable elements " + separateChaining.getElements() + "\n");
        for(Object value : separateChaining.getTable()){
            if(value != null && !value.equals(Hashtable.DELETED_VALUE)){
                if(value instanceof LinkedList<?>){
                    for(Object linkedValue: (LinkedList<?>) value){
                        separateChaining.delete(linkedValue);
                        out.println("DEBUG: Deleted value " + linkedValue);
                    }
                }
                else{
                    separateChaining.delete(value);
                    out.println("DEBUG: Deleted value " + value);
                }
            }
        }
        out.println("\n\nDEBUG: Post-Separate Chaning Hashtable elements " + separateChaining.getElements() + "\n");
    }


}