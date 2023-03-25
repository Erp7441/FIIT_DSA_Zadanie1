package sk.stuba.fiit.martin.szabo.main;


import sk.stuba.fiit.martin.szabo.hashtable.HashtableHelper;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;
import sk.stuba.fiit.martin.szabo.hashtable.separatechaining.HashtableSeparateChaining;
import sk.stuba.fiit.martin.szabo.tree.TreeHelper;
import sk.stuba.fiit.martin.szabo.tree.avl.AvlTree;
import sk.stuba.fiit.martin.szabo.tree.splay.SplayTree;
import sk.stuba.fiit.martin.szabo.utils.Dataset;

import java.util.ArrayList;

/**
 * This program implements four different data structures: {@link AvlTree AVL Tree}, {@link SplayTree Splay Tree},
 * {@link HashtableOpenAddressing Open Addressing Hashtable}, and {@link HashtableSeparateChaining Separate Chaining Hashtable}.
 * Each data structure is designed to store and retrieve data in a specific way.
 * <p>
 * {@link AvlTree AVL Tree} is a self-balancing {@link sk.stuba.fiit.martin.szabo.tree.bst.BstTree binary search tree}.
 * It maintains a balance factor for each node and adjusts the tree structure whenever an insertion or deletion causes
 * the balance factor to become out of balance. This ensures that the height of the tree is always logarithmic and
 * guarantees efficient {@link AvlTree#search(Integer) search}, {@link AvlTree#insert(Integer) insertion}, and
 * {@link AvlTree#delete(Integer) deletion} operations.
 * <p>
 * {@link SplayTree Splay Tree} is another self-balancing binary search tree that reorganizes itself based on the
 * recently accessed nodes. Whenever a node is accessed, it becomes the root of the tree, and the tree is reorganized
 * to minimize the depth of the recently accessed nodes. This provides efficient {@link SplayTree#search(Integer) search}
 * times for frequently accessed data.
 * <p>
 * {@link HashtableOpenAddressing Open Addressing Hashtable} is a data structure that stores data in an array, using a
 * {@link HashtableOpenAddressing#hash(Object) hash} function to map the data to a specific index in the array. If the
 * index is already occupied, the {@link HashtableOpenAddressing#hash(Object) hash} function is modified to find the next
 * available index until an empty slot is found. This allows for efficient {@link HashtableOpenAddressing#search(Object)
 * search}, {@link HashtableOpenAddressing#insert(Object) insertion}, and {@link HashtableOpenAddressing#delete(Object)
 * deletion} of data.
 * <p>
 * {@link HashtableSeparateChaining Separate Chaining Hashtable} is another type of {@link sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable
 * Hashtable} that uses a {@link java.util.LinkedList LinkedList} to store data. Each index in the array stores a pointer
 * to the head of a {@link java.util.LinkedList LinkedList}, and data is inserted at the beginning of the list. If
 * multiple items are stored at the same index, they are stored in a {@link java.util.LinkedList LinkedList}, ensuring
 * efficient retrieval of data.
 * <p>
 * Overall, this program provides a variety of data structures that can be used to store and retrieve data efficiently.
 *
 * @author <a href="mailto:xszabom5@stuba.sk">Martin Szabo</a>
 * @version 1.0
 */
public class Main{
    public static void main(String[] args){

        // Dataset file
        //ArrayList<Integer> dataset = Dataset.createIntegerDataset("data/data1.txt", null, null, null, false);

        // Generating random dataset
        ArrayList<Integer> dataset = Dataset.createIntegerDataset(null, 0, 1147483647, 1000000, false);
        ArrayList<String> datasetString = Dataset.createStringDataset(null, dataset.size());

        trees(dataset, 605022365, datasetString, false, true);
        hashtables(datasetString, "TEST_INSERT",false, true);
    }

    private static void trees(ArrayList<Integer> dataset, Integer insertionValue, ArrayList<String> datasetString, Boolean output, Boolean delete){
        AvlTree avlTree = new AvlTree();
        SplayTree splayTree = new SplayTree();

        TreeHelper.treeExecute(avlTree, "Avl Tree", insertionValue, output, dataset, datasetString, delete);
        TreeHelper.treeExecute(splayTree, "Splay Tree", insertionValue, output, dataset, datasetString, delete);
    }

    private static void hashtables(ArrayList<String> dataset, String insertionValue, Boolean output, Boolean delete){
        HashtableOpenAddressing openAddressing = new HashtableOpenAddressing();
        HashtableSeparateChaining separateChaining = new HashtableSeparateChaining();

        HashtableHelper.hashtableExecute(openAddressing, "Open Addressing Hashtable", insertionValue, output, delete, dataset);
        HashtableHelper.hashtableExecute(separateChaining, "Separate Chaning Hashtable", insertionValue, output, delete, dataset);
    }


}