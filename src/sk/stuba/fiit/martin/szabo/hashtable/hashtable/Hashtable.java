package sk.stuba.fiit.martin.szabo.hashtable.hashtable;

import java.util.ArrayList;
import java.util.List;

public abstract class Hashtable{

    public static final String  DELETED_VALUE = "DELETED_VALUE";

    private ArrayList<Object> table = new ArrayList<>();

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
    public abstract boolean delete(Object value);

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

        if(calculateLoad() > 0.75) rehash();
    }

    public double calculateLoad(){
        int numOfNonNull = 0;
        for(Object value : this.getTable()){
            if(value != null) numOfNonNull++;
        }
        return ((double) numOfNonNull / (double) this.getSize());
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


}
