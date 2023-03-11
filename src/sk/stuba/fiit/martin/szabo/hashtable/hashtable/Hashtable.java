package sk.stuba.fiit.martin.szabo.hashtable.hashtable;

import java.util.ArrayList;
import java.util.List;

public abstract class Hashtable{

    private ArrayList<Object> table = new ArrayList<>();

    protected Hashtable(int size){
        initialize(size);
    }

    public void initialize(int size){
        for(int i=0; i<size; i++){
            table.add(null);
        }
    }

    public abstract void resolveCollision(int index, Object value);
    public abstract Object search(Object value);
    public abstract Object delete(Object value);

    public int hash(String value){
        int hashValue = 0;
        for(int i = 0; i < value.length(); i++){
            hashValue += value.charAt(i) - 64;
        }
        return hashValue % this.getSize();
    }

    public void insert(String value){
        int index = hash(value);

        if(this.getTable().get(index) != null){
            resolveCollision(index, value);
        }
        else{
            this.set(index, value);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.getSize(); i++){
            Object value = this.getTable().get(i);
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


}
