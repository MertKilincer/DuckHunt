import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class SelectionScene {
    public Scene selectionScene;
    public GameElements backgrounds;
    public int IndexBack;

    public int IndexCross;

    public StackPane pane;

    public StackPane cross;

    public SelectionScene(double scale) {
        pane = new StackPane();
        cross =new StackPane();

        backgrounds=new GameElements(pane,scale);
        IndexBack =0;
        IndexCross= 0;

        pane.setBackground(backgrounds.views.get(0));
        cross.setBackground(backgrounds.Crossair.get(0));

        this.selectionScene=new Scene(pane,scale*300,scale*300);

        Message text =new Message(scale,10.0,Pos.CENTER);
        text.addText("");
        text.addText("USE ARROW KEYS TO NAVIGATE");
        text.addText("PRESS ENTER TO START");
        text.addText( "PRESS ESC TO EXIT");
        text.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().add(text);





        //CustomCursor customCursor= new CustomCursor(view);
        //selectionScene.setOnMouseMoved(event -> customCursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        pane.getChildren().add(cross);
        //pane.getChildren().add(customCursor);
        

        cross.setAlignment(Pos.CENTER);



        //selectionScene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> customCursor.setVisible(false));
        //selectionScene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> customCursor.setVisible(true));



        selectionScene.setCursor(Cursor.NONE);











    }


    public void rightArrow(){
        IndexBack = (IndexBack>=5) ? 5:IndexBack+1;
        pane.setBackground(backgrounds.views.get(IndexBack));
    }
    public void leftArrow(){
        IndexBack = (IndexBack >0) ? IndexBack -1:0;
        pane.setBackground(backgrounds.views.get(IndexBack));
    }

    public void upArrow(){
        IndexCross = (IndexCross>=6) ? 6:IndexCross+1;
        cross.setBackground(backgrounds.Crossair.get(IndexCross));
    }

    public void downArrow(){
        IndexCross = (IndexCross>0) ? IndexCross-1:0;
        cross.setBackground(backgrounds.Crossair.get(IndexCross));
    }






}
