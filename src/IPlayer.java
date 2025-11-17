import java.awt.image.BufferedImage;

public interface IPlayer {

  void move(int x, int y);

  double getX();
  double getY();

  void setSprite(int direction);
  BufferedImage getSprite();

}
