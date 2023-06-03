
/**
 * Level 5 of the duck hunt game is created with these class.
 */
public class Round5 extends Round{
    public Round5(GameElements elements) {
        super(5, elements);
        /**
         Ducks adding to the round
         */
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new LinearDuck("duck_black",scale,'L');
        Duck duck3=new DiagonalDuck("duck_red",scale,'U','R');
        duck1.setLayouts(0,getScene().getHeight()/2);
        duck2.setLayouts(getScene().getWidth()-duck2.getDuckWidth(),getScene().getHeight()/3);
        duck3.setLayouts(0,getScene().getHeight()/2.5);
        duckAdder(duck1);
        duckAdder(duck2);
        duckAdder(duck3);

        /**
         * Round Listener that handle the level ending.
         */
        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    //In case of GAME_OVER call gameOver() function
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    //In case of NEXT_LEVEL call gameNextLevel function with 6 as a level num
                    gameNextLevel(6);
                    break;
            }
        });
    }
}


