package sk.stuba.fiit.martin.szabo.hashtable.hashtable;

import java.util.ArrayList;
import java.util.List;

public abstract class Hashtable{

    public static final String  DELETED_VALUE = "DELETED_VALUE";

    private ArrayList<Object> table = new ArrayList<>();
    private Long elements = 0L;

    protected Hashtable(){ initialize(1); }
    protected Hashtable(int size){
        initialize(size);
    }

    public void initialize(int size){
        table = new ArrayList<>();
        for(int i=0; i<size; i++){
            table.add(null);
        }
    }

    public abstract void resolveCollision(int index, Object value);
    public abstract Object search(Object value);
    public boolean delete(Object value){
        // Find the value
        Object found = search(value);

        // If we have not found value there is nothing to delete
        if(found == null) return false;

        // Calculate index
        Integer index = hash(found);

        // Replace value at index with DELETED_VALUE, so it won't get searched again
        if(index != null) this.getTable().set(index, DELETED_VALUE);

        this.decrementElements();

        return true;
    }

    public Integer hash(Object value){
        if(value instanceof String){
            return hash((String) value);
        }
        else return null;
    }

    public int hash(String value){
        int hashValue = 0;
        for(int i = 0; i < value.length(); i++){
            hashValue += value.charAt(i) - 64;
        }
        return hashValue % this.getSize();
    }

    public void insert(Object value){
        int index;

        if(value instanceof String) index = hash(value);
        else return;

        if(this.getTable().get(index) != null && !this.getTable().get(index).equals(DELETED_VALUE)){
            resolveCollision(index, value);
        }
        else{
            this.set(index, value);
        }

        this.incrementElements();

        if(calculateLoad() > 0.75) rehash();
    }

    public double calculateLoad(){
        return ((double) this.getElements() / (double) this.getSize());
    }

    public void rehash(){
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
