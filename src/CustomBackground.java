import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CustomBackground {

    public Image image;

    public Background background;


    public CustomBackground(String filePath, Pane pane){
        image = new Image(filePath);
        BackgroundImage BackgroundValue = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(pane.getWidth(), pane.getHeight(), false, false, false, true)
        );
        background=new Background(BackgroundValue);

    }


    public Background getBackground() {
        return background;
    }

    public Image getImage() {
        return image;
    }
}
