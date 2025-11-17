import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class Renderer extends JComponent {

  ITile[][] tiles;
  IPlayer player;
  IPlayer enemy;
  IGenerator[] generators;
  ArrayList<IGenerator> toggled = new ArrayList<>();
  int genCounter = 0;

  final int scale = 100;
  final int offsetX = 5;
  final int offsetY = 5;
  private int scareChance = 1;
  private boolean scare = false;

  private boolean showInstructions;


  File img = new File("customfloor.png");
  BufferedImage in = ImageIO.read(img);

  File img2 = new File("wall.jpg");
  BufferedImage in2 = ImageIO.read(img2);

  File img3= new File("note.png");
  BufferedImage in3 = ImageIO.read(img3);

  File img4 = new File("enemySprite.png");
  BufferedImage scareImg = ImageIO.read(img4);

  File img5 = new File("jumpscare.png");
  BufferedImage jumpscareImg = ImageIO.read(img5);


  public Renderer(ITile[][] tiles, IPlayer player, IPlayer enemy, IGenerator[] generators) throws IOException {
    this.tiles = tiles;
    this.player = player;
    this.generators = generators;
    this.enemy = enemy;
  }

  public void enableScare() {
    scare = true;
  }

  public void paint(Graphics gr)
  {

    Random rand = new Random();
    int darkChance = 51;
    int fullDark = rand.nextInt(darkChance);
    Graphics2D g2 = (Graphics2D) gr;

    for(IGenerator gen : generators) {
      if (gen.isFinished() && !toggled.contains(gen)) {
        g2.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        System.out.println("gen powered!");
        genCounter = 50;
        toggled.add(gen);
      }
    }

    for(int y = 0; y < tiles.length; y++) {

      for(int x = 0; x < tiles[y].length; x++) {

        int randVal = rand.nextInt(15);
        int randVal2 = rand.nextInt(8);



        if(fullDark == 5) {
          g2.setColor(Color.BLACK);
          g2.drawRect((int) (x - player.getX() + offsetX) * scale, (int)(y - player.getY() + offsetY) * scale, scale, scale);
          g2.fillRect((int) (x - player.getX() + offsetX) * scale, (int)(y - player.getY() + offsetY) * scale, scale, scale);
        }else if(Math.abs(player.getX() - x) > 3 || Math.abs(player.getY() - y) > 3) {
          g2.setColor(new Color(randVal2, randVal2, randVal2));
          g2.drawRect((int) (x - player.getX() + offsetX) * scale, (int)(y - player.getY() + offsetY) * scale, scale, scale);
          g2.fillRect((int) (x - player.getX() + offsetX) * scale, (int) (y - player.getY() + offsetY) * scale, scale, scale);
        } else if(Math.abs(player.getX() - x) == 3 || Math.abs(player.getY() - y) == 3) {
          g2.setColor(new Color(randVal, randVal, randVal));
          g2.drawRect((int) (x - player.getX() + offsetX) * scale, (int)(y - player.getY() + offsetY) * scale, scale, scale);
          g2.fillRect((int) (x - player.getX() + offsetX) * scale, (int)(y - player.getY() + offsetY) * scale, scale, scale);
        } else if(tiles[y][x].validMove()) {

          int playerStepOffset = (1 - rand.nextInt(2));
          playerStepOffset = 0;

          g2.drawImage(in, null , (int) (x - player.getX() + offsetX) * scale + playerStepOffset,(int) (y - player.getY() + offsetY) * scale + playerStepOffset);
        } else {
          g2.drawImage(in2, null ,(int) (x - player.getX() + offsetX) * scale,(int) (y - player.getY() + offsetY) * scale);
        }

      }
    }

    if(Math.abs(player.getX() - 1) < 3 && Math.abs(player.getY() - 2) < 3) {
      g2.drawImage(in3, null,(int) (1 - player.getX() + offsetX) * scale,(int) (2 - player.getY() + offsetY) * scale);
    }


    for(IGenerator g : generators) {

      Color color;

      if(fullDark == 5 || Math.abs(player.getX() - g.getX()) >= 3 || Math.abs(player.getY() - g.getY()) >= 3) {
        color = Color.BLACK;
        g2.setColor(color);
        g2.drawRect((int) (g.getX() - player.getX() + offsetX) * scale, (int) (g.getY() - player.getY() + offsetY) * scale, scale, scale);
        g2.fillRect((int) (g.getX() - player.getX() + offsetX) * scale, (int) (g.getY() - player.getY() + offsetY) * scale, scale, scale);
      } else {
        g2.drawImage(g.getSprite(), null , (int) (g.getX() - player.getX() + offsetX) * scale,(int) (g.getY() - player.getY() + offsetY) * scale);
      }


    }
    if(fullDark == 5 || Math.abs(player.getX() - enemy.getX()) >= 3 || Math.abs(player.getY() - enemy.getY()) >= 3) {
      g2.setColor(Color.BLACK);
      g2.drawRect((int) (enemy.getX() - player.getX() + offsetX) * scale, (int) (enemy.getY() - player.getY() + offsetY) * scale, scale, scale);
      g2.fillRect((int) (enemy.getX() - player.getX() + offsetX) * scale, (int) (enemy.getY() - player.getY() + offsetY) * scale, scale, scale);
    } else {
      g2.drawImage(scareImg, null, (int) (enemy.getX() - player.getX() + offsetX) * scale,(int) (enemy.getY() - player.getY() + offsetY) * scale);
    }

    //g2.drawImage(scareImg, null, (enemy.getX() - player.getX() + offsetX) * scale, (enemy.getY() - player.getY() + offsetY) * scale);



    g2.setColor(Color.RED);
    g2.setFont(new Font("Serif", Font.PLAIN, 30));
    g2.drawString(toggled.size() +  "/" + generators.length, 1000, 1020);

    if(fullDark == 5) {
      g2.setColor(Color.BLACK);
      g2.drawRect((500 + (2 - rand.nextInt(4))), 500 + (2 - rand.nextInt(4)), scale, scale);
      g2.fillRect((500 + (2 - rand.nextInt(4))), 500 + (2 - rand.nextInt(4)), scale, scale);
    } else {
      g2.drawImage(player.getSprite(), null, 500 + (2 - rand.nextInt(4)), 500 + (2 - rand.nextInt(4)));
    }

    if(rand.nextInt(scareChance) == 2) {



      for(int i = 0; i < rand.nextInt(4); i ++) {
        int xScare = rand.nextInt(11);
        int yScare = rand.nextInt(11);

        if((xScare < 4 || xScare > 8) && (yScare < 4 || yScare > 8)) {
          g2.drawImage(scareImg, null, xScare * 100, yScare * 100);
        } else {
          g2.drawImage(scareImg, null, xScare * 100, yScare * 100);
        }
      }

    }


    if(genCounter > 0) {
      g2.setColor(Color.RED);
      g2.setFont(new Font("Serif", Font.PLAIN, 30));
      g2.drawString("A generator has been powered.", 20, 1020);
      scareChance = 3;
      genCounter--;
    } else {
      scareChance = 1;
    }

    if(scare) {
      g2.drawImage(jumpscareImg, null, 0, 0);
      g2.setColor(Color.RED);
      g2.setFont(new Font("Serif", Font.PLAIN, 30));
      g2.drawString("あなたがここに永遠に横たわって、永遠に地下に閉じ込められますように。", 20, 1020);
    }


  }
}