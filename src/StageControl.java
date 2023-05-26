import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class StageControl {

    public StartScreen title;

    public SelectionScene selection;

    public GameElements elements;

    public Stage stage;


    public StageControl(Stage stage ,double scale){
        this.stage = stage;
        title =new StartScreen(scale,stage);
        elements=new GameElements(title,scale);
        selection =new SelectionScene(scale,elements.views,elements.Crossair);

        stage.setScene(title.titleScene);


        Player player = new Player("assets/effects/Title.mp3");
        player.InfinitePlay();

        title.titleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    stage.setScene(selection.selectionScene);
                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;
                default:
                    break;
            }
    });
        selection.selectionScene.setOnKeyPressed(event -> {

            switch (event.getCode()){

                case RIGHT:
                    selection.rightArrow();
                    break;
                case LEFT:
                    selection.leftArrow();
                    break;
                case UP:
                    selection.upArrow();
                    break;
                case DOWN:
                    selection.downArrow();
                    break;
                case ESCAPE:
                    stage.setScene(title.titleScene);
                    player.InfinitePlay();
                    break;
                case ENTER:
                    player.pause();
                    CustomCrosshair cursor =selection.getCurrentCross();
                    Bc aaaaabee = new Bc(scale,cursor);
                    stage.setScene(aaaaabee.scene1);

                    break;
                default:
                    break;
            }
        });
}
}
