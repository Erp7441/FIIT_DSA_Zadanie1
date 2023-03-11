package sk.stuba.fiit.martin.szabo.hashtable;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;
import sk.stuba.fiit.martin.szabo.utils.TimeConverter;

import java.util.ArrayList;

import static java.lang.System.out;

public class HashtableHelper{

    private HashtableHelper(){}

    public static void hashtableExecute(Hashtable hashtable, String type, Integer insertionValue, Boolean output, ArrayList<String> dataset){

        //* ----------------------------- Hashtable creation -------------------------
        long startTime = System.nanoTime();

        for(String s : dataset){
            hashtable.insert(s);
        }

        long endTime = System.nanoTime();
        long durationNanoseconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable insert ---------------------------
        startTime = System.nanoTime();

        hashtable.insert(insertionValue);

        endTime = System.nanoTime();
        long insertNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable search ---------------------------
        startTime = System.nanoTime();

        hashtable.search(insertionValue);

        endTime = System.nanoTime();
        long searchNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable output ---------------------------
        startTime = System.nanoTime();

        if(Boolean.TRUE.equals(output)){
            out.println(hashtable);
        }

        endTime = System.nanoTime();
        long outputNanoSeconds = (endTime - startTime);
        //* --------------------------------------------------------------------------

        //* ----------------------------- Hashtable deletion -------------------------
        startTime = System.nanoTime();

        for(String dataToDelete : dataset){
            hashtable.delete(dataToDelete);
        }
        hashtable.delete(insertionValue);

        endTime = System.nanoTime();
        long deletionNanoSeconds = endTime - startTime;
        //* --------------------------------------------------------------------------

        //*  ----------------------------- Statistics  -------------------------------
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
        //* ---------------------------------------------------------------------------
    }
}
