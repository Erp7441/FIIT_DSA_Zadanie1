package sk.stuba.fiit.martin.szabo.hashtable.hashtable;

import java.util.ArrayList;

import java.util.List;

/**
 * The Hashtable class provides an abstract data type for a hash table that maps keys to values. It implements the
 * hash table using an {@link ArrayList} of {@link Object}s. The class is abstract, so it needs to be extended by another
 * class that implements the {@link #resolveCollision} and {@link #search(Object)} methods, which are abstract methods of
 * this class. The {@link Hashtable} class provides methods for initializing the hashtable, computing the hash value
 * for a given object, inserting an object into the hashtable, searching for an object in the hashtable, deleting an
 * object from the hashtable, and rehashing the hashtable if the load factor becomes too low or too high.
 */
public abstract class Hashtable{

    public static final String DELETED_VALUE = "DELETED_VALUE";

    private ArrayList<Object> table = new ArrayList<>();
    private Long elements = 0L;

    /**
     * This is a protected constructor for the Hashtable class that initializes the hashtable with a null value.
     * This constructor is used when the initial size of the hashtable is not known, and the default size of 1 is
     * used. The hashtable is implemented as an ArrayList of objects, and the first element of the ArrayList is
     * always set to null to make sure that the index of the elements starts from 1 instead of 0, which is used
     * for other purposes in the implementation.
     */
    protected Hashtable(){ table.add(null); }

    /**
     * Constructs an empty Hashtable with the specified initial size.
     *
     * @param size the initial size of the Hashtable
     */
    protected Hashtable(int size){
        initialize(size);
    }

    /**
     * This is an abstract method that needs to be implemented by any class that extends the Hashtable class.
     * It is used to resolve collisions that may occur when inserting a new value into the hashtable. The method
     * takes in two arguments: the index where the collision occurred, and the value that caused the collision.
     * The implementation of the method should resolve the collision according to the specific collision resolution
     * technique used by the hashtable. For example, if linear probing is used, the resolveCollision method should
     * implement linear probing. If chaining is used, the resolveCollision method should implement chaining. The
     * method should not return any value, but should modify the hashtable as needed.
     *
     * @param index position where the collision occurred
     * @param value the value that caused the collision
     */
    public abstract void resolveCollision(int index, Object value);

    /**
     * This method is an abstract method that must be implemented by concrete classes extending the Hashtable
     * abstract class. The method performs a search for a specific value within the Hashtable.
     *
     * @param value the value to search for in the Hashtable
     * @return the value found in the Hashtable or null if the value is not found
     */
    public abstract Object search(Object value);

    /**
     * Initializes the hashtable with the specified size. It creates a new ArrayList object of the specified size
     * and adds null to each element. This method is used by the constructors to initialize the hashtable.
     *
     * @param size the initial size of the hashtable
     */
    public void initialize(int size){
        table = new ArrayList<>();
        for(int i = 0; i < size; i++){
            table.add(null);
        }
    }

    /**
     * Deletes the specified value from the hashtable. Returns false if the value is null or if the value is not
     * found in the hashtable. Otherwise, deletes the value and returns true. First, the method checks if the value
     * is null. If so, it returns false. Then, it searches for the value using the {@link #search(Object)} method,
     * which returns null if the value is not found in the hashtable. If the value is found, the method deletes it
     * by replacing the value at its corresponding index with the {@link #DELETED_VALUE}  constant. This ensures that
     * the value will not be found again during future searches. If the number of elements in the hashtable becomes less
     * than 25% or greater than 75% of the hashtable's size after deleting the value, the method triggers a rehash to
     * adjust the size of the hashtable accordingly.
     *
     * @param value the value to be deleted from the hashtable
     * @return true if the value is successfully deleted, false otherwisen
     * @see #search(Object)
     * @see #DELETED_VALUE
     * @see #rehash(boolean)
     */
    public boolean delete(Object value){

        if(value == null) return false;

        // Find the value
        Object found = search(value);

        // If we have not found value there is nothing to delete
        if(found == null){
            return false;
        }

        // Replace value at index with DELETED_VALUE, so it won't get searched again
        deleteValue(hash(found));
        if(rehashNeeded()) rehash(false);

        return true;
    }

    /**
     * Returns the hash value for the given object. This is computed as the absolute value of the object's hash
     * code modulo the size of the hash table.
     *
     * @param value the object whose hash value is to be computed.
     * @return the hash value of the specified object.
     * @see Object#hashCode()
     * @see Math#abs(int)
     * @see Hashtable#getSize()
     */
    public int hash(Object value){
        return Math.abs(value.hashCode()) % this.getSize();
    }

