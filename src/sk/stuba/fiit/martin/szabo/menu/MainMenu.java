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
            Menu datasetMenu = new Menu();
            Entry showData = new Entry("Show data", () -> out.println("\n" + parser + "\n"));
            Entry getData = new Entry("Get data", () -> {
                Menu getDataMenu = new Menu();

                Entry fromUser = new Entry("Get data from user", parser::getInputFromUser);
                Entry fromFile = new Entry("Get data from file", () -> {
                    Scanner sc = new Scanner(System.in);
                    Menu.print("Enter path to file: ");
                    String path = sc.next();
                    out.print("\n");
                    parser.getInputFromFile(path);
                    out.print("\n");
                });

                // TODO:: Fix this option
                Entry fromFileWithDelimiter = new Entry("Get data from delimited file", () -> {
                    Scanner sc = new Scanner(System.in);

                    Menu.print("Enter path to file: ");
                    String path = sc.next();

                    Menu.print("Enter file delimiter: ");
                    String delimiter = sc.next();

                    parser.getInputFromFile(path, delimiter);
                    sc.close();
                });

                getDataMenu.addEntry(fromUser);
                getDataMenu.addEntry(fromFile);
                getDataMenu.addEntry(fromFileWithDelimiter);

                getDataMenu.start();
            });

            datasetMenu.addEntry(showData);
            datasetMenu.addEntry(getData);

            datasetMenu.start();
        }));
        entries.add(new Entry("Avl tree", () -> {
            Menu avlMenu = new Menu();

            Entry createTree = new Entry("Create tree", () -> avl.set(parser.createTree()));
            Entry printTree = new Entry("Print tree", () -> {
                if(avl.get() == null){
                    Menu.print("You need to create the tree first!\n");
                    return;
                }
                out.println("\n" + avl.get());
            });
            Entry deleteNode = new Entry("Delete node", () -> {
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
            });
            Entry insertNode = new Entry("Insert node", () -> {
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
            });
            Entry searchNode = new Entry("Search node", () -> {
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
            });

            avlMenu.addEntry(createTree);
            avlMenu.addEntry(printTree);
            avlMenu.addEntry(deleteNode);
            avlMenu.addEntry(insertNode);
            avlMenu.addEntry(searchNode);

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
