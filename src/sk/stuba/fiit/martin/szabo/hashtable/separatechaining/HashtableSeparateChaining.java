package sk.stuba.fiit.martin.szabo.hashtable.separatechaining;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

import java.util.LinkedList;

public class HashtableSeparateChaining extends Hashtable{

    public HashtableSeparateChaining(int size){
        super(size);
    }

    @Override
    public Object search(Object value){
        return null;
    }

    @Override
    public Object delete(Object value){
        return null;
    }

    public void resolveCollision(int index, Object value){

        Object tableValue = this.getTable().get(index);

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
