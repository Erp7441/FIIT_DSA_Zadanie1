package sk.stuba.fiit.martin.szabo.hashtable.openaddressing;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

// TODO:: Add javadoc comments
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
