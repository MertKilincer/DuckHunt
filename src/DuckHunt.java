import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class DuckHunt extends Application {

    public Double scale= 2.0;

    @Override
    public void start(Stage primaryStage){


        StartScreen s = new StartScreen(scale);
        Scene TitleScene = new Scene(s,scale*300 ,scale*300 );
        SelectionScene selection =new SelectionScene(scale,primaryStage,TitleScene);




        Bc aaaaabee = new Bc(scale);


        primaryStage.setTitle("HUBBM DUCKHUNT"); // Set the stage title
        primaryStage.getIcons().add(new Image("/assets/favicon/1.png"));
        primaryStage.setScene(TitleScene);
        primaryStage.setResizable(false);
        s.mediaPlay();


        primaryStage.show();


        TitleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    primaryStage.setScene(selection.selectionScene);

                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;

                case A:

                    primaryStage.setScene(aaaaabee.scene1);
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