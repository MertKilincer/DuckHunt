/**
 * Necessary java fx classes to import fully function this class.
 */

import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Class that create backgrounds and foregrounds as a field in it.
 */
public class CustomBackground {

    /**
     * Background that created from java fx library components.
     */
    private Background background;
    /**
     * Foreground image correspond to the background.
     */
    private Image foreground;

    /**
     * CustomBackground class that creates background and foreground.
     * @param num number of the background.
     * @param pane pane for the getting width and height.
     */

    public CustomBackground(int num, Pane pane){


        Image image = new Image(String.format("/assets/background/%s.png",num));
        foreground = new Image(String.format("/assets/foreground/%s.png",num));
        /*
        Background image created from the image and cover the pane
         */
        BackgroundImage BackgroundValue = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(pane.getWidth(), pane.getHeight(), false, false, false, true)
        );
        background=new Background(BackgroundValue);

    }


    public Background getBackground() {
        return background;
    }


    public Image getForeground() {
        return foreground;
    }
}
