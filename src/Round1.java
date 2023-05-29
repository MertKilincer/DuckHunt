import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.LinkedList;

public class  Round1 extends Round{




    public Round1(CustomCrosshair cursor, Image foreground ,Background background,double scale,Stage stage){
        super(1,new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale);
        Duck duck1=new LinearDuck("duck_black",scale,'R');
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getHeight()/2);
        group.getChildren().add(duck1.animationView);
        duckList.add(duck1);
        ammoCount.set(duckList.size()*3);

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
                            Round2 round2 =new Round2(cursor,foreground,background,scale,stage);
                            stage.setScene(round2.scene);
                        }
                    });
                    break;

            }
        });


    }
}

