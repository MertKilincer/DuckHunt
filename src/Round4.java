import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Round4 extends Round{
    public Round4(GameElements elements){
        super(4,elements);
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
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    gameNextLevel(5);
                    break;
            }
        });



    }
}

