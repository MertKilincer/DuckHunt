import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Player {

    public String filePath;
    public Media media;
    public MediaPlayer player;
    public Duration pauseTime;


    public Player(String filePath){
        this.filePath=filePath;
        this.media=new Media(new File(filePath).toURI().toString());
        this.player=new MediaPlayer(media);

    }

    public void InfinitePlay(){
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();
    }


    public void singlePlay(){
        player.play();
    }

    public void pause(){
        player.stop();

    }


}
