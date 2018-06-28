package tool;

import data.Record;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;

//set Text from record to arraylist
public class TextSetter {

    public void setText(Record record, ArrayList<Text> arrayList, ArrayList<VBox> vBoxArrayList) {
        for (int j = 0; j < arrayList.size(); j++) {
            if (j < record.size() && !record.get(j).equals("")) {
                arrayList.get(j).setText(record.get(j));
                arrayList.get(j).setVisible(true);
                vBoxArrayList.get(j).setVisible(true);
            } else {
                arrayList.get(j).setVisible(false);
                vBoxArrayList.get(j).setVisible(false);
            }
        }
    }

}
