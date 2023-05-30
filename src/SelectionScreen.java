import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedList;

public class SelectionScreen {
    private Scene selectionScene;

    private final LinkedList<CustomBackground> backgrounds;

    private final LinkedList<CustomCrosshair> crosshairs;
    private int backgroundIndex;

    private int chrosshairIndex;

    private final StackPane pane;

    private final StackPane cross;


    public SelectionScreen(double scale, TitleScreen title, Stage stage,double volume) {
        pane = new StackPane();
        cross = new StackPane();
        this.selectionScene = new Scene(pane, scale * 256, scale * 240);
        VisualConstants visuals = new VisualConstants(title, scale);
        this.backgrounds = visuals.getBackgrounds();
        this.crosshairs = visuals.getCrossair();


        backgroundIndex = 0;
        chrosshairIndex = 0;

        pane.setBackground(backgrounds.get(0).getBackground());
        cross.setBackground(crosshairs.get(0).getBack());


        Message text = new Message(scale, 10.0, Pos.CENTER);
        text.addText("");
        text.addText("USE ARROW KEYS TO NAVIGATE");
        text.addText("PRESS ENTER TO START");
        text.addText("PRESS ESC TO EXIT");
        text.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().add(text);


        pane.getChildren().add(cross);
        cross.setAlignment(Pos.CENTER);
        cross.toFront();


        Media media =new Media(new File("assets/effects/Intro.mp3").toURI().toString());
        MediaPlayer intro = new MediaPlayer(media);
        intro.setVolume(volume);



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
                    TitleScreen newTitle = new TitleScreen(scale,stage,volume);
                    stage.setScene(newTitle.getTitleScene());

                    break;
                case ENTER:
                    title.getTitleMusic().stop();
                    CustomCrosshair cursor =getCurrentCross();
                    Image foreground =getCurrentBackground().getForeground();
                    Background background =getCurrentBackground().getBackground();
                    GameElements elements =new GameElements(scale,volume,cursor,background,foreground,stage);
                    intro.play();
                    intro.setOnEndOfMedia(()->{
                        Round1 round1 =new Round1(elements);
                        stage.setScene(round1.scene);
                    });


                    break;
                default:
                    break;
            }

        });


    }


    public void rightArrow() {
        backgroundIndex = (backgroundIndex >= 5) ? 5 : backgroundIndex + 1;
        pane.setBackground(backgrounds.get(backgroundIndex).getBackground());

    }

    public void leftArrow() {
        backgroundIndex = (backgroundIndex > 0) ? backgroundIndex - 1 : 0;
        pane.setBackground(backgrounds.get(backgroundIndex).getBackground());
    }

    public void upArrow() {
        chrosshairIndex = (chrosshairIndex >= 6) ? 6 : chrosshairIndex + 1;
        cross.setBackground(crosshairs.get(chrosshairIndex).getBack());
    }

    public void downArrow() {
        chrosshairIndex = (chrosshairIndex > 0) ? chrosshairIndex - 1 : 0;
        cross.setBackground(crosshairs.get(chrosshairIndex).getBack());
    }


    public CustomCrosshair getCurrentCross() {
        return crosshairs.get(chrosshairIndex);
    }

    public CustomBackground getCurrentBackground(){ return  backgrounds.get(backgroundIndex);}


    public Scene getSelectionScene() {
        return selectionScene;
    }
}