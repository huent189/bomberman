package bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlay {
    public static String LEVEL_1_SOUND;
    public static String BOMB_FIRE;
    public static String BOMBER_RUN_SOUND;
    public static String START_SOUND;
    public static String GAME_OVER;
    public static String LEVEL_2_SOUND;
    static {
        LEVEL_1_SOUND = SoundPlay.class.getResource("/BombermanSound/04Level1.wav").getFile();
        LEVEL_2_SOUND = SoundPlay.class.getResource("/BombermanSound/05Level2.wav").getFile();
        BOMBER_RUN_SOUND=SoundPlay.class.getResource("/BombermanSound/foot.wav").getFile();
        START_SOUND=SoundPlay.class.getResource("/BombermanSound/03 Start.wav").getFile();
        BOMB_FIRE = SoundPlay.class.getResource("/BombermanSound/bomb_bang.wav").getFile();
        GAME_OVER = SoundPlay.class.getResource("/BombermanSound/GameOver.wav").getFile();
    }
    public static void playSound(String soundPath)  {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void play(String soundName)
    {
        if(soundName.equals("LEVEL_1_SOUND"))
        {
            playSound(LEVEL_1_SOUND);
        }
        else if(soundName.equals("LEVEL_2_SOUND"))
        {
            playSound(LEVEL_2_SOUND);
        }
    }
    public static void main(String[] args) {
        playSound(LEVEL_1_SOUND);
    }
}


