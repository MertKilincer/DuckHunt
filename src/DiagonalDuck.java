import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class DiagonalDuck extends Duck{

    private final double initialVelocityX;
    private final double initialVelocityY;

    public double velocityX;
    public double velocityY;

    public DiagonalDuck(String colorType, double scale, char verticalDirection, char horizontalDirection) {
        super(colorType, scale);
        if (horizontalDirection=='R'){
            initialVelocityX=scale*1;
        }else {
            animationView.setScaleX(animationView.getScaleX()*-1);
            initialVelocityX=scale*-1;
        }
        if (verticalDirection=='U'){

            initialVelocityY=scale*-1;
        }else {
            initialVelocityY=scale*1
            ;
            animationView.setScaleY(animationView.getScaleY()*-1);
        }
        Motion();

    }

    @Override
    public void addImages(String colorType, Timeline timeline) {
        for (int i=1;i<4;i++){
            Image image = new Image(String.format("/assets/%s/%s.png",colorType, i));


            KeyFrame keyFrame = new KeyFrame(Duration.millis( 250*i), e -> animationView.setImage(image));

            timeline.getKeyFrames().add(keyFrame);
        }

    }

    public void Motion(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        addImages(colorType,timeline);


        velocityX = initialVelocityX;
        velocityY = initialVelocityY;
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> Move()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        super.setAnimation(new ParallelTransition(timeline,animation));

        super.animation.play();
    }


    public void Move() {


        // Check for collision with the scene bounds


        Bounds boundsImage = animationView.getBoundsInParent();
        BoundingBox gameBorders =new BoundingBox(0,0,256*scale,240*scale);

        if (boundsImage.getMaxX() > gameBorders.getMaxX()){
            animationView.setScaleX(animationView.getScaleX() * -1);
            velocityX*=-1;

        }
        if (boundsImage.getMinX()< gameBorders.getMinX()){
            animationView.setScaleX(animationView.getScaleX() * -1);
            velocityX*=-1;

        }
        if (boundsImage.getMaxY() >  gameBorders.getMaxY()) {
            velocityY *= -1; // Reverse the vertical velocity
            animationView.setScaleY(animationView.getScaleY() * -1);

        }
        if (boundsImage.getMinY() < gameBorders.getMinY()){
            velocityY *= -1;
            animationView.setScaleY(animationView.getScaleY() * -1);
        }

        super.imageX += velocityX;
        super.imageY += velocityY;
        // Update the ImageView's position
        animationView.setTranslateX(super.imageX);
        animationView.setTranslateY(super.imageY);
    }
}
