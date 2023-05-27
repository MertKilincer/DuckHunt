import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

public class GameScene extends Scene {

    public  GamePane root;

    public double scale;

    public int ammoCount;





    public GameScene(GamePane root,double scale){
        super(root,300*scale,300*scale);
        this.root=root;
        this.scale=scale;

        this.setCursor(Cursor.NONE);
        events();
    }
    public void  updateAmmoCount(){
        ammoCount+=3;
    }



    public void events(){
        setOnMouseMoved(event -> root.cursor.updatePosition(event.getX()-(scale/3*16), event.getY()-(scale/3*16)));
        addEventHandler(MouseEvent.MOUSE_EXITED, event -> root.cursor.setVisible(false));
        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> root.cursor.setVisible(true));


        setOnMouseClicked(event -> {
            Double x = event.getX()-(scale/3*16);
            Double y = event.getY()-(scale/3*16);
            LinkedList<Duck> removedDucks=new LinkedList<>();
            ammoCount=ammoCount -1;
            for (Duck i:root.duckList){

               if (i.inBounds(x,y)){
                    i.Stop();
                    i.Falling();
                    i.setState("Shooted");
                    removedDucks.add(i);
                }


            }

            root.duckList.removeAll(removedDucks);
            System.out.println(ammoCount);
            if ((ammoCount >0)) {
                if (root.duckList.isEmpty()) {
                    System.out.println("Yes");
                }
            }else {
                if (root.duckList.isEmpty()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("GAMEoVER");

                }
            }

        });


    }



}
