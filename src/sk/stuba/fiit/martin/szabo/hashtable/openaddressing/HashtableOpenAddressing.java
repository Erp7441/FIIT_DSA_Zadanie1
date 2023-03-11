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
        if(!(value instanceof String)){ return null; } // Functions to hash other types are not implemented yet

        // Get value from table
        Object tableValue = this.getTable().get(hash(value));

        // If it does not contain the value we are looking for
        if(value != tableValue){

            // Get index of current value
            int index = this.getTable().indexOf(tableValue);
            boolean weGoAgain = false;

            // Go through the table until we find a null
            while(this.getTable().get(index) != null){

                // Search doesn't stop at deleted values
                if(this.getTable().get(index).equals(Hashtable.DELETED_VALUE)){
                    index++;
                    continue;
                }

                // If we found the node we were looking for return it
                if(this.getTable().get(index).equals(tableValue)){
                    return this.getTable().get(index);
                }

                // If we are at the end of the table we jump back to the beginning of table
                if(index == this.getSize() - 1 && !weGoAgain){
                    index = 0;
                    weGoAgain = true;
                }

                // Else if we already jumped back at the end of the table and managed to reach the end of the table again
                // We have not found the value we were looking for
                else if(weGoAgain && index == this.getSize() - 1){
                    return null;
                }

                index++;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Object value){

        // Find the value
        Object found = search(value);

        // If we have not found value there is nothing to delete
        if(found == null) return false;

        // Calculate index
        Integer index = hash(found);

        // Replace value at index with DELETED_VALUE, so it won't get searched again
        if(index != null) this.getTable().set(index, DELETED_VALUE);
        return true;
    }

    @Override
    public void resolveCollision(int index, Object value){
        boolean weGoAgain = false;

        // Loop through the table until we reach null
        while(this.getTable().get(index) != null){

            // If we are at the end of the table we go again
            if(index == this.getSize()-1 && !weGoAgain){
                index = 0;
                weGoAgain = true;
            }

            // Else if we already jumped back at the end of the table and managed to reach the end of the table again
            // We have no null node
            else if(weGoAgain && index == this.getSize()-1){
                rehash();
                return;
            }

            index = (index + 1) % this.getSize();
        }
        this.set(index, value);
    }

}
