import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;




public class Round2 extends Round{
    public Round2(GameElements elements){
        super(2,elements);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','R');
        duck1.setLayouts(0,scene.getHeight()/2);
        duckAdder(duck1);

        ammoCount.set(super.duckList.size()*3);

        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    gameNextLevel(3);
                    break;
            }
        });

    }
}
