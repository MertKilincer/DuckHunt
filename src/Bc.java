import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class Bc extends Pane {

    public Scene scene1;

    public CustomCrosshair cursor;

    public Bc(Double scale,CustomCrosshair cursor){

        Image image = new Image("/assets/background/3.png");

        ImageView imageView = new ImageView(clearer(image));;
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
        this.cursor = cursor;

        getChildren().add(cursor);
        getChildren().add(imageView);


        this.scene1=new Scene(this,scale*300,scale*300);

        Duck duck1= new Duck("duck_black",scene1,this);
        getChildren().add(duck1.animationView);
        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(150);
        //duck1.LinearMotionRight('R');
        duck1.diagonalMotion();
        scene1.setCursor(Cursor.NONE);




        scene1.setOnMouseMoved(event -> cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        scene1.addEventHandler(MouseEvent.MOUSE_EXITED, event -> cursor.setVisible(false));
        scene1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> cursor.setVisible(true));


        scene1.setOnMouseClicked(event -> {
            Double x = event.getX()-(scale/3*16);
            Double y = event.getY()-(scale/3*16);
            if (duck1.animationView.getBoundsInParent().contains(x,y)){
                System.out.println(duck1.animationView.getBoundsInParent());
                duck1.Stop();
                duck1.Falling();

            }
        });



    }





    public Image clearer(Image image){
        PixelReader pixelReader = image.getPixelReader();

        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();


        HashMap<Color, Integer> colorFrequencies = new HashMap<>();

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color color = pixelReader.getColor(x, y);
                Integer frequency = colorFrequencies.getOrDefault(color, 0);
                colorFrequencies.put(color, frequency + 1);
            }
        }

        int maxFrequency = 0;
        Color mostFrequentColor = null;
        for (Map.Entry<Color, Integer> entry : colorFrequencies.entrySet()) {
            Color color = entry.getKey();
            int frequency = entry.getValue();
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentColor = color;
            }
        }

        WritableImage newImage = new WritableImage(imageWidth, imageHeight);
        PixelWriter pixelWriter = newImage.getPixelWriter();



        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color color = pixelReader.getColor(x, y);
                if (color.equals(mostFrequentColor)) {
                    pixelWriter.setColor(x, y, Color.TRANSPARENT);
                } else {
                    pixelWriter.setColor(x, y, color);
                }
            }
        }
        BackgroundFill background_fill = new BackgroundFill(mostFrequentColor,
                CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);
        setBackground(background);
            return newImage;

        }

    private boolean isIntersecting(Circle circle, double x, double y) {
        Bounds bounds = circle.localToScene(circle.getBoundsInLocal());
        return bounds.contains(x, y);
    }





}
