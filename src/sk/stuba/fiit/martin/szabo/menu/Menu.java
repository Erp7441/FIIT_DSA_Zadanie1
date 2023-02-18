package sk.stuba.fiit.martin.szabo.menu;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Menu{
    private ArrayList<Entry> entries = new ArrayList<>();
    private Integer selection = null;

    public Menu(){
        this.entries = new ArrayList<>();
    }
    public Menu(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    private boolean getInput(){
        Scanner sc = new Scanner(in);
        out.println("Enter number:");
        try{
            this.setSelection(sc.nextInt());

            if(this.getSelection() == 0){
                return false;
            }

            this.run();
        }
        catch(Exception e){
            err.println("Incorrect input!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void start(){
        boolean input;
        do{
            out.println(this);
            input = this.getInput();
        } while(input);
    }

    private void run(){
        entries.get(this.selection - 1).run();
    }

    public static Integer scanInteger(){
        Scanner in = new Scanner(System.in);
        Integer input = null;
        boolean result = false;

        do{
            try{
                input = in.nextInt();
                result = true;
            }
            catch(Exception e){
                err.println("Incorrect input!");
                e.printStackTrace();
            }
        } while(!result);

        return input;
    }

    @Override
    public String toString(){
        Integer count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------\n");
        for(Entry entry : this.entries){
            sb.append(count).append(". ").append(entry).append("\n");
            count++;
        }
        sb.append("0. Exit\n");
        sb.append("--------------------");
        return sb.toString();
    }

    public static void print(String s) {
        out.print("--------------------\n");
        out.print(s);
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void removeEntry(Entry entry) {
        this.entries.remove(entry);
    }

    public ArrayList<Entry> getEntries(){
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries){
        this.entries = entries;
    }

    public Integer getSelection(){
        return selection;
    }

    public void setSelection(Integer selection){
        this.selection = selection;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        return getEntries().equals(menu.getEntries());
    }

    @Override
    public int hashCode(){
        return getEntries().hashCode();
    }
}
