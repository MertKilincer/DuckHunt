import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DuckHunt extends Application {

    public Double scale= 2.0;

    @Override
    public void start(Stage primaryStage){


        StartScreen s = new StartScreen(scale);

        Scene TitleScene = new Scene(s,scale*300 ,scale*300 );


        primaryStage.setTitle("HUBBM DUCKHUNT"); // Set the stage title
        primaryStage.getIcons().add(new Image("/assets/favicon/1.png"));
        primaryStage.setScene(TitleScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        s.mediaPlay();

        TitleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    //primaryStage.setScene(SelectionScene);
                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;
                default:
                    break;
            }

        });
    }


    public static void main(String[] args){
        launch(args);
    }
}