import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;

import java.util.LinkedList;


public class Round2 extends Round{
    public Round2(CustomCrosshair cursor, Image foreground ,Background background,double scale,StageControl control,
               Round3 round3){
        super(2,cursor,foreground,background,scale);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','R');
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getWidth()/2);
        super.group.getChildren().add(duck1.animationView);
        super.duckList.add(duck1);

        ammoCount.set(super.duckList.size()*3);

    }
}
