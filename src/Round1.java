import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.util.LinkedList;

public class  Round1 extends Round{




    public Round1(CustomCrosshair cursor, Image foreground ,Background background,double scale,StageControl control,
                  Round2 round2){
        super(1,cursor,foreground,background,scale);
        Duck duck1=new LinearDuck("duck_black",scale,'R');
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getWidth()/2);
        group.getChildren().add(duck1.animationView);
        duckList.add(duck1);
        ammoCount.set(duckList.size()*3);





    }
}

