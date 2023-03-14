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
        Object current = this.getTable().get(index);

        if(current == null || current == value) return current;

        // Get index of current value
        boolean weGoAgain = false;

        // Go through the table until we find a null
        while(current != null){

            // Search doesn't stop at deleted values
            if(current.equals(Hashtable.DELETED_VALUE)){
                index++;
                current = this.getTable().get(index);
                continue;
            }

            // If we found the node we were looking for return it
            if(current.equals(value)){
                return current;
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

            index++;
            current = this.getTable().get(index);
        }
        return null;
    }

    @Override
    public void resolveCollision(int index, Object value){
        boolean weGoAgain = false;
        int quadrant = 1;
        Object current = this.getTable().get(index);

        // Loop through the table until we reach null
        while(current != null){

            // If we are at the end of the table we go again
            if(index == this.getSize() - 1 && !weGoAgain){
                index = 0;
                weGoAgain = true;
            }

            // Else if we already jumped back at the end of the table and managed to reach the end of the table again
            // We have no null node
            else if(weGoAgain && index == this.getSize()-1){
                rehash();
                return;
            }

            index = quadraticProbing(index, quadrant);
            quadrant++;
            current = this.getTable().get(index);
        }
        this.set(index, value);
    }

    private int linearProbing(int index){
        return (index + 1) % this.getSize();
    }

    private int quadraticProbing(int index, int quadrant){
        return (int) ((index + Math.pow(quadrant, 2)) % this.getSize());
    }

}
