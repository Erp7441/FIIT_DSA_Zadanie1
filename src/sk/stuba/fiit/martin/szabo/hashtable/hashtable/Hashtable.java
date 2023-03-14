package sk.stuba.fiit.martin.szabo.hashtable.hashtable;

import java.util.ArrayList;
import java.util.List;

public abstract class Hashtable{

    public static final String DELETED_VALUE = "DELETED_VALUE";

    private ArrayList<Object> table = new ArrayList<>();
    private Long elements = 0L;

    protected Hashtable(){ table.add(null); }
    protected Hashtable(int size){
        initialize(size);
    }

    public abstract void resolveCollision(int index, Object value);
    public abstract Object search(Object value);

    public void initialize(int size){
        table = new ArrayList<>();
        for(int i = 0; i < size; i++){
            table.add(null);
        }
    }

    public boolean delete(Object value){

        if(value == null) return false;

        // Find the value
        Object found = search(value);

        // If we have not found value there is nothing to delete
        if(found == null){
            return false;
        }

        // Replace value at index with DELETED_VALUE, so it won't get searched again
        this.getTable().set(hash(found), DELETED_VALUE);

        this.decrementElements();

        return true;
    }

    public int hash(Object value){
        return Math.abs(value.hashCode()) % this.getSize();
    }

    public void insert(Object value){

        if(value == null) return;

        Object found = search(value);

        if(found != null && !found.equals(DELETED_VALUE)){
            resolveCollision(hash(value), value);
        }
        else{
            this.set(hash(value), value);
        }

        this.incrementElements();
        if(calculateLoad() > 0.75) rehash();
    }

    public double calculateLoad(){
        return ((double) this.getElements() / (double) this.getSize());
    }

    public void rehash(){

        if(this.getTable() == null || this.getSize() == 0) return;

        ArrayList<Object> values = new ArrayList<>(this.getTable());
        initialize(this.getSize() * 2);

        for(Object value : values){
            this.insert(value);
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

    public Long getElements(){
        return elements;
    }

    public void setElements(Long elements){
        this.elements = elements;
    }

    public void incrementElements(){
        this.elements++;
    }

    public void decrementElements(){
        this.elements--;
    }
}
