package sk.stuba.fiit.martin.szabo.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.err;

public class Generators{

    private Generators(){}

    public static void generateRandomDatasetToFile(String path, Integer min, Integer max, Integer count){
        File file = new File(path);

        try{
            if(!file.exists() && !file.createNewFile()){
                err.println("Error creating file " + path);
            }
        }
        catch(IOException e){
            err.println("Error creating file " + path);
            e.printStackTrace();
        }

        try(FileWriter write = new FileWriter(path)){
            for(int i=0; i<count; i++){
                int number = (int) Math.floor((Math.random() * (max - min + 1)) + min);
                write.append(String.valueOf(number));
                if(i != count-1){
                    write.append("\n");
                }
            }
        }
        catch(IOException e){
            err.println("Error generating dataset");
            e.printStackTrace();
        }

    }

    public static ArrayList<Integer> generateRandomDataset(Integer min, Integer max, Integer count){
        ArrayList<Integer> randomDataset = new ArrayList<>();
        for(int i=0; i<count; i++){
            Integer number = generateRandomInt(min, max);
            randomDataset.add(number);
        }
        return randomDataset;
    }

    public static ArrayList<String> generateRandomDataset(Boolean alphabetOnly, Integer lenght, Integer count){
        ArrayList<String> randomDataset = new ArrayList<>();
        for(int i=0; i<count; i++){
            String value;
            value = generateRandomString(Objects.requireNonNullElseGet(lenght, () -> generateRandomInt(0, 100)), alphabetOnly);
            randomDataset.add(value);
        }
        return randomDataset;
    }

    public static int generateRandomInt(int min, int max){
        return (int) Math.floor((Math.random() * (max - min + 1)) + min);
    }

    public static char generateRandomChar(boolean alphabetOnly){
        if(alphabetOnly){
            char result;
            if(Math.random() < 0.5){
                result = (char) generateRandomInt(97, 122);
            }
            else{
                result = (char) generateRandomInt(65, 90);
            }
            return result;
        }
        return (char) generateRandomInt(0, 255);
    }

    public static String generateRandomString(Integer length, Boolean alphabetOnly){
        StringBuilder sb = new StringBuilder();
        while(sb.length() != length){
            sb.append(generateRandomChar(alphabetOnly));
        }
        return sb.toString();
    }

}
