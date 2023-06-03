
/**
 * Level 1 of the duck hunt game is created with these class.
 */
public class  Round1 extends Round{



    public Round1(GameElements elements){
        super(1,elements);
        /**
         Duck adding to the round
         */
        Duck duck1=new LinearDuck("duck_black",scale,'R');
        duck1.setLayouts(0,getScene().getHeight()/2);
        duckAdder(duck1);

        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    //In case of GAME_OVER call gameOver() function
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    //In case of NEXT_LEVEL call gameNextLevel function with 2 as a level num
                    gameNextLevel(2);
                    break;

            }
        });


    }
}

