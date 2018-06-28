package controller;


import data.ExplainImageData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

//explain of the question on each page
//2 buttons on the page,previous and next
//use explain.fxml layout

public class ExplainStage {
    private Stage stage;
    private ImageView imv;
    private ExplainImageData explainImageData;
    private int chapter;
    private Button nextBnt;
    private Button previousBnt;
    private Button close;

    private final String explainFXML="/layout/explain.fxml";
    private final String explainCss="/style/game.css";
    private final String explainTitle="Explanation for Game";

    public ExplainStage() {
        explainImageData = new ExplainImageData();
        imv = new ImageView();
        try {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(explainFXML));
            GridPane borderPane=fxmlLoader.load();
            borderPane.add(imv,1,1);
            Scene popScene = new Scene(borderPane);
            popScene.getStylesheets().add(getClass().getResource(explainCss).toExternalForm());
            nextBnt = (Button) popScene.lookup("#next");
            previousBnt = (Button) popScene.lookup("#previous");
            close = (Button) popScene.lookup("#close");
            nextBnt.setOnAction(this::nextExplain);
            previousBnt.setOnAction(this::previousExplain);
            close.setOnAction(this::closeStage);
            stage.setTitle(explainTitle);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(popScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStage(ActionEvent actionEvent){
        stage.close();
    }

    public void setChapter(int i) {
        chapter = i;
        imv.setImage(explainImageData.getExplainImage(i,0));
        previousBnt.setVisible(false);
        nextBnt.setVisible(true);
        stage.show();
    }

    private void previousExplain(ActionEvent event) {
        nextBnt.setVisible(true);
        previousBnt.setVisible(false);
        imv.setImage(explainImageData.getExplainImage(chapter, 0));


    }

    private void nextExplain(ActionEvent event) {
        previousBnt.setVisible(true);
        nextBnt.setVisible(false);
        imv.setImage(explainImageData.getExplainImage(chapter, 1));

    }




}
