package sk.stuba.fiit.martin.szabo.hashtable.separatechaining;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashtableSeparateChaining extends Hashtable{

    public HashtableSeparateChaining(){
        super();
    }

    public HashtableSeparateChaining(int size){
        super(size);
    }

    @Override
    public Object search(Object value){
        if(!(value instanceof String)){ return null; } // Functions to hash other types are not implemented yet

        // Get value from table
        Object tableValue = this.getTable().get(hash(value));

        // If it is a array look for the value in the array
        if(tableValue instanceof LinkedList){
            tableValue = searchLinkedList((LinkedList<Object>) tableValue, value);
        }

        // If it does not contain the value we are looking for
        if(value != tableValue){

            // Get index of current value
            tableValue = this.getTable().get(hash(value));
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

                // If we found a array list. Look through it for a value
                if(this.getTable().get(index) instanceof ArrayList){
                    tableValue = searchLinkedList((LinkedList<Object>) this.getTable().get(index), value);
                    if(tableValue == value){
                        return tableValue;
                    }
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

    public static Object searchLinkedList(LinkedList<Object> list, Object value){
        for(Object item : list){
            if(item.equals(Hashtable.DELETED_VALUE)) continue;
            if(item.equals(value)) return item;
        }
        return null;
    }

    public void resolveCollision(int index, Object value){

        Object tableValue = this.search(value);

        if(tableValue instanceof LinkedList<?>){
            try{
                // TODO:: fix this :(
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
