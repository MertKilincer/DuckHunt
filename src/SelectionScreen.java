/**
 * Necessary imports.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.util.LinkedList;


/**
 * SelectionScreen class that uses to select background and crosshair at this class scene field.
 */
public class SelectionScreen {

    /**
     * Scene  of the class that shown.
      */
    private Scene selectionScene;
    /**
     * Backgrounds are hold at these LinkedList as CustomBackground.
     */
    private final LinkedList<CustomBackground> backgrounds;
    /**
     * Chrossairs are hold at these LinkedList as CustomCrosshair.
     */
    private final LinkedList<CustomCrosshair> crosshairs;
    /**
     * Index of the current background in the LinkedList.
     */
    private int backgroundIndex;
    /**
     * Index of the current chrossair in the LinkedList.
     */
    private int chrosshairIndex;
    /**
     * Pane that holds the background.
     */
    private final StackPane pane;
    /**
     * Pane that hold the chrossair.
     */
    private final StackPane cross;

    /**
     *
     * @param scale scale of the application.
     * @param title titleScreen class that used to access player of the title music.
     * @param stage stage of the application.
     * @param volume volume of the sound effects.
     */
    public SelectionScreen(double scale, TitleScreen title, Stage stage,double volume) {
        /**
         * Managing the panes and visuals of the start of the selection screen.
         */
        pane = new StackPane();
        cross = new StackPane();
        this.selectionScene = new Scene(pane, scale * 256, scale * 240);
        VisualConstants visuals = new VisualConstants(title, scale);
        this.backgrounds = visuals.getBackgrounds();
        this.crosshairs = visuals.getCrossair();

        /**
         * Index of the current background and chrossair.
         */
        backgroundIndex = 0;
        chrosshairIndex = 0;

        pane.setBackground(backgrounds.get(0).getBackground());
        cross.setBackground(crosshairs.get(0).getBack());

        /**
         * Message of the scene that seen above of the selection screen.
         */
        Message text = new Message(scale, 10.0, Pos.CENTER);
        text.addText("");
        text.addText("USE ARROW KEYS TO NAVIGATE");
        text.addText("PRESS ENTER TO START");
        text.addText("PRESS ESC TO EXIT");
        text.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().add(text);

        /**
         * Set alignment and layering the nodes.
         */
        pane.getChildren().add(cross);
        cross.setAlignment(Pos.CENTER);
        cross.toFront();

        /**
         *  Intro music that played its created and adjusted its volume.
         */
        Media media =new Media(new File("assets/effects/Intro.mp3").toURI().toString());
        MediaPlayer intro = new MediaPlayer(media);
        intro.setVolume(volume);


        /**
         * SelectionScene and its events control the change between backgrounds and chrossairs.
         */
        selectionScene.setOnKeyPressed(event -> {

            switch (event.getCode()){
                case RIGHT:
                    rightArrow();
                    break;
                case LEFT:
                    leftArrow();
                    break;
                case UP:
                    upArrow();
                    break;
                case DOWN:
                    downArrow();
                    break;
                case ESCAPE:
                    /**
                     * New title scene created in the case of escape button pressed.
                     */
                    TitleScreen newTitle = new TitleScreen(scale,stage,volume);
                    stage.setScene(newTitle.getTitleScene());

                    break;
                case ENTER:
                    /**
                     * Title music stops ,current chrossair,background,foreground new game elements class created
                     * intro music played and at the end of the intro music game is started wth round1
                     * @see GameElements
                     * @see Round1
                     */
                    title.getTitleMusic().stop();
                    CustomCrosshair cursor =getCurrentCross();
                    Image foreground =getCurrentBackground().getForeground();
                    Background background =getCurrentBackground().getBackground();
                    GameElements elements =new GameElements(scale,volume,cursor,background,foreground,stage);
                    intro.play();
                    intro.setOnEndOfMedia(()->{
                        Round1 round1 =new Round1(elements);
                        stage.setScene(round1.getScene());
                    });
                    break;
                default:
                    break;
            }

        });


    }

    /**
     *  rightArrow method skip to the next background.
     */
    public void rightArrow() {
        backgroundIndex = (backgroundIndex >= 5) ? 0 : backgroundIndex + 1;
        pane.setBackground(backgrounds.get(backgroundIndex).getBackground());

    }

    /**
     * leftArrow method returns to the previous background.
     */
    public void leftArrow() {
        backgroundIndex = (backgroundIndex > 0) ? backgroundIndex - 1 : 5;
        pane.setBackground(backgrounds.get(backgroundIndex).getBackground());
    }
    /**
     *  upArrow method skips to the next chrossair.
     */
    public void upArrow() {
        chrosshairIndex = (chrosshairIndex >= 6) ? 0 : chrosshairIndex + 1;
        cross.setBackground(crosshairs.get(chrosshairIndex).getBack());
    }

    /**
     * downArrow method returns to the previous chrossair.
     */
    public void downArrow() {
        chrosshairIndex = (chrosshairIndex > 0) ? chrosshairIndex - 1 : 6;
        cross.setBackground(crosshairs.get(chrosshairIndex).getBack());
    }

    /**
     * getter for the current cross displayed at the scene.
     * @return CustomChrossair instance that displayen in the scene.
     */
    public CustomCrosshair getCurrentCross() {
        return crosshairs.get(chrosshairIndex);
    }

    /**
     * getter for current background displayed at the scene.
     * @return CustomBackground instance that displayed in the scene.
     */
    public CustomBackground getCurrentBackground(){ return  backgrounds.get(backgroundIndex);}

    /**
     * getter for the scene field in the instance.
     * @return Scene of the selectionScene instance.
     */
    public Scene getSelectionScene() {
        return selectionScene;
    }
}