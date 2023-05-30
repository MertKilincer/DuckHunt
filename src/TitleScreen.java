/**
 * Necessary java fx classes to import fully function this class.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

/**
 * TitleScreen class that create the title screen of the duck hunt game.
 */
public class TitleScreen extends Pane {
    /**
     * Java fx media player field that plays the title music.
     */
    private MediaPlayer player;
    /**
     * Scene that hold the titleScreen pane and labels etc.
     */
    private Scene titleScene;

    /**
     * TitleScreen class constructor that used to create TitleScreen objects in the app.
     * @param scale scale of the application
     * @param stage stage that uses scenes in start() we called this as a primary stage parameter.
     * @param volume volume of the audio in the app
     */
    public TitleScreen(double scale, Stage stage,double volume){

        /*
            These Lines created for adjusting background and place it to approtiate position in the pane.
         */
        Image image =new Image("assets/welcome/1.png");
        ImageView view = new ImageView(image);
        view.fitWidthProperty().bind(widthProperty());
        view.fitHeightProperty().bind(heightProperty());


        /*
            message1 objects holds a flashing text in it
         */
        Message message1 =new Message(scale,20.0,Pos.CENTER);
        message1.addFadeText("PRESS ENTER TO START");
        message1.addFadeText("PRESS ESC TO EXIT");
        getChildren().addAll(view,message1);
        message1.layoutYProperty().bind(heightProperty().divide(1.7));
        message1.layoutXProperty().bind(widthProperty().divide(8.8));


        /*
            These lines create the player for playing the title music.
         */
        Media media=new Media(new File("assets/effects/Title.mp3").toURI().toString());
        this.player=new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(volume);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();




        /*
            These lines create the title scene and add the key pressed events for continuity of the app.
         */
        titleScene =new Scene(this,scale*image.getWidth() ,scale*image.getHeight() );

        titleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    /*
                        Create the SelectionScreen
                     */
                    SelectionScreen selection = new SelectionScreen(scale,this,stage,volume);
                    stage.setScene(selection.getSelectionScene());
                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;
                default:
                    break;
            }





    });

}

    /**
     * Getter for titleScene field in TitleScreen object.
     * @return Scene of the instance.
     */
    public Scene getTitleScene() {
        return titleScene;
    }

    /**
     * Getter of the title music MediaPlayer.
     * @return MediaPlayer of the title Music.
     */
    public MediaPlayer getTitleMusic() {
        return player;
    }
}





