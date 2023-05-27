import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;

public class GameScene extends Scene {

    public  GamePane root;

    public double scale;

    public IntegerProperty ammoCount;

    public int roundNum;

    public Boolean gameState=false;




    public GameScene(GamePane root,double scale,int roundNum){
        super(root,300*scale,300*scale);
        this.root=root;
        this.scale=scale;
        this.roundNum=roundNum;
        this.ammoCount=new SimpleIntegerProperty(root.duckList.size()*3);
        this.setCursor(Cursor.NONE);


        Label Round = new Label(String.format("Level " +
                "%s/6",roundNum));
        Round.setFont(Font.font("calibri", FontWeight.BOLD,10*scale));
        Round.setTextFill(Color.ORANGE);
        root.getChildren().add(Round);
        root.setAlignment(Round, Pos.TOP_CENTER);
        events();





        ammoCount.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                root.ammoCount.setText("Ammo Left: "+ newValue.intValue()+" ");
            }
        });






    }




    public void events(){
        setOnMouseMoved(event -> root.cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        addEventHandler(MouseEvent.MOUSE_EXITED, event -> root.cursor.setVisible(false));
        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> root.cursor.setVisible(true));




        setOnMouseClicked(event -> {
            Double x = event.getX()-(scale/3*16);
            Double y = event.getY()-(scale/3*16);
            LinkedList<Duck> removedDucks=new LinkedList<>();
            ammoCount.set(ammoCount.getValue()-1);
            for (Duck i:root.duckList){

               if (i.inBounds(x,y)){
                    i.Stop();
                    i.Falling();
                    i.setState("Shooted");
                    removedDucks.add(i);
                }


            }

            root.duckList.removeAll(removedDucks);
            Message roundResult=new Message(scale,15.0,Pos.CENTER);
            if ((ammoCount.getValue() >0)) {
                if (root.duckList.isEmpty()) {
                    setOnMouseClicked(Event::consume);
                    gameState=true;
                    roundResult.addText("YOU WIN!");

                }
            }else {
                gameState=true;
                setOnMouseClicked(Event::consume);
                if (root.duckList.isEmpty()) {
                    roundResult.addText("YOU WIN!");
                } else {
                    roundResult.addText("GAME OVER!");

                }
            }


        });


    }




}
