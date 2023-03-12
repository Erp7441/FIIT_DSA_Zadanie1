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
                if(this.getTable().get(index).equals(value)){
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
    public void resolveCollision(int index, Object value){
        boolean weGoAgain = false;
        int quadrant = 1;

        // Loop through the table until we reach null
        while(this.getTable().get(index) != null){

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
        }
        this.set(index, value);
    }

    private Integer linearProbing(Integer index){
        return (index + 1) % this.getSize();
    }

    private Integer quadraticProbing(Integer index, Integer quadrant){
        return (int) ((index + Math.pow(quadrant, 2)) % this.getSize());
    }

}
