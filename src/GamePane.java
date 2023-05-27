
import javafx.geometry.Bounds;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GamePane extends Pane {

    public GameScene scene;

    public CustomCrosshair cursor;

    public double scale;

    public LinkedList<Duck> duckList=new LinkedList<>();



    public GamePane(Double scale, CustomCrosshair cursor){

        Image image = new Image("/assets/background/3.png");

        ImageView imageView = new ImageView(clearer(image));;
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());
        this.cursor = cursor;
        getChildren().add(imageView);
        getChildren().add(cursor);
        this.scale=scale;



        this.scene =new GameScene(this,scale);

        Duck duck1= new Duck("duck_black",scene,scale);

        Duck duck2= new Duck("duck_blue",scene,scale);
        duckList.add(duck1);
        duckList.add(duck2);
        scene.updateAmmoCount();
        scene.updateAmmoCount();

        getChildren().addAll(duck1.animationView,duck2.animationView);

        duck1.animationView.setLayoutX(0);
        duck1.animationView.setLayoutY(200);
        duck2.animationView.setLayoutX(150);

        duck2.animationView.setLayoutY(200);
        duck1.animationView.toBack();
        duck2.animationView.toBack();

        duck1.LinearMotionRight('R');
        duck2.LinearMotionRight('R');











    }

//    public void events(Scene scene){
//        scene.setOnMouseMoved(event -> cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
//        scene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> cursor.setVisible(false));
//        scene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> cursor.setVisible(true));
//
//
//        scene.setOnMouseClicked(event -> {
//            Double x = event.getX()-(scale/3*16);
//            Double y = event.getY()-(scale/3*16);
//            if (duck1.animationView.getBoundsInParent().contains(x,y)){
//                System.out.println(duck1.animationView.getBoundsInParent());
//                duck1.Stop();
//                duck1.Falling();
//                duck1.state="A";
//
//            }else {
//
//            }
//            if (!duck1.state.equals("Alive")){
//                System.out.println("İt is shorten");
//            }
//        });
//    }










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
