package controller;

import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tool.AnswerChecker;
import tool.TextSetter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//left up:gif,left down:hint
//4 buttons on the game pane
//c for class, m for method, f for field
//tc for target class and so on

//fail submit pop Alert:icon/fail.png
public class GameController implements Initializable {

    @FXML
    private VBox lcb1, lcb2, lcb3, lcb4, lcb5;
    @FXML
    private VBox lmb1, lmb2, lmb3, lmb4, lmb5, lmb6, lmb7, lmb8, lmb9, lmb10;
    @FXML
    private VBox lfb1, lfb2, lfb3, lfb4, lfb5, lfb6, lfb7, lfb8, lfb9, lfb10;

    @FXML
    private VBox tcb1, tcb2, tcb3, tcb4, tcb5;
    @FXML
    private VBox tmb1, tmb2, tmb3, tmb4, tmb5, tmb6, tmb7, tmb8, tmb9, tmb10;
    @FXML
    private VBox tfb1, tfb2, tfb3, tfb4, tfb5, tfb6, tfb7, tfb8, tfb9, tfb10;

    @FXML
    private ImageView lefttopimv, hintiv, backima;
    @FXML
    private Button submit, reset, explain, previousChapter;
    @FXML
    private Text c1, c2, c3, c4, c5;
    @FXML
    private Text m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;
    @FXML
    private Text f1, f2, f3, f4, f5, f6, f7, f8, f9, f10;
    @FXML
    private Text tc1, tc2, tc3, tc4, tc5;
    @FXML
    private Text tm1, tm2, tm3, tm4, tm5, tm6, tm7, tm8, tm9, tm10;
    @FXML
    private Text tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;


    private int chapterNum = 1;
    private int LASTCHAPTER = 7;

    private ArrayList<Text> libCla;
    private ArrayList<Text> libMet;
    private ArrayList<Text> libFie;
    private ArrayList<Text> ansCla;
    private ArrayList<Text> ansMet;
    private ArrayList<Text> ansFie;

    private ArrayList<VBox> lvboxcla;
    private ArrayList<VBox> lvboxmet;
    private ArrayList<VBox> lvboxfie;
    private ArrayList<VBox> tvboxcla;
    private ArrayList<VBox> tvboxmet;
    private ArrayList<VBox> tvboxfie;

    private GameData gameData;
    private ImageData imageData;

    private AnswerChecker answerChecker;
    private TextSetter textSetter;


    private Alert alert;
    private VideoStage videoStage;
    private ExplainStage explainStage;


    private void setupText() {
        libCla = new ArrayList<>();
        libMet = new ArrayList<>();
        libFie = new ArrayList<>();
        ansCla = new ArrayList<>();
        ansMet = new ArrayList<>();
        ansFie = new ArrayList<>();

        addTextToArray(libCla, c1, c2, c3, c4, c5);
        addTextToArray(libMet, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10);
        addTextToArray(libFie, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);

        addTextToArray(ansCla, tc1, tc2, tc3, tc4, tc5);
        addTextToArray(ansMet, tm1, tm2, tm3, tm4, tm5, tm6, tm7, tm8, tm9, tm10);
        addTextToArray(ansFie, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10);

    }

    private void addTextToArray(ArrayList<Text> arrayList, Text... texts) {
        for (Text t : texts) {
            arrayList.add(t);
        }
    }


    private void setuptVbox() {
        lvboxcla = new ArrayList<>();
        lvboxmet = new ArrayList<>();
        lvboxfie = new ArrayList<>();
        tvboxcla = new ArrayList<>();
        tvboxmet = new ArrayList<>();
        tvboxfie = new ArrayList<>();

        addtVboxToArray(lvboxcla, lcb1, lcb2, lcb3, lcb4, lcb5);
        addtVboxToArray(lvboxmet, lmb1, lmb2, lmb3, lmb4, lmb5, lmb6, lmb7, lmb8, lmb9, lmb10);
        addtVboxToArray(lvboxfie, lfb1, lfb2, lfb3, lfb4, lfb5, lfb6, lfb7, lfb8, lfb9, lfb10);

        addtVboxToArray(tvboxcla, tcb1, tcb2, tcb3, tcb4, tcb5);
        addtVboxToArray(tvboxmet, tmb1, tmb2, tmb3, tmb4, tmb5, tmb6, tmb7, tmb8, tmb9, tmb10);
        addtVboxToArray(tvboxfie, tfb1, tfb2, tfb3, tfb4, tfb5, tfb6, tfb7, tfb8, tfb9, tfb10);
    }

