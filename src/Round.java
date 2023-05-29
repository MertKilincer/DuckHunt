import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.util.*;

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

    protected Sounds sounds;


    public enum GameState {

        ON_HOLD,
        COMPLETED,
        NEXT_LEVEL,
        GAME_OVER,

    }




    public Round(int roundNum, CustomCrosshair cursor, Image foreground ,Background background,double scale){
        this.roundNum=roundNum;
        this.scale=scale;
        this.root=new StackPane();
        this.cursor =cursor;
        this.foreground = new ImageView(foreground);
        this.group=new Group();
        this.sounds=new Sounds();
        root.setBackground(background);

        Pane pane = new Pane();

        root.getChildren().add(pane);

        pane.getChildren().add(cursor);





        this.foreground.fitWidthProperty().bind(root.widthProperty());
        this.foreground.fitHeightProperty().bind(root.heightProperty());
        pane.getChildren().add(this.foreground);
        pane.getChildren().add(group);
        ammoCount=new SimpleIntegerProperty(0);


        Label Round = new Label(String.format("Level " + "%s/6",this.roundNum));
        Round.setFont(Font.font("calibri", FontWeight.BOLD,10*scale));
        Round.setTextFill(Color.ORANGE);
        root.getChildren().add(Round);
        StackPane.setAlignment(Round, Pos.TOP_CENTER);


        Label ammoText =new Label("Ammo Left: " + this.ammoCount.getValue()+" ");
        ammoText.setFont(Font.font("Calibri", FontWeight.BOLD,10*scale));
        ammoText.setTextFill(Color.ORANGE);
        root.getChildren().add(ammoText);
        StackPane.setAlignment(ammoText,Pos.TOP_RIGHT);

        this.scene=new Scene(root,256*scale,scale*240);

        scene.setCursor(Cursor.NONE);
        cursor.toFront();
        group.toBack();



        this.ammoCount.addListener((observable, oldValue, newValue) -> ammoText.setText("Ammo Left: "+ newValue.intValue()+" "));


        events();





    }




    public void events(){
        scene.setOnMouseMoved(event -> cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        scene.addEventHandler(MouseEvent.MOUSE_EXITED, event -> cursor.setVisible(false));
        scene.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> cursor.setVisible(true));




        scene.setOnMouseClicked(event -> {
            sounds.gun();
            Double x = event.getX()+(scale/3*16);
            Double y = event.getY()+(scale/3*16);
            Point2D point = new Point2D(x,y); // Create a point

            // Create a bound with a width and height of 100
            BoundingBox bound = new BoundingBox(point.getX(), point.getY(),scale/3*32 , scale/3*32);
            LinkedList<Duck> removedDucks=new LinkedList<>();
            ammoCount.set(ammoCount.getValue()-1);

            for (Duck i:duckList){

               if (i.inBounds(bound)){

                    i.Stop();
                    i.Falling();
                    sounds.fall();
                    removedDucks.add(i);
                }


            }

            duckList.removeAll(removedDucks);
            Message roundResult=new Message(scale,15.0,Pos.CENTER);
            if ((ammoCount.getValue() >0)) {
                if (duckList.isEmpty()) {
                    scene.setOnMouseClicked(Event::consume);


                    if (roundNum<6){
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
                    if (roundNum<6){
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






























