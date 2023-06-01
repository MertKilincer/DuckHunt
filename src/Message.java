/**
 * Necessary java fx imports.
 */

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


/**
 * Message class to give multiple lines of message as a one object in app.
 */
public class Message extends VBox {

    /**
     * Message is a vbox and its components is scaled by the scale.
     */
    private Double scale;

    /**
     * size of the text in the boxes.
     */
    private Double size;

    /**
     * Message class constructor that takes params of the field.
     * @param scale scale of the components.
     * @param textSize text-size of the pane.
     * @param position position of the box in the pane.
     */
    public Message(Double scale,Double textSize,Pos position){
        super();
        setAlignment(position);
        this.scale=scale;
        size=textSize;
        setSpacing(scale*(-textSize/3));

    }

    /**
     * Add normal text as a line to message.
     * @param line line as a string parameter to add to the box.
     */
    public void addText(String line) {

        Label text = new Label(line);
        text.setFont(Font.font("Calibri", FontWeight.BOLD,size*scale));
        text.setTextFill(Color.ORANGE);
        text.setAlignment(Pos.CENTER);
        getChildren().add(text);

    }

    /**
     * addFadeText method adds strings to the message as a flashing text line.
     * @param line string that the wanted to displayed at the scene.
     */
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

    /**
     * fade method is created for flashing texts.
     * @return FadeTransition for flashing texts
     * @see FadeTransition
     */
    public FadeTransition fade(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(800));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0.1);
        fade.setToValue(10);

        return fade;
    }
}
