import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class GameElements {

    private Image foreground;

    private Background background;

    private CustomCrosshair crosshair;

    private double scale;

    private double volume;

    private Stage stage;



    public GameElements(double scale,double volume,CustomCrosshair crosshair,Background background,Image foreground,Stage stage){
        this.scale=scale;
        this.volume=volume;
        this.crosshair=crosshair;
        this.background=background;
        this.foreground=foreground;
        this.stage=stage;

    }


    public Image getForeground() {
        return foreground;
    }

    public Background getBackground() {
        return background;
    }

    public CustomCrosshair getCrosshair() {
        return crosshair;
    }

    public double getVolume() {
        return volume;
    }

    public double getScale() {
        return scale;
    }

    public Stage getStage() {
        return stage;
    }
}
