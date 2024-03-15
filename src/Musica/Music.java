
package Musica;

import java.io.File;
import java.io.Serializable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author jomel
 */
public class Music extends Thread implements Serializable{
    
    private Clip BackGroundSound;
    public File music;
    public Music(File music) {
        try {
            this.music = music;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(music);
            BackGroundSound = AudioSystem.getClip();
            BackGroundSound.open(audioInputStream);

            // Control de volumen
            FloatControl controlVolumen = (FloatControl) BackGroundSound.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolumen.setValue((float) (controlVolumen.getValue() * 0.5));
            // Agregar un LineListener para cerrar la música cuando termine
            BackGroundSound.addLineListener(event -> {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        OpenMusic();
    }
    public void OpenMusic() {
        try {
            BackGroundSound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void CloseMusic() {
        try {
            if (BackGroundSound != null && BackGroundSound.isOpen()) {
                BackGroundSound.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PauseMusic() {
        try {
            if (BackGroundSound != null && BackGroundSound.isOpen()) {
                BackGroundSound.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ContinueMusic() {
        try {
            if (BackGroundSound != null && BackGroundSound.isOpen()) {
                BackGroundSound.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
