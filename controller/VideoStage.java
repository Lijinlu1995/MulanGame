package controller;

import data.VideoData;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.image.*;

//video for story
public class VideoStage {
    private final String over="assets/icon/endgame.png";
    private Stage stage;
    private VideoData videoData;
    private BorderPane storyPane;


    public VideoStage() {
        stage = new Stage();
        videoData = new VideoData();
        storyPane = new BorderPane();

        try {
            Scene storyScene = new Scene(storyPane);
            stage.setScene(storyScene);
            stage.setTitle("Story is going...");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Cannot play Video");
        }
    }

    public void popOver(WindowEvent e) {
        Stage overStage=new Stage();
        BorderPane overpane = new BorderPane();
        Scene overscene=new Scene(overpane);
        overStage.setScene(overscene);
        overStage.setTitle("Story ends");

        ClassLoader cl = this.getClass().getClassLoader();
        ImageView gameOverImg = new ImageView(new Image(cl.getResource(over).toString()));
        overpane.setCenter(gameOverImg);
        overStage.show();
    }

    public void gameOver() {
        stage.setOnCloseRequest(this::popOver);
    }

    public void popVideo(int chapterNum) {
        storyPane.setCenter(videoData.getVideo(chapterNum));
        stage.show();
    }
}
