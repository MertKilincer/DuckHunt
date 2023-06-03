/**
 * Necessary java fx imports.
 */

import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Duck class is an abstract  that duck are derived from to create different styles of moving ducks.
 */
public abstract class Duck {
    /**
     * Duck animation view that we see on the pane.
     */
    private ImageView animationView=new ImageView();
    /**
     * Ducks animation that played when created.
     */
    public ParallelTransition animation;

    /**
     * Starting X position of duck.
     */
    protected double imageX;
    /**
     * Starting Y position of duck.
     */
    protected double imageY;
    /**
     * Scale of the ducks that change according to the game.
     */
    protected double scale;
    /**
     * ColorType of the ducks.
     */
    private String colorType;

    /**
     * Duck Constructor that create duck instances with their color type.
     * @param colorType red blue or black like color of ducks.
     * @param scale scale of the game.
     */
    public Duck(String colorType,double scale){
        //create a duck imageView.
        this.colorType=colorType;
        this.scale=scale;
        Image image=new Image(String.format("/assets/%s/%s.png",colorType, 4));
        animationView.setPreserveRatio(true);
        animationView.setSmooth(true);
        imageSetter(image);

        imageX = animationView.getLayoutX();
        imageY = animationView.getLayoutY();


    }

    /**
     * Getter for duck imageView.
     * @return duck's imageView that animates.
     */
    public ImageView getAnimationView() {
        return animationView;
    }

    /**
     * Getter for colorType of the duck.
     * @return
     */
    public String getColorType() {
        return colorType;
    }
    /**
     * Getter for width of the duck's animationView
     */
    public double getDuckWidth(){
        return animationView.getFitWidth();
    }
    /**
     * Getter for height of the duck's animationView
     */
    public double getDuckHeight(){
        return animationView.getFitHeight();
    }

    /**
     * Set the animation of the duck.
     * @param animation animation created in subclasses.
     */
    public void setAnimation(ParallelTransition animation) {
        this.animation = animation;
    }

    /**
     * it fills the timeline with animation frames for flapping.
     * @param colorType color of the duck
     * @param timeline timeline that frames added.
     */
    public abstract void addImages(String colorType, Timeline timeline);

    /**
     * This methods stop the animation of moving.
     */
    public void Stop(){
        animation.stop();
    }

    /**
     * This method check the duck is shot or not.
     * @param bound bound of the crosshair.
     * @return return true if the ducks is shot.
     */
    public Boolean inBounds(Bounds bound){

        return this.animationView.getBoundsInParent().intersects(bound);

    }

    /**
     * This method starts the falling animation.
     */
    public void Falling(){
        Timeline timeline = new Timeline();
            Image image1 = new Image(String.format("/assets/%s/%s.png",colorType, 7));
            Image image2 = new Image(String.format("/assets/%s/%s.png",colorType, 8));
            if (animationView.getScaleY()<0){
                animationView.setScaleY(animationView.getScaleY()*-1);
            }

            KeyFrame keyFrame = new KeyFrame(Duration.millis( 70), e -> {
                imageSetter(image1);

            });
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(300),event -> {
                imageSetter(image2);
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.getKeyFrames().add(keyFrame1);


        TranslateTransition fall = new TranslateTransition(Duration.seconds(3), animationView);

        fall.setByY(240*scale);
        //firstly change the image view with timeline and after it starts to fall by transition.
        SequentialTransition fallAnimation = new SequentialTransition(timeline,fall);
        fallAnimation.play();
    }

    /**
     * This methods change the starting positions of ducks in the game.
     * @param x starting x position.
     * @param y starting y position.
     */
    public void setLayouts(double x,double y){
        this.animationView.setLayoutX(x);
        this.animationView.setLayoutY(y);
    }

    /**
     * Set the animationView image and change the size of the imageview.
     * @param image new image of the duck.
     */
    public void imageSetter(Image image){
        animationView.setImage(image);
        animationView.setFitHeight(image.getHeight()*scale);
        animationView.setFitWidth(image.getWidth()*scale);

    }



}



