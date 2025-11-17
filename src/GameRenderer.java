import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GameRenderer extends JFrame {

  IGameBoard gameBoard;
  IPlayer player;
  IPlayer enemy;
  JFrame frame;
  IGenerator[] generators;
  Renderer renderer;
  ArrayList<IGenerator> toggled = new ArrayList<>();
  boolean read = false;

  public GameRenderer(IGameBoard gameBoard, IPlayer player, IPlayer enemy, IGenerator[] generators) throws IOException {

    this.gameBoard = gameBoard;
    this.player = player;
    this.generators = generators;
    this.enemy = enemy;

    renderer = new Renderer(gameBoard.getLevel(), player, enemy, generators);

    frame = new JFrame("Run");

    ImageIcon img = new ImageIcon("icon.png");
    frame.setIconImage(img.getImage());
    frame.setSize(1100, 1100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(renderer);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setVisible(true);
    frame.setFocusable(true);
    frame.requestFocus();
    frame.setResizable(false);

    Thread thread2 = new Thread(new GameRendererRunner(this));
    thread2.start();

    String soundName = "noise.wav";
    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
    } catch (UnsupportedAudioFileException | IOException ex) {
      throw new RuntimeException(ex);
    }
    Clip clip = null;
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

  }

  public void updateVisuals() {
    renderer.repaint();
  }


  public void run() {


    System.out.println("Render updating!");



    while (true) {

      updateVisuals();

      if(!read && player.getY() == 2 & player.getX() == 1) {
        read = true;
        JOptionPane.showMessageDialog(this,
                "You must power the generators to escape.", "Error", JOptionPane.ERROR_MESSAGE);

      } else if(read && player.getY() != 2 || player.getX() != 1) {
        read = false;
      }

      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (Exception e){
        System.out.println("oops");
      }
    }
  }

  public void setKeyListener(KeyListener keyListener) {
    System.out.println("hi");
    frame.addKeyListener(keyListener);
  }

  public void jumpscare() {
    this.renderer.enableScare();

    String soundName2 = "instantdeath.wav";
    AudioInputStream audioInputStream2;
    try {
      audioInputStream2 = AudioSystem.getAudioInputStream(new File(soundName2).getAbsoluteFile());
    } catch (UnsupportedAudioFileException | IOException ex) {
      throw new RuntimeException(ex);
    }

    String soundName = "instantdeath.wav";
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

    //uncomment for LOUD SOUND
    // clip.start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (Exception e){
      System.out.println("oops");
    }

    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));


  }

}
