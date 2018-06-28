package data;


//There are several chapters in the game,each chapter has labels for library,user answer(answer) and correct answer(keyAnswer)

public class ChapterData {

    private final String group1="library",group2="answer",group3="keyAnswer";
    private GroupData library;
    private GroupData answer;
    private GroupData keyAnswer;

    public ChapterData(){
        library=new GroupData();
        answer=new GroupData();
        keyAnswer=new GroupData();
    }


    public GroupData switchGroup(String group) {
        switch (group) {
            case group1:
                return library;
            case group2:
                return answer;
            case group3:
                return keyAnswer;
            default:
                throw new Error("Wrong chapter");
        }
    }


    public void addGroup(String group,String typeAndText) {
        String[] spiltTypeAndText=typeAndText.split("!");
        String type = spiltTypeAndText[0];
        if(spiltTypeAndText.length!=1) {
            String[] text = spiltTypeAndText[1].split(",");
            switchGroup(group).switchRecord(type).add(text);
        }
        else{

        }
    }

    private static void claim(boolean b) {
        if (!b) throw new Error("Bug");
    }

    private static void testLibrary(){
        ChapterData chapterData=new ChapterData();
        chapterData.addGroup("library","Class:Person,dog,apple,China,Mulan");
        chapterData.addGroup("library","Method:eat,drink,sleep,pig,dog,pear,orange,bark,Mulan,happy");
        chapterData.addGroup("library","Field:age=17,height=183,bb=,bb=1,bb=1,bb=a,bb=a,bb=a,bb=c,bb=b");

        claim(chapterData.library.switchRecord("Class").get(0).equals("Person"));
        claim(chapterData.library.switchRecord("Class").get(1).equals("dog"));
        claim(chapterData.library.switchRecord("Method").get(0).equals("eat"));
        claim(chapterData.library.switchRecord("Field").get(1).equals("height=183"));
    }

    public static void main(String[] args) {
        testLibrary();
        System.out.println("ChapterData class OK");
    }

}


