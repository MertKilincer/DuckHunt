import javafx.animation.*;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;



public class Duck {

    public ImageView animationView=new ImageView();


    public ParallelTransition animation;


    public Scene  scene;
    private  final double VELOCITY_X;
    private  final double VELOCITY_Y;

    private double imageX;
    private double imageY;
    private double velocityX;
    private double velocityY;

    public String state="Alive";

    public String colorType;
    public Duck(String colorType, Scene scene,double scale){

        this.colorType=colorType;
        this.scene=scene;
        Image image=new Image(String.format("/assets/%s/%s.png",colorType, 4));
        animationView.setImage(image);
        animationView.setFitWidth(image.getWidth()*2);
        animationView.setFitHeight(image.getHeight()*2);
        VELOCITY_X=scale*1;
        VELOCITY_Y=scale*1;

        imageX = animationView.getLayoutX();
        imageY = animationView.getLayoutY();


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



        velocityX = VELOCITY_X;
        velocityY =0.0;
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        this.animation = new ParallelTransition(timeline,animation);

        this.animation.play();


    }

    public void Stop(){
        animation.stop();
    }


    public void diagonalMotion(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(colorType,timeline);


        velocityX = VELOCITY_X;
        velocityY = VELOCITY_Y;
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        this.animation = new ParallelTransition(timeline,animation);

        this.animation.play();





    }












    public void Falling(){
        Timeline timeline = new Timeline();

            Image image1 = new Image(String.format("/assets/%s/%s.png",colorType, 7));
            Image image2 = new Image(String.format("/assets/%s/%s.png",colorType, 8));
            if (animationView.getScaleY()<0){
                animationView.setScaleY(animationView.getScaleY()*-1);
            }



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
        Bounds bounds =scene.getRoot().getLayoutBounds();
        fall.setByY(bounds.getMaxY());
        SequentialTransition fallAnimation = new SequentialTransition(timeline,fall);

        fallAnimation.play();
    }


    public void Move() {


        // Check for collision with the scene bounds
        Bounds bounds = scene.getRoot().getLayoutBounds();

        Bounds boundsImage = animationView.getBoundsInParent();

      if (boundsImage.getMaxX() > bounds.getMaxX()){
            velocityX*=-1;
            animationView.setScaleX(animationView.getScaleX() * -1);
      }
      if (boundsImage.getMinX()< bounds.getMinX()){
          velocityX*=-1;
          animationView.setScaleX(animationView.getScaleX() * -1);
      }
        if (boundsImage.getMaxY() >  bounds.getMaxY()) {
            velocityY *= -1; // Reverse the vertical velocity
            animationView.setScaleY(animationView.getScaleY() * -1);

        }
        if (boundsImage.getMinY() < bounds.getMinY()){
            velocityY *= -1;
            animationView.setScaleY(animationView.getScaleY() * -1);
        }



        imageX += velocityX;
        imageY += velocityY;
        // Update the ImageView's position
        animationView.setTranslateX(imageX);
        animationView.setTranslateY(imageY);
    }

}



