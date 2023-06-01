/**
 * Necessary java fx imports to class functionate itself.
 */
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.*;

/**
 * Round class that base class of all rounds.
 */
public class Round{

    /**
     * Scale of the game
     */
    protected double scale;
    /**
     * Group that in pane and holds the ducks image views.
     */
    public Group group;
    /**
     *scene that displayed when the current round played.
     */
    private Scene scene;
    /**
     * Sounds effects of the game.
     */
    protected Sounds sounds;
    /**
     * Ducklist that holds the duck instances.
     */
    public LinkedList<Duck> duckList= new LinkedList<>();
    /**
     * AmmoCount of the round that determines the round ending.
     */
    protected IntegerProperty ammoCount;
    /**
     * GameState property change and according to this we handle the key events of each round.
     */
    protected ObjectProperty<GameState> gameStateProperty = new SimpleObjectProperty<>(GameState.ON_HOLD);
    /**
     * GameElements of the game.
     * @see GameElements
     */
    private GameElements elements;

    /**
     * GameState enum that change according to the game
     */
    public enum GameState {
        /**
         * Starting of the round its on hold
         */
        ON_HOLD,
        /**
         * if you complete the round6 successfully it becames COMPLETED
         */
        COMPLETED,
        /**
         * but if it is not round6 its becames NEXT_LEVEL when completed.
         */
        NEXT_LEVEL,
        /**
         * GAME_OVER state will be end of failure rounds.
         */
        GAME_OVER,

    }


    /**
     * Round constructor creates the rounds base template.
     * @param roundNum roundNum of the round
     * @param elements elements of the game used to create base visuals like foreground, background etc.
     */
    public Round(int roundNum, GameElements elements){
        this.elements=elements;
        this.scale=elements.getScale();
        StackPane root=new StackPane();
        CustomCrosshair cursor =new CustomCrosshair(elements.getCrosshair().getFilepath(),elements.getScale());
        this.group=new Group();
        this.sounds=new Sounds(elements.getVolume());

        root.setBackground(elements.getBackground());

        Pane pane = new Pane();
        root.getChildren().add(pane);
        pane.getChildren().add(cursor);

        /*
        Foreground and its manipulations.
         */
        ImageView foreground =new ImageView(elements.getForeground());
        foreground.fitWidthProperty().bind(root.widthProperty());
        foreground.fitHeightProperty().bind(root.heightProperty());
        pane.getChildren().add(foreground);
        pane.getChildren().add(group);
        ammoCount=new SimpleIntegerProperty(0);

        /*
        Level num Text at the top of the scene.
         */
        Label Round = new Label(String.format("Level " + "%s/6",roundNum));
        Round.setFont(Font.font("Calibri", FontWeight.BOLD,10*scale));
        Round.setTextFill(Color.ORANGE);
        root.getChildren().add(Round);
        StackPane.setAlignment(Round, Pos.TOP_CENTER);

        /*
        Ammo text screen that seen .
         */
        Label ammoText =new Label("Ammo Left: " + this.ammoCount.getValue()+" ");
        ammoText.setFont(Font.font("Calibri", FontWeight.BOLD,10*scale));
        ammoText.setTextFill(Color.ORANGE);
        root.getChildren().add(ammoText);
        StackPane.setAlignment(ammoText,Pos.TOP_RIGHT);

        this.scene=new Scene(root,256*scale,scale*240);
        /*
         getting the cursor at the front of the pane.
         */
        scene.setCursor(Cursor.NONE);
        cursor.toFront();
        group.toBack();


        /*
          AmmoCount is a IntegerProperty can be seen and manipulated and its changes can be detected by a changeListener.
          @see javafx.beans.value.ChangeListener
         * @see IntegerProperty
         */
        this.ammoCount.addListener((observable, oldValue, newValue) -> ammoText.setText("Ammo Left: "+ newValue.intValue()+" "));
        /*
          Mouse movement event handler that detect when mouse entered or exited from the game screen,and it can bem oved by mouse
          event handlers too.
         */
        scene.setOnMouseMoved(event -> cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        scene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> cursor.setVisible(false));
        scene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> cursor.setVisible(true));


