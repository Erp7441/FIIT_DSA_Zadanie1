package sk.stuba.fiit.martin.szabo.menu;

import sk.stuba.fiit.martin.szabo.avl.Node;
import sk.stuba.fiit.martin.szabo.avl.Tree;
import sk.stuba.fiit.martin.szabo.utils.Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.*;

public class MainMenu{

    private static MainMenu instance = null;
    private MainMenu(){

        // TODO:: Fix invalid input on done
        // TODO:: Fix spacing
        // TODO:: Add functionality for moving between avl and redblack tree

        //* Variables
        Parser parser = new Parser();
        AtomicReference<Tree> avl = new AtomicReference<>();

        //* Entries
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry("Dataset", () -> {
            ArrayList<Entry> subEntries = new ArrayList<>();
            subEntries.add(new Entry("Show data", () -> out.println("\n" + parser + "\n")));
            subEntries.add(new Entry("Get data", () -> {

                ArrayList<Entry> subSubEntries = new ArrayList<>();
                subSubEntries.add(new Entry("Get data from user", parser::getInputFromUser));
                subSubEntries.add(new Entry("Get data from file", () -> {
                    Scanner sc = new Scanner(System.in);
                    Menu.print("Enter path to file: ");
                    String path = sc.next();
                    out.print("\n");
                    parser.getInputFromFile(path);
                    out.print("\n");
                }));

                // TODO:: Fix this option
                subSubEntries.add(new Entry("Get data from delimited file", () -> {
                    Scanner sc = new Scanner(System.in);

                    Menu.print("Enter path to file: ");
                    String path = sc.next();

                    Menu.print("Enter file delimiter: ");
                    String delimiter = sc.next();

                    parser.getInputFromFile(path, delimiter);
                    sc.close();
                }));

                Menu getDataMenu = new Menu(subSubEntries);
                getDataMenu.start();
            }));

            Menu datasetMenu = new Menu(subEntries);
            datasetMenu.start();
        }));
        entries.add(new Entry("Avl tree", () -> {

            ArrayList<Entry> subEntries = new ArrayList<>();
            subEntries.add(new Entry("Create tree", () -> avl.set(parser.createTree())));
            subEntries.add(new Entry("Print tree", () -> {
                if(avl.get() == null){
                    Menu.print("You need to create the tree first!\n");
                    return;
                }
                out.println("\n" + avl.get());
            }));
            subEntries.add(new Entry("Delete node", () -> {
                if(avl.get() == null){
                    Menu.print("You need to create the tree first!\n");
                    return;
                }
                try{
                    out.print("Enter node value: ");
                    boolean success = avl.get().delete(Menu.scanInteger());
                    if(success){
                        out.println("Node deleted successfully!");
                    }
                    else{
                        out.println("Node couldn't be deleted!");
                    }
                }
                catch(Exception e){
                    System.err.println("Cannot delete node");
                    e.printStackTrace();
                }
            }));
            subEntries.add(new Entry("Insert node", () -> {
                if(avl.get() == null){
                    Menu.print("You need to create the tree first!\n");
                    return;
                }
                try{
                    out.print("Enter node value: ");
                    boolean success = avl.get().insert(Menu.scanInteger());
                    if(success){
                        out.println("Node inserted successfully!");
                    }
                    else{
                        out.println("Couldn't insert node!");
                    }
                }
                catch(Exception e){
                    System.err.println("Cannot insert node");
                    e.printStackTrace();
                }
            }));
            subEntries.add(new Entry("Search node", () -> {
                if(avl.get() == null){
                    Menu.print("You need to create the tree first!\n");
                    return;
                }
                try{
                    out.print("Enter node value: ");
                    Node found = avl.get().search(Menu.scanInteger());
                    if(found != null){
                        out.println("Node " + found.getKey() + " found!");
                        out.println(found);
                    }
                    else{
                        out.println("Node not found!");
                    }
                }
                catch(Exception e){
                    System.err.println("Cannot find node");
                    e.printStackTrace();
                }
            }));

            Menu avlMenu = new Menu(subEntries);
            avlMenu.start();
        }));
        entries.add(new Entry("Redblack tree", () -> {
            err.println("--------------------\nNot yet implemented!\n--------------------");
            out.println("--------------------");
        }));

        //* Menu
        Menu menu = new Menu(entries);
        menu.start();
    }

    public static MainMenu getInstance(){
        if(MainMenu.instance == null) MainMenu.instance = new MainMenu();
        return MainMenu.instance;
    }
}
