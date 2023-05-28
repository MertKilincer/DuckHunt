import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class  Round1 extends Round{


    public Round1(int roundNum, Game game, Round2 round2, Stage stage) {
        super(roundNum, game);

        Duck duck1=new LinearDuck("duck_black",scale,'R');
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getWidth()/2);
        group.getChildren().add(duck1.animationView);
        duckList.add(duck1);

        ammoCount.set(duckList.size()*3);

        gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {



                case GAME_OVER:
                    scene.setOnKeyPressed(event1 -> {
                        if (event1.getCode().equals(KeyCode.ESCAPE)){
                            javafx.application.Platform.exit();
                        }
                    });

                    break;
                case NEXT_LEVEL:
                    scene.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round2.scene);
                        }
                    });

                    break;
            }
        });






    }
}

