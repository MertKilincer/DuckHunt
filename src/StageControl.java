import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class StageControl {

    public StartScreen title;

    public SelectionScene selection;

    public Stage stage;


    public StageControl(Stage stage ,double scale){
        this.stage = stage;
        title =new StartScreen(scale,stage);
        selection =new SelectionScene(scale);
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

                case A:
                    Bc aaaaabee = new Bc(scale);
                    stage.setScene(aaaaabee.scene1);
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
                    break;
                default:
                    break;
            }
        });
}
}
