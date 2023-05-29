import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class Round6 extends Round{
    public Round6(CustomCrosshair cursor, Image foreground, Background background, double scale, Stage stage) {
        super(6, cursor, foreground, background, scale);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'D','R');
        Duck duck2=new DiagonalDuck("duck_black",scale,'U','L');
        Duck duck3=new DiagonalDuck("duck_red",scale,'D','L');
        duck2.animationView.setLayoutX(scene.getWidth()-duck2.animationView.getFitWidth());
        duck1.animationView.setLayoutX(0);
        duck3.animationView.setLayoutX(scene.getWidth()-duck2.animationView.getFitWidth());
        duck1.animationView.setLayoutY(scene.getHeight()/2);
        duck2.animationView.setLayoutY(scene.getHeight()/3);
        duck3.animationView.setLayoutY(scene.getHeight()/4);
        super.group.getChildren().add(duck1.animationView);
        super.group.getChildren().add(duck2.animationView);
        super.group.getChildren().add(duck3.animationView);
        super.duckList.add(duck1);
        super.duckList.add(duck2);
        super.duckList.add(duck3);

        ammoCount.set(super.duckList.size()*3);



        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    sounds.getGameOver().play();
                    scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ENTER)){
                            sounds.getGameOver().stop();
                            Round1 round1 =new Round1(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,stage);
                            stage.setScene(round1.scene);
                        }else if (event5.getCode().equals(KeyCode.ESCAPE)){
                            sounds.getGameOver().stop();
                            StartScreen title =new StartScreen(scale,stage);
                            stage.setScene(title.titleScene);
                        }
                    });
                    break;
                case COMPLETED:
                    sounds.getGameCompleted().play();
                    scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ENTER)){
                            sounds.getGameCompleted().stop();
                            Round1 round1 =new Round1(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,stage);
                            stage.setScene(round1.scene);
                        }else if (event5.getCode().equals(KeyCode.ESCAPE)){
                            sounds.getGameCompleted().stop();
                            StartScreen title = new StartScreen(scale,stage);
                            stage.setScene(title.titleScene);
                        }
                    });
                    break;
            }
        });
    }
}
