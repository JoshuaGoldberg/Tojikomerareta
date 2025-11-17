import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IPlayer {

  IGameBoard level;
  double posX;
  double posY;
  int currSprite;

  public Player(IGameBoard level, double posX, double posY) throws IOException {
    this.level = level;
    this.posX = posX;
    this.posY = posY;
    currSprite = 1;
  }

  File img = new File("sprite_1.png");
  BufferedImage in = ImageIO.read(img);

  File img2 = new File("sprite_0.png");
  BufferedImage in2 = ImageIO.read(img2);

  File img3 = new File("sprite_3.png");
  BufferedImage in3 = ImageIO.read(img3);

  File img4 = new File("sprite_2.png");
  BufferedImage in4 = ImageIO.read(img4);

  BufferedImage[] sprites = new BufferedImage[] {in, in2, in3, in4};

  @Override
  public void move(int x, int y) {

    if(level.validPosition((int) posX + x, (int) posY + y)) {
      posX = posX + x;
      posY = posY + y;
    }

  }

  @Override
  public double getX() {
    return posX;
  }

  @Override
  public double getY() {
    return posY;
  }

  @Override
  public void setSprite(int direction) {
    currSprite = direction;
  }

  @Override
  public BufferedImage getSprite() {
    return sprites[currSprite];
  }

}
