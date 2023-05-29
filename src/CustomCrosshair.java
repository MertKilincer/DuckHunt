
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class CustomCrosshair extends Pane {
    public  ImageView cursorImageView;

    public Image cursorImage;

    public Background background;

    public String filepath;

    public double scale;

    public CustomCrosshair(String filepath,Double scale) {
        this.filepath=filepath;
        this.scale=scale;
        Image image = new Image(filepath, 32 * (scale / 3), 32 * (scale / 3), true, true);
        cursorImageView = new ImageView(image);
        getChildren().add(cursorImageView);



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

    public Image getCursorImage() {
        return cursorImage;
    }

    }


