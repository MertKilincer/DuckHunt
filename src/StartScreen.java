
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;

import javafx.geometry.Pos;

import javafx.scene.Cursor;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;


public class StartScreen  extends Pane {



    public Scene titleScene;


    public StartScreen(double scale, Stage stage){
        setCursor(Cursor.NONE);

        Image image =new Image("assets/welcome/1.png");
        ImageView view = new ImageView(image);
        view.fitWidthProperty().bind(widthProperty());
        view.fitHeightProperty().bind(heightProperty());



        Message message1 =new Message(scale,23.0,Pos.CENTER);
        message1.addText("PRESS ENTER TO START");
        message1.addText("PRESS ESC TO EXIT");
        fadeText(message1);


        message1.setAlignment(Pos.CENTER);
        getChildren().addAll(view,message1);
        message1.layoutYProperty().bind(heightProperty().divide(1.55));
        message1.layoutXProperty().bind(widthProperty().divide(7.5));

        titleScene =new Scene(this,scale*300 ,scale*300 );



        };






    public static void fadeText(Node node) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(node);
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0.1);
        fade.setToValue(10);
        fade.play();
    }
}





