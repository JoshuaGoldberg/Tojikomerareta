import java.awt.image.BufferedImage;

public interface IGenerator {

  boolean isFinished();
  double getCharge();
  void execute();
  int getX();
  int getY();
  BufferedImage getSprite();
  void ping();

}
