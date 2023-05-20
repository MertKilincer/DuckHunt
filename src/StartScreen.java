
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
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


public class StartScreen extends Pane {

    String media =new File("assets/effects/Title.mp3").toURI().toString();
    Media med=new Media(media);

    MediaPlayer player =new MediaPlayer(med);


    public StartScreen(double scale){
        super();
        Image image =new Image("assets/welcome/1.png");
//        ImageView view = new ImageView(image);
//        view.fitHeightProperty().bind(this.heightProperty());
//        view.fitWidthProperty().bind(this.widthProperty());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(widthProperty().get(),heightProperty().get(), false, false, true, true)
        );


        Background background = new Background(backgroundImage);
        this.setBackground(background);

        Label message = new Label("PRESS ENTER TO START\n    PRESS ESC TO EXIT");
        message.setLineSpacing(-10*scale);
        message.setFont(Font.font("Calibri", FontWeight.BOLD,17.5*scale));
        message.setTextFill(Color.ORANGE);
        message.layoutYProperty().bind(heightProperty().divide(1.65));
        message.layoutXProperty().bind(widthProperty().divide(4.90));


        FadeTransition fade = new FadeTransition();
        fade.setNode(message);
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        //getChildren().add(view);
        getChildren().add(message);

    }
    public void mediaPlay(){
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();
    }

    }





