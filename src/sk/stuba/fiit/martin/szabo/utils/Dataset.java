package sk.stuba.fiit.martin.szabo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dataset{

    private Dataset(){}

    public static ArrayList<Integer> createIntegerDataset(String pathToFile, Integer min, Integer max, Integer count, Boolean overwrite){
        if(pathToFile != null){
            File file = new File(pathToFile);
            if(!file.exists() || Boolean.TRUE.equals(overwrite)){
                Generators.generateRandomDatasetToFile(pathToFile, min, max, count);
            }
            return fileToArray(pathToFile);
        }
        else{
            //? Generating random dataset
            return Generators.generateRandomDataset(min, max, count);
        }
    }

    public static ArrayList<String> createStringDataset(Integer length, Integer count){
        // TODO:: Implement generating to file
        return Generators.generateRandomDataset(true, length, count);
    }

    public static ArrayList<Integer> fileToArray(String pathToFile){
        File file = new File(pathToFile);
        Scanner sc;

        try{
            sc = new Scanner(file);
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        ArrayList<Integer> dataset = new ArrayList<>();
        while(sc.hasNext()){
            dataset.add(Integer.valueOf(sc.next()));
        }

        return dataset;
    }
}
