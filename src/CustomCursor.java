import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class CustomCursor extends Pane {
    private Circle cursor;

    public CustomCursor() {
        cursor = new Circle(10);; // Adjust the size of the cursor circle as desired
        getChildren().add(cursor);


    }

    public void updatePosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
}

