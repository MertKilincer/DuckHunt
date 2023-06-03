//Necessary imports
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * This class creates ducks that move diagonally.
 */
public class DiagonalDuck extends Duck{
    /**
     * Initial horizontal velocity
     */
    private final double initialVelocityX;
    /**
     * Initial vertical velocity
     */
    private final double initialVelocityY;
    /**
     * Horizontal velocity that change depends on the situation.
     */
    private double velocityX;
    /**
     * Vertical velocity that change depends on the situation.
     */
    private double velocityY;

    /**
     * Constructor of the diagonal duck class
     * @param colorType colorType of the duck like black or blue etc.
     * @param scale scale of the game
     * @param verticalDirection Initial vertical direction of motion.
     * @param horizontalDirection Initial horizontal direction of motion
     */
    public DiagonalDuck(String colorType, double scale, char verticalDirection, char horizontalDirection) {
        super(colorType, scale);
        /*
        Change the orientation of the image based on the initial directions.
         */
        if (horizontalDirection=='R'){
            initialVelocityX=scale*1;
        }else {
            getAnimationView().setScaleX(getAnimationView().getScaleX()*-1);
            initialVelocityX=scale*-1;
        }
        if (verticalDirection=='U'){

            initialVelocityY=scale*-1;
        }else {
            initialVelocityY=scale*1;

            getAnimationView().setScaleY(getAnimationView().getScaleY()*-1);
        }
        Motion();

    }

    /**
     * Fills the flapping animation with Keyframes.
     * @param colorType color of the duck
     * @param timeline timeline that frames added.
     * @see KeyFrame
     */
    @Override
    public void addImages(String colorType, Timeline timeline) {
        for (int i=1;i<4;i++){
            Image image = new Image(String.format("/assets/%s/%s.png",colorType, i));


            KeyFrame keyFrame = new KeyFrame(Duration.millis( 300*i), e -> imageSetter(image));

            timeline.getKeyFrames().add(keyFrame);
        }

    }

    /**
     * Motion of the duck consist of three animation that play in parallel.
     */
    public void Motion(){
        //Flapping animation
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(getColorType(),timeline);


        velocityX = initialVelocityX;
        velocityY = initialVelocityY;
        //Horizontal Motion
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(25), e -> MoveH()));
        animation.setCycleCount(Timeline.INDEFINITE);
        //Vertical Motion
        Timeline animation2 = new Timeline(new KeyFrame(Duration.millis(25), e -> MoveV()));
        animation2.setCycleCount(Timeline.INDEFINITE);
        //Parallel Motion
        super.setAnimation(new ParallelTransition(timeline,animation,animation2));

        super.animation.play();
    }

    /**
     * This method move the duck horizontally and reflect the image appropriately.It used in lambda of motion().
     */
    public void MoveH() {
        Bounds boundsImage = getAnimationView().getBoundsInParent();
        BoundingBox gameBorders =new BoundingBox(0,0,256*scale,240*scale);

        if (boundsImage.getMaxX() > gameBorders.getMaxX()){
            velocityX*=-1;
            getAnimationView().setScaleX(getAnimationView().getScaleX() * -1);

        }
        if (boundsImage.getMinX()< gameBorders.getMinX()){
            velocityX*=-1;
            getAnimationView().setScaleX(getAnimationView().getScaleX() * -1);
        }

        super.imageX += velocityX;

        // Update the ImageView's position
        getAnimationView().setTranslateX(super.imageX);

    }

    /**
     * This method move the duck vertically and reflect the image appropriately.It used in lambda of motion().
     */
    public void MoveV(){
        Bounds boundsImage = getAnimationView().getBoundsInParent();
        BoundingBox gameBorders =new BoundingBox(0,0,256*scale,240*scale);

        if (boundsImage.getMaxY() >  gameBorders.getMaxY()) {
            velocityY *= -1; // Reverse the vertical velocity
            getAnimationView().setScaleY(getAnimationView().getScaleY() * -1);

        }
        if (boundsImage.getMinY() < gameBorders.getMinY()){
            velocityY *= -1;
            getAnimationView().setScaleY(getAnimationView().getScaleY() * -1);
        }
        super.imageY += velocityY;
        getAnimationView().setTranslateY(super.imageY);
    }
}
