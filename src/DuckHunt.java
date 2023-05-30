import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;




public class DuckHunt extends Application {

    public double SCALE= 3.0;

    public double VOLUME =0.5;

    @Override
    public void start(Stage primaryStage){

       TitleScreen title = new TitleScreen(SCALE,primaryStage,VOLUME);
       primaryStage.setScene(title.getTitleScene());




        primaryStage.setTitle("HUBBM DUCKHUNT"); // Set the stage title
        primaryStage.getIcons().add(new Image("/assets/favicon/1.png"));
        primaryStage.setResizable(false);
        primaryStage.show();






    }


    public static void main(String[] args){
        launch(args);
    }
}