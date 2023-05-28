import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.util.LinkedList;

public class GameElements {

    public LinkedList<CustomBackground> views = new LinkedList<>();

    public LinkedList<CustomCrosshair> Crossair = new LinkedList<>();


    public GameElements(Pane pane, double scale){
        createBackgrounds(pane);
        createCrossair(scale);

    }


    public void createBackgrounds(Pane pane){
        for (int i=1;i<7;i++){
            views.add(new CustomBackground(i,pane));
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



}

