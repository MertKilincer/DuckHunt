import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Message extends VBox {


    public Double scale;

    public Double size;

    public Message(Double scale,Double textSize){
        super();
        this.scale=scale;
        size=textSize;

    }
    public void addText(String line) {
        Text text = new Text(line);
        text.setFont(Font.font("Calibri", FontWeight.BOLD,size*scale));
        text.setFill(Color.ORANGE);
        getChildren().add(text);
    }
}
