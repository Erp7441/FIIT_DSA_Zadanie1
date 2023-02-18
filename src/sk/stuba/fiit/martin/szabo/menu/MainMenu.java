package sk.stuba.fiit.martin.szabo.menu;

import sk.stuba.fiit.martin.szabo.avl.Tree;
import sk.stuba.fiit.martin.szabo.utils.Parser;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;

public class MainMenu{

    private static MainMenu instance = null;
    private MainMenu(){

        // TODO:: Fix invalid input on done
        // TODO:: Fix spacing
        // TODO:: Add done to creating tree

        Parser parser = new Parser();
        Menu menu = new Menu();
        AtomicReference<Tree> avl = new AtomicReference<>();

        //* Enteries
        Entry getData = new Entry("Get data", () -> {
            Menu getDataMenu = new Menu();
            Entry fromUser = new Entry("Get data from user", parser::getInputFromUser);
            Entry fromFile = new Entry("Get data from file", () -> {
                Scanner sc = new Scanner(System.in);
                Menu.print("Enter path to file:\n");
                String path = sc.next();
                out.print("\n");
                parser.getInputFromFile(path);
                out.print("\n");
            });
            Entry fromFileWithDelimiter = new Entry("Get data from delimited file", () -> {
                Scanner sc = new Scanner(System.in);

                out.println("Enter path to file: ");
                String path = sc.next();

                out.println("Enter file delimiter: ");
                String delimiter = sc.next();

                parser.getInputFromFile(path, delimiter);
                sc.close();
            });

            getDataMenu.addEntry(fromUser);
            getDataMenu.addEntry(fromFile);
            getDataMenu.addEntry(fromFileWithDelimiter);

            getDataMenu.start();
        });
        Entry showData = new Entry("Show data", () -> {
            out.println("\n" + parser + "\n");
        });
        Entry avlTree = new Entry("Avl tree", () -> {
            Menu avlMenu = new Menu();

            Entry createTree = new Entry("Create tree", () -> {
                avl.set(parser.createTree());
            });
            Entry printTree = new Entry("Print tree", () -> {
                out.println(avl.get());
            });
            Entry deleteNode = new Entry("Delete node", () -> {
                try{
                    avl.get().delete(Menu.scanInteger());
                }
                catch(Exception e){
                    System.err.println("Cannot delete node");
                    e.printStackTrace();
                }
            });
            Entry insertNode = new Entry("Insert node", () -> {
                try{
                    avl.get().insert(Menu.scanInteger());
                }
                catch(Exception e){
                    System.err.println("Cannot insert node");
                    e.printStackTrace();
                }
            });
            Entry searchNode = new Entry("Search node", () -> {
                try{
                    avl.get().search(Menu.scanInteger());
                }
                catch(Exception e){
                    System.err.println("Cannot delete node");
                    e.printStackTrace();
                }
            });

            avlMenu.addEntry(createTree);
            avlMenu.addEntry(printTree);
            avlMenu.addEntry(deleteNode);
            avlMenu.addEntry(insertNode);
            avlMenu.addEntry(searchNode);

            avlMenu.start();
        });

        menu.addEntry(getData);
        menu.addEntry(showData);
        menu.addEntry(avlTree);

        //* Menu
        menu.start();
    }

    public static MainMenu getInstance(){
        if(MainMenu.instance == null) MainMenu.instance = new MainMenu();
        return MainMenu.instance;
    }
}
