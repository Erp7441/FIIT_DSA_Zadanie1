package sk.stuba.fiit.martin.szabo.hashtable.separatechaining;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;
import sk.stuba.fiit.martin.szabo.hashtable.openaddressing.HashtableOpenAddressing;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that implements a hash table with separate chaining collision resolution. Each index in the hash table can
 * contain multiple values in a {@link LinkedList}, with each value having its own hash code. When a new value is added
 * to the hash table, the index is determined by hashing the value, and any collisions are resolved by adding the value
 * to the {@link LinkedList} at that index. When a value is searched for or deleted, the index is determined in the same
 * way, and the {@link LinkedList} at that index is traversed until the value is found or the end of the list is reached.
 * <p>
 * This class extends the {@link Hashtable} abstract class and overrides the {@link #search} and {@link #resolveCollision}
 * methods to implement separate chaining collision resolution.
 * <p>
 * The {@link #resolveCollision(int, Object)} method is used to add a value to the hash table when a collision occurs.
 * It first searches for the value in the hash table using the search method, and then adds the value to the {@link LinkedList}
 * at that index if it is found. If the value is not found, a new {@link LinkedList} is created and added to the index.
 *
 * @see Hashtable
 * @see HashtableOpenAddressing
 * @see LinkedList
 */
public class HashtableSeparateChaining extends Hashtable{

    public HashtableSeparateChaining(){
        super();
    }

    public HashtableSeparateChaining(int size){
        super(size);
    }

    /**
     * When searching for a value, the hash code of the value is used to find the index in the hash table where it
     * may be located. If the value is not found at that index, the {@link LinkedList} at that index is traversed until
     * the value is found or the end of the list is reached. If the end of the list is reached, the search terminates and
     * returns null. If the value is found in the {@link LinkedList}, it is returned.
     *
     * @param value the value to search for in the Hashtable
     * @return the found value or null if it does not exist in the Hashtable
     */
    @Override
    public Object search(Object value){

        if(value == null){ return null; }

        int index = hash(value);

        // Get value from table
        Object current = this.get(index);

        if(current == null || current == value) return current;

        // If it is array look for the value in the array
        if(current instanceof LinkedList){
            Object linkedCurrent = searchLinkedList((LinkedList<Object>) current, value);
            if(linkedCurrent == value) return linkedCurrent;
        }

        // Get index of current value
        boolean weGoAgain = false;

        // Go through the table until we find a null
        while(current != null){

            // Search doesn't stop at deleted values
            if(current.equals(Hashtable.DELETED_VALUE)){
                index = linearProbing(index);
                current = this.get(index);
                continue;
            }

            // If we found the node we were looking for return it
            if(current.equals(value)){
                return current;
            }

            // If we found array list. Look through it for a value
            if(current instanceof ArrayList){
                Object linkedCurrent = searchLinkedList((LinkedList<Object>) current, value);
                if(linkedCurrent == value) return linkedCurrent;
            }

            // If we are at the end of the table we jump back to the beginning of table
            if(index == this.getSize() - 1 && !weGoAgain){
                index = 0;
                weGoAgain = true;
            }

            // Else if we already jumped back at the end of the table and managed to reach the end of the table again
            // We have not found the value we were looking for
            else if(index == this.getSize() - 1 && weGoAgain){
                return null;
            }

            index = linearProbing(index);
            current = this.get(index);
        }
        return null;
    }

    /**
     * Searches the given {@link LinkedList} for the given value. If the value is found, it is returned. If the value
     * is not found, null is returned. If the {@link LinkedList} contains a deleted value (marked by the special
     * {@link #DELETED_VALUE} object), it is skipped and the search continues. This method is used by the
     * {@link HashtableSeparateChaining} class to search {@link LinkedList}s for values.
     *
     * @param list the linked list to search
     * @param value the value to search for in the linked list
     * @return the value if found in the linked list, null otherwise
     */
    public static Object searchLinkedList(LinkedList<Object> list, Object value){
        for(Object item : list){
            if(item.equals(Hashtable.DELETED_VALUE)) continue;
            if(item.equals(value)) return item;
        }
        return null;
    }

    /**
     * Resolves a collision that occurs during the insertion of a value into the hash table. If the index of the
     * hash table is already occupied with a value, the {@link #resolveCollision} method is called to
     * {@link #insert(Object)} the value using separate chaining. The method first searches for the value in the hash
     * table using the {@link #search(Object)} method. If the {@link #search(Object)} returns a {@link LinkedList},
     * the value is added to the end of that list. If the search returns a single value, a new {@link LinkedList} is
     * created containing both the existing value and the new value, and the {@link LinkedList} is added to the hash
     * table. If the {@link #search(Object)} returns null, a new {@link LinkedList} is created containing only the
     * new value and the {@link LinkedList} is added to the hash table.
     *
     * @param index the index in the hash table where the value is to be inserted
     * @param value the value to insert into the hash table
     * @see Hashtable#search
     */
    public void resolveCollision(int index, Object value){

        Object tableValue = this.search(value);

        if(tableValue instanceof LinkedList<?>){
            try{
                ((LinkedList <Object>) tableValue).add(value);
            }
            catch(ClassCastException e){
                e.printStackTrace();
                throw new ClassCastException();
            }
        }
        else{
            LinkedList<Object> list = new LinkedList<>();
            list.add(tableValue);
            list.add(value);
            this.set(index, list);
        }
    }
}
