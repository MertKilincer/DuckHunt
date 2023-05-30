import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


public class Round3 extends Round{

    public Round3(GameElements elements){
        super(3,elements);
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new LinearDuck("duck_black",scale,'L');
        duck1.setLayouts(scene.getWidth()-duck2.animationView.getFitWidth(),scene.getHeight()/3.5);
        duck2.setLayouts(0,scene.getHeight()/4);
        duckAdder(duck1);
        duckAdder(duck2);


        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    gameNextLevel(4);
                    break;
            }
        });
    }
}
