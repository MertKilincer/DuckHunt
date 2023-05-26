import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;




public class DuckHunt extends Application {

    public Double scale= 2.0;

    @Override
    public void start(Stage primaryStage){


        StageControl control = new StageControl(primaryStage,scale);




        primaryStage.setTitle("HUBBM DUCKHUNT"); // Set the stage title
        primaryStage.getIcons().add(new Image("/assets/favicon/1.png"));
        primaryStage.setResizable(false);
        primaryStage.show();






    }


    public static void main(String[] args){
        launch(args);
    }
}