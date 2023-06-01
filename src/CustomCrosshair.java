/**
 * Necessary java fx imports.
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * CustomCrosshair class that extends pane and have a background image as chrossair.
 */
public class CustomCrosshair extends Pane {
    /**
     * cursor image that chrossair has.
     */
    private   ImageView cursorImageView;
    /**
     * Background has chrossair as a image this used at selection scene.
     */
    private Background background;
    /**
     * Filepath that uses for creating new instances because multiple adding the same chrosshair.
     */
    private String filepath;
    /**
     * Scale of the chrossair.
     */
    private double scale;

    /**
     * Constructor of the chrossair class.
     * @param filepath filepath of the chrossair.
     * @param scale scale of the chrossair.
     */
    public CustomCrosshair(String filepath,Double scale) {
        this.filepath=filepath;
        this.scale=scale;
        /*
        scales the chrossair by adjusting the image.
         */
        Image image = new Image(filepath, 32 * (scale / 3), 32 * (scale / 3), true, true);
        cursorImageView = new ImageView(image);
        getChildren().add(cursorImageView);



        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        background=new Background(backgroundImage);

    }

    /**
     * Update position method that change the chrossair position.
     * @param x position x that the cursor go.
     * @param y position y that the cursor go.
     */
    public void updatePosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    /**
     * getter for background field ,these is used at selection scene to get background.
     * @return Background of the cursorImage.
     */
    public Background getBack(){
        return background;
    }

    /**
     * getter for filepath.
     * @return String of the filepath.
     */
    public String getFilepath() {
        return filepath;
    }
}


