//Necessary imports
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class that application start and main function.
 */
public class DuckHunt extends Application {
    /**
     * Scale of the game
     */
    public double SCALE= 3.00;
    /**
     * Volume of the game
     */
    public double VOLUME =0.25;

    /**
     * start of the game.
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage){

       TitleScreen title = new TitleScreen(SCALE,primaryStage,VOLUME);
       primaryStage.setScene(title.getTitleScene());
        primaryStage.setTitle("HUBBM Duck Hunt"); // Set the stage title
        primaryStage.getIcons().add(new Image("/assets/favicon/1.png"));
        primaryStage.setResizable(false);
        primaryStage.show();



    }

    /**
     * Driver for the application.
     * @param args commnand line argumnents.
     */
    public static void main(String[] args){
        launch(args);
    }
}