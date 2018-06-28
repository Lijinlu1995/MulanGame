package data;
import javafx.scene.image.*;
import java.util.ArrayList;

//image data for gif and hints
public class ImageData {
    private final int chapter = 7;

    private ArrayList<Image> gifImage = new ArrayList<>();
    private ArrayList<String> gifUrl = new ArrayList<>();


    private ArrayList<Image> hintsImage = new ArrayList<>();
    private ArrayList<String> hintsUrl = new ArrayList<>();

    public ImageData() {
        setGifImage();
        setHintImage();
    }

    //input chapter number
    public Image getGifImage(int i) {
        if (i > chapter) {
            throw new Error("Wrong chapter");
        }
        return gifImage.get(i - 1);
    }



    public Image gethintsImage(int i) {
        if (i > chapter) {
            throw new Error("Wrong chapter");
        }
        return hintsImage.get(i-1);
    }


    private void setGifImage() {
        for (int k = 1; k <= chapter; k++) {
            gifUrl.add("assets/gif/00" + k + ".gif");
        }
        ClassLoader cl = this.getClass().getClassLoader();
        for (int i = 0; i < chapter; i++) {
            gifImage.add(new Image(cl.getResource(gifUrl.get(i)).toString()));
        }
    }



    private void setHintImage() {
        for (int k = 1; k <= chapter; k++) {
            hintsUrl.add("assets/hint/00" + k + ".png");
        }
        ClassLoader cl = this.getClass().getClassLoader();
        for (int i = 0; i < chapter; i++) {
            hintsImage.add(new Image(cl.getResource(hintsUrl.get(i)).toString()));
        }

    }
}
