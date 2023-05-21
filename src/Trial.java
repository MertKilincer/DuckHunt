import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Trial extends StackPane {


    public Trial(){
        Image image = new Image("/assets/background/1.png");

        // Create an ImageView for the image
        ImageView imageView = new ImageView(image);

        // Create a mask for the foreground part
        Rectangle foregroundMask = new Rectangle(100, 100); // Adjust the size and position as needed
        foregroundMask.setStyle("-fx-fill: white;"); // Set the color of the foreground mask

        // Create a mask for the background part
        Rectangle backgroundMask = new Rectangle(100, 100); // Adjust the size and position as needed
        backgroundMask.setStyle("-fx-fill: transparent; -fx-stroke: white; -fx-stroke-width: 5;"); // Set the color and border properties of the background mask

        // Create a Pane to hold the image and foreground mask
        Pane foregroundPane = new Pane(imageView, foregroundMask);

        Pane animationPane = new Pane();

        // Create a StackPane to hold the foreground and background layers

        getChildren().addAll(foregroundPane, backgroundMask);

        // Set the size of the stackPane to match the image
        setPrefWidth(image.getWidth());
        setPrefHeight(image.getHeight());

        // Set the foreground mask as the clip for the foreground pane




        Rectangle animatedObject = new Rectangle(100, 100, 50, 50); // Adjust the properties of the animated object
        animatedObject.setStyle("-fx-fill: red;"); // Set the fill color of the animated object



        // Create a Timeline to animate the object's position
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(animatedObject.translateXProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(animatedObject.translateXProperty(), 300))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        // Add the animated object to the animation pane
        animationPane.getChildren().add(animatedObject);
        getChildren().add(animationPane);


    }

}
