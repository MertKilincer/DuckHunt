/**
 * Necessary imports
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * LinearDuck class that are specialised to create ducks move in horizontal.
 */
public class LinearDuck extends Duck{
    /**
     * Initial velocity of the duck.
     */
    private double initialVelocityX;
    /**
     * changing velocity of the duck when moving it not change magnitude it changes the direction.
     */
    private double velocityX;

    /**
     * Linear duck constructor that create duck instances that move in horizontal.
     * @param colorType color of the duck
     * @param scale scale of the game
     * @param direction initial direction of movement.
     */
    public LinearDuck(String colorType, double scale,char direction) {
        super(colorType, scale);
        if (direction=='R'){
            initialVelocityX=scale*1;
        }else {
            initialVelocityX=scale*-1;
            getAnimationView().setScaleX(getAnimationView().getScaleX()*-1);
        }
        Motion();




    }

    /**
     * Motion of the duck consist of two animation that play in parallel.
     */
    public void Motion() {

        //flapping animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(getColorType(), timeline);


        velocityX = initialVelocityX;
        //Moving animation
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(25), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        //These animation are played in parallel.
        setAnimation(new ParallelTransition(timeline, animation));

        super.animation.play();

    }


    /**
     *  This function moves the duck position and reflect the animation if necessary
     */
    public void Move() {


        // Check for collision with the scene bounds


        Bounds boundsImage = getAnimationView().getBoundsInParent();

        if (boundsImage.getMaxX() > 256*scale){
            velocityX*=-1;
            getAnimationView().setScaleX(getAnimationView().getScaleX() * -1);
        }
        if (boundsImage.getMinX()< 0
        ){
            velocityX*=-1;
            getAnimationView().setScaleX(getAnimationView().getScaleX() * -1);
        }



        imageX += velocityX;
        // Update the ImageView's position
        getAnimationView().setTranslateX(imageX);

    }

    /**
     * Fills the flapping animation with Keyframes.
     * @param colorType color of the duck
     * @param timeline timeline that frames added.
     * @see KeyFrame
     */
    public void addImages(String colorType, Timeline timeline){
        for (int i=4;i<7;i++){
            Image image = new Image(String.format("/assets/%s/%s.png",colorType, i));


            KeyFrame keyFrame = new KeyFrame(Duration.millis( 250*i), e -> imageSetter(image));

            timeline.getKeyFrames().add(keyFrame);
        }
    }



}
