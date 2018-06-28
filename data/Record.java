package data;

import java.util.*;
//a list of labels that can be dragged and shown in the game
public class Record {

    private List<String> text;

    public Record() {
        text = new ArrayList<String>();
    }

    public Record(String... values) {
        text = new ArrayList<String>();
        if (values == null || values.length == 0) throw new Error("No values");
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) throw new Error("Null");
            text.add(i, values[i]);
        }
    }

    //shuffle the records
    public void shuffle(){
        Collections.shuffle(text);
    }


    public int size() {
        return text.size();
    }
    public String get(int col) {
        if (col < 0 || col >= text.size()) throw new Error("Bad col");
        return text.get(col);
    }

    public void add(String...value) {
        for(int i=0;i<value.length;i++){
            if (value == null) throw new Error("Null value");
            text.add(value[i]);
        }
    }


    private static void claim(boolean b) {
        if (!b) throw new Error("Bug");
    }

    public static void main(String[] args) {
        Record example = new Record("Zero", "One", "Two");
        claim(example.size() == 3);
        claim(example.get(0).equals("Zero"));
        claim(example.get(1).equals("One"));
        claim(example.get(2).equals("Two"));

        Record example2 = new Record();
        example2.add("hello","yes","me");
        claim(example2.get(0).equals("hello"));
        claim(example2.get(1).equals("yes"));
        claim(example2.get(2).equals("me"));

        System.out.println("Record class OK");
    }
}