package sk.stuba.fiit.martin.szabo.menu;

public class Entry{

    private String label;
    private Runnable function;

    public Entry(){
        this.label = "<ENTRY WITHOUT LABEL>";
        this.function = null;
    }

    public Entry(String label, Runnable function){
        this.label = label;
        this.function = function;
    }

    public void run(){
        function.run();
    }

    @Override
    public String toString(){
        return label;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public Runnable getFunction(){
        return function;
    }

    public void setFunction(Runnable function){
        this.function = function;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;

        if(!getLabel().equals(entry.getLabel())) return false;
        return getFunction().equals(entry.getFunction());
    }

    @Override
    public int hashCode(){
        int result = getLabel().hashCode();
        result = 31 * result + getFunction().hashCode();
        return result;
    }
}
