

/**
 * Level 2 of the duck hunt game is created with these class.
 */

public class Round2 extends Round{
    public Round2(GameElements elements){
        /**
         Duck adding to the round
         */
        super(2,elements);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','R');
        duck1.setLayouts(0,getScene().getHeight()/2-duck1.getDuckHeight()/2);
        duckAdder(duck1);

        ammoCount.set(super.duckList.size()*3);

        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    //In case of GAME_OVER call gameOver() function
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    //In case of NEXT_LEVEL call gameNextLevel function with 3 as a level num
                    gameNextLevel(3);
                    break;
            }
        });

    }
}