        /*
          Mouse event that activated when game played and player clicked the screen with mouse.
          it firstly calls the gunShot sound effect,get the mouse position and create a bounding box from it.
         */
        scene.setOnMouseClicked(event -> {
            sounds.gun();
            Double x = event.getX()+(scale/3*16);
            Double y = event.getY()+(scale/3*16);
            Point2D point = new Point2D(x,y); // Create a point

            BoundingBox bound = new BoundingBox(point.getX(), point.getY(),scale/3*32 , scale/3*32);
            LinkedList<Duck> removedDucks=new LinkedList<>();
            //reduce the ammoCount
            ammoCount.set(ammoCount.getValue()-1);
            //check for one shot many kills condition in the game
            for (Duck i:duckList){

                if (i.inBounds(bound)){
                    //if duck in bound of the chrossair it stop it moving animation and start to fall with sound effect.
                    i.Stop();
                    i.Falling();
                    sounds.fall();
                    removedDucks.add(i);
                }


            }
            //remove the shot ducks from the duck-list.
            duckList.removeAll(removedDucks);
            Message roundResult=new Message(scale,15.0,Pos.CENTER);
            //handle the game events situation depend on the remaining ducks and ammos with branching.
            if ((ammoCount.getValue() >0)) {
                if (duckList.isEmpty()) {
                    //consume the click event
                    scene.setOnMouseClicked(Event::consume);

                    //depending on the round number give the round ending message.
                    if (roundNum<6){
                        roundResult.addText("YOU WIN!");
                        roundResult.addFadeText("Press ENTER to play next level");
                        //change the gameStateProperty
                        gameStateProperty.set(GameState.NEXT_LEVEL);

                    }else{
                        roundResult.addText("You have completed the game!");
                        roundResult.addFadeText("Press ENTER to play again");
                        roundResult.addFadeText("Press ESC to exit");
                        //change the gameStateProperty
                        gameStateProperty.set(GameState.COMPLETED);
                    }
                    root.getChildren().add(roundResult);

                }
            }else {
                //consume the click event
                scene.setOnMouseClicked(Event::consume);
                if (duckList.isEmpty()) {
                    //depending on the round number give the round ending message.
                    if (roundNum<6){
                        roundResult.addText("YOU WIN!");
                        roundResult.addFadeText("Press ENTER to play next level");
                        //change the gameStateProperty
                        gameStateProperty.set(GameState.NEXT_LEVEL);

                    }else{
                        roundResult.addText("You have completed the game!");
                        roundResult.addFadeText("Press ENTER to play again");
                        roundResult.addFadeText("Press ESC to exit");
                        //change the gameStateProperty
                        gameStateProperty.set(GameState.COMPLETED);
                    }

                } else {
                    //in case of failure you give the game error message.
                    roundResult.addText("GAME OVER!");
                    roundResult.addFadeText("Press ENTER to play again");
                    roundResult.addFadeText("Press ESC to exit");
                    //change the gameStateProperty
                    gameStateProperty.set(GameState.GAME_OVER);
                }
                root.getChildren().add(roundResult);
            }


        });

    }

    /**
     * Getter for the scene
     * @return scene that game currently played.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * gameOver method is called when the gameState is GAME_OVER end handle the events.
     * @param stage stage that scene shown.
     */
    protected void gameOver(Stage stage){
        //Play the sound effect
        sounds.getGameOver().play();
        //handle the key events
        scene.setOnKeyPressed(event5 -> {
            if (event5.getCode().equals(KeyCode.ENTER)){
                //stop the sound effect
                sounds.getGameOver().stop();
                Round1 round1 =new Round1(elements);
                stage.setScene(round1.getScene());
            } else if (event5.getCode().equals(KeyCode.ESCAPE)) {
                //stop the sound effect
                sounds.getGameOver().stop();
                TitleScreen title =new TitleScreen(scale,stage,elements.getVolume());
                stage.setScene(title.getTitleScene());

            }
        });
    }

    /**
     * gameOver method is called when the gameState is GAME_OVER end handle the events.
     * @param levelNum round num of the next level.
     */
    protected void gameNextLevel(int levelNum){
        //Play the sound effect
        sounds.getLevelCompleted().play();
        //handle the key events
        scene.setOnKeyPressed(event3 -> {
            if (event3.getCode().equals(KeyCode.ENTER)){
                //stop the sound effect
                sounds.getLevelCompleted().stop();
                Round round =nextRound(levelNum,elements);
                elements.getStage().setScene(round.scene);
            }
        });
    }


    /**
     * nextRound methods create the next round in the game.
     * @param levelNum level num of the next level
     * @param elements elements of the current game
     * @return Round that will be played next level.
     */
    private Round nextRound(int levelNum,GameElements elements){
        Round round = null;
        switch (levelNum){

            case 2:
                round = new Round2(elements);
                break;

            case 3:
                round = new Round3(elements);
                break;

            case 4:
                round = new Round4(elements);
                break;

            case 5:
                round = new Round5(elements);
                break;

            case 6:
                round = new Round6(elements);
                break;

        }

    return round;
    }


    /**
     * duckAdder methods add ducks to the rounds.
     * @param duck duck that added to the round.
     */
    protected void duckAdder(Duck duck){
        group.getChildren().add(duck.getAnimationView());
        duckList.add(duck);
        ammoCount.set(duckList.size()*3);
    }


}






























