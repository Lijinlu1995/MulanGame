package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tool.VideoMaker;

import java.net.URL;


//Scene controller for start Game and Game stage
public class SceneController {
    private VideoMaker videoMaker;
    private Stage primaryStage;
    private Stage gameStage;

    private final String game = "/layout/game.fxml";
    private final String startVideo="assets/video/start.mp4";
    private final String gameCss="/style/game.css";

    private final String startTitle="Start Game";
    private final String gameTitle="Mulan Story";

    public SceneController(Stage stage) {
        videoMaker=new VideoMaker();
        this.primaryStage = stage;
        gameStage=new Stage();
    }


    public void startScene() {
        BorderPane borderPane=new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene storyScene = new Scene(borderPane,640,510);
        storyScene.setFill(Color.BLACK);
        storyScene.getStylesheets().add(getClass().getResource(gameCss).toExternalForm());
        MediaView video = videoMaker.makeVideo(startVideo);
        borderPane.setCenter(video);

        Button button = new Button(startTitle);
        button.setOnAction(this::startGameScene);
        HBox hBox = new HBox();
        hBox.getChildren().add(button);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);
        primaryStage.setScene(storyScene);
        primaryStage.setTitle(startTitle);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        video.getMediaPlayer().setAutoPlay(true);

    }


    public void startGameScene(ActionEvent event){
        primaryStage.close();
        gameStage.show();

    }

    public void loadGameScene(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(game));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(gameCss).toExternalForm());
            gameStage.setScene(scene);
            gameStage.setTitle(gameTitle);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Cannot play Game");
        }

    }

}




