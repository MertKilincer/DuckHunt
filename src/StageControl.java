import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;

import javafx.stage.Stage;

import java.util.LinkedList;

public class StageControl {

    public StartScreen title;

    public SelectionScene selection;

    public GameElements elements;

    public Stage stage;

    public Round1 round1;

    public Round2 round2;

    public Round3 round3;

    public Round4 round4;

    public Round5 round5;

    public Round6 round6;

    public Image foreground;

    public Background background;

    public CustomCrosshair cursor;

    public double scale;



    public StageControl(Stage stage ,double scale){
        this.stage = stage;
        this.scale = scale;
        title =new StartScreen(scale,stage);
        elements=new GameElements(title,scale);
        selection =new SelectionScene(scale,elements.views,elements.Crossair);




        stage.setScene(title.titleScene);




        title.titleScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    stage.setScene(selection.selectionScene);
                    break;
                case ESCAPE:
                    javafx.application.Platform.exit();
                    break;
                default:
                    break;
            }
        });

        selection.selectionScene.setOnKeyPressed(event -> {

            switch (event.getCode()){

                case RIGHT:
                    selection.rightArrow();
                    break;
                case LEFT:
                    selection.leftArrow();
                    break;
                case UP:
                    selection.upArrow();
                    break;
                case DOWN:
                    selection.downArrow();
                    break;
                case ESCAPE:
                    selection.reset();
                    stage.setScene(title.titleScene);

                    break;
                case ENTER:

                    cursor =selection.getCurrentCross();

                    foreground =selection.backgrounds.get(selection.IndexBack).getForeground();
                    background =selection.backgrounds.get(selection.IndexBack).getBackground();
                    round6=new Round6(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this);
                    round5=new Round5(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round6);
                    round4=new Round4(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round5);
                    round3=new Round3(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round4);
                    round2 =new Round2(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round3);
                    round1 =new Round1(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round2);
                    stage.setScene(round1.scene);
                    events();






                    break;
                default:
                    break;
            }



        });








    }

    public void events(){
        round1.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round1.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    round1.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round2.scene);
                        }
                    });
                    break;
                case COMPLETED:
                    round1.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){

                        } else if (event3.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();

                        }
                    });
                    break;
            }
        });



        round2.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round2.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    round2.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round3.scene);
                        }
                    });
                    break;

            }
        });


        round3.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round3.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    round3.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round4.scene);
                        }
                    });
                    break;
            }
        });


        round4.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round4.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    round4.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round5.scene);
                        }
                    });
                    break;
            }
        });


        round5.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round5.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case NEXT_LEVEL:
                    round5.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            stage.setScene(round6.scene);
                        }
                    });
                    break;
            }
        });

        round6.gameStateProperty.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case GAME_OVER:
                    round6.scene.setOnKeyPressed(event5 -> {
                        if (event5.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();
                        }
                    });
                    break;
                case COMPLETED:
                    round6.scene.setOnKeyPressed(event3 -> {
                        if (event3.getCode().equals(KeyCode.ENTER)){
                            round6=new Round6(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this);
                            round5=new Round5(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round6);
                            round4=new Round4(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round5);
                            round3=new Round3(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round4);
                            round2 =new Round2(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round3);
                            round1 =new Round1(new CustomCrosshair(cursor.filepath,cursor.scale),foreground,background,scale,this,round2);
                            stage.setScene(round1.scene);
                            events();
                        } else if (event3.getCode().equals(KeyCode.ESCAPE)){
                            stage.setScene(title.titleScene);
                            selection.reset();

                        }
                    });
                    break;
            }
        });



    }

}


