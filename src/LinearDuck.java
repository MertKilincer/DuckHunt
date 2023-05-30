import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class LinearDuck extends Duck{

    private double initialVelocityX;

    public double imageX;

    public double velocityX;

    public char direction;



    public LinearDuck(String colorType, double scale,char direction) {
        super(colorType, scale);
        this.direction=direction;
        if (direction=='R'){
            initialVelocityX=scale*1;
        }else {
            initialVelocityX=scale*-1;
            animationView.setScaleX(animationView.getScaleX()*-1);
        }
        Motion();




    }

    public void Motion() {


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(colorType, timeline);


        velocityX = initialVelocityX;

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(50), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        setAnimation(new ParallelTransition(timeline, animation));

        super.animation.play();

    }



    public void Move() {


        // Check for collision with the scene bounds


        Bounds boundsImage = animationView.getBoundsInParent();

        if (boundsImage.getMaxX() > 256*scale){
            velocityX*=-1;
            animationView.setScaleX(animationView.getScaleX() * -1);
        }
        if (boundsImage.getMinX()< 0
        ){
            velocityX*=-1;
            animationView.setScaleX(animationView.getScaleX() * -1);
        }



        imageX += velocityX;
        // Update the ImageView's position
        animationView.setTranslateX(imageX);

    }

    public void addImages(String colorType, Timeline timeline){
        for (int i=4;i<7;i++){
            Image image = new Image(String.format("/assets/%s/%s.png",colorType, i));


            KeyFrame keyFrame = new KeyFrame(Duration.millis( 150*i), e -> animationView.setImage(image));

            timeline.getKeyFrames().add(keyFrame);
        }
    }



}
