
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SelectionScene {

    public Scene selectionScene;
    public GameBackgrounds backgrounds;
    public int IndexBack;





    public SelectionScene(double scale, Stage stage,Scene Title) {
        StackPane pane = new StackPane();


        backgrounds=new GameBackgrounds(pane);
        IndexBack =0;

        pane.setBackground(backgrounds.views.get(0));
        this.selectionScene=new Scene(pane,scale*300,scale*300);

        Message text =new Message(scale,10.0);
        text.addText("USE ARROW KEYS TO NAVIGATE");
        text.addText("PRESS ENTER TO START");
        text.addText( "PRESS ESC TO EXIT");
        text.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().add(text);









        selectionScene.setOnKeyPressed(event -> {

            switch (event.getCode()){

                case RIGHT:
                    IndexBack = (IndexBack>=5) ? 5:IndexBack+1;
                    pane.setBackground(backgrounds.views.get(IndexBack));
                    break;
                case LEFT:
                    IndexBack = (IndexBack >0) ? IndexBack -1:0;
                    pane.setBackground(backgrounds.views.get(IndexBack));
                    break;
                case ESCAPE:
                    stage.setScene(Title);
                    break;
                default:
                    break;
            }
        }


        );
    }






}
