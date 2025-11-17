import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayerController implements KeyListener {

  IPlayer player;
  GameRenderer gameRenderer;

  private boolean upPrev = false;

  public PlayerController(IPlayer player, GameRenderer gameRenderer) {
    System.out.println("pc made!");

    this.player = player;
    this.gameRenderer = gameRenderer;
    this.gameRenderer.setKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {

    if(!upPrev) {
      System.out.println("Key pressed: " + e.getKeyChar());

      String soundName = "Footsteps.wav";
      AudioInputStream audioInputStream;

      try {
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
      } catch (UnsupportedAudioFileException | IOException ex) {
        throw new RuntimeException(ex);
      }
      Clip clip;
      try {
        clip = AudioSystem.getClip();
      } catch (LineUnavailableException ex) {
        throw new RuntimeException(ex);
      }
      try {
        clip.open(audioInputStream);
      } catch (LineUnavailableException | IOException ex) {
        throw new RuntimeException(ex);
      }

      clip.start();


      switch (e.getKeyChar()) {
        case 'w', 'W':
          player.move(0, -1);
          System.out.println(player.getX());
          System.out.println(player.getY());
          player.setSprite(0);
          break;
        case 's', 'S':
          player.move(0, 1);
          System.out.println(player.getX());
          System.out.println(player.getY());
          player.setSprite(1);
          break;
        case 'a', 'A':
          player.move(-1, 0);
          System.out.println(player.getX());
          System.out.println(player.getY());
          player.setSprite(2);
          break;
        case 'd', 'D':
          player.move(1, 0);
          System.out.println(player.getX());
          System.out.println(player.getY());
          player.setSprite(3);
          break;

      }

      upPrev = true;
    }

    gameRenderer.updateVisuals();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    upPrev = false;
  }
}