    private void addtVboxToArray(ArrayList<VBox> arrayList, VBox... vboxes) {
        for (VBox vb : vboxes) {
            arrayList.add(vb);
        }
    }


    public void updateText(int i) {
        textSetter.setText(gameData.getData(i, "library", "Class"), libCla, lvboxcla);
        textSetter.setText(gameData.getData(i, "library", "Method"), libMet, lvboxmet);
        textSetter.setText(gameData.getData(i, "library", "Field"), libFie, lvboxfie);

        textSetter.setText(gameData.getData(i, "answer", "Class"), ansCla, tvboxcla);
        textSetter.setText(gameData.getData(i, "answer", "Method"), ansMet, tvboxmet);
        textSetter.setText(gameData.getData(i, "answer", "Field"), ansFie, tvboxfie);
    }

    private void setupDrag() {
        doDragDetect(libCla);
        doDragDetect(libMet);
        doDragDetect(libFie);

        doDragDone(libCla);
        doDragDone(libMet);
        doDragDone(libFie);

        doDraggedOver(ansCla);
        doDraggedOver(ansMet);
        doDraggedOver(ansFie);

        doDraggedDropped(ansCla);
        doDraggedDropped(ansMet);
        doDraggedDropped(ansFie);

    }


    public void doDragDetect(ArrayList<Text> texts) {
        for (int i = 0; i < texts.size(); i++) {
            int finalI = i;
            texts.get(i).setOnDragDetected(event -> {
                Dragboard db = texts.get(finalI).startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(texts.get(finalI).getText());
                db.setContent(content);
                event.consume();
            });
        }
    }


    public void doDraggedOver(ArrayList<Text> target) {
        for (int i = 0; i < target.size(); i++) {
            int finalI = i;
            target.get(i).setOnDragOver(event -> {
                if (event.getGestureSource() != target.get(finalI) && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                    event.consume();
                }
            });
        }
    }

    public void doDraggedDropped(ArrayList<Text> target) {
        for (int i = 0; i < target.size(); i++) {
            int finalI = i;
            target.get(i).setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean ic = false;
                if (db.hasString() && target.get(finalI).getText().equals("drag here")) {
                    target.get(finalI).setText(db.getString());
                    ic = true;
                }
                event.setDropCompleted(ic);
                event.consume();
            });
        }
    }


    public void doDragDone(ArrayList<Text> texts) {
        for (int i = 0; i < texts.size(); i++) {
            int finalI = i;
            texts.get(i).setOnDragDone(event -> {
                if (event.getTransferMode() == TransferMode.MOVE) {
                    texts.get(finalI).setVisible(false);
                }
            });
        }

    }

    private void setupButton() {
        submit.setOnAction(this::moveOn);
        reset.setOnAction(this::resetText);
        explain.setOnAction(this::Explain);
        previousChapter.setOnAction(this::goBack);
        updateBtn();
    }

    private void updateBtn() {
        if (chapterNum == 1) {
            previousChapter.setVisible(false);
        } else {
            previousChapter.setVisible(true);
        }
    }


    private void moveOn(ActionEvent event) {
        if (answerChecker.check(answerChecker.collectAnswer(ansCla, ansMet, ansFie), gameData.getKeyAnswer(chapterNum))) {
            videoStage.popVideo(chapterNum);
            ++chapterNum;
            if(chapterNum>LASTCHAPTER){
                videoStage.gameOver();
                chapterNum=1;
            }
            updateText(chapterNum);
            updateImage(chapterNum);
            updateBtn();
        } else {
            alert.showAndWait();
        }
    }

    private void setupAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Answer");
        alert.setHeaderText("Fail!\n" + "Click \"Explain\" for help");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefSize(300, 280);
        dialogPane.getStylesheets().add(getClass().getResource("/style/myDialogs.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
    }

    private void updateImage(int i) {
        lefttopimv.setImage(imageData.getGifImage(i));
        backima.setImage(imageData.getGifImage(i));
        hintiv.setImage(imageData.gethintsImage(i));
    }


    private void goBack(ActionEvent event) {
        chapterNum--;
        updateText(chapterNum);
        updateBtn();
        updateImage(chapterNum);
    }

    private void Explain(ActionEvent event) {
        explainStage.setChapter(chapterNum);
    }


    private void resetText(ActionEvent event) {
        updateText(chapterNum);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setuptVbox();
        setupText();
        setupDrag();
        setupButton();
        setupAlert();

        answerChecker = new AnswerChecker();
        textSetter = new TextSetter();

        videoStage = new VideoStage();
        explainStage = new ExplainStage();

        imageData = new ImageData();
        updateImage(chapterNum);
        gameData = new GameData();
        updateText(chapterNum);


    }
}
