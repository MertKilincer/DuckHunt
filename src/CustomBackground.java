import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;

public class CustomBackground {

    public Image image;

    public Background background;

    public Image foreground;


    public CustomBackground(int num, Pane pane){


        image = new Image(String.format("/assets/background/%s.png",num));
        foreground = new Image(String.format("/assets/foreground/%s.png",num));
        BackgroundImage BackgroundValue = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(pane.getWidth(), pane.getHeight(), false, false, false, true)
        );
        background=new Background(BackgroundValue);

    }


    public Background getBackground() {
        return background;
    }

    public Image getImage(){return image;}

    public Image getForeground() {
        return foreground;
    }
}
