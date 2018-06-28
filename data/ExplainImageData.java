package data;

import javafx.scene.image.Image;
import java.util.ArrayList;

//explanation pictures
public class ExplainImageData {
    private final int chapter = 7;

    private ArrayList<Image> explainImage = new ArrayList<>();
    private ArrayList<String> explainUrl = new ArrayList<>();

    public ExplainImageData(){
        seteExplaImage();
    }

    public Image getExplainImage(int i, int j) {
       if (i > chapter) {
            throw new Error("Wrong chapter");
        }
        if (j != 0 && j != 1) {
            throw new Error("Explain image Error");
        }
        return explainImage.get((i - 1) * 2 + j);
    }

    private void seteExplaImage() {
        for (int k = 1; k <= chapter; k++) {
            explainUrl.add("assets/explain/" + k + "_1" + ".png");
            explainUrl.add("assets/explain/" + k + "_2" + ".png");
        }
        ClassLoader cl = this.getClass().getClassLoader();
        for (int i = 0; i < chapter * 2; i++) {
            explainImage.add(new Image(cl.getResource(explainUrl.get(i)).toString()));
        }
    }
}
