import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class Bc extends Pane {



    public Bc(){

        Image image = new Image("/assets/background/1.png");

        ImageView imageView = new ImageView(clearer(image));;
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());



        Line line = new Line();
        line.setStartX(450);
        line.setEndX(450);
        line.setStartY(150);
        line.setEndY(800);


        Circle circle = new Circle(450,150,150);
        Circle circle1 =new Circle(450,150,150);
        circle1.setFill(Color.TRANSPARENT);



        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(line);
        pt.setNode(circle);

        pt.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);



        PathTransition pt2 = new PathTransition();
        pt2.setDuration(Duration.millis(4000));
        pt2.setPath(line);
        pt2.setNode(circle1);
        pt2.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt2.setCycleCount(Timeline.INDEFINITE);
        pt2.setAutoReverse(true);

        ParallelTransition paralel = new ParallelTransition(pt,pt2);
        getChildren().add(circle1);
        getChildren().add(imageView);
        getChildren().addAll(line,circle);

        circle.toBack();
        circle1.toFront();
        paralel.play();






        circle1.setOnMouseClicked(event -> javafx.application.Platform.exit());




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



}
