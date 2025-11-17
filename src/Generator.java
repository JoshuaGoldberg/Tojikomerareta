import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Generator implements IGenerator {

  double charge;
  boolean finished;
  IPlayer player;
  int posX = 0;
  int posY = 0;
  FloatControl volume;

  File img0 = new File("gen_00.png");
  BufferedImage in0 = ImageIO.read(img0);

  File img1 = new File("gen_01.png");
  BufferedImage in1 = ImageIO.read(img1);

  File img2 = new File("gen_02.png");
  BufferedImage in2 = ImageIO.read(img2);

  File img3 = new File("gen_03.png");
  BufferedImage in3 = ImageIO.read(img3);

  File img4 = new File("gen_04.png");
  BufferedImage in4 = ImageIO.read(img4);

  File img5 = new File("gen_05.png");
  BufferedImage in5 = ImageIO.read(img5);

  File img6 = new File("gen_06.png");
  BufferedImage in6 = ImageIO.read(img6);

  File img7 = new File("gen_07.png");
  BufferedImage in7 = ImageIO.read(img7);

  File img8 = new File("gen_08.png");
  BufferedImage in8 = ImageIO.read(img8);

  File img9 = new File("gen_09.png");
  BufferedImage in9 = ImageIO.read(img9);

  File img10 = new File("gen_10.png");
  BufferedImage in10 = ImageIO.read(img10);

  BufferedImage[] sprites = {in0, in1, in2, in3, in4, in5, in6, in7, in8, in9, in10};

  BufferedImage currSprite;

  public Generator(int posX, int posY, IPlayer player) throws IOException {
    this.charge = 0;
    this.finished = false;
    this.posX = posX;
    this.posY = posY;
    this.player = player;

    this.currSprite = sprites[0];

    Thread thread = new Thread(new GeneratorRunner(this));
    thread.start();

    Thread thread2 = new Thread(new GeneratorRunnerPing(this));
    thread2.start();
  }

  public void execute() {

    String soundName = "startup.wav";
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

    System.out.println("started!");

    while(true) {

      assert player != null;

      if(charge >= 10 && !finished) {

        clip.start();
        finished = true;

      } else if(Math.abs(player.getY() - posY) <= 1 && Math.abs(player.getX() - posX) <= 1 && !finished) {
        charge += 1;
        currSprite = sprites[(int) charge];
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (Exception e){
        System.out.println("oops");
      }

    }
  }

  @Override
  public int getX() {
    return posX;
  }

  @Override
  public int getY() {
    return posY;
  }

  @Override
  public BufferedImage getSprite() {
    return currSprite;
  }

  @Override
  public void ping() {


    while(true) {

      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (Exception e) {
//        System.out.println("oops");
      }

      String soundName = "ping.wav";
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

      float value = -(Math.abs((posX + posY) - (int) (player.getX() + player.getY())) * 3F);
      if(value <= -80) {
        value = -80;
      }

      volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      volume.setValue(value);

      if(!finished) {
        clip.start();
      }


    }
  }

  public boolean isFinished() {
    return finished;
  }

  public double getCharge() {
    return charge;
  }

}
