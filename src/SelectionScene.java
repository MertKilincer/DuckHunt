import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.LinkedList;

public class SelectionScene {
    public Scene selectionScene;

    public LinkedList<CustomBackground> backgrounds;

    public LinkedList<CustomCrosshair> crosshairs;
    public int IndexBack;

    public int IndexCross;

    public StackPane pane;

    public StackPane cross;

    public SelectionScene(double scale, LinkedList<CustomBackground> backgrounds ,LinkedList<CustomCrosshair> crosshairs) {
        pane = new StackPane();
        cross =new StackPane();
        this.selectionScene=new Scene(pane,scale*300,scale*300);
        this.backgrounds=backgrounds;
        this.crosshairs=crosshairs;

        IndexBack =0;
        IndexCross= 0;

        pane.setBackground(backgrounds.get(0).getBackground());
        cross.setBackground(crosshairs.get(0).getBack());



        Message text =new Message(scale,10.0,Pos.CENTER);
        text.addText("");
        text.addText("USE ARROW KEYS TO NAVIGATE");
        text.addText("PRESS ENTER TO START");
        text.addText( "PRESS ESC TO EXIT");
        text.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().add(text);


        pane.getChildren().add(cross);
        cross.setAlignment(Pos.CENTER);
        cross.toFront();
        selectionScene.setCursor(Cursor.NONE);


        //















    }


    public void rightArrow(){
        IndexBack = (IndexBack>=5) ? 5:IndexBack+1;
        pane.setBackground(backgrounds.get(IndexBack).getBackground());
    }
    public void leftArrow(){
        IndexBack = (IndexBack >0) ? IndexBack -1:0;
        pane.setBackground(backgrounds.get(IndexBack).getBackground());
    }

    public void upArrow(){
        IndexCross = (IndexCross>=6) ? 6:IndexCross+1;
        cross.setBackground(crosshairs.get(IndexCross).getBack());
    }

    public void downArrow(){
        IndexCross = (IndexCross>0) ? IndexCross-1:0;
        cross.setBackground(crosshairs.get(IndexCross).getBack());
    }


    public CustomCrosshair getCurrentCross(){
         return  crosshairs.get(IndexCross);
    }






}
