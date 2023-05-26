import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;

public class Duck {

    public ImageView animationView=new ImageView();

    public Scene scene;

    public ParallelTransition right;


    public char direction;
    private  final double IMAGE_WIDTH = animationView.getFitWidth();
    private final double IMAGE_HEIGHT =animationView.getFitHeight();
    private  final double SCENE_WIDTH;
    private  final double SCENE_HEIGHT;
    private static final double VELOCITY_X = 5;
    private static final double VELOCITY_Y = 5;

    private double imageX;
    private double imageY;
    private double velocityX;
    private double velocityY;

    public String colorType;
    public Duck(String colorType, Scene scene, Pane pane){
        this.scene=scene;
        this.colorType=colorType;
        SCENE_WIDTH = scene.getWidth();
        SCENE_HEIGHT = scene.getHeight();

        imageX = animationView.getFitWidth()/2;
        imageY = animationView.getFitHeight()/2;
    }




    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void addImages(String colorType, Timeline timeline){
        for (int i=4;i<7;i++){
            Image image = new Image(String.format("/assets/%s/%s.png",colorType, i));
            animationView.setFitWidth(image.getWidth()*2);
            animationView.setFitHeight(image.getHeight()*2);
            KeyFrame keyFrame = new KeyFrame(Duration.millis( 150*i), e -> animationView.setImage(image));

            timeline.getKeyFrames().add(keyFrame);
        }
        }

    public  void LinearMotionRight(char direction){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(colorType,timeline);
        setDirection('R');

        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(5), animationView);
        transition1.setByX(scene.getWidth()-animationView.getFitWidth());

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(5), animationView);

        transition2.setByX(-scene.getWidth()+animationView.getFitWidth());
        transition1.setOnFinished(event -> {
            animationView.setScaleX(animationView.getScaleX() * -1);

        });

        transition2.setOnFinished(event -> {
            animationView.setScaleX(animationView.getScaleX() * -1);

        });
        SequentialTransition sequentialTransition;
        if (direction =='R'){
            sequentialTransition =new SequentialTransition(transition1,transition2);
        }else {
            sequentialTransition =new SequentialTransition(transition2,transition1);
        }


        sequentialTransition.setCycleCount(Animation.INDEFINITE);



       right= new ParallelTransition(timeline,sequentialTransition);

       right.setNode(animationView);


        // Start the animation
        right.play();
    }

    public void Stop(){
        right.stop();
    }


    public void diagonalMotion(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(colorType,timeline);
        setDirection('R');


        velocityX = VELOCITY_X;
        velocityY = VELOCITY_Y;
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        ParallelTransition parallelTransition = new ParallelTransition(timeline,animation);

        parallelTransition.play();





    }












    public void Falling(){
        Timeline timeline = new Timeline();

            Image image1 = new Image(String.format("/assets/%s/%s.png",colorType, 7));
            Image image2 = new Image(String.format("/assets/%s/%s.png",colorType, 8));




            KeyFrame keyFrame = new KeyFrame(Duration.millis( 70), e -> {
                animationView.setImage(image1);
                animationView.setFitWidth(image1.getWidth()*2);
                animationView.setFitHeight(image1.getHeight()*2);
            });
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(300),event -> {
                animationView.setImage(image2);
                animationView.setFitWidth(image2.getWidth()*2);
                animationView.setFitHeight(image2.getHeight()*2);
            });


            timeline.getKeyFrames().add(keyFrame);
            timeline.getKeyFrames().add(keyFrame1);




        TranslateTransition fall = new TranslateTransition(Duration.seconds(5), animationView);
        fall.setByY(scene.getHeight());
        SequentialTransition fallAnimation = new SequentialTransition(timeline,fall);

        fallAnimation.play();
    }


    public void Move() {


        // Check for collision with the scene bounds
        Bounds bounds = scene.getRoot().getLayoutBounds();
        Bounds boundsImage = animationView.getBoundsInParent();

      if (boundsImage.getMaxX() > bounds.getMaxX()){
            velocityX*=-1;
      }
      if (boundsImage.getMinX()< bounds.getMinX()){
          velocityX*=-1;
      }
        if (boundsImage.getMaxY() >  bounds.getMaxY()) {
            velocityY *= -1; // Reverse the vertical velocity
        }
        if (boundsImage.getMinY() < bounds.getMinY()){
            velocityY *= -1;
        }



        imageX += velocityX;
        imageY += velocityY;
        // Update the ImageView's position
        animationView.setTranslateX(imageX);
        animationView.setTranslateY(imageY);
    }

}



