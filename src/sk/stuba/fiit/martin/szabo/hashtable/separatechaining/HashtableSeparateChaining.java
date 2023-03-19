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
