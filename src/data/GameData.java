package data;

import java.io.*;


//The strings for labels in the game
//There are several chapters in the game
//Each chapter has 3 groups of labels: library, answer and keyAnswer
//Each group has 3 types of labels: Class, Method, Field

//read file to store strings for labels; group type can be library, answer and keyAnswer
public class GameData {
    private final String libraryPath="/assets/label/library.txt";
    private final String answerPath="/assets/label/answer.txt";
    private final String keyPath="/assets/label/keyAnswer.txt";

    private ChapterData chapterData1;
    private ChapterData chapterData2;
    private ChapterData chapterData3;
    private ChapterData chapterData4;
    private ChapterData chapterData5;
    private ChapterData chapterData6;
    private ChapterData chapterData7;


    //used for testing
    public GameData(String group,String filePath){
        chapterData1=new ChapterData();
        chapterData2=new ChapterData();
        this.ReadFile(group,filePath);
    }


    public GameData() {
        chapterData1 = new ChapterData();
        chapterData2 = new ChapterData();
        chapterData3 = new ChapterData();
        chapterData4 = new ChapterData();
        chapterData5 = new ChapterData();
        chapterData6 = new ChapterData();
        chapterData7 = new ChapterData();
        this.ReadFile("library", libraryPath);
        this.ReadFile("answer", answerPath);
        this.ReadFile("keyAnswer", keyPath);
    }


    public GroupData getKeyAnswer(int i){
        return switchChapter(i).switchGroup("keyAnswer");
    }

    public Record getData(int i,String group,String type){
        return switchChapter(i).switchGroup(group).switchRecord(type);
    }




    public void ReadFile(String group,String filePath) {
        String lineTxt;
        char title;
        String typeAndText;
        try {
            InputStream read=this.getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(read));
//            File file = new File(filePath);
//            if (file.isFile() && file.exists()) {
//                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
//                BufferedReader bufferedReader = new BufferedReader(read);
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt.charAt(0) == '#') {
                        title = lineTxt.charAt(1);
                        int chapterNum=Integer.parseInt(String.valueOf(title));
                        for (int j = 0; j < 3; j++) {
                            if ((typeAndText = bufferedReader.readLine()) != null) {
                                switchChapter(chapterNum).addGroup(group,typeAndText);
                            }
                        }
                    }
                }
                read.close();
//            } else System.out.println("Cannot find file " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChapterData switchChapter(int i) {
        switch (i) {
            case 1:
                return chapterData1;
            case 2:
                return chapterData2;
            case 3:
                return chapterData3;
            case 4:
                return chapterData4;
            case 5:
                return chapterData5;
            case 6:
                return chapterData6;
            case 7:
                return chapterData7;
            default:
                throw new Error("Wrong chapter");
        }
    }


    private static void claim(boolean b) {
        if (!b) throw new Error("Bug");
    }

    private static void test() {
        GameData libraryData=new GameData("library","/assets/test/testLibrary.txt");

        claim(libraryData.getData(1,"library","Class").get(0).equals("Person"));
        claim(libraryData.getData(1,"library","Class").get(1).equals("dog"));

        claim(libraryData.getData(1,"library","Method").get(0).equals("eat"));
        claim(libraryData.getData(1,"library","Method").get(1).equals("drink"));

        claim(libraryData.getData(1,"library","Field").get(0).equals("age"));
        claim(libraryData.getData(1,"library","Field").get(1).equals("name"));

        claim(libraryData.getData(2,"library","Class").get(2).equals("apple"));
        claim(libraryData.getData(2,"library","Method").get(2).equals("sleep"));

    }


    public static void main(String[] args) {
        test();
        System.out.println("GameData is ok");
    }
}