    /**
     * Inserts the given value into the hash table. If the value is null, nothing happens. If the hash table needs
     * to be rehashed, rehashes the table before inserting. If the value already exists in the hash table, resolves
     * the collision with the existing value by calling the {@link #resolveCollision(int, Object)} method. Otherwise,
     * inserts the value into the hash table at the index computed by the {@link #hash(Object)} method. Finally,
     * increments the element count of the hash table by calling the {@link #incrementElements()} method.
     *
     * @param value the object to be inserted into the hash table.
     * @see #rehash(boolean)
     * @see #search(Object)
     * @see #resolveCollision(int, Object)
     * @see #hash(Object)
     * @see #set(int, Object)
     * @see #incrementElements()
     */
    public void insert(Object value){

        if(value == null) return;

        if(rehashNeeded()) rehash(true);

        Object found = search(value);

        if(found != null && !found.equals(DELETED_VALUE)){
            resolveCollision(hash(value), value);
        }
        else{
            this.set(hash(value), value);
        }

        this.incrementElements();

    }

    /**
     * Returns a boolean indicating whether the hash table needs to be rehashed. The hash table needs to be
     * rehashed if the number of elements in the table is greater than or equal to 75% of the size of the table
     * or less than or equal to 25% of the size of the table. This ensures that the table is neither too full
     * nor too empty, which optimizes performance and minimizes the chance of collisions.
     *
     * @return true if the hash table needs to be rehashed, false otherwise.
     */
    public boolean rehashNeeded(){
        return this.getElements() >= this.getSize() * 0.75 || this.getElements() <= this.getSize() * 0.25;
    }

    /**
     * Rehashes the hash table to increase or decrease its size based on the boolean flag increase.
     * If increase is true, the size of the table is doubled, and if it is false, the size is halved.
     * The elements of the old table are reinserted into the new table after it is initialized.
     *
     * @param increase a boolean flag indicating whether to increase or decrease the size of the hash table.
     * @see #initialize(int)
     * @see #insert(Object)
     */
    public void rehash(boolean increase){

        if(this.getTable() == null || this.getSize() == 0) return;

        ArrayList<Object> values = new ArrayList<>(this.getTable());

        if(increase) initialize(this.getSize() * 2);
        else initialize(this.getSize() / 2);

        for(Object value : values){
            this.insert(value);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.getSize(); i++){
            Object value = this.get(i);
            if(value != null){
                sb.append(i).append(": ").append(value).append("\n");
            }
        }
        return sb.toString();
    }

    //* Table utilities
    public List<Object> getTable(){
        return table;
    }

    public void setTable(List<Object> table){
        this.table = (ArrayList<Object>) table;
    }

    public int getSize(){
        return this.getTable().size();
    }

    protected void set(int index, Object value){
        this.getTable().set(index, value);
    }

    protected Object get(int index){
        return this.getTable().get(index);
    }

    public Long getElements(){
        return elements;
    }

    public void setElements(Long elements){
        this.elements = elements;
    }

    public void incrementElements(){
        this.elements++;
    }

    public void decrementElements(){
        this.elements--;
    }

    /**
     * Computes the next index using linear probing strategy. Linear probing is a collision resolution method
     * which increments the index by 1 until an empty slot is found or the entire table is searched. This method
     * computes the next index using the formula (index + 1) % size, where size is the size of the table.
     *
     * @param index the current index
     * @return the next index computed using linear probing strategy
     */
    protected int linearProbing(int index){
        return (index + 1) % this.getSize();
    }

    /**
     * Implements quadratic probing to find the next available index in the hash table. This method calculates the
     * next available index in the hash table using the quadratic probing technique. It takes the original index of
     * the hash code and adds the square of the current quadrant number to it. The result is then taken modulo the
     * size of the hash table to wrap around to the beginning if necessary. Quadratic probing is a collision resolution
     * technique that attempts to avoid clustering in the hash table by searching for the next available index using a
     * quadratic function instead of linearly probing through the array. This helps to distribute values more evenly in
     * the hash table and reduces the number of collisions.
     *
     * @param index the original hash code index
     * @param quadrant the number of the quadrant to probe
     * @return the next available index to store the value
     */
    protected int quadraticProbing(int index, int quadrant){
        return (int) ((index + Math.pow(quadrant, 2)) % this.getSize());
    }

    /**
     * Sets the value at the given index to the {@link #DELETED_VALUE} constant and decrements the number of elements.
     *
     * @param index the index of the value to be deleted
     * @see #set(int, Object)
     * @see #decrementElements()
     */
    protected void deleteValue(int index){
        this.set(index, DELETED_VALUE);
        this.decrementElements();
    }

}
