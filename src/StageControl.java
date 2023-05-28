import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class StageControl {

    public StartScreen title;

    public SelectionScene selection;

    public GameElements elements;

    public Game game;

    public Stage stage;


    public Round1 round1;

    public Round2 round2;




    public StageControl(Stage stage ,double scale){
        this.stage = stage;
        title =new StartScreen(scale,stage);
        elements=new GameElements(title,scale);
        selection =new SelectionScene(scale,elements.views,elements.Crossair);




        stage.setScene(title.titleScene);




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
                    selection.reset();
                    stage.setScene(title.titleScene);

                    break;
                case ENTER:

                    CustomCrosshair cursor =selection.getCurrentCross();
                    Image foreground =selection.backgrounds.get(selection.IndexBack).getForeground();
                    Background background =selection.backgrounds.get(selection.IndexBack).getBackground();
                    game=new Game(scale,cursor,foreground,background);
                    round1=new Round1(1,game,round2,stage);
                    round2=new Round2(2,game);
                    stage.setScene(round1.scene);



                    break;
                default:
                    break;
            }
        });




    }
}
