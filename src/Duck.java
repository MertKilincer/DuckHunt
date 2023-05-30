import javafx.animation.*;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;



public abstract class Duck {

    public ImageView animationView=new ImageView();

    public ParallelTransition animation;

    public Scene  scene;

    protected double imageX;
    protected double imageY;

    public double scale;
    public String colorType;


    public Duck(String colorType,double scale){

        this.colorType=colorType;
        this.scale=scale;
        Image image=new Image(String.format("/assets/%s/%s.png",colorType, 4));
        animationView.setPreserveRatio(true);
        animationView.setSmooth(true);
        animationView.setFitHeight(33*scale);
        animationView.setFitWidth(33*scale);
        animationView.setImage(image);

        imageX = animationView.getLayoutX();
        imageY = animationView.getLayoutY();


    }

    public void setAnimation(ParallelTransition animation) {
        this.animation = animation;
    }

    public abstract void addImages(String colorType, Timeline timeline);





    public void Stop(){
        animation.stop();
    }

    public Boolean inBounds(Bounds bound){

        return this.animationView.getBoundsInParent().intersects(bound);

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

            });
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(300),event -> {
                animationView.setImage(image2);
            });


            timeline.getKeyFrames().add(keyFrame);
            timeline.getKeyFrames().add(keyFrame1);




        TranslateTransition fall = new TranslateTransition(Duration.seconds(3), animationView);

        fall.setByY(240*scale);
        SequentialTransition fallAnimation = new SequentialTransition(timeline,fall);

        fallAnimation.play();
    }


    public void setLayouts(double x,double y){
        this.animationView.setLayoutX(x);
        this.animationView.setLayoutY(y);
    }




}



