import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.*;

import static javax.swing.text.StyleConstants.setBackground;

public class Round{


    public double scale;

    public StackPane root;

    public LinkedList<Duck> duckList= new LinkedList<>();

    public IntegerProperty ammoCount;

    public int roundNum;

    public ObjectProperty<GameState> gameStateProperty = new SimpleObjectProperty<>(GameState.ON_HOLD);

    public CustomCrosshair cursor;

    public ImageView foreground;

    public Group group;

    public Scene scene;

    public Label ammoText;


    public enum GameState {

        ON_HOLD,
        COMPLETED,
        NEXT_LEVEL,
        GAME_OVER,

    }




    public Round(int roundNum,Game game){
        this.roundNum=roundNum;
        this.scale=game.scale;
        this.root=new StackPane();

        this.cursor =game.cursor;
        this.foreground = new ImageView(game.foreground);
        this.group=new Group();
        root.setBackground(game.background);

        Pane pane = new Pane();

        root.getChildren().add(pane);
        pane.getChildren().add(cursor);
        cursor.toFront();



        foreground.fitWidthProperty().bind(root.widthProperty());
        foreground.fitHeightProperty().bind(root.heightProperty());
        root.getChildren().add(foreground);
        pane.getChildren().add(group);
        ammoCount=new SimpleIntegerProperty(0);


        Label Round = new Label(String.format("Level " + "%s/6",roundNum));
        Round.setFont(Font.font("calibri", FontWeight.BOLD,10*scale));
        Round.setTextFill(Color.ORANGE);
        root.getChildren().add(Round);
        StackPane.setAlignment(Round, Pos.TOP_CENTER);


        ammoText =new Label("Ammo Left: " + this.ammoCount.getValue()+" ");
        ammoText.setFont(Font.font("Calibri", FontWeight.BOLD,10*scale));
        ammoText.setTextFill(Color.ORANGE);
        root.getChildren().add(ammoText);
        StackPane.setAlignment(ammoText,Pos.TOP_RIGHT);

        this.scene=new Scene(root,256*scale,scale*240);
        scene.setCursor(Cursor.NONE);


        this.ammoCount.addListener((observable, oldValue, newValue) -> ammoText.setText("Ammo Left: "+ newValue.intValue()+" "));

        events();





    }




    public void events(){
        scene.setOnMouseMoved(event -> cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        scene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> cursor.setVisible(false));
        scene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> cursor.setVisible(true));




        scene.setOnMouseClicked(event -> {
            Double x = event.getX()+(scale/3*16);
            Double y = event.getY()+(scale/3*16);
            LinkedList<Duck> removedDucks=new LinkedList<>();
            ammoCount.set(ammoCount.getValue()-1);
            for (Duck i:duckList){

               if (i.inBounds(x,y)){
                    i.Stop();
                    i.Falling();
                    removedDucks.add(i);
                }


            }

            duckList.removeAll(removedDucks);
            Message roundResult=new Message(scale,15.0,Pos.CENTER);
            if ((ammoCount.getValue() >0)) {
                if (duckList.isEmpty()) {
                    scene.setOnMouseClicked(Event::consume);


                    if (roundNum<5){
                        roundResult.addText("YOU WIN!");
                        roundResult.addFadeText("Press ENTER to play next level");
                        gameStateProperty.set(GameState.NEXT_LEVEL);
                    }else{
                        roundResult.addText("You have completed the game!");
                        roundResult.addFadeText("Press ENTER to play again");
                        roundResult.addFadeText("Press ESC to exit");
                        gameStateProperty.set(GameState.COMPLETED);
                    }
                    root.getChildren().add(roundResult);

                }
            }else {

                scene.setOnMouseClicked(Event::consume);
                if (duckList.isEmpty()) {
                    if (roundNum<5){
                        roundResult.addText("YOU WIN!");
                        roundResult.addFadeText("Press ENTER to play next level");
                        gameStateProperty.set(GameState.NEXT_LEVEL);

                    }else{
                        roundResult.addText("You have completed the game!");
                        roundResult.addFadeText("Press ENTER to play again");
                        roundResult.addFadeText("Press ESC to exit");

                    }

                } else {
                    roundResult.addText("GAME OVER!");
                    roundResult.addFadeText("Press ENTER to play again");
                    roundResult.addFadeText("Press ESC to exit");
                    gameStateProperty.set(GameState.GAME_OVER);
                }
                root.getChildren().add(roundResult);
            }






        });



    }
}






























