
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CustomCursor extends Pane {
    private ImageView cursor;

    public CustomCursor(Image image) {
        cursor = new ImageView(image);; // Adjust the size of the cursor circle as desired
        getChildren().add(cursor);



    }

    public void updatePosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
}

