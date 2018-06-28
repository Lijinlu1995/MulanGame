package tool;

import data.GroupData;
import data.Record;
import javafx.scene.text.Text;

import java.util.ArrayList;

//used for checking answer and collecting answers from ArrayList<Text>
public class AnswerChecker {

    //collect answer and turn to GroupData
    public GroupData collectAnswer(ArrayList<Text> c, ArrayList<Text> m, ArrayList<Text> f){
        GroupData groupData=new GroupData(userAnswerRecord(c),userAnswerRecord(m),userAnswerRecord(f));
        return groupData;

    }

    private Record userAnswerRecord(ArrayList<Text> texts){
        Record record=new Record();
        for(int i=0;i<texts.size();i++){
            if(texts.get(i).isVisible()){
                record.add(texts.get(i).getText());
            }
        }
        return record;
    }


    //check two GroupData whether same
    public boolean check(GroupData user, GroupData answer){
        if(!checkRecord(user.switchRecord("Class"),answer.switchRecord("Class")))return false;
        if(!checkRecord(user.switchRecord("Method"),answer.switchRecord("Method")))return false;
        if(!checkRecord(user.switchRecord("Field"),answer.switchRecord("Field")))return false;
        return true;
    }


    //check two Records whether same
    private boolean checkRecord(Record usergroup, Record answergroup){
        for(int i=0;i<usergroup.size();i++){
            if(!checkone(usergroup.get(i),answergroup)){
                return false;
            }
        }
        return true;
    }

    //check one string whether in the Record
    private boolean checkone(String u, Record an){
        for(int i=0;i<an.size();i++){
            if(u.equals(an.get(i))){
                return true;
            }
        }
        return false;
    }

    private void test(){
        //right Answer:ra
        //test for right answer:tra
        //test for wrong anseer:twa

        Record ra1 =new Record("person");
        Record ra2 =new Record("eat","drink","breathe","sleep","getolder");
        Record ra3 =new Record("no","yes");

        Record tra1=new Record("person");
        Record tra2 =new Record("drink","eat","sleep","breathe","getolder");
        Record tra3 =new Record("yes","no");

        Record twa1=new Record("dog");
        Record twa2 =new Record("drink","eat","sad","breathe","happy");
        Record twa3 =new Record("no");

        //test Record
        claim(checkRecord(tra1,ra1));
        claim(checkRecord(tra2,ra2));

        claim(!checkRecord(twa1,ra1));
        claim(!checkRecord(twa2,ra2));

        GroupData rightGroup=new GroupData(ra1,ra2,ra3);
        GroupData testRightGroup=new GroupData(tra1,tra2,tra3);
        GroupData testWrongGroup=new GroupData(twa1,twa2,twa3);

        //test group
        claim(check(rightGroup,testRightGroup));
        claim(!check(rightGroup,testWrongGroup));

        System.out.println("CheckAnswer is ok");

    }

    private static void claim(boolean b) { if (! b) throw new Error("Bug"); }

    public static void main(String[] args){
        AnswerChecker checkAnswer=new AnswerChecker();
        checkAnswer.test();
    }
}
