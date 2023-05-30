import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class Round5 extends Round{
    public Round5(GameElements elements) {
        super(5, elements);
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new LinearDuck("duck_black",scale,'L');
        Duck duck3=new DiagonalDuck("duck_red",scale,'U','R');
        duck1.setLayouts(0,scene.getHeight()/2);
        duck2.setLayouts(scene.getWidth()-duck2.animationView.getFitWidth(),scene.getHeight()/3);
        duck3.setLayouts(0,scene.getHeight()/2.5);
        duckAdder(duck1);
        duckAdder(duck2);
        duckAdder(duck3);


        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    gameNextLevel(6);
                    break;
            }
        });
    }
}


