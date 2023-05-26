import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.util.LinkedList;

public class GameElements {

    public LinkedList<CustomBackground> views = new LinkedList<>();

    public LinkedList<CustomCrosshair> Crossair = new LinkedList<>();


    public GameElements(Pane pane, double scale){
        createBackground(pane);
        createCrossair(scale);

    }


    public void createBackground(Pane pane) {
        String directoryPath = "assets/background";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        for (File i : files) {
            views.add(new CustomBackground(i.getPath(),pane));
        }

    }

    public void createCrossair(Double scale) {
        String directoryPath = "assets/crosshair";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        for (File i : files) {
            Crossair.add(new CustomCrosshair(i.getPath(),scale));


        }
    }
    public void createMusics(){
        
    }


}

