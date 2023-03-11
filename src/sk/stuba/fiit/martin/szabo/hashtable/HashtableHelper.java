package sk.stuba.fiit.martin.szabo.hashtable;

import sk.stuba.fiit.martin.szabo.hashtable.hashtable.Hashtable;

public class HashtableHelper{

    private HashtableHelper(){}

    public static void hashtableExecute(Hashtable hashtable){
        hashtable.insert("ab");
        hashtable.insert("cd");
        hashtable.insert("efg");
        hashtable.insert("efg");

        System.out.println(hashtable);
    }
}
