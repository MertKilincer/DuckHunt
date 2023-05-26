
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CustomCrosshair extends Pane {
    private ImageView cursorImage;

    public Background background;

    public CustomCrosshair(String filepath,Double scale) {
        Image image = new Image(filepath, 32 * (scale / 3), 32 * (scale / 3), true, true);
        cursorImage = new ImageView(image);
        getChildren().add(cursorImage);


        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        background=new Background(backgroundImage);

    }

    public void updatePosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    public Background getBack(){
        return background;
    }


    public ImageView getCursorImage() {
        return cursorImage;
    }
}

