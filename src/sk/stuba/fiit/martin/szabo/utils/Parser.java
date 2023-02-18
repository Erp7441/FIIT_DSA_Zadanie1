package sk.stuba.fiit.martin.szabo.utils;

import sk.stuba.fiit.martin.szabo.avl.Node;
import sk.stuba.fiit.martin.szabo.avl.Tree;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

public class Parser{
    private ArrayList<Integer> dataset;

    public Parser(){
        this.dataset = new ArrayList<>();
    }
    public Parser(ArrayList<Integer> dataset){
        if(dataset == null){
            err.println("Can't initialize parser. Invalid dataset entered.");
            System.exit(1);
        }
        this.dataset = dataset;
    }

    public void getInputFromUser(){
        Scanner in = new Scanner(System.in);
        out.println("Enter your dataset [enter \"done\" to end dataset]: ");
        boolean dataFound = true;
        while(dataFound){
            String input = in.next();
            dataFound = this.addInput(input);
        }
    }

    public void getInputFromFile(File file){
        Scanner in;
        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String input;
            try{
                input = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            dataFound = this.addInput(input);
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }

    }

    public void getInputFromFile(String path){
        Scanner in;
        File file;

        try{
            file = new File(path);
        }
        catch(Exception e){
            err.println("Error while opening file: " + path);
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String input;
            try{
                input = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            dataFound = this.addInput(input);
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }

    public void getInputFromFile(Path path){
        Scanner in;
        File file;

        try{
            file = new File(path.toString());
        }
        catch(Exception e){
            err.println("Error while opening file: " + path.toString());
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String input;
            try{
                input = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            dataFound = this.addInput(input);
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }

    public void getInputFromFile(File file, String delimiter){
        Scanner in;
        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter);
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }
    public void getInputFromFile(String path, String delimiter){
        Scanner in;
        File file;

        try{
            file = new File(path);
        }
        catch(Exception e){
            err.println("Error while opening file: " + path);
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter);
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }
    public void getInputFromFile(Path path, String delimiter){
        Scanner in;
        File file;

        try{
            file = new File(path.toString());
        }
        catch(Exception e){
            err.println("Error while opening file: " + path.toString());
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter);
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }

    public void getInputFromFile(File file, Character delimiter){
        Scanner in;
        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter.toString());
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }
    public void getInputFromFile(String path, Character delimiter) {
        Scanner in;
        File file;

        try{
            file = new File(path);
        }
        catch(Exception e){
            err.println("Error while opening file: " + path);
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter.toString());
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }
    public void getInputFromFile(Path path, Character delimiter){
        Scanner in;
        File file;

        try{
            file = new File(path.toString());
        }
        catch(Exception e){
            err.println("Error while opening file: " + path.toString());
            e.printStackTrace();
            return;
        }

        try{
            in = new Scanner(file);
        }
        catch(Exception e){
            err.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        out.println("Scanning file...");
        boolean dataFound = true;
        while(dataFound){
            String line;
            try{
                line = in.next();
            }
            catch(NoSuchElementException e){
                break;
            }
            String[] split = line.split(delimiter.toString());
            for(String input : split){
                dataFound = this.addInput(input);
            }
        }
        out.println("Done!");

        if(dataset != null && !dataset.isEmpty()){
            out.println("\nDataset is: " + dataset);
        }
    }

    private boolean addInput(String input){
        if(input == null || input.equals("") || input.equals("\n")){
            if(dataset.isEmpty()){
                out.println("No data were found");
            }
            return false;
        }

        try{
            dataset.add(Integer.parseInt(input));
            out.println("Found data: " + input);
            return true;
        }
        catch(Exception e){
            out.println("Invalid input");
            return false;
        }
    }

    public Tree createTree(){
        if(dataset == null || dataset.isEmpty()){
            out.println("No data were found in the dataset");
            return null;
        }

        Node root = new Node(dataset.get(0));
        Tree tree = new Tree(root);
        for(int i = 1; i < dataset.size(); i++){
            tree.insert(new Node(dataset.get(i)));
        }
        out.println("Tree successfully created!");
        return tree;
    }

    public ArrayList<Integer> getDataset(){
        return this.dataset;
    }
    public void setDataset(ArrayList<Integer> input){
        this.dataset = input;
    }

    @Override
    public String toString(){
        if(dataset != null && !dataset.isEmpty()){
            return ("Dataset is: " + dataset);
        }
        return "No dataset was found";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Parser)) return false;

        Parser parser = (Parser) o;

        return getDataset().equals(parser.getDataset());
    }

    @Override
    public int hashCode(){
        return getDataset().hashCode();
    }
}
