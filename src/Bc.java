import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Bc extends Pane {



    public Bc(){

        Image image = new Image("/assets/background/1.png");
        Image image2 = clearer(image);
        ImageView imageView = new ImageView(image2);
        getChildren().add(imageView);


    }


    public Image clearer(Image image){
        PixelReader pixelReader = image.getPixelReader();

        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();





        WritableImage newImage = new WritableImage(imageWidth, imageHeight);
        PixelWriter pixelWriter = newImage.getPixelWriter();

        Color clearColor = Color.rgb(99,173,255);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                Color color = pixelReader.getColor(x, y);
                if (color.equals(clearColor)) {
                    pixelWriter.setColor(x, y, Color.ORANGE);
                } else {
                    pixelWriter.setColor(x, y, color);
                }
            }
        }

            return newImage;
        }



}
