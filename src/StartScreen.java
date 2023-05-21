
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


import java.io.File;


public class StartScreen  extends Pane {

    String media =new File("assets/effects/Title.mp3").toURI().toString();
    Media med=new Media(media);

    MediaPlayer player =new MediaPlayer(med);


    public StartScreen(double scale){


        Image image =new Image("assets/welcome/1.png");
        ImageView view = new ImageView(image);
        view.fitWidthProperty().bind(widthProperty());
        view.fitHeightProperty().bind(heightProperty());


        Message message1 =new Message(scale,17.5);
        message1.addText("PRESS ENTER TO START");
        message1.addText("PRESS ESC TO EXIT");
        fadeText(message1);



        getChildren().addAll(view,message1);


        message1.layoutYProperty().bind(heightProperty().divide(1.65));
        message1.layoutXProperty().bind(widthProperty().divide(4.85));







    }
    public void mediaPlay(){
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();
    }

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





