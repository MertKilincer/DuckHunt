/**
 * Necessary java fx classes to import fully function this class.
 */
import javafx.scene.input.KeyCode;

/**
 * Level 6 of the duck hunt game is created with these class.
 */
public class Round6 extends Round{
    public Round6(GameElements elements) {
        /**
        Duck adding to the round
         */
        super(6, elements);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'D','R');
        Duck duck2=new DiagonalDuck("duck_black",scale,'U','L');
        Duck duck3=new DiagonalDuck("duck_red",scale,'D','L');
        duck1.setLayouts(0,getScene().getHeight()/2);
        duck2.setLayouts(getScene().getWidth()-duck2.getDuckWidth(),getScene().getHeight()/3);
        duck3.setLayouts(getScene().getWidth()-duck2.getDuckWidth(),getScene().getHeight()/4);
        duckAdder(duck1);
        duckAdder(duck2);
        duckAdder(duck3);


        /**
         * Round Listener that handle the level ending.
         */
        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());//gameOver key event call.
                    break;
                case COMPLETED:
                    /**
                    Game completed event handlers.
                     */
                    sounds.getGameCompleted().play();
                    getScene().setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ENTER)){
                            sounds.getGameCompleted().stop();
                            Round1 round1 =new Round1(elements);
                            elements.getStage().setScene(round1.getScene());
                        }else if (event5.getCode().equals(KeyCode.ESCAPE)){
                            sounds.getGameCompleted().stop();
                            TitleScreen title = new TitleScreen(scale,elements.getStage(),elements.getVolume());
                            elements.getStage().setScene(title.getTitleScene());
                        }
                    });
                    break;
            }
        });
    }
}
