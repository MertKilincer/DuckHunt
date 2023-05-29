import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;

import java.util.LinkedList;

public class Round4 extends Round{
    public Round4(CustomCrosshair cursor, Image foreground ,Background background,double scale,StageControl control,
                  Round5 round5){
        super(4,cursor,foreground,background,scale);
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new DiagonalDuck("duck_red",scale,'U','R');
        duck2.animationView.setLayoutX(0);
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getWidth()/2);
        duck2.animationView.setLayoutY(scene.getWidth()/3);
        super.group.getChildren().add(duck1.animationView);
        super.group.getChildren().add(duck2.animationView);
        super.duckList.add(duck1);
        super.duckList.add(duck2);
        ammoCount.set(super.duckList.size()*3);



    }
}

