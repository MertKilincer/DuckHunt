import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sounds{


    private MediaPlayer gun;

    private MediaPlayer duckFalls;

    private MediaPlayer gameCompleted;

    private MediaPlayer gameOver;

    private MediaPlayer levelCompleted;

    private double volume;


    public Sounds(double volume){
        this.volume=volume;
        duckFalls=createPlayer("assets/effects/DuckFalls.mp3");
        gun=createPlayer("assets/effects/Gunshot.mp3");
        gameCompleted=createPlayer("assets/effects/GameCompleted.mp3");
        levelCompleted=createPlayer("assets/effects/LevelCompleted.mp3");
        gameOver=createPlayer("assets/effects/GameOver.mp3");


    }


    private MediaPlayer createPlayer(String filepath) {
        Media media=new Media(new File(filepath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        return mediaPlayer;
    }


    public void gun(){
        gun.play();
        gun.seek(Duration.ZERO);

    }

    public MediaPlayer getLevelCompleted() {
        return levelCompleted;
    }

    public MediaPlayer getGameCompleted(){
        return gameCompleted;
    }

    public void fall(){
        duckFalls.play();
        duckFalls.seek(Duration.ZERO);
    }

    public MediaPlayer getGameOver(){
        return gameOver;
    }
}

