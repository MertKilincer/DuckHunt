import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;

import javafx.stage.Stage;

import java.util.LinkedList;

public class StageControl {

    public StartScreen title;

    public Stage stage;

    public double scale;



    public StageControl(Stage stage ,double scale){
        this.stage = stage;
        this.scale = scale;
        title =new StartScreen(scale,stage);

        stage.setScene(title.titleScene);












    }



}


