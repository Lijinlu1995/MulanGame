package tool;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import mulanGame.MulanGame;

import java.io.*;
import java.net.URL;

//make a video for the game
//add getMediaPlay.setAutoPlay(true) when used
public class VideoMaker {
    public MediaView makeVideo(String filename){
        ClassLoader cl = this.getClass().getClassLoader();
        MediaView mediaView=new MediaView(new MediaPlayer(new Media(cl.getResource(filename).toString())));
        return mediaView;
    }
}
