import javafx.scene.image.Image;
import javafx.scene.layout.Background;

public class Round5 extends Round{
    public Round5(CustomCrosshair cursor, Image foreground, Background background, double scale,StageControl control,
                  Round6 round6) {
        super(5, cursor, foreground, background, scale);
        Duck duck1=new LinearDuck("duck_blue",scale,'R');
        Duck duck2=new LinearDuck("duck_black",scale,'L');
        duck2.animationView.setLayoutX(scene.getWidth()-duck2.animationView.getFitWidth());
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


