
/**
 * Level 3 of the duck hunt game is created with these class.
 */
public class Round3 extends Round{

    public Round3(GameElements elements){
        super(3,elements);
        /**
         Ducks adding to the round
         */
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new LinearDuck("duck_black",scale,'L');
        duck1.setLayouts(getScene().getWidth()-duck2.getDuckWidth(),getScene().getHeight()/3.5);
        duck2.setLayouts(0,getScene().getHeight()/4);
        duckAdder(duck1);
        duckAdder(duck2);


        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    //In case of GAME_OVER call gameOver() function
                    gameOver(elements.getStage());
                    break;
                case NEXT_LEVEL:
                    //In case of NEXT_LEVEL call gameNextLevel function with 4 as a level num
                    gameNextLevel(4);
                    break;
            }
        });
    }
}
