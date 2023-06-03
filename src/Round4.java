
/**
 * Level 4 of the duck hunt game is created with these class.
 */
public class Round4 extends Round{
    public Round4(GameElements elements){
        super(4,elements);
        /**
         Ducks adding to the round
         */
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','L');
        Duck duck2=new DiagonalDuck("duck_red",scale,'U','R');
        duck2.setLayouts(0,getScene().getHeight()/4);
        duck1.setLayouts(getScene().getWidth()-duck1.getDuckWidth(),getScene().getHeight()/2-duck1.getDuckHeight()/2);

        duckAdder(duck1);
        duckAdder(duck2);




        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    //In case of GAME_OVER call gameOver() function
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    //In case of NEXT_LEVEL call gameNextLevel function with 5 as a level num
                    gameNextLevel(5);
                    break;
            }
        });



    }
}

