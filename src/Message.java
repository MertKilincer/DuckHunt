import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import org.omg.CORBA.PUBLIC_MEMBER;


public class Message extends VBox {


    public Double scale;


    public Double size;

    public Message(Double scale,Double textSize,Pos position){
        super();
        setAlignment(position);
        this.scale=scale;
        size=textSize;
        setSpacing(scale*(-textSize/3));

    }
    public void addText(String line) {

        Label text = new Label(line);
        text.setFont(Font.font("Calibri", FontWeight.BOLD,size*scale));
        text.setTextFill(Color.ORANGE);
        text.setAlignment(Pos.CENTER);
        getChildren().add(text);

    }
    public void fadeAll(){
        FadeTransition fade=fade();
        fade.setNode(this);
        fade.play();
    }
    public void addFadeText(String line){
        FadeTransition fade=fade();
        Label text = new Label(line);
        text.setFont(Font.font("Calibri", FontWeight.BOLD,size*scale));
        text.setTextFill(Color.ORANGE);
        text.setAlignment(Pos.CENTER);
        getChildren().add(text);
        fade.setNode(text);
        fade.play();
    }


    public FadeTransition fade(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0.1);
        fade.setToValue(10);

        return fade;
    }
}
