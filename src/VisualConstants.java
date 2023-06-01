/**
 * Necessary java fx classes to import fully function this class.
 */
import javafx.scene.layout.*;
import java.io.File;
import java.util.LinkedList;

/**
 * VisualConstants class is create all background ,foregrounds,crosshairs and store them.
 */
public class VisualConstants {
    /**
    LinkedList fields for storing the instances of CustomBackground.
     **/
    private LinkedList<CustomBackground> backgrounds = new LinkedList<>();
    /**
     LinkedList fields for storing the instances of CustomCrosshair.
     **/
    private  LinkedList<CustomCrosshair> Crossair = new LinkedList<>();

    /**
     * Constructor of the VisualConstants class.
     * @param pane pane for getting width and height for backgrounds.
     * @param scale scale of the application it is used for creating crosshairs to appropriate size when scale is changed.
     */
    public VisualConstants(Pane pane, double scale){
        createBackgrounds(pane);
        createCrossairs(scale);
    }

    /**
     * createBackgrounds method create CustomBackground instances and add them to list.
     * @param pane pane is given for CustomBackground instances.
     */
    public void createBackgrounds(Pane pane){
        for (int i=1;i<7;i++){
            backgrounds.add(new CustomBackground(i,pane));
        }
    }

    /**
     * createCrossairs method create CustomCrosshair instances and add them to list.
     * @param scale scale is given for creating crosshairs as scaled by game.
     */
    public void createCrossairs(double scale) {
        String directoryPath = "assets/crosshair";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        for (File i : files) {
            Crossair.add(new CustomCrosshair(i.getPath(),scale));
        }
    }

    /**
     * Getter for CustomBackground's LinkedList.
     * @return List of CustomBackgrounds.
     */
    public LinkedList<CustomBackground> getBackgrounds() {
        return backgrounds;
    }

    /**
     * Getter for CustomCrosshair's LinkedList.
     * @return List of CustomCrosshairs.
     */
    public LinkedList<CustomCrosshair> getCrossair() {
        return Crossair;
    }
}

