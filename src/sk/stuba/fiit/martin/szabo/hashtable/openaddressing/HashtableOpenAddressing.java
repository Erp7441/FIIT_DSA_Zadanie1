package sk.stuba.fiit.martin.szabo.hashtable.openaddressing;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

import static java.lang.System.*;

public class HashtableOpenAddressing extends Hashtable{

    public HashtableOpenAddressing(){
        super();
    }

    public HashtableOpenAddressing(int size){
        super(size);
    }

    @Override
    public Object search(Object value){

        if(value == null){ return null; } // Functions to hash other types are not implemented yet

        // Get value from table
        int index = hash(value);
        Object current = this.get(index);

        if(current == null || current == value) return current;

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

            index = linearProbing(index);
            current = this.get(index);
        }
        return null;
    }

    public int searchIndex(Object value){

        if(value == null){ return -1; } // Functions to hash other types are not implemented yet

        // Get value from table
        int index = hash(value);
        Object current = this.get(index);

        if(current == null || current == value) return index;

        // Go through the table until we find a null
        while(current != null){

            // If we found the node we were looking for return it
            if(current.equals(value)){
                return index;
            }

            // Search doesn't stop at deleted values
            else if(current.equals(Hashtable.DELETED_VALUE)){
                index = linearProbing(index);
                current = this.get(index);
                continue;
            }

            index = linearProbing(index);
            current = this.get(index);
        }
        return -1;
    }

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
        this.set(foundIndex, DELETED_VALUE);

        this.decrementElements();

        return true;
    }

    @Override
    public void resolveCollision(int index, Object value){
        Object current = this.get(index);

        // Loop through the table until we reach null
        while(current != null){

            if(index == this.getSize()-1){
                index = 0;
            }

            index = linearProbing(index);
            current = this.get(index);
        }
        this.set(index, value);
    }
}
