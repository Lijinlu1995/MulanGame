package data;

//There are three groups of labels in the game：Class,Method and Field

public class GroupData {
    private final String type1="Class",type2="Method",type3="Field";
    private Record classGroup;
    private Record methodGroup;
    private Record fieldGroup;


    public GroupData(){
       classGroup=new Record();
       methodGroup=new Record();
       fieldGroup=new Record();
    }

    public GroupData(Record...records){
        if(records.length!=3){
            throw new Error("wrong group");
        }
        else {
            classGroup=records[0];
            methodGroup=records[1];
            fieldGroup=records[2];
        }
    }

    public Record switchRecord(String type){
        if (type.equals(type1)) {
            return classGroup;
        }
        else if (type.equals(type2)) {
           return methodGroup;
        }
        else if (type.equals(type3)) {
            return fieldGroup;
        }
        else throw new Error("wrong type");
    }


    private static void claim(boolean b) {
        if (!b) throw new Error("Bug");
    }

    public static void main(String[] args) {
        Record record1=new Record("person","dog","cat");
        Record record2=new Record("eat","drink","sleep");
        Record record3=new Record("name","gender","age");
        GroupData example=new GroupData(record1,record2,record3);
        claim(example.switchRecord("Class").get(0).equals("person"));
        claim(example.switchRecord("Class").get(1).equals("dog"));
        claim(example.switchRecord("Class").get(2).equals("cat"));
        claim(example.switchRecord("Method").get(1).equals("drink"));
        claim(example.switchRecord("Field").get(2).equals("age"));
        System.out.println("Groupdata class OK");
    }






}
