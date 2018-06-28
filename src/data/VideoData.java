package data;

import javafx.scene.media.MediaView;
import tool.VideoMaker;
import java.util.ArrayList;

//all story videos in the game
public class VideoData {
    private VideoMaker videoMaker;
    private final int chapter = 7;

    private ArrayList<String> videoUrl = new ArrayList<>();
    private ArrayList<MediaView> mediaViews = new ArrayList<>();

    public VideoData(){
        videoMaker=new VideoMaker();
        setVideo();
    }


    public MediaView getVideo(int chapterNum){
        if(chapterNum>chapter||chapterNum-1<0) throw new Error("Wrong chapter for video");
        mediaViews.get(chapterNum-1).getMediaPlayer().play();
        return mediaViews.get(chapterNum-1);

    }

    private void setVideo() {
        for (int k = 1; k <= chapter; k++) {
            videoUrl.add("assets/video/00" + k + ".mp4");
        }
        for (int i = 0; i < chapter; i++) {
            mediaViews.add(videoMaker.makeVideo(videoUrl.get(i)));
        }
    }




}
