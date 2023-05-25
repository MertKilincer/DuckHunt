import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
}
