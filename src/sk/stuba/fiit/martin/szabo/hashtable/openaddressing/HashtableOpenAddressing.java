package sk.stuba.fiit.martin.szabo.hashtable.openaddressing;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

/**
 * {@link HashtableOpenAddressing} is a subclass of {@link Hashtable} that uses open addressing to resolve collisions.
 * This class provides the ability to {@link #search}, {@link #insert}, and {@link #delete(Object)} key-value pairs
 * from the hashtable. {@link HashtableOpenAddressing} overrides the {@link #search}, {@link #delete(Object)}, and
 * {@link #resolveCollision} methods of the {@link Hashtable} class to implement open addressing.
 * <p>
 * {@link HashtableOpenAddressing} provides the {@link #resolveCollision} method to resolve collisions using open addressing.
 * When a collision occurs, {@link #resolveCollision} probes the hashtable using {@link #linearProbing(int)} an empty
 * slot is found.
 *
 * @see #resolveCollision
 * @see #linearProbing(int)
 * @see Hashtable
 * @see #search(Object)
 * @see #insert(Object)
 * @see #delete(Object)
 */
public class HashtableOpenAddressing extends Hashtable{

    /**
     * Constructs a new empty {@link HashtableOpenAddressing} object with the default initial capacity and default
     * load factor (0.75).
     * @see Hashtable#Hashtable()
     */
    public HashtableOpenAddressing(){
        super();
    }

    /**
     * Constructs a new empty {@link HashtableOpenAddressing} object with the specific capacity and default
     * load factor (0.75).
     * @see Hashtable#Hashtable()
     */
    public HashtableOpenAddressing(int size){
        super(size);
    }

    /**
     * Searches for a specific value in the hash table using linear probing. If the value is found, it is returned.
     * If the value is not found, null is returned.
     *
     * @param value the value to search for in the hash table
     * @return the value if found, or null if not found
     *
     * @see HashtableOpenAddressing#getTable()
     * @see HashtableOpenAddressing#getSize()
     * @see HashtableOpenAddressing#hash(Object)
     * @see HashtableOpenAddressing#linearProbing(int)
     * @see HashtableOpenAddressing#get(int)
     */
    @Override
    public Object search(Object value){

        if(value == null){ return null; } // Functions to hash other types are not implemented yet

        // Get value from table
        int index = hash(value);
        Object current = this.get(index);


        // Go through the table until we find a null
        while(current != null){

            // If we found the node we were looking for return it
            if(current.equals(value)){
                return current;
            }

            index = linearProbing(index);
            current = this.get(index);
        }

        return null;
    }

    /**
     * Searches for the specified value in the hash table using linear probing and returns its index if found.
     * Returns -1 if the value is not found or if the value is null.
     *
     * @param value the value to search for in the hash table
     * @return the index of the value if found, -1 otherwise
     * @see #hash(Object)
     * @see #linearProbing(int)
     * @see #get(int)
     */
    public int searchIndex(Object value){

        if(value == null){ return -1; }

        // Get value from table
        int index = hash(value);
        Object current = this.get(index);

        // Go through the table until we find a null
        while(current != null){

            // If we found the node we were looking for return it
            if(current.equals(value)){
                return index;
            }

            index = linearProbing(index);
            current = this.get(index);
        }
        return -1;
    }

    /**
     * Deletes the given value from the hash table.
     *
     * @param value the value to be deleted from the hash table
     * @return true if the value was deleted, false if the value was not found in the hash table
     *
     * @see #searchIndex(Object)
     * @see #deleteValue(int)
     */
    @Override
    public boolean delete(Object value){

        if(value == null) return false;

        // Find the value
        int foundIndex = searchIndex(value);

        // If we have not found value there is nothing to delete
        if(foundIndex == -1){
            return false;
        }

        // Replace value at index with DELETED_VALUE, so it won't get searched again
        this.deleteValue(foundIndex);

        return true;
    }

    /**
     * Resolves a collision at a given index by linearly probing the table for the next available slot
     * and setting the provided value at that index.
     *
     * @param index the index at which the collision occurred
     * @param value the value to be stored in the table
     *
     * @see #linearProbing(int)
     * @see #set(int, Object)
     */
    @Override
    public void resolveCollision(int index, Object value){
        Object current = this.get(index);

        // Loop through the table until we reach null
        while(current != null){
            index = linearProbing(index);
            current = this.get(index);
        }
        this.set(index, value);
    }
}
