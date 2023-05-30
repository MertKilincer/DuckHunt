/**
 * Necessary java fx classes to import fully function this class.
 */
import javafx.scene.input.KeyCode;

/**
 * Level 6 of the duck hunt game is created with these class.
 */
public class Round6 extends Round{
    public Round6(GameElements elements) {
        super(6, elements);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'D','R');
        Duck duck2=new DiagonalDuck("duck_black",scale,'U','L');
        Duck duck3=new DiagonalDuck("duck_red",scale,'D','L');
        duck2.animationView.setLayoutX(scene.getWidth()-duck2.animationView.getFitWidth());
        duck1.animationView.setLayoutX(0);
        duck3.animationView.setLayoutX(scene.getWidth()-duck2.animationView.getFitWidth());
        duck1.animationView.setLayoutY(scene.getHeight()/2);
        duck2.animationView.setLayoutY(scene.getHeight()/3);
        duck3.animationView.setLayoutY(scene.getHeight()/4);
        super.group.getChildren().add(duck1.animationView);
        super.group.getChildren().add(duck2.animationView);
        super.group.getChildren().add(duck3.animationView);
        super.duckList.add(duck1);
        super.duckList.add(duck2);
        super.duckList.add(duck3);

        ammoCount.set(super.duckList.size()*3);



        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    gameOver(elements.getStage());
                    break;
                case COMPLETED:
                    sounds.getGameCompleted().play();
                    scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ENTER)){
                            sounds.getGameCompleted().stop();
                            Round1 round1 =new Round1(elements);
                            elements.getStage().setScene(round1.scene);
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
