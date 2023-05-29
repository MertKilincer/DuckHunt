import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Round4 extends Round{
    public Round4(CustomCrosshair cursor, Image foreground , Background background, double scale, Stage stage){
        super(4,cursor,foreground,background,scale);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','L');
        Duck duck2=new DiagonalDuck("duck_red",scale,'U','R');
        duck2.animationView.setLayoutX(0);
        duck1.animationView.setLayoutX(scene.getWidth()-duck1.animationView.getFitWidth());
        duck1.animationView.setLayoutY(scene.getHeight()/2);
        duck2.animationView.setLayoutY(scene.getHeight()/3);
        super.group.getChildren().add(duck1.animationView);
        super.group.getChildren().add(duck2.animationView);
        super.duckList.add(duck1);
        super.duckList.add(duck2);
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
                        } else if (event5.getCode().equals(KeyCode.ESCAPE)) {
                            sounds.getGameOver().stop();
                            StartScreen title =new StartScreen(scale,stage);
                            stage.setScene(title.titleScene);
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    sounds.getLevelCompleted().play();
                    scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            sounds.getLevelCompleted().stop();
                            Round5 round5 =new Round5(cursor,foreground,background,scale,stage);
                            stage.setScene(round5.scene);
                        }
                    });
                    break;
            }
        });



    }
}

