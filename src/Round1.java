
import javafx.scene.input.KeyCode;


public class  Round1 extends Round{






    public Round1(GameElements elements){

        super(1,elements);
        Duck duck1=new LinearDuck("duck_black",scale,'R');
        duck1.setLayouts(0,scene.getHeight()/2);
        duckAdder(duck1);

        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    gameNextLevel(2);
                    break;

            }
        });


    }
}

