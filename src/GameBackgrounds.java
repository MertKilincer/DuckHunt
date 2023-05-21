import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.LinkedList;

public class GameBackgrounds{

    public LinkedList<Background> views = new LinkedList<>();
    public Pane pane;
    public GameBackgrounds(Pane pane){
        this.pane=pane;
        for(int i=1;i<7;i++){
            views.add(createBackground(pane,i));
        }


    }



    public Background createBackground(Pane pane, int num){
        Image image =new Image(String.format("/assets/background/%s.png", num));
        BackgroundImage BackgroundValue = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(
                        pane.getWidth(),pane.getHeight(),false,false,false,true)
        )s;
        return new Background(BackgroundValue);
    }
}

