

import javafx.geometry.Pos;

import javafx.scene.Cursor;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class StartScreen  extends Pane {

    MediaPlayer player;

    public Scene titleScene;


    public StartScreen(double scale,Stage stage){
        setCursor(Cursor.NONE);

        Image image =new Image("assets/welcome/1.png");
        ImageView view = new ImageView(image);
        view.fitWidthProperty().bind(widthProperty());
        view.fitHeightProperty().bind(heightProperty());



        Message message1 =new Message(scale,20.0,Pos.CENTER);
        message1.addFadeText("PRESS ENTER TO START");
        message1.addFadeText("PRESS ESC TO EXIT");



        Media media=new Media(new File("assets/effects/Title.mp3").toURI().toString());
        this.player=new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();



        getChildren().addAll(view,message1);
        message1.layoutYProperty().bind(heightProperty().divide(1.7));
        message1.layoutXProperty().bind(widthProperty().divide(8.8));

        titleScene =new Scene(this,scale*image.getWidth() ,scale*image.getHeight() );

        titleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    SelectionScene selection = new SelectionScene(scale,this,stage);
                    stage.setScene(selection.selectionScene);
                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;
                default:
                    break;
            }





    });







}
}





