/**
 * Necessary imports.
 */

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

/**
 * Sounds class is holds the audio effects of the game when the users are playing.
 */
public class Sounds{

    /**
     * gun is the audio of the gun shot audio of the game.
     */
    private MediaPlayer gun;
    /**
     * duckFalls is the audio that played when ducks are dead and started to fall.
     */
    private MediaPlayer duckFalls;
    /**
     * gameCompleted is the audio that played when users complete the game successfully.
     */
    private MediaPlayer gameCompleted;

    private MediaPlayer gameOver;
    /**
     * levelCompleted is the audio that played when users complete the level successfully.
     */
    private MediaPlayer levelCompleted;
    /**
     * volume of the  audio effects
     */
    private double volume;

    /**
     * Sounds class constructor that create all of the gameTime sounds effects.
     * @param volume
     */
    public Sounds(double volume){
        this.volume=volume;
        duckFalls=createPlayer("assets/effects/DuckFalls.mp3");
        gun=createPlayer("assets/effects/Gunshot.mp3");
        gameCompleted=createPlayer("assets/effects/GameCompleted.mp3");
        levelCompleted=createPlayer("assets/effects/LevelCompleted.mp3");
        gameOver=createPlayer("assets/effects/GameOver.mp3");


    }

    /**
     * createPlayer methods create mediaPlayers of the sound class.
     * @param filepath audio effects filepaths.
     * @return MediaPlayer of the effects.
     * @see MediaPlayer
     */
    private MediaPlayer createPlayer(String filepath) {
        Media media=new Media(new File(filepath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        return mediaPlayer;
    }

    /**
     * gun method play the gunShot effect.
     */
    public void gun(){
        gun.play();
        gun.seek(Duration.ZERO);

    }

    /**
     * getter method for levelCompleted sound effect.
     * @return MediaPlayer
     * @see MediaPlayer
     */
    public MediaPlayer getLevelCompleted() {
        return levelCompleted;
    }

    /**
     *  getter method that returns the gameCompleted player.
     *@return MediaPlayer
     * @see MediaPlayer
     */
    public MediaPlayer getGameCompleted(){
        return gameCompleted;
    }

    /**
     * fall method play the duckFalls effect.
     */
    public void fall(){
        duckFalls.play();
        duckFalls.seek(Duration.ZERO);
    }

    /**
     * getter for gameOver audio effect.
     * @return MediaPlayer
     * @see MediaPlayer
     */
    public MediaPlayer getGameOver(){
        return gameOver;
    }
}

