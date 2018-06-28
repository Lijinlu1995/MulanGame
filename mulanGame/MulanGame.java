package mulanGame;
import javafx.application.Application;
import javafx.stage.Stage;
import controller.SceneController;


public class MulanGame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneController sceneManager=new SceneController(primaryStage);
        sceneManager.loadGameScene();
        sceneManager.startScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}