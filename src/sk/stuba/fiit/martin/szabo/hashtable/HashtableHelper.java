package sk.stuba.fiit.martin.szabo.hashtable;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;
import sk.stuba.fiit.martin.szabo.utils.TimeConverter;

import java.util.ArrayList;

import static java.lang.System.out;

public class HashtableHelper{

    private HashtableHelper(){}

    public static void hashtableExecute(Hashtable hashtable, String type, Integer insertionValue, Boolean output, Boolean delete, ArrayList<String> dataset){

        if(hashtable == null) return;

        //* ----------------------------- Hashtable creation -------------------------
        long startTime;
        long endTime;
        long durationNanoseconds = -1;

        if(dataset != null){
            startTime = System.nanoTime();

            for(String s : dataset){
                hashtable.insert(s);
            }

            endTime = System.nanoTime();
            durationNanoseconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable insert ---------------------------
        long insertNanoSeconds = -1;

        if(insertionValue != null){
            startTime = System.nanoTime();

            hashtable.insert(insertionValue);

            endTime = System.nanoTime();
            insertNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable search ---------------------------
        long searchNanoSeconds = -1;

        if(insertionValue != null){
            startTime = System.nanoTime();


            hashtable.search(insertionValue);

            endTime = System.nanoTime();
            searchNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable output ---------------------------
        long outputNanoSeconds = -1;

        if(Boolean.TRUE.equals(output)){
            startTime = System.nanoTime();

            out.println(hashtable);

            endTime = System.nanoTime();
            outputNanoSeconds = (endTime - startTime);
        }
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable deletion -------------------------
        long deletionNanoSeconds = -1;

        if(Boolean.TRUE.equals(delete)){
            startTime = System.nanoTime();

            if(dataset != null){
                for(String dataToDelete : dataset){
                    hashtable.delete(dataToDelete);
                }
            }
            if(insertionValue != null) hashtable.delete(insertionValue);

            endTime = System.nanoTime();
            deletionNanoSeconds = endTime - startTime;
        }
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Statistics  -------------------------------

        if(dataset != null){
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

        out.println();
        //* ---------------------------------------------------------------------------
    }
}
