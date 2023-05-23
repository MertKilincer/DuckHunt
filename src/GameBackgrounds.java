import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.util.LinkedList;

public class GameBackgrounds{

    public LinkedList<Background> views = new LinkedList<>();

    public LinkedList<Image> Crossair = new LinkedList<>();

    public Pane pane;

    public Double scale;
    public GameBackgrounds(Pane pane,double scale){
        this.pane=pane;
        this.scale =scale;
        for(int i=1;i<7;i++){
            views.add(createBackground(pane,i));
        }
        createCrossair();



    }



    public Background createBackground(Pane pane, int num){
        Image image =new Image(String.format("/assets/background/%s.png", num));
        BackgroundImage BackgroundValue = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(
                        pane.getWidth(),pane.getHeight(),false,false,false,true)
        );
        return new Background(BackgroundValue);
    }

    public void createCrossair(){
        String directoryPath ="assets/crosshair";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        for (File i : files) {
            Image image = new Image(i.getPath(),32*(scale/3),32*(scale/3),true,true);

            //ImageView view = new ImageView(image);

            Crossair.add(image);
        }
    }



}

