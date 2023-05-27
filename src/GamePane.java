
import javafx.animation.ParallelTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GamePane extends StackPane {

    public GameScene scene;

    public CustomCrosshair cursor;

    public double scale;

    public LinkedList<Duck> duckList=new LinkedList<>();

    public Label ammoCount;



    public GamePane(Double scale, CustomCrosshair cursor){
        this.scale=scale;
        Pane pane =new Pane();
        getChildren().add(pane);

        Image image = new Image("/assets/background/3.png");
        ImageView imageView = new ImageView(clearer(image));;
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
        pane.getChildren().add(imageView);

        this.cursor = cursor;
        pane.getChildren().add(cursor);






        Duck duck1= new Duck("duck_black",scale);

        Duck duck2= new Duck("duck_blue",scale);
        duckList.add(duck1);
        duckList.add(duck2);

        this.scene =new GameScene(this,scale,1);
        Group ducks=new Group(duck1.animationView,duck2.animationView);
        pane.getChildren().add(ducks);

        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(200);
        duck2.animationView.setLayoutX(0);

        duck2.animationView.setLayoutY(200);
        ducks.toBack();


        duck1.LinearMotionRight();
        duck2.LinearMotionRight();



        ammoCount =new Label("Ammo Left: " + scene.ammoCount.getValue()+" ");
        ammoCount.setFont(Font.font("Calibri", FontWeight.BOLD,10*scale));
        ammoCount.setTextFill(Color.ORANGE);
        getChildren().add(ammoCount);
        setAlignment(ammoCount,Pos.TOP_RIGHT);


    }

//









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
