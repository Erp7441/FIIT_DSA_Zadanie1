package sk.stuba.fiit.martin.szabo.hashtable.openaddressing;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

import static java.lang.System.*;

public class HashtableOpenAddressing extends Hashtable{
    public HashtableOpenAddressing(int size){
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

    @Override
    public void resolveCollision(int index, Object value){
        boolean weGoAgain = false;
        while(this.getTable().get(index) != null){
            index = (index + 1) % this.getSize();

            if(index == this.getSize()-1){
                index = 0;
                weGoAgain = true;
            }
            else if(weGoAgain){
                err.println("DEBUG:: Hashtable is full");
                return;
            }
        }
        this.set(index, value);
    }

}
