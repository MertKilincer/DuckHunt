public class Round2 extends Round{
    public Round2(int roundNum, Game game) {
        super(roundNum, game);
        Duck duck1=new DiagonalDuck("duck_blue",scale,'U','R');
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(scene.getWidth()/2);
        group.getChildren().add(duck1.animationView);
        duckList.add(duck1);

        ammoCount.set(duckList.size()*3);

    }
}
